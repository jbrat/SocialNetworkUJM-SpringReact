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
        
    /**
     * The actuality repository to persist in Database
     */
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
        
        // If the user is connected, set the user variable in template
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
    
    /**
     * Method to delete an actuality with his ID
     * 
     * @param idActuality The id of the actuality which want to be deleted
     * 
     * @return a redirect link for actualities page
     */
    @RequestMapping(value="/deleteActuality", method = RequestMethod.GET)
    public String deleteActuality(@RequestParam("id") long idActuality) {

        Actuality actu = actualityRep.findOne(idActuality);
        if(!AuthentificationTools.getCurrentUserId().equals(actu.getPerson().getIdUser())) {
            return "redirect:/actualities";
        }
       
        actualityRep.delete(idActuality);
        
        return "redirect:/actualities";
        
    }
    
    /**
     * Method to update an actuality, this method load the actuality in a form template
     * 
     * @param model Thymeleaf model 
     * @param id the actuality id
     * 
     * @return the template form for update actuality
     */
    @RequestMapping(value="/updateActuality", method = RequestMethod.GET)
    public String updateActuality(Model model, @RequestParam Long id) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            CurrentUser u = (CurrentUser) auth.getPrincipal();
            model.addAttribute("user", u.getUser());
        }  
        
        Actuality actuality = actualityRep.findOne(id);
        if(!actuality.getPerson().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
            return "redirect:/actualities";
        }
        model.addAttribute("actuality", actuality);
        
        return "updateActuality";
    }
    
    /**
     * Method to update an actuality with a POST Request
     * 
     * @param model Thymeleaf model
     * @param actuality the actuality ViewModel which have been POST with the form to load model
     * @param idActuality the actuality id
     * 
     * @return a redirection to actualities page when it update
     */
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
