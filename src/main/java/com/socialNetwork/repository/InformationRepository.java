package com.socialNetwork.repository;

import com.SocialNetwork.model.Information;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository information for the persistance
 * 
 * @author UJM's students
 */
public interface InformationRepository extends CrudRepository<Information, Serializable>{
    
}
