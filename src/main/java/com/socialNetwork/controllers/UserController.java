package com.socialNetwork.controllers;

import com.socialNetwork.exception.EmailAlreadyExistException;
import com.socialNetwork.exception.PasswordNotMatchException;
import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.model.user.User;
import com.socialNetwork.model.user.UserVMValidator;
import com.socialNetwork.model.user.UserViewModel;
import com.socialNetwork.repository.UserRepository;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    
    @Inject
    private UserVMValidator userVMValidator;
    
    @Inject
    private UserRepository userRepo;
    
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String getUserCreatePage(Model model, UserViewModel userVM) {
        model.addAttribute("user", userVM);
 
        return "register";
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
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
    
     @RequestMapping(value="/deleteProfil")
    public String deleteProfil(@RequestParam("id") long idUser) {
      
      //userRepo.findOne(idUser);
        userRepo.delete(idUser);
            return "redirect:/profil";
        
    }
    
    /**@RequestMapping(value="/profil/delete/{idUser}", method=RequestMethod.GET)
    public String delete(@PathVariable long idUser) {

    //userRepo.delete(idUser);
    userRepo.delete(userRepo.findOne(idUser));

    return "redirect:/";

}**/
    
    
    @RequestMapping("/updateprofil/{idUser}")
    public String updateProfil(@PathVariable int idUser) {
        
        return "redirect:/profil";
    }
}