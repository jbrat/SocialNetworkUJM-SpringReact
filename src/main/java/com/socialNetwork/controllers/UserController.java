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
    
    @Inject
    private UserVMValidator userVMValidator;
    
    @Inject
    private UserRepository userRepo;
    
    /**
     * Method to join the users page
     * 
     * @return String name of template
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getUserCreatePage(Model model, UserViewModel userVM) {
        model.addAttribute("user", userVM);
        
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleUserCreate(Model model, @Valid UserViewModel user, BindingResult bindingResult) {
        
        try {
            userVMValidator.validateUserVM(user);
            User u = user.parse();
            userRepo.save(user.parse());

            
        } catch (PasswordNotMatchException | EmailAlreadyExistException ex) {
            System.out.println(ex.getMessage());
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("user", user);
            
            return "register";
        }
        
        return "redirect:/login";
    }
    
    /**
     * Method to join the login page
     * 
     * @return String name of template
     */
    @RequestMapping(value="/profil", method = RequestMethod.GET)
    public String getProfil(Model model) {
             
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
           CurrentUser u = (CurrentUser) auth.getPrincipal();
           model.addAttribute("user", u.getUser());
           
           return "profil";
        } else {
            return "redirect:/login";
        }
    }
    
    @RequestMapping(value="/updateUser", method = RequestMethod.GET)
    public String updateUser(Model model, @RequestParam("id") long idUser) {
       
        if(!AuthentificationTools.getCurrentUserId().equals(idUser)) {
            return "redirect:/profil";
        }
       
        model.addAttribute("user", AuthentificationTools.getCurrentUser());
        
        return "updateProfil";
    }
    
    @RequestMapping(value="/updateProfil", method = RequestMethod.POST)
    public String updateUser(@Valid UserViewModel user) {
  
        userRepo.save(user.update(AuthentificationTools.getCurrentUser()));
        
        return "redirect:/profil";
    }
    
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
    
    @RequestMapping(value="/UpdatePassword", method = RequestMethod.POST)
    public String updatePassword(Model model, @RequestParam String password, @RequestParam String passwordRepeated) {
  
        if(!password.equals(passwordRepeated)) {
            model.addAttribute("error", "The two passwords isn't similar");
            return "updatePassword";
        }
        
        User u = AuthentificationTools.getCurrentUser();
        u.setPasswordHash(new BCryptPasswordEncoder().encode(password));
        userRepo.save(u);
        
        return "redirect:/logout";
    }
    
    @RequestMapping(value="/deleteUser")
    public String deleteUser(@RequestParam("id") long idUser) {

        if(!AuthentificationTools.getCurrentUserId().equals(idUser)) {
            return "redirect:/";
        }
        
        userRepo.delete(idUser);
        
        return "redirect:/logout";
        
    }
    
    /**
     * Method to have the current login User in JSON
     * 
     * @return User format JSON
     */
    @RequestMapping(value="/getCurrentUser", method = RequestMethod.GET)
    public @ResponseBody User getCurrentUser() {
        
        return AuthentificationTools.getCurrentUser();    
    }
    
    /**
     * Method for autocompletes, users
     * 
     * @return HashMap idUser nameUser format JSON
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