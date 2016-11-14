package com.socialNetwork.controllers;

import com.socialNetwork.model.FriendsGroup;
import com.socialNetwork.repository.FriendsGroupRepository;
import com.socialNetwork.viewmodel.FriendsGroupViewModel;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        
        return "groups";
    }
    
    @RequestMapping(value = "/addgroup", method = RequestMethod.POST)
    public String addevent(Model m, @Valid FriendsGroupViewModel group) {
  
        FriendsGroup newGroup = group.parse();
        groupRep.save(newGroup);
            
        return "redirect:/groups";
    }
}
