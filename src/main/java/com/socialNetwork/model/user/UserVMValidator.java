package com.socialNetwork.model.user;

import com.socialNetwork.exception.EmailAlreadyExistException;
import com.socialNetwork.exception.PasswordNotMatchException;
import com.socialNetwork.repository.UserRepository;
import java.util.NoSuchElementException;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/**
 *
 * @author julien
 */
@Component
public class UserVMValidator {
    
    @Inject
    private UserRepository userRepo;
    
    private UserViewModel userVM;
    
    public void validateUserVM(UserViewModel userVM) throws PasswordNotMatchException, EmailAlreadyExistException {
        this.userVM = userVM;  
        validatePasswords();
        validateEmail();
    }
    
    private void validatePasswords() throws PasswordNotMatchException {
        if (!userVM.getPassword().equals(userVM.getPasswordRepeated())) {
            throw new PasswordNotMatchException("The passwords wich you entered are not similar");
        }
    }

    private void validateEmail() throws EmailAlreadyExistException {
        try {
            if (userRepo.findOneByMail(userVM.getEmail()).get() instanceof User) {
                throw new EmailAlreadyExistException("The email which you entered had been already used");
            }
        }catch(NoSuchElementException e) {
        }
    }
}
