
package com.maquendi.theBrain.entities;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class Comment{
    
    protected Integer commentId;
    protected Profile profile;
    protected Date comment_date;
    protected String content;
    private String dateAsString;
    
    

    public String getDateAsString() {
        
        setDateAsString("");
        return dateAsString;
    }

    public void setDateAsString(String dateAsString) {
        
        SimpleDateFormat formatdate = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        this.dateAsString = formatdate.format(comment_date);
    }
    

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
  

    public Integer getID(){
        return commentId;
    }

    public void setID(Integer commentId) {
        this.commentId = commentId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

 
   
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.commentId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.commentId, other.commentId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", profile=" + profile + ", comment_date=" + comment_date + ", comment_type=" + '}';
    }
    
    
    
}
