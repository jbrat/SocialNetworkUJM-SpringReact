package com.socialNetwork.controllers;

import com.socialNetwork.model.user.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main controller of the application
 * 
 * @author UJM student's
 */
@Controller
public class HomeController {
    
    /**
     * Method to access to the home page of the application 
     * 
     * @param model Thymeleaf model
     * 
     * @return template index
     */
    @RequestMapping("/") 
    public String home(Model model) {
       
        // load user if he is authenticated
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
           CurrentUser u = (CurrentUser) auth.getPrincipal();
           model.addAttribute("user", u.getUser());
        }   

        return "index";
    }
}
