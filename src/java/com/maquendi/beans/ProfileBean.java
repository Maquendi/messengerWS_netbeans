
package com.maquendi.beans;

import com.maquendi.theBrain.dao.ProfileDao;
import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value="profilebean")
@ViewScoped
public class ProfileBean implements Serializable{
    
    private List<Profile> list;
    private ProfileDao dao ;
    
    
    @PostConstruct
    public void init(){
        dao = new ProfileDao();
        this.loadprofiles();
    }

    public List<Profile> getList() {
        return list;
    }

    public void setList(List<Profile> list) {
        this.list = list;
    }

    
    public void loadprofiles(){
        
         try{
          list = dao.findAll();
        }catch(SQLException e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"SQL ERROR",""+e.getMessage()));
        }
    }
    
}
