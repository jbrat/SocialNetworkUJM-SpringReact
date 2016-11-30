package com.socialNetwork.model.user;

import com.socialNetwork.exception.EmailAlreadyExistException;
import com.socialNetwork.exception.PasswordNotMatchException;
import com.socialNetwork.repository.UserRepository;
import java.util.NoSuchElementException;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/**
 * Class to validate the user Form informations
 * 
 * @author UJM's students
 */
@Component
public class UserVMValidator {
    
    /**
     * User repository to verify if the email exist in database
     */
    @Inject
    private UserRepository userRepo;
    
    /**
     * User view model 
     */
    private UserViewModel userVM;
    
    /**
     * Method used to validate a user Form
     * 
     * @param userVM the viewModel which have been load by view
     * 
     * @throws PasswordNotMatchException
     * @throws EmailAlreadyExistException 
     */
    public void validateUserVM(UserViewModel userVM) throws PasswordNotMatchException, EmailAlreadyExistException {
        this.userVM = userVM;  
        validatePasswords();
        validateEmail();
    }
    
    /**
     * Method to check if the two passwords match in form register & updatePassword
     * 
     * @throws PasswordNotMatchException 
     */
    private void validatePasswords() throws PasswordNotMatchException {
        if (!userVM.getPassword().equals(userVM.getPasswordRepeated())) {
            throw new PasswordNotMatchException("The passwords wich you entered are not similar");
        }
    }

    /**
     * Method to check if the email of a new user allready exist in database
     * 
     * @throws EmailAlreadyExistException 
     */
    private void validateEmail() throws EmailAlreadyExistException {
        try {
            if (userRepo.findOneByEmail(userVM.getEmail()).get() instanceof User) {
                throw new EmailAlreadyExistException("The email which you entered had been already used");
            }
        }catch(NoSuchElementException e) {}
    }
}
