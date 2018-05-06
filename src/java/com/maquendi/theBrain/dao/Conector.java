
package com.maquendi.theBrain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conector {
    
    private Connection conn = null;
    
    public Conector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    public Connection connectar() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/messenger";
        try{
            
            if(conn ==  null || conn.isClosed()){
               conn = DriverManager.getConnection(url,"root", "Summer01");
            }
        }catch(SQLException e){
            throw e;
        }
        
        return conn;
    }
    
    
    public void desconectar(){
        try {
            if(conn != null || !conn.isClosed()){
               conn.close();
            }
           } catch (SQLException ex) {
               System.out.println(ex.getMessage());
        }
    }
    
    
}
