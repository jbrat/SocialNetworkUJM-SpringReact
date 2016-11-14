/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialNetwork.model;

import java.util.List;

/**
 * Interface EventRep to manage the events
 * @author UJM's students
 */
public interface EventRep {
    
    public List<Event> findAll();
    public void addEvent(Event event);
    
}
