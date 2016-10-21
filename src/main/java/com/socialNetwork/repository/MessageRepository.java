package com.socialNetwork.repository;

import com.socialNetwork.model.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository message for the persistance
 * 
 * @author UJM's students
 */
public interface MessageRepository extends CrudRepository<Message, Long>{
    
}
