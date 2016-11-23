package com.socialNetwork.model;

import com.socialNetwork.model.user.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private long idGroup;
    
    /**
     * Name of the group
     */
    @NotNull
    private String name;
    
    @NotNull
    private String owner;
    
    /**
     * List of the participants in the group
     */
    //@OneToMany
    private ArrayList<User> groupPeoples = new ArrayList<User>();
    
    public FriendsGroup() {}
    
    public FriendsGroup(String name) {
        this.name = name;
    }
    
    public FriendsGroup(String owner, String name, ArrayList<User> groupPeoples) {
        this.owner = owner;
        this.name = name;
        this.groupPeoples = groupPeoples;
    }
    
    public long getIdGroup() {
        return idGroup;
    }
    
    public void setIdGroup(int idGroupe) {
        this.idGroup = idGroupe;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
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
