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
    List<Message> findMessageByReceiver(@Param("receiver") String receiver);
}
