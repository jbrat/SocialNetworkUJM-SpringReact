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
    
    @RequestMapping("/events")
    public String getEvents() {
        return "events";
    }
    
    @RequestMapping("/addEvents")
    public String addEvents( 
        Model m,
        @Valid Event event) {
        eventRep.save(event);
        return "addEvents";
    }
    
    @RequestMapping("/deleteEvents/{idEvent}")
    public String deleteEvents(@PathVariable int idEvent) {
        return "index";
    }
    
    @RequestMapping("/event/{idEvent}")
    public String getEvent(@PathVariable int idEvent) {
        return "index";
    }
}
