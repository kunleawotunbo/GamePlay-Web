/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.MatchPrediction;
import com.kunleawotunbo.gameplay.service.CountryService;
import com.kunleawotunbo.gameplay.service.LeagueService;
import com.kunleawotunbo.gameplay.service.TeamService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
public class JackpotReportController {
    
    @Autowired
    private TunborUtility tunborUtility;
    
    @Autowired
    private CountryService countryService;

    @Autowired
    private TeamService teamService;
    
    @Autowired
    private LeagueService leagueService;
    
    final Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/getEntriesByGame", method = RequestMethod.GET)
    public String getEntriesByGame(ModelMap model, HttpServletRequest request) {

       
        model.addAttribute("game", new Game());
        //model.addAttribute("weekNo", tunborUtility.gameWeek());
       // model.addAttribute("countriesList", countryService.listCountries());       
        //model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/getEntriesByGame";
    }
    
    /**
     * This method will be called on form submission, handling POST request for
     * creating match prediction game in database.
     */
    @RequestMapping(value = "/admin/getEntriesByGame", method = RequestMethod.POST)
    public String getEntriesByGameReport(Game game, BindingResult result,
            ModelMap model, HttpServletRequest req) {        
        
        logger.info("To report on jackpot game");
        /*
        System.out.println("matchPrediction.getAwayTeamId() :: " + matchPrediction.getAwayTeamId());
        System.out.println("matchPrediction.getHomeTeamId() :: " + matchPrediction.getHomeTeamId());
        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());

            model.addAttribute("error", true);
            model.addAttribute("message", "Match prediction Creation failed");

            return "/admin/addMatchPrediction";

        }
        if(matchPrediction.getHomeTeamId() == matchPrediction.getAwayTeamId()){
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());

            model.addAttribute("error", true);
            model.addAttribute("message", "Home and away team can't be the same");

            return "/admin/addMatchPrediction";
        }

        matchPrediction.setCountryName(countryService.getCountryByCountryCode(matchPrediction.getCountryCode()).getCountryName());
        matchPrediction.setLeagueName(leagueService.getLeagueByCode(matchPrediction.getLeagueCode()).getLeagueName());
        matchPrediction.setHomeTeamName(teamService.getTeamById(matchPrediction.getHomeTeamId()).getTeamName());
        matchPrediction.setAwayTeamName(teamService.getTeamById(matchPrediction.getAwayTeamId()).getTeamName());
        matchPrediction.setMatchCode(tunborUtility.getRandomNumber());
        boolean saved = matchPredictionService.save(matchPrediction);
        // If not saved
        if (!saved) {
            logger.error("Error occured, unable to save match predcition");
            model.addAttribute("error", true);
            model.addAttribute("message", "Match prediction Creation failed");

            return "admin/addMatchPrediction";
        }

        boolean status = true;

        model.addAttribute("saved", saved);
        model.addAttribute("message", "Match prediction  Created successfully");
        model.addAttribute("matchPrediction", new MatchPrediction());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("countriesList", countryService.listCountries());
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());
        
        */

        return "admin/getEntriesByGame";

    }
}