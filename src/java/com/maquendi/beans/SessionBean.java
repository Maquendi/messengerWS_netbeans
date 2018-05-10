
package com.maquendi.beans;

import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named(value="userSession")
@SessionScoped
public class SessionBean implements Serializable{
    
    
    private Profile user;
    
    
    
    @PostConstruct
    public void init(){
        user = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");   
    }
    
    

    public Profile getUser() {
        return user;
    }

    public void setUser(Profile user) {
        this.user = user;
    }
    
}
