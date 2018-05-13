
package com.maquendi.beans;

import com.maquendi.theBrain.dao.CommentDao;
import com.maquendi.theBrain.entities.C_Comment;
import com.maquendi.theBrain.entities.Comment;
import com.maquendi.theBrain.entities.Parent_Comment;
import com.maquendi.theBrain.entities.Post;
import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
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
    private Post post;
    private String comentario;
    private String savedComment;
    private Profile loggedUser;
    private Profile visited_profile;
    
    
    @PostConstruct
    public void init(){
        
        pComment = new Parent_Comment();
        loggedUser = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
        pComment.setProfile(loggedUser);
        cComment = new C_Comment();
    }
    
    

    public Profile getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Profile loggedUser) {
        this.loggedUser = loggedUser;
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
    



    
   
     
    public void addParentComment(PostBean postbean){
        
        pComment.setContent(comentario);
        CommentDao dao = new CommentDao();
        try{
             Comment c = dao.addCommentToPOST(pComment,post.getPostId());
             savedComment = c.getContent();
             postbean.loadPosts(post.getProfile());
        }catch(SQLException | NullPointerException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"error","SQL Exception: " + e));
        }  
        
    }
    
    public void addComment(){
        comentario = pComment.getContent();
    }
    
   public void addPost(Post p){
      this.post = p;
   }
   
   
   
   
   public void loadComments(Post p){
      
       CommentDao dao = new CommentDao();
       this.post = p;
       
       try{
           
          commentList = dao.findAllParentComments(post.getPostId());         
          post.setLista_comentarios(commentList);

       }catch(SQLException e){
           System.out.println("error: "+ e.getMessage());
       }
       
       
   }
   
}
