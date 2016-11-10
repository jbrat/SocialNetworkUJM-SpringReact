package com.socialNetwork.controllers;

import com.socialNetwork.model.Event;
import com.socialNetwork.repository.EventRepository;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to manage the events
 * 
 * @author UJM's students
 */

@Controller
public class EventController {
        
    @Inject
    private EventRepository eventRep;
    /**
     * Method to join the events page
     * @return template
     */
    
     @RequestMapping("/events") 
    // use @RequestBody to return String format
    public String home(
            Model model,
            Event event) {
     
        model.addAttribute("events", eventRep.findAll());
        return "events";
    }
    
    @RequestMapping("/addevents")
    public String addevents(
            Model m,
            @Valid Event event) {
        
        eventRep.save(event);
        return "redirect:/events";
    }
}