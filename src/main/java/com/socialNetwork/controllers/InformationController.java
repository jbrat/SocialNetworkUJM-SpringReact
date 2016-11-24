package com.socialNetwork.controllers;

import com.socialNetwork.model.Information;
import com.socialNetwork.model.user.CurrentUser;
import com.socialNetwork.repository.InformationRepository;
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
         * Method to join the information page
         * 
         * @return String name of template
        */
        @RequestMapping("/information")
        public String getInformation(
                Model model, InformationViewModel information ) {
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
         * Method to add an information to the informations page
         * 
         * @return String name of template
        */
        @RequestMapping(value = "/addinformation", method = RequestMethod.POST)
        public String addEvent(Model m, @Valid InformationViewModel information) {
  
            Information newInformation = information.parse();
            informationRep.save(newInformation);
        
            return "redirect:/information";
        }
        
        @RequestMapping("/deleteinformations/{idInformation}")
        public String deleteInformation(@PathVariable int idInformation) {
        
            return "redirect:/information";
        }

        @RequestMapping("/updateinformation/{idInformation}")
        public String updateInformation(@PathVariable int idInformation) {
        
        return "redirect:/information";
        }
}
