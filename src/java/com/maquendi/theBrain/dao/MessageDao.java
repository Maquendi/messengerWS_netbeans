
package com.maquendi.theBrain.dao;


import com.maquendi.theBrain.entities.Message;
import com.maquendi.theBrain.entities.Profile;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MessageDao{

	private final Conector conn;
	
	public MessageDao(){
		conn = new Conector();
	}
	
	public Message save(Message message) throws Exception{
		
		
		String sql = "INSERT INTO message(message,date_created,profileId) VALUES(?,?,?)";
		Connection conexion = conn.connectar();
		int messageId =0;
		
	   
		try{
			
			
			conexion.setAutoCommit(false); //starts transaction here...
	
			PreparedStatement pst =  (PreparedStatement) conexion.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);	
			pst.setString(1,message.getMessage());
			pst.setDate(2,new Date(message.getMessageDate().getTime()));
			pst.setInt(3,message.getProfile().getProfileId());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			
			if(rs.next()){
			    messageId = rs.getInt(1);
			}
			
			conexion.commit();

		}catch(SQLException e){
			conexion.rollback();
			throw e;
		}finally{
			conn.desconectar();
		}
       
		return this.find(messageId);
	}
	
	
	
	
	public Message find(Integer messageId) throws SQLException {
		
		String query = "SELECT * FROM message WHERE messageId = ?";
		Message newMessage = null;
		
		try{
			Connection conexion = conn.connectar();
			PreparedStatement pst = (PreparedStatement) conexion.prepareStatement(query);
			pst.setInt(1,messageId);
			ResultSet rs = pst.executeQuery();
			ProfileDao pDao = new ProfileDao();
			
			if(rs.next()){
				newMessage = new Message();
				newMessage.setMessageId(rs.getInt("messageId"));
				newMessage.setProfile(pDao.find(rs.getInt("profileId")));
                                newMessage.setMessageDate(new java.util.Date(rs.getDate("date_created").getTime()));
				newMessage.setMessage(rs.getString("message"));
			}

		   }catch(SQLException e){
			throw e;
		}finally{
			conn.desconectar();
		}

		return newMessage;
	}
	
	
	public List<Message> findAll()throws SQLException{
		
		List<Message> lista = new ArrayList<>();
		String query = "SELECT * FROM message";
                ProfileDao pDao = new ProfileDao();
		
		try{
                         
			Connection conexion = conn.connectar();
			PreparedStatement pst = (PreparedStatement) conexion.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()){
			Message m = new Message();		
                                m.setMessageId(rs.getInt("messageId"));
				m.setMessage(rs.getString("message"));
				m.setProfile(pDao.find(rs.getInt("profileId")));
                                m.setMessageDate(new java.util.Date(rs.getDate("date_created").getTime()));
				lista.add(m);
			  }
			
			  }catch(SQLException e){
			      throw e;
		    }finally{
			    conn.desconectar();
		}

		return lista;
	}
	
	
	
	
	public Message delete(int messageId)throws Exception{
		
		String sql = "DELETE FROM message WHERE messageId = ?";
		Message message = null;
		
		try{
			
		     message = find(messageId);
                       if(message != null){
			   Connection conexion = conn.connectar();
			   PreparedStatement pst = (PreparedStatement)conexion.prepareStatement(sql);
			   pst.setLong(1,messageId);
			   pst.executeUpdate();
			}

		    }catch(SQLException e){
			throw e;
		   }finally{
			 conn.desconectar();
		}
		return message;
	}
	
	
	
	public Message update(Message message) throws SQLException{
		
		String query = "UPDATE message m SET m.message = ? WHERE m.messageId = ?";
		
		  try{
			Connection conexion = conn.connectar();
			PreparedStatement pst=(PreparedStatement)conexion.prepareStatement(query);
			pst.setString(1,message.getMessage());
			pst.setLong(2,message.getMessageId());
			pst.executeUpdate();

		     }catch(SQLException e){
			throw e;
		}finally{
			conn.desconectar();
		}
		
		return this.find(message.getMessageId());
	}
	
	
	public List<Message> findAllByYear(int year) throws SQLException{
		
		String query = "SELECT * FROM message WHERE YEAR(date_created) = ?";
		List<Message> lista = new ArrayList<>();
                ProfileDao pDao = new ProfileDao();
		
	try{
		 PreparedStatement pst = (PreparedStatement) conn.connectar().prepareStatement(query);
		 pst.setInt(1,year);
		 ResultSet rs = pst.executeQuery();

		 while(rs.next())
		 {
		 Message message = new Message();
			 message.setProfile(pDao.find(rs.getInt("profileId")));
			 message.setMessageDate(new java.util.Date(rs.getDate("date_created").getTime()));
			 message.setMessageId(rs.getInt("messageId"));
			 message.setMessage(rs.getString("message"));
			 lista.add(message);
		  }

		}catch(SQLException e){
			throw e;
		}
		
		return lista;
	}
	
	
	public List<Message> findAllByProfile(Profile profile) throws SQLException{
		
		String query = "SELECT * FROM message WHERE profileId = ?";
		List<Message> lista = new ArrayList<>();
                ProfileDao pDao = new ProfileDao();
		
	try{
		 PreparedStatement pst = (PreparedStatement) conn.connectar().prepareStatement(query);
		 pst.setInt(1,profile.getProfileId());
		 ResultSet rs = pst.executeQuery();

		 while(rs.next())
		 {
		 Message message = new Message();
			 message.setProfile(pDao.find(rs.getInt("profileId")));
			 message.setMessageDate(new java.util.Date(rs.getDate("date_created").getTime()));
			 message.setMessageId(rs.getInt("messageId"));
			 message.setMessage(rs.getString("message"));
			 lista.add(message);
		  }

		}catch(SQLException e){
			throw e;
		}
		
		return lista;
	}
	
        
        
}
