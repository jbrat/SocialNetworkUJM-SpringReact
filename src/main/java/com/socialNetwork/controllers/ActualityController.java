package com.socialNetwork.controllers;

import com.socialNetwork.model.Actuality;
import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.repository.ActualityRepository;
import com.socialNetwork.utils.AuthentificationTools;
import com.socialNetwork.viewmodel.ActualityViewModel;
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
    @RequestMapping(value = "/addActuality", method = RequestMethod.POST)
    public String addActuality(Model m, @Valid ActualityViewModel actuality) {

        Actuality newActuality = actuality.parse(AuthentificationTools.getCurrentUser());
        actualityRep.save(newActuality);
        
        return "redirect:/actualities";
    }
    
    @RequestMapping(value="/deleteActuality", method = RequestMethod.GET)
    public String deleteEvent(@RequestParam("id") long idActuality) {

        Actuality actu = actualityRep.findOne(idActuality);
        if(!AuthentificationTools.getCurrentUserId().equals(actu.getPerson().getIdUser())) {
            return "redirect:/";
        }
       
        actualityRep.delete(idActuality);
        
        return "redirect:/actualities";
        
    }
    
    @RequestMapping(value="/updateActuality", method = RequestMethod.GET)
    public String updateActuality(Model model, @RequestParam Long id) {
        
        Actuality actuality = actualityRep.findOne(id);
        System.out.println(actuality.getPerson().getIdUser());
        System.out.println(AuthentificationTools.getCurrentUserId());
        if(!actuality.getPerson().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
            return "redirect:/actualities";
        }
        
        model.addAttribute("actuality", actuality);
        
        return "updateActuality";
    }
    
    @RequestMapping(value="/updateActuality", method = RequestMethod.POST)
    public String updateActualityPost(Model model, @Valid ActualityViewModel actuality, @RequestParam Long idActuality) {
        
        Actuality actualityUpdate = actualityRep.findOne(idActuality);
        if(!actualityUpdate.getPerson().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
            return "redirect:/actualities";
        }
        
        actualityRep.save(actuality.update(actualityUpdate));

        return "redirect:/actualities";
    }
    
}
