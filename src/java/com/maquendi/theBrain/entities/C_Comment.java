
package com.maquendi.theBrain.entities;

import java.io.Serializable;




public class C_Comment  extends Comment implements Serializable{
    

    private Integer parent;

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parentId) {
        this.parent = parentId;
    }


}
