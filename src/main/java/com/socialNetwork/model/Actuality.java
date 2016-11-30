package com.socialNetwork.model;

import com.socialNetwork.model.user.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import com.socialNetwork.utils.DateOperation;

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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long idActuality;
    
    /**
     * The Owner of the actuality
     */
    @OneToOne
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
    
    /**
     * Constructor Actuality
     * 
     * @param person the owner of the actuality
     * @param title
     * @param message
     * @param date 
     */
    public Actuality(User person, String title, String message, Date date) {
        this.person = person;
        this.title = title;
        this.message = message;
        this.date = date;
    }

    public long getIdActuality() {
        return idActuality;
    }

    public void setIdActuality(long idActuality) {
        this.idActuality = idActuality;
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
    
    public String getDate() {
        return DateOperation.shortFormat(date);
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
}
