
package com.maquendi.theBrain.dao;

import com.maquendi.theBrain.entities.Like;
import com.maquendi.theBrain.entities.PostLike;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class LikeDao {
    
    
    private Conector conn;
    
    
    public LikeDao(){
        conn = new Conector();
    }

     public List<Like> findAll() throws SQLException{
        
       String query = "SELECT * FROM likes"; 
       List<Like> lista = new ArrayList<>();
       ProfileDao pDao = new ProfileDao();
       try{
           PreparedStatement pst = conn.connectar().prepareStatement(query);
           ResultSet rs = pst.executeQuery();
           while(rs.next())
           {
               Like like = new Like();
               like.setLikeId(rs.getInt("likeId"));
               like.setLike_date(new java.util.Date(rs.getDate("like_date").getTime()));
               like.setProfile(pDao.find(rs.getInt("profileId")));
               like.setTipo_like(rs.getString("tipo_like").charAt(0));
               lista.add(like);
           }

       }catch(SQLException e)
       {
           throw e;
       }finally{
           conn.desconectar();
       }
       return lista;
    
     }
    
    
     public List<PostLike> getAllByPost(Integer postId) throws SQLException{
         
       String query = "SELECT * FROM likes li INNER JOIN post_likes pl on pl.likeId = li.likeId WHERE li.tipo_like = ? AND pl.postId = ?"; 
       List<PostLike> lista = new ArrayList<>();
       ProfileDao pDao = new ProfileDao();
       try{
           PreparedStatement pst = conn.connectar().prepareStatement(query);
           pst.setString(1,"P");
           pst.setInt(2,postId);

           ResultSet rs = pst.executeQuery();
           while(rs.next())
           {
               PostLike like = new PostLike();
               like.setLike(new Like());
               like.getLike().setLikeId(rs.getInt("likeId"));
               like.getLike().setLike_date(rs.getTimestamp("like_date"));
               like.getLike().setProfile(pDao.find(rs.getInt("profileId")));
               like.getLike().setTipo_like('P');
               like.setPostId(rs.getInt("postId"));
               lista.add(like);
           }

       }catch(SQLException e){
           throw e;
       }finally{
           conn.desconectar();
       }
         return lista;
     }
     
     
    public List<Like> findAllByComment(Integer commentId) throws SQLException{
        
        
      String query = "SELECT * FROM likes li INNER JOIN comment_likes cl on cl.likeId = li.likeId WHERE li.tipo_like = ? AND cl.commentId = ?"; 
       List<Like> lista = new ArrayList<>();
       ProfileDao pDao = new ProfileDao();
       try{
           PreparedStatement pst = conn.connectar().prepareStatement(query);
           pst.setString(1,"C");
           pst.setInt(2,commentId);

           ResultSet rs = pst.executeQuery();
           while(rs.next())
           {
               Like like = new Like();
               like.setLikeId(rs.getInt("likeId"));
               like.setLike_date(new java.util.Date(rs.getDate("like_date").getTime()));
               like.setProfile(pDao.find(rs.getInt("profileId")));
               like.setTipo_like(rs.getString("tipo_like").charAt(0));
               lista.add(like);
           }

       }catch(SQLException e){
           throw e;
       }finally{
           conn.desconectar();
       }
         return lista;
     }
        
        
    public void removePostLike(PostLike pLike) throws SQLException{
        
        String query = "DELETE li, pl FROM likes li JOIN post_likes pl ON pl.likeId = li.likeId WHERE li.profileId = ? AND pl.postId = ?";
        
       
        
        try{
            PreparedStatement pst = conn.connectar().prepareStatement(query);
             pst.setInt(1,pLike.getLike().getProfile().getProfileId());
             pst.setInt(2,pLike.getPostId());
            
            pst.executeUpdate();    
            
            
        }catch(SQLException e){
            throw e;
        }finally{
            conn.desconectar();
        }
        
        
    }
    
    
    public void registerPlike(PostLike pLike) throws SQLException{
        
        String query = "INSERT INTO likes(profileId,tipo_like)VALUES(?,?)";
        Connection conector = (Connection)conn.connectar();

        
        
        try{
            
             conector.setAutoCommit(false);
             PreparedStatement pst = conector.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
             pst.setInt(1,pLike.getLike().getProfile().getProfileId());
             pst.setString(2,String.valueOf(pLike.getLike().getTipo_like()));
             pst.executeUpdate();
             
             ResultSet rs = pst.getGeneratedKeys();
             
             if(rs.next()){
                 pLike.getLike().setLikeId(rs.getInt(1));
                 link_post_like(pLike);
             }
             conector.commit();
             
            
        }catch(SQLException e){
            conector.rollback();
            throw e;
        }finally{
            conn.desconectar();
        }
        
        
        
        
    }
    
    
    
    private void link_post_like(PostLike pLike) throws SQLException{
       
        String query2 = "INSERT INTO post_likes(likeId,postId)VALUES(?,?)";
        
         try{
              PreparedStatement pst2 = conn.connectar().prepareStatement(query2);
              pst2.setInt(1,pLike.getLike().getLikeId());
              pst2.setInt(2,pLike.getPostId());
              pst2.executeUpdate();
             
             
         }catch(SQLException e){
             throw e;
         }
 
    }
    
    
}
