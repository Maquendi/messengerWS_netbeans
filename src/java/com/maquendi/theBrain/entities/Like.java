
package com.maquendi.theBrain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Like implements Serializable{
    
    private Integer likeId;
    private Profile profile;
    private Date like_date;
    private char tipo_like;
  
    
    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Date getLike_date() {
        return like_date;
    }

    public void setLike_date(Date like_date) {
        this.like_date = like_date;
    }

    public char getTipo_like() {
        return tipo_like;
    }

    public void setTipo_like(char tipo_like) {
        this.tipo_like = tipo_like;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.likeId);
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
        final Like other = (Like) obj;
        if (!Objects.equals(this.likeId, other.likeId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Like{" + "likeId=" + likeId + ", profile=" + profile + ", like_date=" + like_date + ", tipo_like=" + tipo_like + '}';
    }
    
    
    
    
}
