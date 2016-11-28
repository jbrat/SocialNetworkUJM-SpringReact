package com.socialNetwork.viewmodel;

import com.socialNetwork.model.Information;
import com.socialNetwork.model.user.User;
import com.socialNetwork.repository.UserRepository;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class to create a information with the form 
 * @author kevin
 */
public class InformationViewModel {
    @Inject
    private UserRepository userRepo;
    
    private Long idInformation;
    @NotNull
    private String phoneNumber = "";
    @NotNull
    private String street = "";
    @NotNull
    private String city = "";
    @NotNull
    private String postalCode = "";
    
    public Long getIdInformation() {
        return idInformation;
    }
    
    public void setIdInformation(Long idInformation) {
        this.idInformation = idInformation;
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
    
    /**
     * Method to convert InformationViewModel into the Information model
     * 
     * @param User the owner of the creation
     * 
     * @return Information
     */
    public Information parse(User u) {
        
        Information information = new Information(u,
            this.getIdInformation(),
            this.getPhoneNumber(), 
            this.getStreet(), 
            this.getCity(),
            this.getPostalCode());
        
            return information;
    }
    
    public Information update(Information info) {
        if(!info.getPhoneNumber().equals(getPhoneNumber())) {
            info.setPhoneNumber(getPhoneNumber());
        } else if(!info.getStreet().equals(getStreet())) {
            info.setStreet(getStreet());
        }if(!info.getCity().equals(getCity())) {
            info.setCity(getCity());
        } else if(!info.getPostalCode().equals(getPostalCode())) {
            info.setPostalCode(getPostalCode());
        }
        
        return info;
    }
}