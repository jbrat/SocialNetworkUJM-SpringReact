package com.SocialNetwork.model;

import com.SocialNetwork.model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository person for the persistance
 * 
 * @author UJM's students
 */
public interface PersonRepository extends CrudRepository<Person, Long>{
    
}
