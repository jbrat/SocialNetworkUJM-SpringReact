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
    
    /**
     * Method to join the messages page
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
    /**
     * Method to secure the API messages URL
     * 
     * @return String name of template
     */
    @RequestMapping("/api/messages")
    public String secureAPIMessages() {
        return "redirect:/index";
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
