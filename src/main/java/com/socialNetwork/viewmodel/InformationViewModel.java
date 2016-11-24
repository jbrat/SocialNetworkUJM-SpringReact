package com.socialNetwork.viewmodel;

import com.socialNetwork.model.Information;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class to create a information with the form 
 * @author kevin
 */
public class InformationViewModel {
    
    @NotEmpty
    private String phoneNumber = "";
    @NotEmpty
    private String street = "";
    @NotEmpty
    private String city = "";
    @NotEmpty
    private String postalCode = "";
    
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
    
    public Information parse() {
        return new Information(phoneNumber, street, city, postalCode);
    }
}
