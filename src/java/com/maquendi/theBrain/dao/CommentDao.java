
package com.maquendi.theBrain.dao;

import com.maquendi.theBrain.entities.Comment;
import com.maquendi.theBrain.entities.Post;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CommentDao {
    
    
    private final Conector conector;
    public CommentDao(){
        conector = new Conector();
    }
    
    
    public Comment save(Comment comment) throws SQLException{
        
        String sql = "INSERT INTO comment(profileId,comment,comment_date,comment_type)VALUES(?,?,?,?)";
        int commentId = 0;
        Connection conn = conector.connectar();
        try{
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1,comment.getProfile().getProfileId());
            pst.setString(2,comment.getComment());
            pst.setDate(3,new Date(comment.getComment_date().getTimeInMillis()));
            pst.setString(4, String.valueOf(comment.getComment_type()));
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()){
                commentId = rs.getInt(1);
            }
            conn.commit();
        }catch(SQLException e){
            conn.rollback();
            throw e;
        }finally{
            conector.desconectar();
        }
        
        
        return find(commentId);
        
    }
    
    public Comment find(Integer commentId) throws SQLException{
        String query = "SELECT * FROM comment WHERE commentId = ?";
        ProfileDao pDao = new ProfileDao();
        Comment co = null;
        try{
            
            PreparedStatement pst = conector.connectar().prepareStatement(query);
            pst.setInt(1,commentId);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                co = new Comment();
                co.setComment(rs.getString("comment"));
                co.setCommentId(rs.getInt("commentId"));
                co.setComment_type(rs.getString("comment_type").charAt(0));
                co.setComment_date(rs.getDate("comment_date").getTime());
                co.setProfile(pDao.find(rs.getInt("profileId")));
            }
        }catch(SQLException e){
            throw e;
        }finally{
            conector.desconectar();
        }
     
        return co;
    }
    
    
    
    
    public List<Comment> findAll() throws SQLException{
        
       String query = "SELECT * FROM comment"; 
       List<Comment> lista = new ArrayList<>();
       ProfileDao pDao = new ProfileDao();
       try{
           PreparedStatement pst = conector.connectar().prepareStatement(query);
           ResultSet rs = pst.executeQuery();
           while(rs.next())
           {
               Comment co = new Comment();
               co.setComment(rs.getString("comment"));
               co.setCommentId(rs.getInt("commentId"));
               co.setComment_date(rs.getDate("comment_date").getTime());
               co.setComment_type(rs.getString("comment_type").charAt(0));
               co.setProfile(pDao.find(rs.getInt("profileId")));
               lista.add(co);
           }

       }catch(SQLException e)
       {
           throw e;
       }
      return lista;
    }
    
    
    public List<Comment> findAllByPost(Post post) throws SQLException{
        
        String query = "SELECT * FROM comment co INNER JOIN post_comment pc on co.commentId = pc.commentId WHERE CO.comment_type = ? AND PC.postId = ?";
        ProfileDao pDao = new ProfileDao();
        List<Comment> lista = new ArrayList<>();
        
        try{
            PreparedStatement pst = conector.connectar().prepareStatement(query);
            pst.setString(1,"P");
            pst.setInt(2,post.getPostId());
            
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                Comment co = new Comment();
                co.setComment(rs.getString("comment"));
                co.setCommentId(rs.getInt("commentId"));
                co.setComment_date(rs.getDate("comment_date").getTime());
                co.setComment_type(rs.getString("comment_type").charAt(0));
                co.setProfile(pDao.find(rs.getInt("profileId")));
                lista.add(co);
            }

        }catch(SQLException e){
            throw e;
        }finally{
            conector.desconectar();
        }
        
        return lista;
        
        
    }
    
    
     public List<Comment> findAllByComment(Comment comment) throws SQLException{
        
        String query = "SELECT * FROM comment co INNER JOIN comment_comment cc on co.commentId = cc.parent_comment_id WHERE CO.comment_type = ? AND cc.child_comment_id = ?";
        ProfileDao pDao = new ProfileDao();
        List<Comment> lista = new ArrayList<>();
        
        try{
            PreparedStatement pst = conector.connectar().prepareStatement(query);
            pst.setString(1,"C");
            pst.setInt(2,comment.getCommentId());
            
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                Comment co = new Comment();
                co.setComment(rs.getString("comment"));
                co.setCommentId(rs.getInt("commentId"));
                co.setComment_date(rs.getDate("comment_date").getTime());
                co.setComment_type(rs.getString("comment_type").charAt(0));
                co.setProfile(pDao.find(rs.getInt("profileId")));
                lista.add(co);
            }

        }catch(SQLException e){
            throw e;
        }finally{
            conector.desconectar();
        }
        
        return lista;
        
    }
    
     
     public Comment update(Comment comment) throws SQLException{
         
        String query = "UPDATE comment SET comment = ? WHERE commentId = ?";
          
        try{
            
            PreparedStatement pst = conector.connectar().prepareStatement(query);
            pst.setString(1,comment.getComment());
            pst.setInt(2,comment.getCommentId());
            pst.executeUpdate();

          }catch(SQLException e){
            throw e;
         }finally{
            conector.desconectar();
        }
         
        return find(comment.getCommentId());   
     }
    
    
     
     
     public Comment delete(Integer commentId) throws SQLException{
         
         String query = "DELETE FROM comment WHERE commentId = ?";
         Comment co = null;
          
         try{
            co = this.find(commentId);
            PreparedStatement pst = conector.connectar().prepareStatement(query);
            pst.setInt(1,commentId);
            pst.executeUpdate();

          }catch(SQLException e){
            throw e;
         }finally{
            conector.desconectar();
        }
         
       return co;
     }
     
     
    
}
