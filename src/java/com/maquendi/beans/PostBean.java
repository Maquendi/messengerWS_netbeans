
package com.maquendi.beans;


import com.maquendi.theBrain.dao.PostDao;
import com.maquendi.theBrain.entities.Post;
import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
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
    private Profile loggedUser;
    
    
    private PostDao postdao;

    @PostConstruct
    public void init(){
        
        
        postdao = new PostDao();
       
        post = new Post();
        loggedUser = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
        loadPosts();
        post.setProfile(loggedUser);
        post.setWhos_profile(loggedUser);
    }
    
    
    public void loadPosts(){
        
        try {
            myPostList = postdao.findByProfile(loggedUser.getProfileId()); 
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
    
    
    public String addPost(){
        String content="";
        

        try{
            PostDao dao = new PostDao();
            Post newPost = dao.add(post);
            this.loadPosts();
            content = newPost.getPost_content();
        }catch(SQLException e)
        {
            System.out.println("error "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!",""));
        }
        
        
        
        
        return content;
    }
    
    
    
}
