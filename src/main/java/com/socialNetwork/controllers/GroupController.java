package com.socialNetwork.controllers;

import com.socialNetwork.repository.FriendsGroupRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to manage the groups
 * 
 * @author UJM's students
 */
@Controller
public class GroupController {

    @Inject 
    private FriendsGroupRepository groupRep;
    
    @RequestMapping("/getGroups")
    public String getGroups() {
        return "index";
    }
    
    @RequestMapping("/addGroup")
    public String addGroup() {
        return "index";
    }
    
    @RequestMapping("/deleteGroup/{idGroup}")
    public String deleteGroup(@PathVariable int idGroup) {
        return "index";
    }
}
