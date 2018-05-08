
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
    
    
    
    
    public void registrar(){
        
        ProfileDao dao = new ProfileDao();
        FacesContext context = FacesContext.getCurrentInstance();
        
        try{
            
            
         dao.create(profile);
          
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successfull","Profile Created")); 
         
        }catch(SQLException e)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"error","An error ocurred "+e.getMessage()));
        }
    
    }
}
