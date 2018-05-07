
package com.maquendi.theBrain.entities;


import java.util.Date;
import java.util.Objects;


public class Comment implements IComment{
    
    private Integer commentId;
    private Profile profile;
    private Date comment_date;
    private String content;

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

 
    public Date getDate() {
        return comment_date;
    }

    public void setDate(Date date) {
        this.comment_date = date;
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
