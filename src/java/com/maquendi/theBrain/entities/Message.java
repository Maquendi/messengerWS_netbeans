
package com.maquendi.theBrain.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class Message implements Serializable{
    
    private Integer messageId;
    private String message;
    private Date messageDate;
    private Profile profile;

    
   
    
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date date) {
        messageDate = date;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.messageId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (!Objects.equals(this.messageId, other.messageId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "messageId=" + messageId + ", message=" + message + ", cal=" + messageDate + ", profile=" + profile + '}';
    }
    
    
    
    
}
