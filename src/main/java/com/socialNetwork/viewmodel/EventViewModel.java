/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialNetwork.viewmodel;

import com.socialNetwork.model.Event;
import com.socialNetwork.model.user.User;
import com.socialNetwork.utils.DateOperation;
import java.text.ParseException;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author julien
 */
public class EventViewModel {
    
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
     * @param User the owner of the creation
     * 
     * @return Event
     */
    public Event parse(User u) {

        try {
            System.out.println(getDate());
            Event e = new Event(u, 
                this.getName(), 
                this.getDescription(), 
                DateOperation.shortFormat(getDate()));
        
            return e;

        } catch (ParseException e) {
 
            return new Event(u,
                this.getName(),
                this.getDescription(),
                new Date());
        }
    }
    
    public Event update(Event e) {
        if(!e.getName().equals(getName())) {
            e.setName(getName());
        } else if (!e.getDescription().equals(getDescription())){
            e.setDescription(getDescription());
        }
        
        try {
            if (!e.getDate().equals(DateOperation.shortFormat(getDate()))) {
                e.setDate(new Date(getDate()));
            } 
        } catch(ParseException ex) {
            return e;
        }
        
        return e;
    }
    
}
