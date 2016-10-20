package com.SocialNetwork.model;

import com.SocialNetwork.model.FriendsGroup;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository friendsGroup for Persistance
 * 
 * @author UJM's students
 */
public interface FriendsGroupRepository extends CrudRepository<FriendsGroup, Long>{
    
}
