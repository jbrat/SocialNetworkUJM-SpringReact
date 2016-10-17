package com.socialNetwork.repository;

import com.SocialNetwork.model.Person;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository person for the persistance
 * 
 * @author UJM's students
 */
public interface PersonRepository extends CrudRepository<Person, Serializable>{
    
}
