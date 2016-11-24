package com.socialNetwork.controllers;

import com.socialNetwork.model.Event;
import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.repository.EventRepository;
import com.socialNetwork.viewmodel.EventViewModel;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller to manage the events
 * 
 * @author UJM's students
 */
@Controller
public class EventController {
        
    @Inject
    public EventRepository eventRep;
    
    /**
     * Method to join the events page
     * 
     * @return String name of template
     */
    @RequestMapping("/events") 
    public String home(
            Model model,EventViewModel event) {
        model.addAttribute("event", event);
        
        model.addAttribute("events", eventRep.findAll());
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
           CurrentUser u = (CurrentUser) auth.getPrincipal();
           model.addAttribute("user", u.getUser());
        }   
        
        return "events";
    }
    
    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    public String addEvent(Model m, @Valid EventViewModel event) {
  
        Event newEvent = event.parse();
        eventRep.save(newEvent);
        
        return "redirect:/events";
    }
    
    @RequestMapping("/deleteevents/{idEvent}")
    public String deleteEvent(@PathVariable int idEvent) {
        
        return "redirect:/events";
    }

    @RequestMapping("/updateevents/{idEvent}")
    public String updateEvent(@PathVariable int idEvent) {
        
        return "redirect:/events";
    }
}