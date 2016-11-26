package com.socialNetwork.model;

import com.socialNetwork.model.user.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idMessage;
    
    /**
     * The receiver of the message
     */
    //@ManyToOne
    @NotNull
    private String receiver;
    
    /**
     * The Sender of the message
     */
    //@ManyToOne 
    @NotNull
    private String sender;
    
    /**
     * The message String, HTML
     */
    private String message;
    
    /**
     * The date
     */
    @NotNull
    private Date date;

    public Message() {}
    
    public Message(String receiver, String sender, String message, Date date) {
        this.receiver = receiver;
        this.sender = sender;
        
        if(checkEmptyMessage(message)) {
            this.message = message;
        }
        
        this.date = date;
    }
    
    public long getidMessage() {
        return idMessage;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public void setidMessage(long idMessage) {
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

