/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialNetwork.viewmodel;

import com.socialNetwork.model.Actuality;
import com.socialNetwork.model.user.User;
import com.socialNetwork.repository.UserRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kevin
 */
public class ActualityViewModel {
    
    @Inject
    private UserRepository userRepo;
    
    /**
     * The title of the actuality
     */
    @NotNull
    private String title;
    
    /**
     * The message of the actuality
     */
    @NotNull
    private String message;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
     /**
     * Method to convert ActualityViewModel into the Actuality model
     * 
     * @param User the owner of the creation
     * 
     * @return Actuality
     */
    public Actuality parse(User u) {
        
        Actuality actuality = new Actuality(u, 
            this.getTitle(), 
            this.getMessage(), 
            new Date());
        
            return actuality;
    }
    
    public Actuality update(Actuality actu) {
        if(!actu.getTitle().equals(getTitle())) {
            actu.setTitle(getTitle());
        } else if(!actu.getMessage().equals(getMessage())) {
            actu.setMessage(getMessage());
        }
        
        return actu;
    }
    
}
