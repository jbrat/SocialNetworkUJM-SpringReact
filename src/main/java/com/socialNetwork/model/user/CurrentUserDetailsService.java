package com.socialNetwork.model.user;


import com.socialNetwork.repository.UserRepository;
import javax.inject.Inject;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service for load the current user details 
 *
 * @author UJM's students
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {
    /**
     * The user Repository to persist in Database
     */
    @Inject
    private  UserRepository userRepo;
    
    /**
     * Load a user by his email, Override the method of UserDetailsService
     * 
     * @param email use the email of user to identify him
     * 
     * @return the current user
     * 
     * @throws UsernameNotFoundException 
     */
    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
        return new CurrentUser(user);
    }
}
