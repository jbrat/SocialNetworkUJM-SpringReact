package com.SocialNetwork.model;

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
    
    /**
     * List of the participants in the group
     */
    @OneToMany
    private ArrayList<Person> groupPeoples = new ArrayList<Person>();
    
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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addPeople(Person p) {
        groupPeoples.add(p);
    }
    
    public boolean removePeople(Person p) {
        return groupPeoples.remove(p);
    }
    
    public List<Person> getPeoples() {
        return groupPeoples;
    }
    
}
