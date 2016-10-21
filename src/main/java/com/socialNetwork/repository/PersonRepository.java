package com.socialNetwork.repository;

import com.socialNetwork.model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository person for the persistance
 * 
 * @author UJM's students
 */
public interface PersonRepository extends CrudRepository<Person, Long>{
    
}
