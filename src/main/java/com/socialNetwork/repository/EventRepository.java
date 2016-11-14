package com.socialNetwork.repository;

import com.socialNetwork.model.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository event for persistance
 * 
 * @author UJM's students
 */
public interface EventRepository extends CrudRepository<Event, Long> {}
