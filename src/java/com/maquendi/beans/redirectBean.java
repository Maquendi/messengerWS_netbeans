/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
}
