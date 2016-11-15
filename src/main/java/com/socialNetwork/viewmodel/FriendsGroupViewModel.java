package com.socialNetwork.viewmodel;

import com.socialNetwork.model.FriendsGroup;
import com.socialNetwork.model.user.User;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kevin
 */
public class FriendsGroupViewModel {
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
    
    /**
     * Method to convert FriendsGroupViewModel into the Group model
     * 
     * @return Group
     */
    public FriendsGroup parse() {
        
        FriendsGroup group = new FriendsGroup(this.getOwner(), 
                this.getName(),
                this.getPeoples()
        );
        return group;
    }
    
}
