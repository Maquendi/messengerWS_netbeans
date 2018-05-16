
package com.maquendi.beans;

import com.maquendi.theBrain.dao.CommentDao;
import com.maquendi.theBrain.entities.Comment;
import com.maquendi.theBrain.entities.Parent_Comment;
import com.maquendi.theBrain.entities.Post;
import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;



@Named(value="commentBean")
@ViewScoped
public class CommentBean implements Serializable{
    
    private Profile loggedUser;
    private Profile visited_profile;
    private Comment comment;
    private String contenido;
    private String texto;
    @ManagedProperty(value="#{PostBean}")
    private PostBean postbean;

    @PostConstruct
    public void init(){
        
        loggedUser = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public PostBean getPostbean() {
        return postbean;
    }

    public void setPostbean(PostBean postbean) {
        this.postbean = postbean;
    }

    
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    
    
    
    public Profile getVisited_profile() {
        return visited_profile;
    }

    public void setVisited_profile(Profile visited_profile) {
        this.visited_profile = visited_profile;
    }
    
    private List<Parent_Comment> commentList;

    public List<Parent_Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Parent_Comment> commentList) {
        this.commentList = commentList;
    }
    
    public void setText(){
        texto = contenido;
    }

     
    public void addParentComment(Post post){
        
        comment = new Parent_Comment();
        comment.setProfile(loggedUser);
        comment.setContent(texto);
      
        System.out.println(comment);
        CommentDao dao = new CommentDao();
        try{
             dao.addCommentToPOST((Parent_Comment)comment,post.getPostId());
             postbean.loadPosts(post.getProfile());
           }catch(SQLException | NullPointerException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"error","SQL Exception: " + e));
        }  
     }

  
    
   
}
