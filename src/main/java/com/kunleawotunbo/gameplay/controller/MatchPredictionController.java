/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.bean.MatchPredictionAnswerBean;
import com.kunleawotunbo.gameplay.interfaces.Definitions;
import com.kunleawotunbo.gameplay.model.MatchPrediction;
import com.kunleawotunbo.gameplay.model.MatchPredictionAnswer;
import com.kunleawotunbo.gameplay.model.MatchPredictionResult;
import com.kunleawotunbo.gameplay.model.MatchPredictionWinner;
import com.kunleawotunbo.gameplay.service.CountryService;
import com.kunleawotunbo.gameplay.service.LeagueService;
import com.kunleawotunbo.gameplay.service.MatchPredictionAnswerService;
import com.kunleawotunbo.gameplay.service.MatchPredictionResultService;
import com.kunleawotunbo.gameplay.service.MatchPredictionService;
import com.kunleawotunbo.gameplay.service.MatchPredictionWinnerService;
import com.kunleawotunbo.gameplay.service.TeamService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
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
public class MatchPredictionController {

    @Autowired
    private TunborUtility tunborUtility;

    @Autowired
    private MatchPredictionService matchPredictionService;

    @Autowired
    private MatchPredictionResultService matchPredictionResultService;

    @Autowired
    private MatchPredictionWinnerService matchPredictionWinnerService;

    @Autowired
    private MatchPredictionAnswerService matchPredictionAnswerService;
    
    @Autowired
    private CountryService countryService;

    @Autowired
    private TeamService teamService;
    
    @Autowired
    private LeagueService leagueService;
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
      @RequestMapping(value = "/prediction", method = RequestMethod.GET)
    public String getPredictionPage(ModelMap model, HttpServletRequest request) {

        List<MatchPrediction> activeMatchesList = null;
        List<MatchPrediction> eplMatchesList = null;
        List<MatchPrediction> laligaMatchesList = null;
        List<MatchPrediction> otherLeagueMatchesList = null;
        List<MatchPrediction> champLeaguesMatchesList = null;
        String england = "England";
        String epl = "Premier League";
        String spain = "Spani";
        String laliga = "La Liga";
        String championsLeagues = "Champions League";
        
        //LocalDate tenDaysAgo = LocalDate.now().minusDays(10);
        
        Date currentDate = tunborUtility.getDate(Definitions.TIMEZONE);
       
        
        activeMatchesList = matchPredictionService.listActiveMatches(tunborUtility.getDate(Definitions.TIMEZONE));
        eplMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(tunborUtility.getDate(Definitions.TIMEZONE), "EPL");
        laligaMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(tunborUtility.getDate(Definitions.TIMEZONE), "LaLiga");
        otherLeagueMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(tunborUtility.getDate(Definitions.TIMEZONE), "");
        champLeaguesMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(tunborUtility.getDate(Definitions.TIMEZONE), "Champions League");
        
        model.addAttribute("activeMatchesList", activeMatchesList);
        
        model.addAttribute("england", england);
        model.addAttribute("spain", spain);
        model.addAttribute("epl", epl);
        model.addAttribute("laliga", laliga);
        model.addAttribute("championsLeagues", championsLeagues);
        
        
        model.addAttribute("eplMatchesList", eplMatchesList);
        model.addAttribute("laligaMatchesList", laligaMatchesList);
         model.addAttribute("otherLeagueMatchesList", otherLeagueMatchesList);
        model.addAttribute("champLeaguesMatchesList", champLeaguesMatchesList);
        model.addAttribute("currentDate", currentDate);
        
        return "prediction";
    }
    
     @RequestMapping(value = "/soccer/{dateString}", method = RequestMethod.GET)
    public String getMatchByDate(@PathVariable String dateString, ModelMap model, HttpServletRequest request) {

        List<MatchPrediction> activeMatchesList = null;
        List<MatchPrediction> eplMatchesList = null;
        List<MatchPrediction> laligaMatchesList = null;
        List<MatchPrediction> otherLeagueMatchesList = null;
        List<MatchPrediction> champLeaguesMatchesList = null;
        String england = "England";
        String epl = "Premier League";
        String spain = "Spani";
        String laliga = "La Liga";
        String championsLeagues = "Champions League";
        
        //LocalDate tenDaysAgo = LocalDate.now().minusDays(10);
        Date currentDate = tunborUtility.getDate(Definitions.TIMEZONE);
       
        /*
        activeMatchesList = matchPredictionService.listActiveMatches(tunborUtility.getDate(Definitions.TIMEZONE));
        eplMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(tunborUtility.getDate(Definitions.TIMEZONE), "EPL");
        laligaMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(tunborUtility.getDate(Definitions.TIMEZONE), "LaLiga");
        otherLeagueMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(tunborUtility.getDate(Definitions.TIMEZONE), "");
        champLeaguesMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(tunborUtility.getDate(Definitions.TIMEZONE), "Champions League");
        */
        logger.info("dateString :: " + dateString);
        DateFormat inputFormat = new SimpleDateFormat(
                "yyyy MMM dd", Locale.ENGLISH);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        
        Date date = null;
        try {
            System.out.println("format1.parse(dateString) :: " + format1.parse(dateString));
            date =  format1.parse(dateString);
            //date = inputFormat.parse(dateString);
            
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(WeeklyGamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("date :: " + date);
        
        activeMatchesList = matchPredictionService.listActiveMatches(date);
        eplMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(date, "EPL");
        laligaMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(date, "LaLiga");
        otherLeagueMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(date, "");
        champLeaguesMatchesList = matchPredictionService.listActiveMatchesByLeagueCode(date, "Champions League");
        
        
        model.addAttribute("activeMatchesList", activeMatchesList);
        
        model.addAttribute("england", england);
        model.addAttribute("spain", spain);
        model.addAttribute("epl", epl);
        model.addAttribute("laliga", laliga);
        model.addAttribute("championsLeagues", championsLeagues);
        
        
        model.addAttribute("eplMatchesList", eplMatchesList);
        model.addAttribute("laligaMatchesList", laligaMatchesList);
         model.addAttribute("otherLeagueMatchesList", otherLeagueMatchesList);
        model.addAttribute("champLeaguesMatchesList", champLeaguesMatchesList);
        
        model.addAttribute("currentDate", currentDate);
        
        return "prediction";
    }

    /**
     * Set answer for selected match.
     */
    @RequestMapping(value = {"/set-matchPrediction-{selectedAnswer}-{id}"}, method = RequestMethod.GET)
    public String submitMatchPredictionAnswer(@PathVariable String selectedAnswer, @PathVariable int id, ModelMap model) {

        logger.info("Set answer for matchPrediction id :: " + id);

        MatchPrediction matchPredictionObject = matchPredictionService.findById(id);
        boolean matchStarted = false;
        String userAnswer = "";

        MatchPredictionAnswer matchPredictionAnswer = new MatchPredictionAnswer();
        // Check if match has started. Disable submit button if match has started.        
    
         
        if(null != matchPredictionObject &&  tunborUtility.isDateAfter(tunborUtility.getDate(Definitions.TIMEZONE), matchPredictionObject.getStartTime())){
            matchStarted = true;
            System.out.println("start time is after current time");
        }else {
            matchStarted = false;
            System.out.println("Current time is not after start time");
        }
        
        if("1".equalsIgnoreCase(selectedAnswer)){
            userAnswer = "Home Win";
        }else if ("X".equalsIgnoreCase(selectedAnswer)){
            userAnswer = "Draw";
        }else if ("2".equalsIgnoreCase(selectedAnswer)){
            userAnswer = "Away Win";
        }else {
            userAnswer = "Unable to determine your selected answer";
        }
        

        model.addAttribute("matchPredictionObject", matchPredictionObject);
        model.addAttribute("matchPredictionAnswer", matchPredictionAnswer);
        model.addAttribute("selectedAnswer", selectedAnswer);
        model.addAttribute("userAnswer", userAnswer);
        model.addAttribute("matchStarted", matchStarted);

        return "sumbitMatchPrediction";
    }

    /**
     * *****
     * ADMIN SECTION
     */
    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/addMatchPrediction", method = RequestMethod.GET)
    public String getAddMatchPrediction(ModelMap model, HttpServletRequest request) {

        /*
        boolean status = true;
        model.addAttribute("matchPrediction", new MatchPrediction());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        // model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        // model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/addMatchPrediction";
        */
        boolean status = true;
        model.addAttribute("matchPrediction", new MatchPrediction());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("countriesList", countryService.listCountries());       
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/addMatchPrediction";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * creating match prediction game in database.
     */
    @RequestMapping(value = "/admin/addMatchPrediction", method = RequestMethod.POST)
    public String createMatchPrediction(MatchPrediction matchPrediction, BindingResult result,
            ModelMap model, HttpServletRequest req) {        
        
        logger.info("To create new match prediction game");

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
        matchPrediction.setCode(tunborUtility.getRandomNumber());
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

        return "admin/addMatchPrediction";

    }
    
    /**
     * This method will provide the medium to update an game category user.
     */
    @RequestMapping(value = {"/admin/edit-matchPrediction-{id}"}, method = RequestMethod.GET)
    public String editMatchPrediction(@PathVariable int id, ModelMap model) {

        logger.info("Edit  editMatchPrediction id :: " + id);

      
        
        MatchPrediction matchPrediction = matchPredictionService.findById(id);
        
        if(null == matchPrediction){
            System.out.println("match prediction is null");
            return "/admin/addMatchPrediction";
        }else {
             
        boolean status = true;
        model.addAttribute("matchPrediction", matchPrediction);
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("countriesList", countryService.listCountries());       
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/addMatchPrediction";

        }
       
     
    }
    
    
        
         @RequestMapping(value = "/admin/edit-matchPrediction-{id}", method = RequestMethod.POST)
    public String updateMatchPrediction(MatchPrediction matchPrediction, BindingResult result,
            ModelMap model, HttpServletRequest req) {    
        
        logger.info("Edit  editMatchPrediction id :: " + matchPrediction.getId());
        
         matchPrediction.setCountryName(countryService.getCountryByCountryCode(matchPrediction.getCountryCode()).getCountryName());
        matchPrediction.setLeagueName(leagueService.getLeagueByCode(matchPrediction.getLeagueCode()).getLeagueName());
        matchPrediction.setHomeTeamName(teamService.getTeamById(matchPrediction.getHomeTeamId()).getTeamName());
        matchPrediction.setAwayTeamName(teamService.getTeamById(matchPrediction.getAwayTeamId()).getTeamName());
        matchPrediction.setCode(tunborUtility.getRandomNumber());
        boolean saved = matchPredictionService.save(matchPrediction);
        // If not saved
        if (!saved) {
            logger.error("Error occured, unable to update match predcition");
            model.addAttribute("error", true);
            model.addAttribute("message", "Match prediction update failed");

            return "admin/addMatchPrediction";
        }

        boolean status = true;

        model.addAttribute("saved", saved);
        model.addAttribute("message", "Match prediction  updated successfully");
        model.addAttribute("matchPrediction", new MatchPrediction());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("countriesList", countryService.listCountries());
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "admin/addMatchPrediction";
    }



    /**
     * List weekly games
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/listMatchPredictions", method = RequestMethod.GET)
    public String listMatchPredictions(ModelMap model, HttpServletRequest request) {

        List<MatchPrediction> matchPredictionList = null;

        //matchPredictionList = matchPredictionService.listActiveMatches(tunborUtility.getDate(Definitions.TIMEZONE));
        int start = 0;
        int limit = 50;
        matchPredictionList = matchPredictionService.listAllMatchPredictions(start, limit);

        model.addAttribute("matchPredictionList", matchPredictionList);
        //model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/listMatchPredictions";
    }

    /**
     * This method will allows to  submit answer for match prediction.
     */
    @RequestMapping(value = "/admin/set-matchPrediction-Answer-{id}", method = RequestMethod.GET)
    public String setMatchPredictionAnswer(@PathVariable int id, ModelMap model) {

        logger.info("Get setMatchPredictionAnswer answer page id :: " + id);

        MatchPrediction matchPrediction = matchPredictionService.findById(id);
        
        boolean matchStarted = false;
         // If match has expired, if not, admin can not set answer until game expire        
         System.out.println("outcome :: " + tunborUtility.getDate(Definitions.TIMEZONE).before(matchPrediction.getEndTime()));
        if (null != matchPrediction && tunborUtility.getDate(Definitions.TIMEZONE).before(matchPrediction.getEndTime()) ) {
            matchStarted = true;
           
            logger.info("Game has not ended, Please wait till match ends before setting answer");
            
            model.addAttribute("matchStarted", matchStarted);
            model.addAttribute("msg", "Match has not ended, Please wait till match ends before setting answer");
           
        } else {
            matchStarted = false;
            
            logger.info("Game has expired, admin can set answer");
        }

        model.addAttribute("matchPrediction", matchPrediction);
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());
        model.addAttribute("matchPredictionResult", matchPredictionResultService.findByMatchPredictionId(id));

        return "/admin/setMatchPredictionAnswer";
    }

    /**
     * List weekly games
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/listMatchPredictionsWinners-{gameId}", method = RequestMethod.GET)
    public String listMatchPredictionsWinners(@PathVariable int gameId, ModelMap model, HttpServletRequest request) {

        List<MatchPredictionWinner> matchPredictionWinnersList = null;
        MatchPrediction matchPrediction = null;
        MatchPredictionResult matchPredictionResult = null;

        try {
            matchPrediction = matchPredictionService.findById(gameId);
            matchPredictionResult = matchPredictionResultService.findByMatchPredictionId(gameId);
            matchPredictionWinnersList = matchPredictionWinnerService.listAllMatchPredictionWinnersByGameId(gameId);
            logger.info("matchPredictionWinnersList.size() :: " + matchPredictionWinnersList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("matchPrediction", matchPrediction);
        model.addAttribute("matchPredictionResult", matchPredictionResult);
        model.addAttribute("matchPredictionWinnersList", matchPredictionWinnersList);

        return "/admin/listMatchPredictionsWinners";
    }

    /**
     * List weekly games
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/viewAllAnswersMatchPredictions-{gameId}", method = RequestMethod.GET)
    public String viewAllAnswersMatchPredictions(@PathVariable int gameId, ModelMap model, HttpServletRequest request) {

        List<MatchPredictionAnswer> matchPredictionAnswerList = null;
        MatchPrediction matchPrediction = null;
        

        matchPrediction = matchPredictionService.findById(gameId);
        matchPredictionAnswerList = matchPredictionAnswerService.listAllMatchPredictionAnswersByGameId(gameId);

        logger.info("matchPredictionAnswerList.size() :: " + matchPredictionAnswerList.size());

        model.addAttribute("matchPrediction", matchPrediction);
        model.addAttribute("matchPredictionAnswerList", matchPredictionAnswerList);

        return "/admin/viewAllAnswersMatchPredictions";
    }
    
    
//    Version 2 for the match prediction for
     @RequestMapping(value = "/admin/addMatchPredictionNew", method = RequestMethod.GET)
    public String getAddMatchPredictionNew(ModelMap model, HttpServletRequest request) {

        boolean status = true;
        model.addAttribute("matchPrediction", new MatchPrediction());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("countriesList", countryService.listCountries());       
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/addMatchPredictionNew";
    }
    
    @RequestMapping(value = "/admin/addMatchPredictionNew", method = RequestMethod.POST)
    public String createMatchPredictionNew(MatchPrediction matchPrediction, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        logger.info("To create new match prediction game");

        System.out.println("matchPrediction.getAwayTeamId() :: " + matchPrediction.getAwayTeamId());
        System.out.println("matchPrediction.getHomeTeamId() :: " + matchPrediction.getHomeTeamId());
        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());

            model.addAttribute("error", true);
            model.addAttribute("message", "Match prediction Creation failed");

            return "/admin/addMatchPredictionNew";

        }

        matchPrediction.setCountryName(countryService.getCountryByCountryCode(matchPrediction.getCountryCode()).getCountryName());
        matchPrediction.setLeagueName(leagueService.getLeagueByCode(matchPrediction.getLeagueCode()).getLeagueName());
        matchPrediction.setHomeTeamName(teamService.getTeamById(matchPrediction.getHomeTeamId()).getTeamName());
        matchPrediction.setAwayTeamName(teamService.getTeamById(matchPrediction.getAwayTeamId()).getTeamName());
        boolean saved = matchPredictionService.save(matchPrediction);
        // If not saved
        if (!saved) {
            logger.error("Error occured, unable to save match predcition");
            model.addAttribute("error", true);
            model.addAttribute("message", "Match prediction Creation failed");

            return "admin/addMatchPredictionNew";
        }

        boolean status = true;

        model.addAttribute("saved", saved);
        model.addAttribute("message", "Match prediction  Created successfully");
        model.addAttribute("matchPrediction", new MatchPrediction());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("countriesList", countryService.listCountries());
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "admin/addMatchPredictionNew";

    }
    
    
    @RequestMapping(value = "/admin/answersByPlayerCountry", method = RequestMethod.GET)
    public String gamePredictionByLeagueReportGet(ModelMap model, HttpServletRequest request) {
        
         model.addAttribute("matchPrediction", new MatchPrediction());
        //model.addAttribute("urlPath", request.getLocalAddr());
        //model.addAttribute("loggedinuser", getPrincipal());
       // model.addAttribute("game", new Game());
       // model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/answersByPlayerCountry";
    }
    
    
    @RequestMapping(value = "/admin/answersByPlayerCountry", method = RequestMethod.POST)
    public String answerByPlayerCountry(MatchPrediction matchPrediction, BindingResult result,
            ModelMap model, HttpServletRequest req) {
        
           System.out.println("Inside game prediction by player country controller :: ");
            
            logger.info("Country Code :: " + matchPrediction.getCountryCode());
            
           List<MatchPredictionAnswer> matchPredictionList = new ArrayList<MatchPredictionAnswer>();
            
           matchPredictionList = matchPredictionAnswerService.listByCountry(matchPrediction.getCountryCode());
            
             
           logger.info("List Length :: " + matchPredictionList.size());
           logger.info("Country Code :: " + matchPredictionList.get(0).getCountryCode());
            model.addAttribute("matchPredictionList", matchPredictionList);
            model.addAttribute("CountryName", matchPredictionList.get(0).getCountry());

            model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

         return "/admin/answersByPlayerCountryList";

      }
    
    
    /**
     * Get mpReportByPeriod page
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/mpReportByPeriod", method = RequestMethod.GET)
    public String getMpReportByPeriod(ModelMap model, HttpServletRequest request) {

       
        model.addAttribute("matchPredictionAnswer", new MatchPredictionAnswerBean());
               
        return "/admin/mpReportByPeriod";
    }
    
    @RequestMapping(value = "/admin/mpReportByPeriod", method = RequestMethod.POST)
    public String mpReportByPeriod(MatchPredictionAnswerBean matchPredictionAnswerBean, BindingResult result,
            ModelMap model, HttpServletRequest req) {        
        
        logger.info("To report on match prediction game");
        
        System.out.println("getUserPhoneNo :: " + matchPredictionAnswerBean.getUserPhoneNo());
        System.out.println("getStartDate :: " + matchPredictionAnswerBean.getStartDate());
        System.out.println("getStartDate :: " + matchPredictionAnswerBean.getEndDate());
        
        List<MatchPredictionAnswer> list = null;
        
        list = matchPredictionAnswerService.listAnswerByPhoneAndDate(
                matchPredictionAnswerBean.getUserPhoneNo(),                
                matchPredictionAnswerBean.getStartDate(), matchPredictionAnswerBean.getEndDate());
        
        model.addAttribute("list", list);
        model.addAttribute("total", list.size());
        model.addAttribute("matchPredictionAnswer", matchPredictionAnswerBean);
        model.addAttribute("userPhoneNo", matchPredictionAnswerBean.getUserPhoneNo());

        return "admin/mpReportByPeriod";

    }
    
    
    /**
     * Get JpReportByPeriodCountry page
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/mpReportByCountry", method = RequestMethod.GET)
    public String getMpReportByCountry(ModelMap model, HttpServletRequest request) {

       
        model.addAttribute("matchPredictionAnswer", new MatchPredictionAnswerBean());
        model.addAttribute("countriesList", countryService.listCountries());
              
        return "/admin/mpReportByCountry";
    }
    
    /**
     * Fetch reports by game code and country code
     * @param weeklyGamesAnswersBean
     * @param result
     * @param model
     * @param req
     * @return 
     */
    @RequestMapping(value = "/admin/mpReportByCountry", method = RequestMethod.POST)
    public String mpReportByCountry(MatchPredictionAnswerBean matchPredictionAnswerBean, BindingResult result,
            ModelMap model, HttpServletRequest req) {        
        
        logger.info("Report on mpReportByCountry");
     
        System.out.println("getCode :: " + matchPredictionAnswerBean.getCode());
        System.out.println("getCountryCode :: " + matchPredictionAnswerBean.getCountryCode());       
        
        List<MatchPredictionAnswer> list = null;
        
        list = matchPredictionAnswerService.listAnswerByCodeAndCountry(matchPredictionAnswerBean.getCode(),
                matchPredictionAnswerBean.getCountryCode());
        
        model.addAttribute("list", list);
        model.addAttribute("total", list.size());
        model.addAttribute("matchPredictionAnswer", matchPredictionAnswerBean);
        model.addAttribute("userPhoneNo", matchPredictionAnswerBean.getUserPhoneNo());
        model.addAttribute("countriesList", countryService.listCountries());

        return "admin/mpReportByCountry";

    }
    


}
