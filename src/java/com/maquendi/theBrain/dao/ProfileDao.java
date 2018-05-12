
package com.maquendi.theBrain.dao;

import com.maquendi.theBrain.entities.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProfileDao {
    
    private final Conector connector;
    
    
    public ProfileDao(){
        connector = new Conector();
    }
    
    public Profile find(Integer profileId) throws SQLException{
        
        String query = "SELECT * FROM profile WHERE profileId = ?";
        Profile profile = null;
        
        try{
            PreparedStatement pst = connector.connectar().prepareStatement(query);
            pst.setInt(1,profileId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                profile = new Profile();
                profile.setProfileId(profileId);
                profile.setProfileName(rs.getString("prof_name"));
                profile.setFirstName(rs.getString("first_name"));
                profile.setLastName(rs.getString("last_name"));
                profile.setFecha_creada(new java.util.Date(rs.getDate("date_created").getTime()));
              }
            
            }catch(SQLException e){
              throw e;
         }finally{
            //connector.desconectar();
        }
         return profile;
    }
    
    
    
     public Profile findByName(String profName) throws SQLException{
        
        String query = "SELECT * FROM profile WHERE profileId = ?";
        Profile profile = null;
        
        try{
            PreparedStatement pst = connector.connectar().prepareStatement(query);
            pst.setString(1,profName);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                profile = new Profile();
                profile.setProfileId(rs.getInt("profileId"));
                profile.setProfileName(rs.getString("prof_name"));
                profile.setFirstName(rs.getString("first_name"));
                profile.setLastName(rs.getString("last_name"));
                profile.setFecha_creada(new java.util.Date(rs.getDate("date_created").getTime()));
              }
            
            }catch(SQLException e){
              throw e;
         }finally{
            connector.desconectar();
        }
         return profile;
    }
    
     
     
     public ResultSet getResultSet() throws SQLException{
         
         String query = "SELECT * FROM profile";
         ResultSet rs = null;
		
		try{
			
		    PreparedStatement pst = connector.connectar().prepareStatement(query);
		    rs = pst.executeQuery(); 
		  }catch(SQLException e){
			throw e;
		}finally{
		   connector.desconectar();
		}
            return rs;
         
     }
    
    
    
  public List<Profile> findAll()throws SQLException{
		List<Profile> lista = new ArrayList<>();
		String query = "SELECT * FROM profile";
		
		
		try{
			
		    PreparedStatement pst = connector.connectar().prepareStatement(query);
		    ResultSet rs = pst.executeQuery();
			
		    while(rs.next()){
		    Profile prof = new Profile();
                    prof.setProfileId(rs.getInt("profileId"));
		    prof.setFirstName(rs.getString("first_name"));
		    prof.setLastName(rs.getString("last_name"));
		    prof.setProfileName(rs.getString("prof_name"));
                     prof.setFecha_creada(new java.util.Date(rs.getDate("date_created").getTime()));
		    lista.add(prof);
		   }
                    
		  }catch(SQLException e){
			throw e;
		}finally{
			connector.desconectar();
		}
		
		return lista;
		
	}
  
  
      
      public Profile create(Profile profile) throws SQLException{
		 
		String insert = "INSERT INTO profile(prof_name,first_name,last_name,email,password) VALUES(?,?,?,?,?)";
		Connection conn = connector.connectar();
		
		try{
			conn.setAutoCommit(false);
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(insert);
			pst.setString(1,profile.getProfileName());
			pst.setString(2,profile.getFirstName());
			pst.setString(3,profile.getLastName());
                        pst.setString(4,profile.getEmail());
                        pst.setString(5,profile.getPassword());
			pst.executeUpdate();
			conn.commit();
		   }catch(SQLException e){
			   conn.rollback();
			 throw e;
		 }finally{
			 connector.desconectar();
		 }
	  	
		return this.findByName(profile.getProfileName());
	 }
	
    
    
      public Profile delete(String profile_name)throws SQLException{
		
		String sql = "DELETE FROM profile WHERE prof_name = ?";
		Profile deletedProf = null;
		
		try{
			deletedProf = this.findByName(profile_name);
			
			if(deletedProf != null){
				PreparedStatement pst = (PreparedStatement) connector.connectar().prepareStatement(sql);
				pst.setString(1,profile_name);
				pst.executeUpdate();
			}
			
		   }catch(SQLException e){
			throw e;
		  }finally{
			 connector.desconectar();
		}
		
		return deletedProf;
	}
      
      
      
      public Profile update(String prof_name, Profile prof) throws SQLException{
		String query = "UPDATE profile p SET p.prof_name = ?, p.first_name = ?, p.last_name = ? WHERE p.prof_name = ?";
		Connection conexion = connector.connectar();
		
	    try{
			conexion.setAutoCommit(false);
			PreparedStatement pst = (PreparedStatement) conexion.prepareStatement(query);
			pst.setString(1,prof.getProfileName());
			pst.setString(2,prof.getFirstName());
			pst.setString(3,prof.getLastName());
			pst.setString(4,prof_name);
			pst.executeUpdate();
			conexion.commit();
			
			
		}catch(SQLException e){
			conexion.rollback();
			throw e;
		}
		
		return findByName(prof.getProfileName());
	}
      
    
      
      
      public Profile authenticar(String username,String password) throws SQLException{
          
          String query;
          Profile prof = null;
          if(username.contains("@")){
              query = "SELECT * FROM profile WHERE email = ? AND password = ?";
          }else{
              query = "SELECT * FROM profile WHERE prof_name = ? AND password = ?";
          }
          
          try{
              PreparedStatement pst = connector.connectar().prepareStatement(query);
              pst.setString(1,username);
              pst.setString(2,password);
              
              ResultSet rs = pst.executeQuery();
              
              if(rs.next()){
                  prof = new Profile();
                  prof.setEmail(rs.getString("email"));
                  prof.setPassword(rs.getString("password"));
                  prof.setProfileName(rs.getString("prof_name"));
                  prof.setProfileId(rs.getInt("profileId"));
                  prof.setFecha_creada(rs.getDate("date_created"));
                  prof.setLastName(rs.getString("last_name"));
                  prof.setFirstName(rs.getString("first_name"));
                  prof.setFoto_path(rs.getString("profile_picture"));

              }
              
              
              
          }catch(SQLException e){
              throw e;
          }

          return prof;          
      }
      
      
}
