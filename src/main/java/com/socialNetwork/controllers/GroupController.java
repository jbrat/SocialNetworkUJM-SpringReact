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
    @Inject
    private FriendsGroupRepository groupRep;
        
    @Inject
    private UserRepository userRep;
    
    
    /**
     * Method to join the groups page
     * 
     * @return String name of template
     */
    @RequestMapping("/groups") 
    public String home( Model model, FriendsGroupViewModel group) {
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
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String addGroup(Model m, @Valid FriendsGroupViewModel group) {

        FriendsGroup newGroup = group.parse(AuthentificationTools.getCurrentUser(), (List<User>) userRep.findAll());
      ;
        groupRep.save(newGroup);  
        
        return "redirect:/groups";
    }
    
    @RequestMapping("/deleteGroup")
    public String deleteGroup(@RequestParam Long id) {
        
        FriendsGroup group = groupRep.findOne(id);
        if(!group.getOwner().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
            return "redirect:/groups";
        }
        
        groupRep.delete(group);
        
        return "redirect:/groups";
    }
    
    @RequestMapping("/updateGroup")
    public String updateGroup(Model model, @RequestParam Long id) {
        
        FriendsGroup group = groupRep.findOne(id);
        if(!group.getOwner().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
            return "redirect:/groups";
        }
        
        model.addAttribute("group", new FriendsGroupViewModel(group));
        
        return "updateGroup";
    }
    
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
