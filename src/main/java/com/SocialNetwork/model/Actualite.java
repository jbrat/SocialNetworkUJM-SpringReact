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
public class Actualite {
    private int idActualite;
    private int idPersonne;
    private String actualite;
    private Date date;
    
    public Actualite() {
        
    }
    
    public int getidActualite() {
        return idActualite;
    }
    
    public void setidActualite(int idActualite) {
        this.idActualite = idActualite;
    }
    
    public void setidPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }
    
    public int getidPersonne() {
        return idPersonne;
    }
    
  
    public String getActualite() {
        return actualite;
    }
    
    public void setActualite(String actualite) {
        this.actualite = actualite;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
}
