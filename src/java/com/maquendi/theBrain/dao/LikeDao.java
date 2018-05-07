
package com.maquendi.theBrain.dao;

import com.maquendi.theBrain.entities.Like;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LikeDao {
    
    
    private Conector conn = new Conector();
    

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
    
    
     public List<Like> getAllByPost(Integer postId) throws SQLException{
         
       String query = "SELECT * FROM likes li INNER JOIN post_likes pl on pl.likeId = li.likeId WHERE li.tipo_like = ? AND pl.postId = ?"; 
       List<Like> lista = new ArrayList<>();
       ProfileDao pDao = new ProfileDao();
       try{
           PreparedStatement pst = conn.connectar().prepareStatement(query);
           pst.setString(1,"P");
           pst.setInt(2,postId);

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
        
        
    public Like delete(Integer likeId){
        
        String query = "DELETE FROM likes WHERE likeId = 1";
        
        
        return null;
    }
    
}
