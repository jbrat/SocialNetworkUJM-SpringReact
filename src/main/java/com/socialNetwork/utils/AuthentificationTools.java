package com.socialNetwork.utils;

import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.model.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Class to have methods about authentification 
 * 
 * @author UJM's students
 */
public class AuthentificationTools {
    
    /**
     * Method to get the curren User authenticated
     * 
     * @return User
     */
    public static User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser u = (CurrentUser) auth.getPrincipal();
        
        return u.getUser();   
    }
    
    /**
     * Method to get the current user id authenticated
     * 
     * @return user id
     */
    public static Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser u = (CurrentUser) auth.getPrincipal();
        
        return u.getId();
    }
}
