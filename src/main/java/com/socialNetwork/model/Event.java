package com.socialNetwork.model;

import com.socialNetwork.model.user.User;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private long idEvent;
    
    /**
     * The person list
     */
    @OneToMany
    private List<User> peoplesInvited;
    
    /**
     * The persons which participate at the event
     */
    @OneToMany
    private List<User> peoplesParticipate;
    
    /**
     * The owner of the event
     */
    @OneToOne
    @NotNull
    private User owner;
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
     * Date of the event
     */
    @NotNull
    private Date date;
    
    public Event() {}
    
    public Event(User owner, String name, String description, Date date) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
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
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
}
