package com.socialNetwork.controllers;

import com.socialNetwork.model.FriendsGroup;
import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.model.user.User;
import com.socialNetwork.repository.FriendsGroupRepository;
import com.socialNetwork.repository.UserRepository;
import com.socialNetwork.utils.AuthentificationTools;
import com.socialNetwork.viewmodel.FriendsGroupViewModel;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller to manage the groups
 * 
 * @author UJM's students
 */
@Controller
public class GroupController {
    
    /**
     * The FriendsGroup repository to persist in Database
     */
    @Inject
    private FriendsGroupRepository groupRep;
    
    /**
     * The User repository to get informations about users
     */
    @Inject
    private UserRepository userRep;
    
    
    /**
     * Method to load the groups page
     * 
     * @param model Thymeleaf model
     * @param group viewModel to load the model with the view
     * 
     * @return String name of template
     */
    @RequestMapping("/groups") 
    public String home( Model model, FriendsGroupViewModel group) {
        
        model.addAttribute("group", group);
        model.addAttribute("groups", groupRep.findAll());
        
        // If the user connected, we load it
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            CurrentUser u = (CurrentUser) auth.getPrincipal();
            model.addAttribute("user", u.getUser());
        }   
        
        return "groups";
    }
    
    /**
     * Method to add a group with POST Request
     * 
     * @param m Thymeleaf model
     * @param group viewModel to load the model from view
     * 
     * @return redirection to groups page
     */
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String addGroup(Model m, @Valid FriendsGroupViewModel group) {

        FriendsGroup newGroup = group.parse(AuthentificationTools.getCurrentUser(), (List<User>) userRep.findAll());
        groupRep.save(newGroup);  
        
        return "redirect:/groups";
    }
    
    /**
     * Method to delete a group with an ID
     * 
     * @param id id of the group
     * 
     * @return redirection to groups page
     */
    @RequestMapping("/deleteGroup")
    public String deleteGroup(@RequestParam Long id) {
        
        FriendsGroup group = groupRep.findOne(id);
        if(!group.getOwner().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
            return "redirect:/groups";
        }
        
        groupRep.delete(group);
        
        return "redirect:/groups";
    }
    
    /**
     * Method to load a form with a group to update it by id
     * 
     * @param model Thymeleaf model
     * @param id id of the group
     * 
     * @return template update with the group loaded
     */
    @RequestMapping(value="/updateGroup", method = RequestMethod.GET)
    public String updateGroup(Model model, @RequestParam Long id) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            CurrentUser u = (CurrentUser) auth.getPrincipal();
            model.addAttribute("user", u.getUser());
        }  
        
        FriendsGroup group = groupRep.findOne(id);
        if(!group.getOwner().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
            return "redirect:/groups";
        }
        
        model.addAttribute("group", new FriendsGroupViewModel(group));
        
        return "updateGroup";
    }
    
    /**
     * Method to update a group with a POST Request 
     * 
     * @param model Thymeleaf model
     * @param idGroup id of the group updated
     * @param groupVM ViewModel to load model updated with view
     * 
     * @return redirection to groups page
     */
    @RequestMapping(value="/updateGroup", method = RequestMethod.POST)
    public String updateGroupPost(Model model, @RequestParam Long idGroup, @Valid FriendsGroupViewModel groupVM) {
        
        FriendsGroup group = groupRep.findOne(idGroup);
        if(!group.getOwner().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
            return "redirect:/groups";
        }
        
        groupRep.save(groupVM.update(group));
        
        return "redirect:/groups";
    }
}
