package com.SocialNetwork.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
    private int idActuality;
    
    private static final long serialVersionUID = 1L;
    
    /**
     * The Owner
     */
    @OneToOne
    private Person person;
    
    /**
     * The title of the actuality
     */
    private String title;
    
    /**
     * The message of the actuality
     */
    private String message;
    
    /**
     * The date of the actuality
     */
    private Date date;
    
    public Actuality(Person person, String title, String message, Date date) {
        this.person = person;
        this.title = title;
        this.message = message;
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
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
