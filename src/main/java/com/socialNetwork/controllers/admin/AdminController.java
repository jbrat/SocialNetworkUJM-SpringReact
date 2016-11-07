package com.socialNetwork.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Admin controller
 *  
 * @author UJM's Students
 */
@Controller
public class AdminController {
    
    @RequestMapping("/admin/index") 
    public String indexAdmin() {
        return "admin/index";
    }
}
