
package com.maquendi.theBrain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Parent_Comment extends Comment implements Serializable{
    

    
    List<C_Comment> childrenList = new ArrayList<>();
    
    
    public Parent_Comment(){
        super();
    }
    

    public List<C_Comment> getChildrenList() {
        return childrenList;
        
    }

    public void setChildrenList(List<C_Comment> childrenList) {
        this.childrenList = childrenList;
    }
    
   
    
}
