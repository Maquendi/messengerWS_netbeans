
package com.maquendi.beans;

import com.maquendi.theBrain.dao.CommentDao;
import com.maquendi.theBrain.entities.C_Comment;
import com.maquendi.theBrain.entities.Comment;
import com.maquendi.theBrain.entities.Parent_Comment;
import com.maquendi.theBrain.entities.Post;
import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;



@Named(value="commentBean")
@ViewScoped
public class CommentBean implements Serializable{
    
    private Parent_Comment pComment;
    private C_Comment cComment;
    private Comment comment;
    private String comentario;
    private String savedComment;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getSavedComment() {
        return savedComment;
    }

    public void setSavedComment(String savedComment) {
        this.savedComment = savedComment;
    }
    
    
    
    

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

  

    public Parent_Comment getpComment() {
        return pComment;
    }

    public void setpComment(Parent_Comment pComment) {
        this.pComment = pComment;
    }

    public C_Comment getcComment() {
        return cComment;
    }

    public void setcComment(C_Comment cComment) {
        this.cComment = cComment;
    }
    


    @PostConstruct
    public void init(){
        
        pComment = new Parent_Comment();
        
        Profile prof = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
        pComment.setProfile(prof);
        cComment = new C_Comment();
    }

    
   
    
    public void addParentComment(){
        
        pComment.setContent(comentario);
        CommentDao dao = new CommentDao();
      
        
        try{
           Comment c = dao.addCommentToPOST(pComment);
           
           savedComment = c.getContent();
            
        }catch(SQLException | NullPointerException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"error","SQL Exception: " + e));
        }     
    }
    
   
    public void addComment(){
        comentario = pComment.getContent();
    }
    
    
   public void addPost(Post posts){
      this.pComment.setPost(posts);
   }
    
}
