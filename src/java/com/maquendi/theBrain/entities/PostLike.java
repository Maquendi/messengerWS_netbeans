
package com.maquendi.theBrain.entities;

import java.io.Serializable;
import java.util.Objects;


public class PostLike implements Serializable {
    
    private Post post;
    private Like like;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.post);
        hash = 97 * hash + Objects.hashCode(this.like);
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
        final PostLike other = (PostLike) obj;
        if (!Objects.equals(this.post, other.post)) {
            return false;
        }
        if (!Objects.equals(this.like, other.like)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PostLike{" + "post=" + post + ", like=" + like + '}';
    }
    
    
}
