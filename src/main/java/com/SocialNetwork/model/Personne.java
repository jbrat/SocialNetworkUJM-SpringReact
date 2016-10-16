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
public class Personne {
    
    private int idPersonne;
    private int idGroupe;
    private String nom;
    private String prenom;
    private String mail;
    private String login;
    private String passe;
    private int idInformation;
    
    public Personne(){
        
    }
    
    public void setidPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }
    
    public int getidPersonne() {
        return idPersonne;
    }
    
    public int getIdGroupe() {
        return idGroupe;
    }
    
    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPasse() {
        return passe;
    }
    
    public void setPasse(String passe) {
        this.passe = passe;
    }
    
    public int getIdInformation() {
        return idInformation;
    }
    
    public void setIdInformation(int idInformation) {
        this.idInformation = idInformation;
    }
    
}
