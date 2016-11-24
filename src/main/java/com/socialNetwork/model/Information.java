package com.socialNetwork.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author UJM's students
 */
@Entity
public class Information implements Serializable {
    
    /**
     * Id of the class for the database
     */
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idInformation;
    
    /**
     * phoneNumber of a person
     */
    private String phoneNumber;
   
    /**
     * The street of a person
     */
    private String street;
    
    /**
     * THe city of a person
     */
    private String city;
    
    /**
     * The postal code for a person
     */
    private String postalCode;
    
    public Information() {}
    
    public Information(String phoneNumber, String street, String city, String postalCode) {
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }
    
    public long getIdInformation() {
        return idInformation;
    }
    
    public void setIdInformation(long idInformation) {
        this.idInformation = idInformation;
    }
    
    public String getRue() {
        return street;
    }
    
    public void setRue(String street) {
        this.street = street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    
}
