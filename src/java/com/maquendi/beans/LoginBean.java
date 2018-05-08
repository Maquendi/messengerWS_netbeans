/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

  
    
    public void authenticate(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        ProfileDao dao = new ProfileDao();
        try{
            
           Profile prof = dao.authenticar(username, password);
           if(prof == null){
               context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Authentication Failed","Could not Authenticate"));
           }else{
               context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Authenticated","Welcome " + prof.getFirstName()));
           }
           
           
        }catch(SQLException e){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR !!",""+e.getMessage()));
        }
        
        
        
        
    }
    
}
