
package com.maquendi.beans;


import com.maquendi.theBrain.dao.PostDao;
import com.maquendi.theBrain.entities.Post;
import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value="postbean")
@ViewScoped
public class PostBean implements Serializable{
    
    private Post post;
    private Profile user;

    @PostConstruct
    public void init(){
       
        post = new Post();
        user = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
        post.setProfile(user);
        post.setWhos_profile(user);
    }
    
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Profile getUser() {
        return user;
    }

    public void setUser(Profile user) {
        this.user = user;
    }
    
    
    public String addPost(){
        String content="";
        Post newPost = null;

        try{
            PostDao dao = new PostDao();
            
            newPost = dao.add(post);
            content = newPost.getPost_content();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"POSTED!!",""));
        }catch(SQLException e)
        {
            System.out.println("error "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!",""));
        }
        
        
        
        
        return content;
    }
    
    
    
}
