package com.socialNetwork.repository;

import com.socialNetwork.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository person for the persistance
 * 
 * @author UJM's students
 */
public interface UserRepository extends CrudRepository<User, String>{
    
    public List<User> findAllByOrderByLogin();
}
