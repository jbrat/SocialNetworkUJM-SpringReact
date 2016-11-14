package com.socialNetwork.controllers;

import com.socialNetwork.model.Actuality;
import com.socialNetwork.repository.ActualityRepository;
import com.socialNetwork.viewmodel.ActualityViewModel;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller to manage the actualities
 * 
 * @author UJM's students
 */

@Controller
public class ActualityController {
        
    @Inject
    private ActualityRepository actualityRep;
    
    /**
     * Method to join the actualities page
     * 
     * @return String name of template
     */
     @RequestMapping("/actualities") 
    public String home(
            Model model,ActualityViewModel actuality) {
        model.addAttribute("actuality", actuality);
        
        model.addAttribute("actualities", actualityRep.findAll());
        
        return "actualities";
    }
    
    @RequestMapping(value = "/addactuality", method = RequestMethod.POST)
    public String addevent(Model m, @Valid ActualityViewModel actuality) {
  
        Actuality newActuality = actuality.parse();
        actualityRep.save(newActuality);
        
        return "redirect:/actualities";
    }
}
