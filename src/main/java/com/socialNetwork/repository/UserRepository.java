package com.socialNetwork.repository;

import com.socialNetwork.model.user.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository User for the persistance
 * 
 * @author UJM's students
 */
public interface UserRepository extends CrudRepository<User, Long>{
    /**
     * Method to find a user with his email in database
     * 
     * @param email
     * 
     * @return User
     */
    Optional<User> findOneByEmail(String email);
}
