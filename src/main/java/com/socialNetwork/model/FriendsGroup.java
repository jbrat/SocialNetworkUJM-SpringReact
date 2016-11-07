package com.socialNetwork.model;

import com.socialNetwork.model.user.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private long idGroup;
    
    /**
     * Name of the group
     */
    @NotNull
    private String name;
    
    @NotNull
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
    
    public long getIdGroup() {
        return idGroup;
    }
    
    public void setIdGroup(int idGroupe) {
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
    
    public List<User> getPeoples() {
        return groupPeoples;
    }
    
}
