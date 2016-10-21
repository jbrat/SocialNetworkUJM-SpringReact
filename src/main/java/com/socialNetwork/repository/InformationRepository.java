package com.socialNetwork.repository;

import com.socialNetwork.model.Information;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository information for the persistance
 * 
 * @author UJM's students
 */
public interface InformationRepository extends CrudRepository<Information, Long>{
    
}
