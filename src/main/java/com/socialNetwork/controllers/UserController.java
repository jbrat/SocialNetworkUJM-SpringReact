package com.socialNetwork.controllers;

import com.socialNetwork.exception.EmailAlreadyExistException;
import com.socialNetwork.exception.PasswordNotMatchException;
import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.model.user.User;
import com.socialNetwork.model.user.UserVMValidator;
import com.socialNetwork.model.user.UserViewModel;
import com.socialNetwork.repository.UserRepository;
import com.socialNetwork.utils.AuthentificationTools;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to manage the users
 * 
 * @author UJM's students
 */
@Controller
public class UserController {
    
    /**
     * Validator for the form user
     */
    @Inject
    private UserVMValidator userVMValidator;
    
    /**
     * User repository to persist in database
     */
    @Inject
    private UserRepository userRepo;
    
    /**
     * Method to load the register form
     * 
     * @param model Thymeleaf model
     * @param userVM the viewModel to load model from view
     * 
     * @return String name of template
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getUserCreatePage(Model model, UserViewModel userVM) {
        model.addAttribute("user", userVM);
        
        return "register";
    }

    /**
     * Method to add a user from POST request
     * 
     * @param model Thymeleaf model
     * @param user ViewModel to load the model from the view request post
     * @param bindingResult
     * 
     * @return redirection to login form
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleUserCreate(Model model, @Valid UserViewModel user, BindingResult bindingResult) {
        
        try {
            userVMValidator.validateUserVM(user);
            User u = user.parse();
            userRepo.save(user.parse());
            
        } catch (PasswordNotMatchException | EmailAlreadyExistException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("user", user);
            
            return "register";
        }
        
        return "redirect:/login";
    }
    
    /**
     * Method to load the profil page
     * 
     * @param model Thymeleaf model
     * 
     * @return String name of template
     */
    @RequestMapping(value="/profil", method = RequestMethod.GET)
    public String getProfil(Model model) {

        // if user is connected load the profil
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            CurrentUser u = (CurrentUser) auth.getPrincipal();
            model.addAttribute("user", u.getUser());

            return "profil";
        } else {
            // else redirect to login form
            return "redirect:/login";
        }
    }
    
    /**
     * Method to load the form update user
     * 
     * @param model Thymeleaf model
     * @param idUser 
     * 
     * @return template update User with user loaded by id
     */
    @RequestMapping(value="/updateUser", method = RequestMethod.GET)
    public String updateUser(Model model, @RequestParam("id") long idUser) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            CurrentUser u = (CurrentUser) auth.getPrincipal();
            model.addAttribute("user", u.getUser());
        } 
       
        if(!AuthentificationTools.getCurrentUserId().equals(idUser)) {
            return "redirect:/profil";
        }
        model.addAttribute("user", AuthentificationTools.getCurrentUser());
        
        return "updateProfil";
    }
    
    /**
     * Method to update a profil with POST Request
     * 
     * @param user ViewModel to load the model user from the post request view
     * 
     * @return redirection to profil
     */
    @RequestMapping(value="/updateProfil", method = RequestMethod.POST)
    public String updateUser(@Valid UserViewModel user) {
  
        userRepo.save(user.update(AuthentificationTools.getCurrentUser()));
        
        return "redirect:/profil";
    }
    
    /**
     * Method to load the update password form 
     * 
     * @param model Thymeleaf model
     * 
     * @return template form update password with password of actual user loaded
     */
    @RequestMapping(value="/updatePassword", method = RequestMethod.GET)
    public String updatePassword(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
           CurrentUser u = (CurrentUser) auth.getPrincipal();
           model.addAttribute("user", u.getUser());   
           
           return "updatePassword";
        } else {
           return "redirect:/login";
        }    
    }
    
    /**
     * Method to update password for the actual user with POST Request
     * 
     * @param model Thymeleaf model
     * @param password
     * @param passwordRepeated
     * 
     * @return redirection to profil when password is updated
     */
    @RequestMapping(value="/UpdatePassword", method = RequestMethod.POST)
    public String updatePassword(Model model, @RequestParam String password, @RequestParam String passwordRepeated) {
  
        if(!password.equals(passwordRepeated)) {
            model.addAttribute("error", "The two passwords isn't similar");
            return "updatePassword";
        }
        
        User u = AuthentificationTools.getCurrentUser();
        u.setPasswordHash(new BCryptPasswordEncoder().encode(password));
        userRepo.save(u);
        
        return "redirect:/profil";
    }
    
    /**
     * Method to delete a user with his ID
     * 
     * @param idUser
     * 
     * @return redirection to disconnect the user when he was deleted
     */
    @RequestMapping(value="/deleteUser")
    public String deleteUser(@RequestParam("id") long idUser) {

        if(!AuthentificationTools.getCurrentUserId().equals(idUser)) {
            return "redirect:/";
        }
        
        userRepo.delete(idUser);
        
        return "redirect:/logout";  
    }
    
    /**
     * Method to get the current User from GET Request
     * 
     * @return User format JSON
     */
    @RequestMapping(value="/getCurrentUser", method = RequestMethod.GET)
    public @ResponseBody User getCurrentUser() {
        return AuthentificationTools.getCurrentUser();    
    }
    
    /**
     * Method to get the users from a pattern name, use for autocompletion
     * 
     * @return list of nameUser format JSON
     */
    @RequestMapping(value="/autocomplete/users", method = RequestMethod.GET)
    public @ResponseBody List<String> autocompleteUsers(@RequestParam("query") String userName) {
        List<String> listUsers = new ArrayList<String>();

        for(User u : userRepo.findAll()) {
            listUsers.add(u.getFirstName() + " " + u.getLastName());
        }        
        
        List<String> result = listUsers.stream().filter(t -> t.contains(userName)).collect(Collectors.toList());
        
        return result;      
    }
}