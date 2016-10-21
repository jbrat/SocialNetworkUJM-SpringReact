/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialNetwork.controllers;

import com.socialNetwork.repository.MessageRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
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
    
    @RequestMapping("/getMessages")
    public String getMessages() {
        return "index";
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
