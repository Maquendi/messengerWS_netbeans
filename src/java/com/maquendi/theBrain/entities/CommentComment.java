
package com.maquendi.theBrain.entities;

import java.util.Objects;


public class CommentComment {
    
    private Comment parent_comment;
    private Comment child_comment;

    public Comment getParent_comment() {
        return parent_comment;
    }

    public void setParent_comment(Comment parent_comment) {
        this.parent_comment = parent_comment;
    }

    public Comment getChild_comment() {
        return child_comment;
    }

    public void setChild_comment(Comment child_comment) {
        this.child_comment = child_comment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.parent_comment);
        hash = 37 * hash + Objects.hashCode(this.child_comment);
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
        final CommentComment other = (CommentComment) obj;
        if (!Objects.equals(this.parent_comment, other.parent_comment)) {
            return false;
        }
        if (!Objects.equals(this.child_comment, other.child_comment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommentComment{" + "parent_comment=" + parent_comment + ", child_comment=" + child_comment + '}';
    }
    
    
}
