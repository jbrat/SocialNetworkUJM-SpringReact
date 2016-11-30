package com.socialNetwork.viewmodel;

import com.socialNetwork.model.Actuality;
import com.socialNetwork.model.user.User;
import com.socialNetwork.repository.UserRepository;
import java.util.Date;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

/**
 * Class ActualityViewModel to load actuality model from the view
 * 
 * @author UJM's students
 */
public class ActualityViewModel {
    
    /**
     * User repository to get informations of users in the database
     */
    @Inject
    private UserRepository userRepo;
    
    /**
     * The title of the actuality
     */
    @NotNull
    private String title;
    
    /**
     * The message of the actuality
     */
    @NotNull
    private String message;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
     /**
     * Method to load a Actuality model from view form
     * 
     * @param User the owner of the actuality
     * 
     * @return Actuality
     */
    public Actuality parse(User u) {
        
        Actuality actuality = new Actuality(u, 
            this.getTitle(), 
            this.getMessage(), 
            new Date());
        
            return actuality;
    }
    
    /**
     * Method to update an actuality from the view
     * 
     * @param actu actuality to be modify
     * 
     * @return actuality which had been modifyed
     */
    public Actuality update(Actuality actu) {
        if(!actu.getTitle().equals(getTitle())) {
            actu.setTitle(getTitle());
        } else if(!actu.getMessage().equals(getMessage())) {
            actu.setMessage(getMessage());
        }
        
        return actu;
    }
    
}
