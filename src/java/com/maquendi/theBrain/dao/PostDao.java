
package com.maquendi.theBrain.dao;


import com.maquendi.theBrain.entities.Post;
import java.sql.Connection;
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
               po.setPost_date(new java.util.Date(rs.getDate("date_created").getTime()));
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
               po.setPost_date(new java.util.Date(rs.getTimestamp("date_created").getTime()));
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
         
         String sql = "INSERT INTO post(profileId,whos_profile,post_content)VALUES(?,?,?)";
        int postId = 0;
        Connection conector = conn.connectar();
        try{
            conector.setAutoCommit(false);
            PreparedStatement pst = conector.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1,post.getProfile().getProfileId());
            pst.setInt(2,post.getWhos_profile().getProfileId());
            pst.setString(3,post.getPost_content());
            //pst.setDate(4,new java.sql.Date(post.getPost_date().getTime()));
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()){
                postId = rs.getInt(1);
            }
            conector.commit();
        }catch(SQLException e){
            conector.rollback();
            throw e;
        }finally{
            conn.desconectar();
        }
           
        return find(postId);
         
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
                post.setPost_date(new java.util.Date(rs.getDate("date_created").getTime()));
               
            }
        }catch(SQLException e){
            throw e;
        }finally{
            conn.desconectar();
        }
     
         return post;
     }
     
     
      
     
     public Post update(Post post) throws SQLException
     {
         
         String query = "UPDATE post SET post_content = ? WHERE postId = ?";
         
         try{
             PreparedStatement pst = conn.connectar().prepareStatement(query);
             pst.setString(1,post.getPost_content());
             pst.setInt(2,post.getPostId());
             pst.executeUpdate();
             
         }catch(SQLException e)
         {
             throw e;
         }
         
         return find(post.getPostId());
     }
     
     
    
     
     public Post delete(Integer postId) throws SQLException {
         
         String query = "DELETE FROM post WHERE postId = ?";
         Post post = null;
         
         try{
             
             post = find(postId);
             PreparedStatement pst = conn.connectar().prepareStatement(query);
             pst.setInt(1,postId);
             pst.executeUpdate();

         }catch(SQLException e){
             throw e;
         }
         
         
         return post;
     }
     
}
