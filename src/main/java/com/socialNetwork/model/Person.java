package com.socialNetwork.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Class Person which use to have informations for a Person
 * 
 * @author UJM student's
 */
@Entity
public class Person implements Serializable {
    
    /**
     * Id of the person in Database
     */
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPerson;

    /**
     * lastName of the Person
     */
    @NotNull
    private String lastName;
    
    /**
     * firstName of the Person
     */
    @NotNull
    private String firstName;
    
    /**
     * email of the Person
     */
    @NotNull
    private String mail;
    
    /**
     * login for the Person
     */
    @NotNull
    private String login;
    
    /**
     * password for the Person
     */
    @NotNull
    private String password;
    
    /**
     * Supplementary informations for a Person
     */
    @OneToOne
    private Information information;
    
    /**
     * The group of the Person ( can be null ) 
     */
    @ManyToOne
    private FriendsGroup group;
  
    public Person() {}
    
    public void Person(int idPerson, String lastName, String firstName) {
        this.idPerson = idPerson;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public FriendsGroup getGroup() {
        return group;
    }

    public void setGroup(FriendsGroup group) {
        this.group = group;
    }
  
    
}
