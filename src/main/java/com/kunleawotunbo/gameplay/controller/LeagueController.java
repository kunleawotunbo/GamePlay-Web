/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody2;
import com.kunleawotunbo.gameplay.model.Country;
import com.kunleawotunbo.gameplay.model.League;
import com.kunleawotunbo.gameplay.service.CountryService;
import com.kunleawotunbo.gameplay.service.LeagueService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
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
public class LeagueController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private TunborUtility tunborUtility;

    CustomResponseBody2 result2 = new CustomResponseBody2();

    final Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/manageLeague", method = RequestMethod.GET)
    public String getAddLeague(ModelMap model, HttpServletRequest request) {

        model.addAttribute("league", new League());
        model.addAttribute("countriesList", countryService.listCountries());
        model.addAttribute("leagueList", leagueService.listLeagues());
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/manageLeague";
    }

    /**
     * To save/ create new league
     *
     * @param league
     * @param result
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = {"/manageLeague"}, method = RequestMethod.POST)
    public String saveLeague(League league, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        boolean saved = false;

        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());

            return "/admin/addLeague";

        }

        Country country = countryService.getCountryById(league.getCountryId());

        league.setCountryName(country.getCountryName());
        league.setCountryCode(country.getCountryCode());
        saved = leagueService.saveLeague(league);
        // If not saved
        if (!saved) {

            model.addAttribute("error", true);
            model.addAttribute("message", "League Creation failed");

            return "admin/manageLeague";
        }

        model.addAttribute("saved", saved);
        model.addAttribute("message", "League Created successfully");
        model.addAttribute("league", new League());

        return "redirect:/admin/manageLeague";
    }

    /**
     * Get edit league page
     */
    @RequestMapping(value = {"/edit-league-{id}"}, method = RequestMethod.GET)
    public String editLeague(@PathVariable int id, ModelMap model) {

        logger.info("Edit league id :: " + id);

        League league = leagueService.getLeagueById(id);

        model.addAttribute("edit", true);
        model.addAttribute("league", league);
        model.addAttribute("leagueList", leagueService.listLeagues());
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/manageLeague";

    }

    @RequestMapping(value = {"/edit-league-{id}"}, method = RequestMethod.POST)
    public String updateLeague(League league, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());
            model.addAttribute("error", true);
            model.addAttribute("message", "Error occured while updating weekly game");
            // populate field with the current object
            model.addAttribute("league", league);

            return "/admin/manageLeague";

        }

        Country country = countryService.getCountryById(league.getCountryId());

        league.setCountryName(country.getCountryName());
        league.setCountryCode(country.getCountryCode());

        boolean saved = leagueService.updateLeague(league);
        if (!saved) {
            // unable to update 

            model.addAttribute("error", true);
            model.addAttribute("message", "Weeklygame Creation failed");

            return "admin/manageLeague";
        }

        /*
        boolean status = true;

        model.addAttribute("saved", saved);
        model.addAttribute("message", "Weeklygame Updated successfully");
        model.addAttribute("weeklyGame", new FileBucket());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("weeklyGame", new FileBucket());

        return "admin/manageLeague";
        
         */
        return "redirect:/admin/manageLeague";

    }

    /**
     * *
     * Delete legaue by league Id
     *
     * @param id League id to delete
     * @return
     */
    @RequestMapping(value = {"/delete-league-{id}"}, method = RequestMethod.GET)
    public String deleteLeague(@PathVariable int id) {
        logger.info("Delete  deleteLeague id :: " + id);
        League league = leagueService.getLeagueById(id);
        leagueService.deleteLeague(league);

        return "redirect:/admin/manageLeague";
    }

    /***
     * Web service to list leagues by country code
     * @param countryCode
     * @return 
     */
    @GetMapping("/getLeagueByCountry/{countryCode}")
    public ResponseEntity getLeagueByCountry(@PathVariable String countryCode) {
        List<League> leagueList = null;
        

        System.out.println("countryCode :: " + countryCode);
        leagueList = leagueService.listLeaguesByCountryCode(countryCode);

        if (leagueList.isEmpty() || leagueList == null) {
            logger.info("No league found! ");
            result2.setCode("204");
            result2.setMessage("No league found!");
            result2.setResult(leagueList);
            return new ResponseEntity(result2, HttpStatus.NO_CONTENT);
        } else {
            result2.setCode("200");
            result2.setMessage("success");
            //result2.setResult(weeklyGameList);
            result2.setResult(leagueList);
            return new ResponseEntity(result2, HttpStatus.OK);
        }

    }

}
