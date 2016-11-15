package com.socialNetwork.model.user;

import com.socialNetwork.model.FriendsGroup;
import com.socialNetwork.model.Information;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class User implements Serializable {
    
    /**
     * Id of the person in Database
     */
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;
   
    /**
     * login for the Person
     */
    private String login;

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
    
    private String passwordHash;
    
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User() {}
    
    public void User(String lastName, String firstName, String login, String password, String passwordRepeated, String mail) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.passwordHash = password;
        this.passwordHash = passwordRepeated;
        this.mail = mail;
    }
    
    public void User(int idUser, String lastName, String firstName, UserRole role, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.role = role;
        this.passwordHash = password;
    }

    public long getIdUser() {
        return idUser;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
