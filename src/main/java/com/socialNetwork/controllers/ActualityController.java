package com.socialNetwork.controllers;

import com.socialNetwork.model.Actuality;
import com.socialNetwork.repository.ActualityRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to manage the actualities
 * 
 * @author UJM's students
 */

@Controller
public class ActualityController {
        
    @Inject
    private ActualityRepository actualityRep;
    
    @RequestMapping("/actualities")
    public String getActualities(Model m) { 
        
        List<Actuality> actualities =  (List<Actuality>) actualityRep.findAll();
        Actuality act = new Actuality();
        m.addAttribute("actualities",actualities);
        
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
