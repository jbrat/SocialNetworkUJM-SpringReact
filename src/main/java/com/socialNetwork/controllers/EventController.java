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
 * 
 */
@Controller
public class EventController {
        
    /**
     * The event repository to persist in Database
     */
    @Inject
    public EventRepository eventRep;
    
    /**
     * Method to join the events page
     * 
     * @param model Thymeleaf model
     * @param event the view model event to interact with model and view
     * 
     * @return String name of template
     */
    @RequestMapping("/events") 
    public String home(Model model, EventViewModel event) {
        
        model.addAttribute("event", event);
        model.addAttribute("events", eventRep.findAll());
        
        // load the user if he is authenticated
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
     * @param model Thymeleaf model
     * @param event the viewModel which have been post by form
     * 
     * @return String name of template
     */
    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    public String addEvent(Model m, @Valid EventViewModel event) {

        Event newEvent = event.parse(AuthentificationTools.getCurrentUser());
        eventRep.save(newEvent);
        
        return "redirect:/events";
    }
    
    /**
     * Method to delete an event with his ID
     * 
     * @param idEvent 
     * 
     * @return redirection to page of events
     */
    @RequestMapping(value="/deleteEvent")
    public String deleteEvent(@RequestParam("id") long idEvent) {
      
        Event actu = eventRep.findOne(idEvent);
        
        // if you try to delete an other events like your when you're connected
        if(!AuthentificationTools.getCurrentUserId().equals(actu.getOwner().getIdUser())) {
            return "redirect:/events";
        } 
        eventRep.delete(idEvent);
        
        return "redirect:/events";
    }
    
    /**
     * Method to load an update form with an event
     * 
     * @param model Thymeleaf model
     * @param idEvent 
     * 
     * @return template update with the event loaded
     */
    @RequestMapping(value="/updateEvent", method = RequestMethod.GET)
    public String updateEvent(Model model, @RequestParam("id") long idEvent) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            CurrentUser u = (CurrentUser) auth.getPrincipal();
            model.addAttribute("user", u.getUser());
        }  
        
        Event event = eventRep.findOne(idEvent);
        if(!event.getOwner().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
            return "redirect:/events";
        }
        
        model.addAttribute("event", event);

        return "updateEvent"; 
    }
    
    /**
     * Method to update an event with a POST request
     * 
     * @param m Thymeleaf model
     * @param event viewModel which have been Post with form
     * @param idEvent 
     * 
     * @return redirection to events page
     */              
    @RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
    public String updateEventPost(Model m, @Valid EventViewModel event, @RequestParam("idEvent") long idEvent) {
            
        Event updateEvent = eventRep.findOne(idEvent);
        if(!updateEvent.getOwner().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
            return "redirect:/events";
        }
        
        eventRep.save(event.update(updateEvent));
            
        return "redirect:/events";
    }  
}