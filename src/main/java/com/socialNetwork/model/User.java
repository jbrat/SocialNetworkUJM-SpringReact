package com.socialNetwork.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
     *
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;
    */
    
    /**
     * login for the Person
     */
    @Id
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
    
    private String derivedPassword;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    public User() {}
    
    public void User(int idUser, String lastName, String firstName) {
        //this.idUser = idUser;
        this.lastName = lastName;
        this.firstName = firstName;
        this.roles.add(UserRole.USER);
        this.derivedPassword = null;
    }
    
    public void User(int idUser, String lastName, String firstName, List<String> roles) {
        //this.idUser = idUser;
        this.lastName = lastName;
        this.firstName = firstName;
        this.roles.add(UserRole.USER);
        this.roles.addAll(roles.stream().map(UserRole::valueOf).collect(Collectors.toList()));
    }
    /*
    public long getIdUser() {
        return idUser;
    }

    public void setIdPerson(long idUser) {
        this.idUser = idUser;
    }
    */
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

    public String getDerivedPassword() {
        return derivedPassword;
    }

    public void setDerivedPassword(String derivedPassword) {
        this.derivedPassword = derivedPassword;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }
}
