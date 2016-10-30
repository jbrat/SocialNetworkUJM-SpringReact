package com.socialNetwork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to manage the authentication
 * 
 * @author UJM's students
 */
@Controller
public class AuthenticationController {
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}


