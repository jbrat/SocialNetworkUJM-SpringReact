package com.socialNetwork.viewmodel;

import com.socialNetwork.model.FriendsGroup;
import com.socialNetwork.model.user.User;
import com.socialNetwork.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kevin
 */
public class FriendsGroupViewModel {
    
    @Inject 
    private UserRepository userRep;
    
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
    public FriendsGroup parse(User u, List<User> users) {

        FriendsGroup group = new FriendsGroup(u, 
            getName(),
            getPeoples(getListUsers(), users)
        );

        System.out.println("TEST"+group.getIdGroup());
        return group;
    }
    
    public FriendsGroup update(FriendsGroup group) {
        if(!group.getName().equals(getName())) {
            group.setName(getName());
        }
        
        return group;
    }
    
    private ArrayList<User> getPeoples(String listUsersParam, List<User> usersDB) {
        ArrayList<String> names = new ArrayList<String>();
        
        if(listUsersParam.contains(";")) {
            String[] params = listUsersParam.split(";");
            for(String param : params) {
                names.add(param);
            }
        } else {
            names.add(listUsersParam);
        }
        
        ArrayList<User> returnResult = new ArrayList<User>();
        for(User user : usersDB) {
            String pattern = user.getFirstName()+ " " + user.getLastName();
            if(names.contains(pattern)) {
                returnResult.add(user);
            }
        }
        
        return returnResult;
    }
}
