/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialNetwork.controllers;

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
