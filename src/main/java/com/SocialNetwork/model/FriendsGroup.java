package com.SocialNetwork.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    private int idGroupe;
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Name of the group
     */
    private String name;
    
    /**
     * List of the participants in the group
     */
    @OneToMany
    private ArrayList<Person> groupPeoples = new ArrayList<Person>();
    
    
    public FriendsGroup(String name) {
        this.name = name;
    }
    
    public int getIdGroupe() {
        return idGroupe;
    }
    
    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
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
