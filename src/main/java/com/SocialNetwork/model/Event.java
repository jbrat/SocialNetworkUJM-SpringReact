package com.SocialNetwork.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Class for an Event in the UJM
 * 
 * @author UJM's students
 */
@Entity
public class Event implements Serializable{
    
    /**
     * Id of the Event in Database
     */
    @Id
    private int idEvent;
    
    private static final long serialVersionUID = 1L;
    
    /**
     * The person list
     */
    @OneToMany
    private List<Person> peoplesInvited;
    
    /**
     * The persons which participate at the event
     */
    @OneToMany
    private List<Person> peoplesParticipate;
    
    /**
     * The owner of the event
     */
    @OneToOne
    private Person owner;
    
    /**
     * Name of the event
     */
    private String name;
    
    /**
     * Description of the event
     */
    private String description;
    
    /**
     * Date of the event
     */
    private Date date;
    
    
    public Event(Person owner, String name, String description, Date date) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.date = date;
    }
    
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
}
