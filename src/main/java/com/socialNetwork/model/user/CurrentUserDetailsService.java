package com.socialNetwork.model.user;


import com.socialNetwork.repository.UserRepository;
import javax.inject.Inject;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {
    @Inject
    private  UserRepository userRepo;
    
    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
        return new CurrentUser(user);
    }
}
