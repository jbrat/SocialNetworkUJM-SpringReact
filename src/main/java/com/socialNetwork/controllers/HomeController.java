/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialNetwork.controllers;

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
     * @param model for thymeleaf
     * 
     * @return template
     */
    @RequestMapping("/") 
    public String home(Model model) {
        return "index";
    }
}
