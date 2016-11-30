package com.socialNetwork.repository;

import com.socialNetwork.model.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository message for the persistance
 * 
 * @author UJM's students
 */
public interface MessageRepository extends CrudRepository<Message, Long>{
    /**
     * Method to get all the messages for a receiver
     * 
     * @param receiver
     * 
     * @return list of messages
     */
    List<Message> findMessageByReceiver(@Param("receiver") String receiver);
    
    /**
     * Method to get all the messages for a sender
     * 
     * @param sender
     * 
     * @return list of messages
     */
    List<Message> findMessageBySender(@Param("sender") String sender);
}
