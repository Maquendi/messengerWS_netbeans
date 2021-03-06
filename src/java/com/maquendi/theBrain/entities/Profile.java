
package com.maquendi.theBrain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Profile implements Serializable{
    
    private Integer profileId;
    private String profileName;
    private String firstName;
    private String lastName;
    private Date fecha_creada;
    private String foto_path;
    private String email;
    private String password;
    private List<String> pictureList;

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public String getFoto_path() {
        return foto_path;
    }

    public void setFoto_path(String foto_path) {
        this.foto_path = foto_path;
    }
    
    public Date getFecha_creada() {
        return fecha_creada;
    }

    public void setFecha_creada(Date fecha_creada) {
        this.fecha_creada = fecha_creada;
    }

    
    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.profileId);
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
        final Profile other = (Profile) obj;
        if (!Objects.equals(this.profileId, other.profileId)) {
            return false;
        }
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    

    @Override
    public String toString() {
        return "Profile{" + "profileId=" + profileId + ", profileName=" + profileName + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
    
    
}
