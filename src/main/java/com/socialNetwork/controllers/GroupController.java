package com.socialNetwork.controllers;

import com.socialNetwork.model.FriendsGroup;
import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.repository.FriendsGroupRepository;
import com.socialNetwork.viewmodel.FriendsGroupViewModel;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller to manage the groups
 * 
 * @author UJM's students
 */
@Controller
public class GroupController {
    @Inject
    public FriendsGroupRepository groupRep;
    
    /**
     * Method to join the groups page
     * 
     * @return String name of template
     */
     @RequestMapping("/groups") 
    public String home(
            Model model,FriendsGroupViewModel group) {
        model.addAttribute("group", group);
        
        model.addAttribute("groups", groupRep.findAll());
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
           CurrentUser u = (CurrentUser) auth.getPrincipal();
           model.addAttribute("user", u.getUser());
        }   
        
        return "groups";
    }
    
    /**
     * Method to add a group the groups page
     * 
     * @return String name of template
     */
    @RequestMapping(value = "/addgroup", method = RequestMethod.POST)
    public String addGroup(Model m, @Valid FriendsGroupViewModel group) {
  
        FriendsGroup newGroup = group.parse();
        groupRep.save(newGroup);
            
        return "redirect:/groups";
    }
    
    @RequestMapping("/deletegroup/{idGroup}")
    public String deleteGroup(@PathVariable int idGroup) {
        
        return "redirect:/groups";
    }
    
    @RequestMapping("/updategroup/{idGroup}")
    public String updateGroup(@PathVariable int idGroup) {
        
        return "redirect:/groups";
    }
}
