package com.socialNetwork.viewmodel;

import com.socialNetwork.model.FriendsGroup;
import com.socialNetwork.model.user.User;
import java.util.ArrayList;
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
    
    /**
     * List of the participants in the group
     */
    //@OneToMany
    private String users;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    
    /**
     * Method to convert FriendsGroupViewModel into the Group model
     * 
     * @return Group
     */
    public FriendsGroup parse(User u) {
        
        FriendsGroup group = new FriendsGroup(u, 
                this.getName(),
                null
        );
        return group;
    }
    
}
