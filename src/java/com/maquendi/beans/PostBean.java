
package com.maquendi.beans;


import com.maquendi.theBrain.dao.PostDao;
import com.maquendi.theBrain.entities.Post;
import com.maquendi.theBrain.entities.PostLike;
import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value="postbean")
@ViewScoped
public class PostBean implements Serializable{
    
    
    private Post post;
    private List<Post> myPostList;
    private List<Post> visited_post_list;
    private Profile loggedUser;
    private Profile profile;

 
    
    public List<Post> getVisited_post_list() {
        return visited_post_list;
    }

    public void setVisited_post_list(List<Post> visited_post_list) {
        this.visited_post_list = visited_post_list;
    }
    
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    private PostDao postdao;

    @PostConstruct
    public void init(){
        loggedUser = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
        postdao = new PostDao(); 
        post = new Post();
        post.setProfile(loggedUser);
    }
    
    
    
    public void loadmyPosts(){
        profile = null;
        post.setWhos_profile(loggedUser);
        loadPosts(loggedUser);
          
    }
    
    
    
    
    public void loadPosts(Profile prof){
        
       try {
            myPostList = postdao.findByProfile(prof.getProfileId()); 
            setPostState(myPostList);
            Collections.reverse(myPostList);
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error !","Unable to load user Posts." + ex.getMessage()));
        }
    }
    
    
    

    public List<Post> getMyPostList() {
        return myPostList;
    }

    public void setMyPostList(List<Post> myPostList) {
        this.myPostList = myPostList;
    }
    

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Profile getUser() {
        return loggedUser;
    }

    public void setUser(Profile user) {
        this.loggedUser = user;
    }
    
    
    public void addPost(){
        
        
        
        
        try{
            PostDao dao = new PostDao();
            dao.add(post);
          
            if(this.profile == null){
                this.loadPosts(loggedUser);
            }else{
                loadPosts(this.profile);
            }
           
        }catch(SQLException e)
        {
            System.out.println("error "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!",""));
        }
    
       }
    
    
    
    
    
       public void loadVisitPostList(Profile prof){
         
             this.profile = prof;
             post.setWhos_profile(prof);
             
             try{
                 visited_post_list = this.postdao.findByProfile(prof.getProfileId());
                 Collections.reverse(visited_post_list);
                 setPostState(visited_post_list);
             }catch(SQLException e){
                 System.out.println("error: " + e.getMessage());
             }
             
          } 
       
       
         public void setPostState(List<Post> lista){
             
             List<PostLike> likeList;
  
             for(Post p : lista){
                 
                likeList = p.getListaLikes();
                 for(PostLike like : likeList){
                    if(Objects.equals(like.getLike().getProfile().getProfileId(), loggedUser.getProfileId())){
                        
                        p.setState(1);
                        p.setStyleClass("btnClicked");
                    }     
                 }
                 
             }
             
         }
         
         
         
         
         public void deletePost(Post post){
             
            
             
             
          try{
                 PostDao dao = new PostDao();
                 dao.delete(post.getPostId());
                 
             }catch(SQLException e){
                 //some cool thing here...
             }
         }
    
}
