package com.socialNetwork.repository;

import com.SocialNetwork.model.Actuality;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface actuality for persistance
 * 
 * @author UJM's students
 */
public interface ActualityRepository extends CrudRepository<Actuality, Serializable>{
    
}
