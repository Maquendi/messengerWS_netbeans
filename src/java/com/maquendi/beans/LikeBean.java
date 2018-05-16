
package com.maquendi.beans;


import com.maquendi.theBrain.dao.LikeDao;
import com.maquendi.theBrain.entities.Like;
import com.maquendi.theBrain.entities.Post;
import com.maquendi.theBrain.entities.PostLike;
import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;



@Named(value="likebean")
@ViewScoped
public class LikeBean implements Serializable{
    
    private PostLike pLike;
    private LikeDao dao;
    private Profile loggedUser;
    
    @PostConstruct
    public void init(){
        dao = new LikeDao();
        pLike = new PostLike();
        loggedUser = (Profile)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
        pLike.setLike(new Like());
        pLike.getLike().setProfile(loggedUser);
        pLike.getLike().setTipo_like('P');
    }

    public PostLike getpLike() {
        return pLike;
    }

    public void setpLike(PostLike pLike) {
        this.pLike = pLike;
    }


    
    public void registerPlike(Post post){
        
        try{
           
             pLike.setPostId(post.getPostId());
             
             if(post.getState() == 1){
                  dao.removePostLike(pLike);       
             }else{
                 dao.registerPlike(pLike);
             }
    
        }catch(SQLException e){
          System.out.println(""+e.getMessage());     
        }
  
    }


    public Integer getLikeId(Post post){
      
       List<PostLike> lista = post.getListaLikes();
        
         for(PostLike pl : lista){
             if(Objects.equals(pl.getLike().getProfile().getProfileId(), loggedUser.getProfileId())){
               return pl.getLike().getLikeId();
             }
         }
        return 0;
    }    

}
    
    

    

