package com.socialNetwork.security;

import com.socialNetwork.model.User;
import com.socialNetwork.model.UserRole;
import com.socialNetwork.repository.UserRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author
 */
@Component
public class UserService implements UserDetailsService {
    
    @Inject
    UserRepository repo;
    
    public final PasswordEncoder encoder = new BCryptPasswordEncoder();
    
    /**
     * Method to load a user by his login / username
     *
     * @param login
     * 
     * @return User
     * 
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String login) {
        User u = repo.findOne(login);
        if (u == null) {
            throw new UsernameNotFoundException(login);
        }
        return new org.springframework.security.core.userdetails.User(u.getLogin(), u.getDerivedPassword(), u.getRoles());
    }

    public void saveUserComputingDerivedPassword(User u, String rawPassword) {
        setComputingDerivedPassword(u, rawPassword);
        repo.save(u);
    }

    public void setComputingDerivedPassword(User u, String rawPassword) {
        String codedPassword = encoder.encode(rawPassword);
        u.setDerivedPassword(codedPassword);
    }

    public void makeUserAdmin(String username) {
        User u = repo.findOne(username);
        u.getRoles().add(UserRole.ADMIN);
        repo.save(u);
    }

    public List<User> listAllUsers() {
        return repo.findAllByOrderByLogin();
    }    
}