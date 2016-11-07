package com.socialNetwork.model;

import com.socialNetwork.model.user.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Class for the actuality
 * 
 * @author UJM's students
 */
@Entity
public class Actuality implements Serializable{
    
    /**
     * IdActuality in the Database
     */
    @Id
    @NotNull
    private long idActuality;
    
    /**
     * The Owner
     */
    //@OneToOne
    @NotNull
    private User person;
    
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
    private Date date;
    
    public Actuality() {}
    
    public Actuality(User person, String title, String message, Date date) {
        this.person = person;
        this.title = title;
        this.message = message;
        this.date = date;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
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
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
}
