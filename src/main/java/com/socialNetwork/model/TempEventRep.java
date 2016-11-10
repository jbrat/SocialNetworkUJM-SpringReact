package com.socialNetwork.model;

import com.socialNetwork.model.user.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Class TempEventRep which implement interface for add & print all event
 * @author UJM's students
 */

@Component
public class TempEventRep implements EventRep {

    public List<Event> listEvents = new ArrayList<>();
   
    /**
     * Init with data test
     */
    public TempEventRep() {
        User name = null;
        Date aujourdhui = new Date();
        listEvents.add(new Event(name,"nom","fete",aujourdhui));
    }
    
    /**
     * Method to get all Events
     * @return 
     */
    public List<Event> findAll() {
        return listEvents;
    }

    /**
     * Method to add a events in the list
     * @param event
     */
    public void addEvent(Event event) {
        listEvents.add(event);
    }
    
}

