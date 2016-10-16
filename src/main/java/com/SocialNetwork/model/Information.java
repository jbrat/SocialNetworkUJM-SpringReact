/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SocialNetwork.model;

/**
 *
 * @author kevin
 */
public class Information {
    private int idInformation;
    private int telephone;
    private String favori;
    private String rue;
    private String ville;
    private int codePostal;
    
    public Information() {
        
    }
    
    public int getIdInformation() {
        return idInformation;
    }
    
    public void setIdInformation(int idInformation) {
        this.idInformation = idInformation;
    }
    
    public int getTelephone() {
        return telephone;
    }
    
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
    
    public String getFavori() {
        return favori;
    }
    
    public void setFavori(String favori) {
        this.favori = favori;
    }
    
    public String getRue() {
        return rue;
    }
    
    public void setRue(String rue) {
        this.rue = rue;
    }
    
    public String getVille() {
        return ville;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public int getCodePostal() {
        return codePostal;
    }
    
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }
}
