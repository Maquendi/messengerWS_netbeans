
package com.maquendi.beans;

import com.maquendi.theBrain.dao.ProfileDao;
import com.maquendi.theBrain.entities.Profile;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value="registerBean")
@ViewScoped
public class RegisterBean implements Serializable{
    
    
    Profile profile = new Profile();

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    
    
    
    public String registrar(){
        
        ProfileDao dao = new ProfileDao();
        FacesContext context = FacesContext.getCurrentInstance();
        String result ="";
        
        try{
            
         
         dao.create(profile);
          
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successfull","Profile Created, Sign In.")); 
         result = "index";
        }catch(SQLException e)
        {
            result = "register";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"error","An error ocurred "+e.getMessage()));
        }
    
        return result;
    }
}
