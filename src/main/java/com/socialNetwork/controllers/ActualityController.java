package com.socialNetwork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to manage the actualities
 * 
 * @author UJM's students
 */

@Controller
public class ActualityController {
        
    @RequestMapping("/actualities")
    public String getActualities() {
        return "actualities";
    }
    
    @RequestMapping("/addActualities")
    public String addActualities() {
        return "index";
    }
    
    @RequestMapping("/deleteActualities/{idActuality}")
    public String deleteActualities(@PathVariable int idActuality) {
        return "index";
    }
    
    @RequestMapping("/actuality/{idActuality}")
    public String getActuality(@PathVariable int idActuality) {
        return "index";
    }
}
