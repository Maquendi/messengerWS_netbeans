
package com.maquendi.beans;

import com.maquendi.theBrain.dao.ProfileDao;
import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named(value="login")
@SessionScoped
public class LoginBean implements Serializable{
    
    private String username;
    private String password;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  
    
    public String authenticate(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        ProfileDao dao = new ProfileDao();
        
        try{
            
           Profile prof = dao.authenticar(username, password);
           if(prof == null){
               context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Authentication Failed","Could not Authenticate"));
               result = "failed";
               context.getExternalContext().getFlash().setKeepMessages(true);
           }else{
               
               
               context.getExternalContext().getSessionMap().put("loggedUser", prof);
               
               context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"User Authenticated","Welcome " + prof.getFirstName()));
               result = "success";
               context.getExternalContext().getFlash().setKeepMessages(true);
               //FacesContext.getCurrentInstance().getExternalContext().redirect("userViews/home.xhtml");
           }
           
           
        }catch(SQLException e){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR !!",""+e.getMessage()));
        }
        
        
        return result;
        
    }
    
}
