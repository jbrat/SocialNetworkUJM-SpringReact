package com.socialNetwork.model;

import com.socialNetwork.model.user.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Class Friends Group for have a list of students
 * 
 * @author UJM's students
 */
@Entity
public class FriendsGroup implements Serializable {
    
    private static final long serialVersionUID = 1L;
    /**
     * Id of the group
     */
    @Id
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
    @OneToMany
    private Set<User> groupPeoples = new HashSet<User>();
    
    public FriendsGroup() {}

    public FriendsGroup(User owner, String name, ArrayList<User> groupPeoples) {
        this.owner = owner;
        this.name = name;
        for(User u : groupPeoples) {
            this.groupPeoples.add(u);
        }
    }
    
    public Long getIdGroup() {
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
    
    public Set<User> getPeoples() {
        return groupPeoples;
    }
}
