/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialNetwork.controllers;

import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.repository.MessageRepository;
import javax.inject.Inject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to manage the chat messages
 *
 * @author UJM's students
 */
@Controller
public class MessageController {
    
    @Inject
    private MessageRepository messageRep; 
    
    @RequestMapping("/messages")
    public String getMessages(Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
           CurrentUser u = (CurrentUser) auth.getPrincipal();
           model.addAttribute("user", u.getUser());
        }   
        return "messages";
    }
    
    @RequestMapping("/getMessageByPerson/{idPerson}")
    public String getMessageByPerson(@PathVariable int idPerson) {
        return "index";
    }
    
    @RequestMapping("/deleteMessage/{idMessage}")
    public String deleteMessage(@PathVariable int idMessage) {
        return "index";
    }
    
}
