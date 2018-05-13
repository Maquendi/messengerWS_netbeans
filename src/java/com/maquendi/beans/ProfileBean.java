
package com.maquendi.beans;

import com.maquendi.theBrain.dao.ProfileDao;
import com.maquendi.theBrain.entities.Profile;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named(value="profilebean")
@SessionScoped
public class ProfileBean implements Serializable{
    
    private String query;
    private List<Profile> list;
    private List<Profile> foundList;
    private ProfileDao dao ;
    private Profile prof;
    private int state;

    
    @PostConstruct
    public void init(){
        foundList = new ArrayList<>();
        dao = new ProfileDao();
        loadprofiles();
    }
    
    
    
    public List<Profile> getFoundList() {
        return foundList;
    }

    public void setFoundList(List<Profile> foundList) {
        this.foundList = foundList;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    

    public Profile getProf() {
        return prof;
    }

    public void setProf(Profile prof) {
        this.prof = prof;
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
    
    
    
    public String redirect(){
        
        
        if(state == 0){
            state++;
            return "otherprofile";  
        }else{
              return "from_other_profile";
        }
        

    }
    
    public String home(){
        state = 0;
        return "home";
    }
    
    
    public void search(){
        foundList = new ArrayList<>();
        
        
      if(!query.isEmpty()){
          list.stream().filter((p) -> (p.getLastName().contains(query) | p.getFirstName().contains(query) | 
                  (p.getFirstName() + " " + p.getLastName()).contains(query))).forEachOrdered((p) -> {
                      foundList.add(p);
            });
        }
     }
    
    
    
    public void selected(Profile prof)
    {
        System.out.println(prof);
    } 
    
}
