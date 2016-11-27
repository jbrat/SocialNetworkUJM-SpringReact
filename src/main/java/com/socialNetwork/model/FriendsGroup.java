package com.socialNetwork.model;

import com.socialNetwork.model.user.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Class Friends Group for have a list of students
 * 
 * @author UJM's students
 */
@Entity
public class FriendsGroup implements Serializable {
    
    /**
     * Id of the group
     */
    @Id
    @NotNull
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idGroup;
    
    /**
     * Name of the group
     */
    @NotNull
    private String name;
    
    @NotNull
    @OneToOne
    private User owner;
    
    /**
     * List of the participants in the group
     */
    //@OneToMany
    private ArrayList<User> groupPeoples = new ArrayList<User>();
    
    public FriendsGroup() {}
    
    public FriendsGroup(String name) {
        this.name = name;
    }
    
    public FriendsGroup(User owner, String name, ArrayList<User> groupPeoples) {
        this.owner = owner;
        this.name = name;
        this.groupPeoples = groupPeoples;
    }
    
    public long getIdGroup() {
        return idGroup;
    }
    
    public void setIdGroup(Long idGroupe) {
        this.idGroup = idGroupe;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addPeople(User p) {
        groupPeoples.add(p);
    }
    
    public boolean removePeople(User p) {
        return groupPeoples.remove(p);
    }
    
    public ArrayList<User> getPeoples() {
        return groupPeoples;
    }
}
