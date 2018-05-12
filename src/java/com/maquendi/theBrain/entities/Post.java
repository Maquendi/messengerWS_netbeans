
package com.maquendi.theBrain.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Date;
import java.util.List;


public class Post implements Serializable{
    
    private Integer postId;
    private Profile profile;
    private Profile whos_profile;
    private String post_content;
    private Date date_created;
    private String dateAsString;

    public String getDateAsString() {
        
        setDateAsString("");
        return dateAsString;
    }

    public void setDateAsString(String dateAsString) {
        
        SimpleDateFormat formatdate = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        this.dateAsString = formatdate.format(date_created);
    }
    
    
    
    
    
    
    private List<Parent_Comment> lista_comentarios;

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public List<Parent_Comment> getLista_comentarios() {
        return lista_comentarios;
    }

    public void setLista_comentarios(List<Parent_Comment> lista_comentarios) {
        this.lista_comentarios = lista_comentarios;
    }

   
    
    
    
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getWhos_profile() {
        return whos_profile;
    }

    public void setWhos_profile(Profile whos_profile) {
        this.whos_profile = whos_profile;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public Date getPost_date() {
        return date_created;
    }

    public void setPost_date(Date date) {
        this.date_created = date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.postId);
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
        final Post other = (Post) obj;
        if (!Objects.equals(this.postId, other.postId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", profile=" + profile + ", whos_profile=" + whos_profile + ", post_content=" + post_content + ", post_date=" + date_created + '}';
    }
    
    
    
    
}
