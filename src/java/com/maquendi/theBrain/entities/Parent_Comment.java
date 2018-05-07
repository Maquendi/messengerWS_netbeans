
package com.maquendi.theBrain.entities;

import java.util.ArrayList;
import java.util.List;


public class Parent_Comment extends Comment{
    
    private Post post;
    List<Integer> childList = new ArrayList<>();
    
    List<C_Comment> childrenList = new ArrayList<>();

    public List<C_Comment> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<C_Comment> childrenList) {
        this.childrenList = childrenList;
    }
    
    
    
 
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Integer> getChildList() {
        return childList;
    }

    public void setChildList(List<Integer> childList) {
        this.childList = childList;
    }
    
}
