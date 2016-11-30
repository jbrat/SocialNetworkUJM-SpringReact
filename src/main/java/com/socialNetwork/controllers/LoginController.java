package com.socialNetwork.controllers;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller to manage the login
 * 
 * @author UJM's students
 */
@Controller
public class LoginController {

    /**
     * Method to access at the form login with GET Request
     * 
     * @param model Thymeleaf model
     * @param error login errors
     * 
     * @return template login form
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model, @RequestParam Optional<String> error) {
        
        // if the user already connected, redirect to home
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/";
        }
        model.addAttribute("error", error);
        
        return "login";
    }
}
