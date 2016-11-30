package com.socialNetwork.model.user;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * The current User which is authenticated
 * 
 * @author UJM's students
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    /**
     * The model User
     */
    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
    public Long getId() {
        return user.getIdUser();
    }

    public UserRole getRole() {
        return user.getRole();
    }
}