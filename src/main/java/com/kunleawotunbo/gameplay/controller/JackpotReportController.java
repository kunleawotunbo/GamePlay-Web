/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.WeeklyGamesAnswersBean;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.service.CountryService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.LeagueService;
import com.kunleawotunbo.gameplay.service.TeamService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesAnswersService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    
    @Autowired
    private GameService gameService;
    
    @Autowired
    private WeeklyGamesAnswersService weeklyGamesAnswersService;
    
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

       
        model.addAttribute("weeklyGamesAnswersBean", new WeeklyGamesAnswersBean());
        //model.addAttribute("gameList", gameService.listAllGames());
       // model.addAttribute("countriesList", countryService.listCountries());       
        //model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/getEntriesByGame";
    }
    
    /**
     * This method will be called on form submission, handling POST request for
     * creating match prediction game in database.
     */
    @RequestMapping(value = "/admin/getEntriesByGame", method = RequestMethod.POST)
    public String getEntriesByGameReport(WeeklyGamesAnswersBean weeklyGamesAnswersBean, BindingResult result,
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
       
        System.out.println("gameCode :: " + weeklyGamesAnswersBean.getCode());
        
       
        
         List<WeeklyGamesAnswers> list = null;
        
        list = weeklyGamesAnswersService.listAnswerByCode( weeklyGamesAnswersBean.getCode());
        
        model.addAttribute("list", list);
        model.addAttribute("total", list.size());
        model.addAttribute("weeklyGamesAnswersBean", weeklyGamesAnswersBean);

        return "admin/getEntriesByGame";

    }
    
    /**
     * Get JpReportByPeriod page
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/jpReportByPeriod", method = RequestMethod.GET)
    public String getJpReportByPeriod(ModelMap model, HttpServletRequest request) {

       
        model.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswersBean());
        //model.addAttribute("gameList", weeklyGamesAnswersService.listAllWeeklyGamesAnswers());
       
        return "/admin/jpReportByPeriod";
    }
    
    
    @RequestMapping(value = "/admin/jpReportByPeriod", method = RequestMethod.POST)
    public String jpReportByPeriod(WeeklyGamesAnswersBean weeklyGamesAnswersBean, BindingResult result,
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
        System.out.println("getUserPhoneNo :: " + weeklyGamesAnswersBean.getUserPhoneNo());
        System.out.println("getStartDate :: " + weeklyGamesAnswersBean.getStartDate());
        System.out.println("getStartDate :: " + weeklyGamesAnswersBean.getEndDate());
        
        List<WeeklyGamesAnswers> list = null;
        
        list = weeklyGamesAnswersService.listAnswerByPhoneAndDate(
                weeklyGamesAnswersBean.getUserPhoneNo(),                
                weeklyGamesAnswersBean.getStartDate(), weeklyGamesAnswersBean.getEndDate());
        
        model.addAttribute("list", list);
        model.addAttribute("total", list.size());
        model.addAttribute("weeklyGamesAnswers", weeklyGamesAnswersBean);
        model.addAttribute("userPhoneNo", weeklyGamesAnswersBean.getUserPhoneNo());

        return "admin/jpReportByPeriod";

    }
    
    /**
     * Get JpReportByPeriodCountry page
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/jpReportByCountry", method = RequestMethod.GET)
    public String getJpReportByCountry(ModelMap model, HttpServletRequest request) {

       
        model.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswersBean());
        model.addAttribute("countriesList", countryService.listCountries());
        //model.addAttribute("gameList", weeklyGamesAnswersService.listAllWeeklyGamesAnswers());
       
        return "/admin/jpReportByCountry";
    }
    
    /**
     * Fetch reports by game code and country code
     * @param weeklyGamesAnswersBean
     * @param result
     * @param model
     * @param req
     * @return 
     */
    @RequestMapping(value = "/admin/jpReportByCountry", method = RequestMethod.POST)
    public String jpReportByCountry(WeeklyGamesAnswersBean weeklyGamesAnswersBean, BindingResult result,
            ModelMap model, HttpServletRequest req) {        
        
        logger.info("Report on jpReportByCountry");
     
        System.out.println("getCode :: " + weeklyGamesAnswersBean.getCode());
        System.out.println("getCountryCode :: " + weeklyGamesAnswersBean.getCountryCode());       
        
        List<WeeklyGamesAnswers> list = null;
        
        list = weeklyGamesAnswersService.listAnswerByCodeAndCountry(weeklyGamesAnswersBean.getCode(),
                weeklyGamesAnswersBean.getCountryCode());
        
        model.addAttribute("list", list);
        model.addAttribute("total", list.size());
        model.addAttribute("weeklyGamesAnswers", weeklyGamesAnswersBean);
        model.addAttribute("userPhoneNo", weeklyGamesAnswersBean.getUserPhoneNo());
        model.addAttribute("countriesList", countryService.listCountries());

        return "admin/jpReportByCountry";

    }
    
    /**
     * Get JpReportByPeriodPhoneNo page
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/jpReportByPhoneNo", method = RequestMethod.GET)
    public String getJpReportByPhoneNo(ModelMap model, HttpServletRequest request) {

       
        model.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswersBean());
        model.addAttribute("countriesList", countryService.listCountries());
        //model.addAttribute("gameList", weeklyGamesAnswersService.listAllWeeklyGamesAnswers());
       
        return "/admin/jpReportByPhoneNo";
    }
    
    /**
     * Fetch answers submitted by user phone numbers
     * @param weeklyGamesAnswersBean
     * @param result
     * @param model
     * @param req
     * @return 
     */
    @RequestMapping(value = "/admin/jpReportByPhoneNo", method = RequestMethod.POST)
    public String jpReportByPhoneNo(WeeklyGamesAnswersBean weeklyGamesAnswersBean, BindingResult result,
            ModelMap model, HttpServletRequest req) {        
        
        logger.info("Report on jpReportByPhoneNo");
     
        System.out.println("getUserPhoneNo :: " + weeklyGamesAnswersBean.getUserPhoneNo());
          
        
        List<WeeklyGamesAnswers> list = null;
        
        list = weeklyGamesAnswersService.listAnswersByUserPhoneNo(weeklyGamesAnswersBean.getUserPhoneNo());
        
        model.addAttribute("list", list);
        model.addAttribute("total", list.size());
        model.addAttribute("weeklyGamesAnswers", weeklyGamesAnswersBean);
        model.addAttribute("userPhoneNo", weeklyGamesAnswersBean.getUserPhoneNo());
        model.addAttribute("countriesList", countryService.listCountries());

        return "admin/jpReportByPhoneNo";

    }
    
    /**
     * Get jpReportByCorrectAnswers page
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/jpReportByCorrectAnswers", method = RequestMethod.GET)
    public String getJpCorrectAnswers(ModelMap model, HttpServletRequest request) {

       
        model.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswersBean());       
       
        return "/admin/jpReportByCorrectAnswers";
    }
    
    /**
     * Get all correct answers by game code
     * @param weeklyGamesAnswersBean
     * @param result
     * @param model
     * @param req
     * @return 
     */
    @RequestMapping(value = "/admin/jpReportByCorrectAnswers", method = RequestMethod.POST)
    public String jpCorrectAnswers(WeeklyGamesAnswersBean weeklyGamesAnswersBean, BindingResult result,
            ModelMap model, HttpServletRequest req) {        
        
        logger.info("Report on jpReportByCorrectAnswers");
     
        System.out.println("getCode :: " + weeklyGamesAnswersBean.getCode());
          
        
        List<WeeklyGamesAnswers> list = null;
        
        list = weeklyGamesAnswersService.listAnswerByCode(weeklyGamesAnswersBean.getCode());
        
        model.addAttribute("list", list);
        model.addAttribute("total", list.size());
        model.addAttribute("weeklyGamesAnswers", weeklyGamesAnswersBean);
        model.addAttribute("userPhoneNo", weeklyGamesAnswersBean.getUserPhoneNo());
        model.addAttribute("countriesList", countryService.listCountries());

        return "admin/jpReportByCorrectAnswers";

    }
    
    /**
     * Get jpReportByWinners page to fetch random winners by game code
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/jpReportByWinners", method = RequestMethod.GET)
    public String getJjpReportByWinners(ModelMap model, HttpServletRequest request) {

       
        model.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswersBean());       
       
        return "/admin/jpReportByWinners";
    }
    
    /**
     * List selected winners by game code
     * @param weeklyGamesAnswersBean
     * @param result
     * @param model
     * @param req
     * @return 
     */
    @RequestMapping(value = "/admin/jpReportByWinners", method = RequestMethod.POST)
    public String jjpReportByWinners(WeeklyGamesAnswersBean weeklyGamesAnswersBean, BindingResult result,
            ModelMap model, HttpServletRequest req) {        
        
        logger.info("Report on jpReportByCorrectAnswers");
     
        System.out.println("getCode :: " + weeklyGamesAnswersBean.getCode());
          
        
        List<WeeklyGamesAnswers> list = null;
        
        list = weeklyGamesAnswersService.listAnswerByCode(weeklyGamesAnswersBean.getCode());
        
        model.addAttribute("list", list);
        model.addAttribute("total", list.size());
        model.addAttribute("weeklyGamesAnswers", weeklyGamesAnswersBean);
        model.addAttribute("userPhoneNo", weeklyGamesAnswersBean.getUserPhoneNo());
        model.addAttribute("countriesList", countryService.listCountries());

        return "admin/jpReportByWinners";

    }
    
}
