
package com.maquendi.theBrain.dao;

import com.maquendi.theBrain.entities.C_Comment;
import com.maquendi.theBrain.entities.Comment;
import com.maquendi.theBrain.entities.Post;
import com.maquendi.theBrain.entities.Parent_Comment;
import java.sql.Connection;
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
    
    
    public Comment addCommentToPOST(Parent_Comment comment) throws SQLException{
        
        String sql = "INSERT INTO comment(profileId,comment,comment_date,comment_type)VALUES(?,?,?,?)";
       
        Connection conn = conector.connectar();
        try{
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1,comment.getProfile().getProfileId());
            pst.setString(2,comment.getContent());
            pst.setDate(3,new java.sql.Date(comment.getDate().getTime()));
            pst.setString(4,"P");
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()){
                int commentID =rs.getInt(1); 
                comment.setID(commentID);
                link_comment_Topost(commentID, comment.getPost().getPostId());
            }
            conn.commit();
        }catch(SQLException e){
            conn.rollback();
            throw e;
        }finally{
            conector.desconectar();
        }
        
        return comment;   
    }
    
    
    
    private void link_comment_Topost(int commentId,int postId) throws SQLException{
        String sql = "INSERT INTO post_comment (commentId,postId) VALUES(?,?)";
        
        try{
            PreparedStatement pst = conector.connectar().prepareStatement(sql);
            pst.setInt(1,commentId);
            pst.setInt(2, postId);
            pst.executeUpdate();
            
        }catch(SQLException e){
            throw e;
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Parent_Comment findParent(Integer childID) throws SQLException{
        String query = "SELECT * FROM comment WHERE commentId = ?";
        ProfileDao pDao = new ProfileDao();
        PostDao postdao = new PostDao();
        Parent_Comment parent = new Parent_Comment();
        
        
        try{
            
            PreparedStatement pst = conector.connectar().prepareStatement(query);
            pst.setInt(1,childID);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                
                parent.setContent(rs.getString("comment"));
                parent.setID(rs.getInt("commentId"));
                parent.setDate(new java.util.Date(rs.getDate("comment_date").getTime()));
                parent.setProfile(pDao.find(rs.getInt("profileId")));
                parent.setPost(postdao.find(parent.getID())); //el post puede ser nulo....
                parent.setProfile(pDao.find(rs.getInt("profileId")));
            }
        }catch(SQLException e){
            throw e;
        }finally{
            conector.desconectar();
        }
     
        return parent;
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
               co.setContent(rs.getString("comment"));
               co.setID(rs.getInt("commentId"));
               co.setDate(new java.util.Date(rs.getDate("comment_date").getTime()));
               co.setProfile(pDao.find(rs.getInt("profileId")));
               lista.add(co);
           }

       }catch(SQLException e)
       {
           throw e;
       }
      return lista;
    }
    
    
    public List<Parent_Comment> findAllParentComments(Post post) throws SQLException{
        
        String query = "SELECT * FROM comment co INNER JOIN post_comment pc on co.commentId = pc.commentId WHERE CO.comment_type = ? AND PC.postId = ?";
        ProfileDao pDao = new ProfileDao();
        PostDao postDao = new PostDao();
        List<Parent_Comment> lista = new ArrayList<>();
        
        try{
            PreparedStatement pst = conector.connectar().prepareStatement(query);
            pst.setString(1,"P");
            pst.setInt(2,post.getPostId());
            
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                Parent_Comment parent = new Parent_Comment();
                parent.setContent(rs.getString("comment"));
                parent.setID(rs.getInt("commentId"));
                parent.setDate(new java.util.Date(rs.getDate("comment_date").getTime()));
                parent.setProfile(pDao.find(rs.getInt("profileId")));
                parent.setChildrenList(findChildren(parent.getID()));
                parent.setPost(post);
                lista.add(parent);
            }

        }catch(SQLException e){
            throw e;
        }finally{
            conector.desconectar();
        }
        
        return lista;
        
        
    }
    
    
    
    
  
    
    
    
    
    
    
    
     public List<C_Comment> findChildren(Integer parentId) throws SQLException{
        
        String query = "SELECT * FROM comment co INNER JOIN comment_comment cc on co.commentId = cc.child_comment_id WHERE CO.comment_type = ? AND cc.parent_comment_id = ?";
        ProfileDao pDao = new ProfileDao();
        List<C_Comment> childList = new ArrayList<>();
        
        
        try{
            PreparedStatement pst = conector.connectar().prepareStatement(query);
            pst.setString(1,"C");
            pst.setInt(2,parentId);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
              C_Comment child = new C_Comment();
               child.setID(rs.getInt("commentId"));
               child.setContent(rs.getString("comment"));
               child.setDate(new java.util.Date(rs.getDate("date_created").getTime()));
               child.setParent(parentId);
               childList.add(child);
            }

        }catch(SQLException e){
            throw e;
        }finally{
            conector.desconectar();
        }
        
        return childList; 
    }
    
    
     public Comment update(Comment comment) throws SQLException{
         
        String query = "UPDATE comment SET comment = ? WHERE commentId = ?";
          
        try{
            
            PreparedStatement pst = conector.connectar().prepareStatement(query);
            pst.setString(1,comment.getContent());
            pst.setInt(2,comment.getID());
            pst.executeUpdate();

          }catch(SQLException e){
            throw e;
         }finally{
            conector.desconectar();
        }
        
        return findParent(comment.getID());   
     }
    
    
     
     
     public Comment delete(Parent_Comment comment) throws SQLException{
         
         List<C_Comment> lista = this.findChildren(comment.getID());
        
         try{
             
             if(lista.isEmpty()){
                 this.delete(comment.getID());
                 return comment;
             }
             
          while(lista.isEmpty() == false){
                   
            for(C_Comment c:lista){
               
                List<C_Comment> nuevo = findChildren(c.getID());
                if(nuevo.isEmpty()){
                    delete(c.getID());
                    lista.remove(c);
                }else{
                    nuevo.forEach((comm) -> {
                        lista.add(comm);
                        delete(c.getID());
                        lista.remove(c);
                   });
                }
             }     
         }
         delete(comment.getID());
             
          }catch(SQLException e){
            throw e;
         }finally{
            conector.desconectar();
        }
         
       return comment;
     }
     
     
     
     
     
     
     public void delete(Integer id){
          String query = "DELETE FROM comment WHERE commentId = ?";
         
          try{
            PreparedStatement pst = conector.connectar().prepareStatement(query);
            pst.setInt(1,id);
            pst.executeUpdate();

          }catch(SQLException e){
              System.out.println(e.getMessage());
         }

     }
     
     
    
}
