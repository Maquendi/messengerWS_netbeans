
package com.maquendi.beans;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value="redirect")
@ViewScoped
public class redirectBean implements Serializable{
    
    
    
    
    
    public String register(){
        return "register";
    }
    
    public String login(){
        return "index";
    }
    
    
    public String change_account_info(){
        return "open";
    }
    
    
}
