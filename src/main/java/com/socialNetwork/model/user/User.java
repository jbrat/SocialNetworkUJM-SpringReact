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
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Id of the person in Database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;
   
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
    private String email;
    
    /**
     * password for the Person
     */
    @NotNull
    private String passwordHash;
    
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
    
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User() {}
    
    public User(String lastName, String firstName, String login, String passwordHash, String mail) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.passwordHash = passwordHash;
        this.email = mail;
        this.role = UserRole.USER;
    }

    public Long getIdUser() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
