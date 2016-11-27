package com.socialNetwork.viewmodel;

import com.socialNetwork.model.FriendsGroup;
import com.socialNetwork.model.user.User;
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
    private String listUsers;
    
    /**
     * Id group
     */
    private Long idGroup;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getUsers() {
        return listUsers;
    }

    public void setUsers(String users) {
        this.listUsers = users;
    }

    public String getListUsers() {
        return listUsers;
    }

    public void setListUsers(String listUsers) {
        this.listUsers = listUsers;
    }

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public FriendsGroupViewModel(FriendsGroup group) {
        this.name = group.getName();
        this.idGroup = group.getIdGroup();
    }
    
    public FriendsGroupViewModel() {}
    

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
    
    public FriendsGroup update(FriendsGroup group) {
        if(!group.getName().equals(getName())) {
            group.setName(getName());
        }
        
        return group;
    }
    
}
