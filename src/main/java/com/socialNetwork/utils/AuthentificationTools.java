package com.socialNetwork.utils;

import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.model.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuthentificationTools {
    
    public static User getCurrentUser() {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser u = (CurrentUser) auth.getPrincipal();
        
        return u.getUser();   
    }
    
    public static Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser u = (CurrentUser) auth.getPrincipal();
        
        return u.getId();
    }
}
