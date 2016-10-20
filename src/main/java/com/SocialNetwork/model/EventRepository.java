package com.SocialNetwork.model;

import com.SocialNetwork.model.Event;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository event for persistance
 * 
 * @author UJM's students
 */
public interface EventRepository extends CrudRepository<Event, Long>{
    
}
