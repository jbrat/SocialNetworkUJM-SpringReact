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
public class Evenement {
    
    private int idEvenement;
    private int idPersonne;
    private String evenement;
    private Date date;
    
    public Evenement() {
        
    }
    
    public int getidEvenement() {
        return idEvenement;
    }
    
    public void setidEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }
    
    public void setidPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }
    
     public int getidPersonne() {
        return idPersonne;
    }
    
  
     public String getEvenement() {
        return evenement;
    }
    
    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }
     public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
}
