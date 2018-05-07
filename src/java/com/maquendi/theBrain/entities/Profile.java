
package com.maquendi.theBrain.entities;

import java.util.Date;
import java.util.Objects;


public class Profile {
    
    private Integer profileId;
    private String profileName;
    private String firstName;
    private String lastName;
    private Date fecha_creada;

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

    @Override
    public String toString() {
        return "Profile{" + "profileId=" + profileId + ", profileName=" + profileName + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
    
    
}
