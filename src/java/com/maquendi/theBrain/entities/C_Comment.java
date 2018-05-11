
package com.maquendi.theBrain.entities;

import java.io.Serializable;




public class C_Comment  extends Comment implements Serializable{
    

    private Integer parent;
    
    public C_Comment(){
        super();
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parentId) {
        this.parent = parentId;
    }

   
}
