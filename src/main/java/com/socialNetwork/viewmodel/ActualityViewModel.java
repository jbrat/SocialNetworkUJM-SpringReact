/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialNetwork.viewmodel;

import com.socialNetwork.model.Actuality;
import com.socialNetwork.model.user.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kevin
 */
public class ActualityViewModel {
    
    /**
     * The Owner
     */
    //@OneToOne
    @NotNull
    private String person;
    
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
    
    /**
     * The date of the actuality
     */
    @NotNull
    private String date;
    
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

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
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
     /**
     * Method to convert ActualityViewModel into the Actuality model
     * 
     * @return Actuality
     */
    public Actuality parse() {
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = formatter.parse(this.getDate());
  
            Actuality actuality = new Actuality(this.getPerson(), 
                this.getTitle(), 
                this.getMessage(), 
                date);
        
            return actuality;

        } catch (ParseException actuality) {
 
            return new Actuality(this.getPerson(),
                this.getTitle(),
                this.getMessage(),
                new Date());
        }
    }
    
}
