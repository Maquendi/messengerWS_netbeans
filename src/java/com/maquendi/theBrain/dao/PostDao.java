
package com.maquendi.theBrain.dao;


import com.maquendi.theBrain.entities.Post;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PostDao {
 
    private final Conector conn;
    
    public PostDao(){
        conn =  new Conector();
    }
    
    
     public List<Post> findAll() throws SQLException{
        
       String query = "SELECT * FROM post"; 
       List<Post> lista = new ArrayList<>();
       ProfileDao pDao = new ProfileDao();
       try{
           PreparedStatement pst = conn.connectar().prepareStatement(query);
           ResultSet rs = pst.executeQuery();
           while(rs.next())
           {
               Post po = new Post();
               po.setPostId(rs.getInt("postId"));
               po.setPost_content(rs.getString("post_content"));
               po.setProfile(pDao.find(rs.getInt("profileId")));
               po.setWhos_profile(pDao.find(rs.getInt("whos_profile")));
               po.setPost_date(rs.getDate("post_date").getTime());
               lista.add(po);
           }

       }catch(SQLException e)
       {
           throw e;
       }finally{
           conn.desconectar();
       }
       return lista;
    
     }
    
     
     
     
     public List<Post> findByProfile(Integer profileId) throws SQLException{
         
         List<Post> lista = new ArrayList<>();
         String query = "SELECT * FROM post WHERE profileId = ?";
         
         try{
             
             PreparedStatement pst = conn.connectar().prepareStatement(query);
             pst.setInt(1,profileId);
             ProfileDao pDao = new ProfileDao();
             ResultSet rs = pst.executeQuery();
             
             while(rs.next())
             {
               Post po = new Post();
               po.setPostId(rs.getInt("postId"));
               po.setPost_content(rs.getString("post_content"));
               po.setProfile(pDao.find(rs.getInt("profileId")));
               po.setWhos_profile(pDao.find(rs.getInt("whos_profile")));
               po.setPost_date(rs.getDate("post_date").getTime());
               lista.add(po);
             }
             
             
         }catch(SQLException e)
         {
             throw e;
         }finally{
             conn.desconectar();
         }
         
      return lista; 
     }
     
     
     
     
     
     
     public Post add(Post post) throws SQLException{
         
         String sql = "INSERT INTO post(profileId,whos_profile,post_content,post_date)VALUES(?,?,?,?)";
        int commentId = 0;
        Connection conector = conn.connectar();
        try{
            conector.setAutoCommit(false);
            PreparedStatement pst = conector.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1,post.getProfile().getProfileId());
            pst.setInt(2,post.getWhos_profile().getProfileId());
            pst.setString(3,post.getPost_content());
            pst.setDate(4,new Date(post.getPost_date().getTimeInMillis()));
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()){
                commentId = rs.getInt(1);
            }
            conector.commit();
        }catch(SQLException e){
            conector.rollback();
            throw e;
        }finally{
            conn.desconectar();
        }
           
        return find(commentId);
         
     }
     
     
     
     public Post find(Integer postId) throws SQLException{
         
        String query = "SELECT * FROM post WHERE postId = ?";
        ProfileDao pDao = new ProfileDao();
        Post post = null;
        try{
            
            PreparedStatement pst = conn.connectar().prepareStatement(query);
            pst.setInt(1,postId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                post = new Post();
                post.setPostId(rs.getInt("postId"));
                post.setPost_content(rs.getString("post_content"));
                post.setProfile(pDao.find(rs.getInt("profileId")));
                post.setWhos_profile(pDao.find(rs.getInt("whos_profile")));
                post.setPost_date(rs.getDate("post_date").getTime());
               
            }
        }catch(SQLException e){
            throw e;
        }finally{
            conn.desconectar();
        }
     
         return post;
     }
     
     
     
     
     
    
}
