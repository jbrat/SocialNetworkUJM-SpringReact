package com.socialNetwork.repository;

import com.SocialNetwork.model.Message;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository message for the persistance
 * 
 * @author UJM's students
 */
public interface MessageRepository extends CrudRepository<Message, Serializable>{
    
}
