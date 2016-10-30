package com.socialNetwork.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Class to manage the differents roles for a user
 * 
 * @author UJM's Students
 */
public enum UserRole implements GrantedAuthority {
    
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_"+this.name();
    }
}
