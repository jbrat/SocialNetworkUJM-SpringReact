package com.socialNetwork.controllers;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
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
    
    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public HomeController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    
    /**
     * Method to access to the home page of the application 
     * 
     * @param model for thymeleaf
     * 
     * @return template
     */
    @RequestMapping("/") 
    public String home(Model model) {
        
      /*  if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        String [] fields = { "id", "email",  "first_name", "last_name" };
        User u = facebook.fetchObject("me", User.class, fields);

        System.err.println(u.getLastName() + " " + u.getFirstName());*/
        return "index";
    }
}
