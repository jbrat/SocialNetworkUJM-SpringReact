/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialNetwork.viewmodel;

import com.socialNetwork.model.Event;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author julien
 */
public class EventViewModel {
    
    /**
     * The owner of the event
     */
    @NotNull
    private String owner;
    /**
     * Name of the event
     */
    @NotNull
    private String name;
    
    /**
     * Description of the event
     */
    private String description;
    
    /**
     * date of the event 
     */
    private String date;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * Method to convert EventViewModel into the Event model
     * 
     * @return Event
     */
    public Event parse() {
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = formatter.parse(this.getDate());
  
            Event e = new Event(this.getOwner(), 
                this.getName(), 
                this.getDescription(), 
                date);
        
            return e;

        } catch (ParseException e) {
 
            return new Event(this.getOwner(),
                this.getName(),
                this.getDescription(),
                new Date());
        }
    }
}
