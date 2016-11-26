package com.socialNetwork.controllers;

import com.socialNetwork.model.Actuality;
import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.repository.ActualityRepository;
import com.socialNetwork.utils.AuthentificationTools;
import com.socialNetwork.viewmodel.ActualityViewModel;
import javassist.NotFoundException;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String home(Model model, ActualityViewModel actuality) {
        
        model.addAttribute("actuality", actuality);
        model.addAttribute("actualities", actualityRep.findAll());
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            CurrentUser u = (CurrentUser) auth.getPrincipal();
            model.addAttribute("user", u.getUser());
        }   
        
        return "actualities";
    }
    
    /**
     * Method to add a actuality to the actualities page
     * 
     * @return String name of template
     */
    @RequestMapping(value = "/addactuality", method = RequestMethod.POST)
    public String addActuality(Model m, @Valid ActualityViewModel actuality) {

        Actuality newActuality = actuality.parse(AuthentificationTools.getCurrentUser());
        actualityRep.save(newActuality);
        
        return "redirect:/actualities";
    }
    
    @RequestMapping(value="/deleteActuality")
    public String deleteEvent(@RequestParam("id") long idActuality) {

        Actuality actu = actualityRep.findOne(idActuality);
        if(!AuthentificationTools.getCurrentUserId().equals(actu.getPerson().getIdUser())) {
            return "redirect:/";
        }
       
        actualityRep.delete(idActuality);
        
        return "redirect:/actualities";
        
    }
    
    
    @RequestMapping("/updateactuality/{idActuality}")
    public String updateActuality(@PathVariable int idActuality) {
        
        return "redirect:/actualities";
    }
}
