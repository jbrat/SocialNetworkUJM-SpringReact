package com.socialNetwork.viewmodel;

import com.socialNetwork.model.FriendsGroup;
import com.socialNetwork.model.user.User;
import java.text.ParseException;
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
    
    /**
     * Method to convert FriendsGroupViewModel into the Group model
     * 
     * @return Group
     */
    public FriendsGroup parse() {
        
        FriendsGroup group = new FriendsGroup(this.getOwner(), 
                this.getName()
        );
        return group;
    }
    
}
