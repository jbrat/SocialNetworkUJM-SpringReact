package com.socialNetwork.controllers;

import com.socialNetwork.model.Event;
import com.socialNetwork.repository.EventRepository;
import com.socialNetwork.viewmodel.EventViewModel;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        
        return "events";
    }
    
    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    public String addevent(Model m, @Valid EventViewModel event) {
  
        Event newEvent = event.parse();
        eventRep.save(newEvent);
        
        return "redirect:/events";
    }
}