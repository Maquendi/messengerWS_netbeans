
package com.maquendi.beans;

import com.maquendi.theBrain.entities.Profile;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value="validateBean")
@ViewScoped
public class ValidateBean implements Serializable{
    
    public void verify(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        Profile user = (Profile)context.getExternalContext().getSessionMap().get("loggedUser");
        
        if(user == null){
            try {
                
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Not Allowed","Please Sign In."));
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.getExternalContext().redirect("./../index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ValidateBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    public String logout(){
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        return "logout";
    }
    
    
    
    
}
