package com.socialNetwork.controllers;

import com.socialNetwork.model.Information;
import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.repository.InformationRepository;
import com.socialNetwork.utils.AuthentificationTools;
import com.socialNetwork.viewmodel.InformationViewModel;
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
 * Controller to manage the informations
 * 
 * @author UJM's students
 */
@Controller
public class InformationController {
    
        @Inject
        public InformationRepository informationRep;
        
          /**
        * Method to join the actualities page
        * 
        * @return String name of template
        */
        @RequestMapping("/information") 
        public String home(Model model, InformationViewModel information) {
        
        model.addAttribute("information", information);
        model.addAttribute("informations", informationRep.findAll());
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            CurrentUser u = (CurrentUser) auth.getPrincipal();
            model.addAttribute("user", u.getUser());
        }   
        
        return "information";
        }
    
        /**
        * Method to add a actuality to the actualities page
        * 
        * @return String name of template
        */
        @RequestMapping(value = "/addInformation", method = RequestMethod.POST)
        public String addInformation(Model m, @Valid InformationViewModel information) {

            Information newInformation = information.parse(AuthentificationTools.getCurrentUser());
            informationRep.save(newInformation);
        
        return "redirect:/information";
        }

            
         @RequestMapping(value="/deleteInformation", method = RequestMethod.GET)
        public String deleteInformation(@RequestParam("id") Long idInformation) {

            Information info = informationRep.findOne(idInformation);
            if(!AuthentificationTools.getCurrentUserId().equals(info.getPerson().getIdUser())) {
                return "redirect:/";
            }
       
            informationRep.delete(idInformation);
        
            return "redirect:/information";
        
            }
    
            @RequestMapping(value="/updateInformation", method = RequestMethod.GET)
            public String updateInformation(Model model, @RequestParam Long idInformation) {
        
                Information information = informationRep.findOne(idInformation);
                System.out.println(information.getPerson().getIdUser());
                System.out.println(AuthentificationTools.getCurrentUserId());
                if(!information.getPerson().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
                    return "redirect:/information";
                }
        
                model.addAttribute("information", information);
        
                return "updateInformation";
            }
    
            @RequestMapping(value="/updateInformation", method = RequestMethod.POST)
            public String updateInformation(Model model, @Valid InformationViewModel information, @RequestParam Long idInformation) {
        
                Information informationUpdate = informationRep.findOne(idInformation);
                if(!informationUpdate.getPerson().getIdUser().equals(AuthentificationTools.getCurrentUserId())) {
                return "redirect:/information";
                }
        
                informationRep.save(information.update(informationUpdate));

                return "redirect:/information";
            }
}