package com.socialNetwork.model.user;

import java.io.Serializable;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 * Interface to have method for authentication user
 * 
 * @author UJM's students
 */
public interface UserDetails extends Serializable {
    
    Collection<? extends GrantedAuthority> getAuthorities();
    String getPassword();
    String getUsername();
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
}
