package com.socialNetwork.controllers;

import com.socialNetwork.model.user.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to manage the chat messages
 *
 * @author UJM's students
 */
@Controller
public class MessageController {

    /**
     * Method to join the React messages page
     * 
     * @param model Thymeleaf model
     * 
     * @return String name of template
     */
    @RequestMapping("/messages")
    public String getMessages(Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
           CurrentUser u = (CurrentUser) auth.getPrincipal();
           model.addAttribute("user", u.getUser());
        }   
        
        return "messages";
    }   
}
