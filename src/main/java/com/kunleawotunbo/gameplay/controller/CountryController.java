/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Country;
import com.kunleawotunbo.gameplay.service.CountryService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
@RequestMapping("/admin/")
public class CountryController {
    
    @Autowired
    private CountryService countryService;
    
     @Autowired
    private TunborUtility tunborUtility;
     
    final Logger logger = LoggerFactory.getLogger(getClass());

    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
     @RequestMapping(value = "/addCountry", method = RequestMethod.GET)
    public String getAddCountry(ModelMap model, HttpServletRequest request) {

        model.addAttribute("country", new Country());       
        model.addAttribute("countriesList", countryService.listCountries());        
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/addCountry";
    }
    
     @RequestMapping(value = {"/addCountry"}, method = RequestMethod.POST)
    public String saveCountry(Country country, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        boolean saved = false;

        
        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());

            return "/admin/addCountry";

        }
        
        saved = countryService.saveCountry(country);
         // If not saved
        if (!saved) {

            model.addAttribute("error", true);
            model.addAttribute("message", "Country Creation failed");

            return "admin/addCountry";
        }
        

        model.addAttribute("saved", saved);
        model.addAttribute("message", "Country Created successfully");
        model.addAttribute("country", new Country());
        
        return "redirect:/admin/addCountry";
    }
    
     /**
     * Get edit country page
     */
    @RequestMapping(value = {"/edit-country-{id}"}, method = RequestMethod.GET)
    public String editCountry(@PathVariable int id, ModelMap model) {

        logger.info("Edit country id :: " + id);
        
        Country country = countryService.getCountryById(id);
        
        model.addAttribute("edit", true);
        model.addAttribute("country", country);       
        model.addAttribute("countriesList", countryService.listCountries());        
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/addCountry";
        
    }
    
     @RequestMapping(value = {"/delete-country-{id}"}, method = RequestMethod.GET)
    public String deleteCountry(@PathVariable int id) {
        logger.info("Delete  deleteLeague id :: " + id);
        Country country = countryService.getCountryById(id);
        countryService.deleteCountry(country);

        return "redirect:/admin/addCountry";
    }
    
    
    
}
