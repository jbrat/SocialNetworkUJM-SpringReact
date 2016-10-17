package com.SocialNetwork.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *  Class Message for chat
 * 
 * @author UJM's Students
 */
@Entity
public class Message implements Serializable {
    
    /**
     * Id message in the database
     */
    @Id
    private int idMessage;
    
     private static final long serialVersionUID = 1L;
    
    /**
     * The receiver of the message
     */
    @ManyToOne
    private Person receiver;
    
    /**
     * The Sender of the message
     */
    @ManyToOne 
    private Person sender;
    
    /**
     * The message String, HTML
     */
    private String message;
    
    /**
     * The date
     */
    private Date date;

    
    public Message(Person receiver, Person sender, String message, Date date) {
        this.receiver = receiver;
        this.sender = sender;
        
        if(checkEmptyMessage(message)) {
            this.message = message;
        }
        
        this.date = date;
    }
    
    public int getidMessage() {
        return idMessage;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }
    
    public void setidMessage(int idMessage) {
        this.idMessage = idMessage;
    }
  
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        if(checkEmptyMessage(message)) {
            this.message = message;
        }
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * Check if the message is empty
     * 
     * @param message
     * 
     * @return Boolean
     */
    private boolean checkEmptyMessage(String message) {
        return message.isEmpty();
    }
    
}

