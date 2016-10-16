/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SocialNetwork.model;

import java.util.Date;

/**
 *
 * @author kevin
 */
public class Messagerie {
    
    private int idMessage;
    private int idPersonne;
    private String message;
    private Date date;
    
    public Messagerie() {
        
    }
    
    public int getidMessage() {
        return idMessage;
    }
    
    public void setidMessage(int idMessage) {
        this.idMessage = idMessage;
    }
    
    public void setidPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }
    
    public int getidPersonne() {
        return idPersonne;
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

