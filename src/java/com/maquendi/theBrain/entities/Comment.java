
package com.maquendi.theBrain.entities;

import java.util.Calendar;
import java.util.Objects;


public class Comment {
    
    private Integer commentId;
    private Profile profile;
    private String comment;
    private Calendar comment_date;
    private char comment_type;

    
    
    public Comment(){
      comment_date = Calendar.getInstance();
    }
    
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Calendar getComment_date() {
        return comment_date;
    }

    public void setComment_date(Long date) {
        this.comment_date.setTimeInMillis(date);
    }

    public char getComment_type() {
        return comment_type;
    }

    public void setComment_type(char comment_type) {
        this.comment_type = comment_type;
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
        return "Comment{" + "commentId=" + commentId + ", profile=" + profile + ", comment=" + comment + ", comment_date=" + comment_date + ", comment_type=" + comment_type + '}';
    }
    
    
    
}
