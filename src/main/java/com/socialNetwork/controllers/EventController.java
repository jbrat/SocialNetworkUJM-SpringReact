package com.socialNetwork.controllers;

import com.socialNetwork.model.Event;
import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.repository.EventRepository;
import com.socialNetwork.utils.AuthentificationTools;
import com.socialNetwork.viewmodel.EventViewModel;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String home(Model model, EventViewModel event) {
        
        model.addAttribute("event", event);
        model.addAttribute("events", eventRep.findAll());
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            CurrentUser u = (CurrentUser) auth.getPrincipal();
            model.addAttribute("user", u.getUser());
        }   
        
        return "events";
    }
    
    /**
     * Method to add a event the events page
     * 
     * @return String name of template
     */
    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    public String addEvent(Model m, @Valid EventViewModel event) {

        Event newEvent = event.parse(AuthentificationTools.getCurrentUser());
        eventRep.save(newEvent);
        
        return "redirect:/events";
    }
    
     @RequestMapping(value="/deleteEvent")
    public String deleteEvent(@RequestParam("id") long idEvent) {
      
        Event actu = eventRep.findOne(idEvent);
        
        if(!AuthentificationTools.getCurrentUserId().equals(actu.getOwner().getIdUser())) {
            return "redirect:/";
        }
        
        eventRep.delete(idEvent);
        
        return "redirect:/events";
  
    }
    
    @RequestMapping(value="/editEvent")
    public String editEvent(@RequestParam("id") long idEvent) {
            Event event = eventRep.findOne(idEvent);
            
            return "redirect:/eventUpdate";
        
    }
    
                
    @RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
        public String updateEvent(Model m, @Valid EventViewModel event) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser u = (CurrentUser) auth.getPrincipal();
        
        Event newEvent = event.parse(u.getUser());
        eventRep.save(newEvent);
            
            return "redirect:/events";
            }
        
    /**@RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
        public String updateEvent(@RequestParam("id") long idEvent) {
            Event event = eventRep.findOne(idEvent);
            eventRep.save(event);
            
            return "redirect:/events";
        
    }  **/ 

        
}