package com.socialNetwork.repository;

import com.socialNetwork.model.user.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository person for the persistance
 * 
 * @author UJM's students
 */
public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findOneByMail(String email);
}
