/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.interfaces.Definitions;
import com.kunleawotunbo.gameplay.model.MatchPrediction;
import com.kunleawotunbo.gameplay.model.MatchPredictionAnswer;
import com.kunleawotunbo.gameplay.model.MatchPredictionResult;
import com.kunleawotunbo.gameplay.model.MatchPredictionWinner;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.service.MatchPredictionAnswerService;
import com.kunleawotunbo.gameplay.service.MatchPredictionResultService;
import com.kunleawotunbo.gameplay.service.MatchPredictionService;
import com.kunleawotunbo.gameplay.service.MatchPredictionWinnerService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.Days;
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
import org.springframework.web.servlet.ModelAndView;

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

    final Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/prediction", method = RequestMethod.GET)
    public String getPredictionPage(ModelMap model, HttpServletRequest request) {

        List<MatchPrediction> activeMatchesList = null;

        activeMatchesList = matchPredictionService.listActiveMatches(tunborUtility.getDate(Definitions.TIMEZONE));

        model.addAttribute("activeMatchesList", activeMatchesList);
        //  model.addAttribute("lastWeekTotalAnswers", weeklyGamesAnswersService.submittedAnswersByWeek(tunborUtility.gameWeek() - 1));

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
    
         
        if(tunborUtility.isDateAfter(tunborUtility.getDate(Definitions.TIMEZONE), matchPredictionObject.getStartTime())){
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

        boolean status = true;
        model.addAttribute("matchPrediction", new MatchPrediction());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        // model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        // model.addAttribute("gameList", gameService.listGames(status));
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

        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());

            model.addAttribute("error", true);
            model.addAttribute("message", "Match prediction Creation failed");

            return "/admin/addMatchPrediction";

        }

        boolean saved = matchPredictionService.save(matchPrediction);
        // If not saved
        if (!saved) {

            model.addAttribute("error", true);
            model.addAttribute("message", "Match prediction Creation failed");

            return "admin/addMatchPrediction";
        }

        boolean status = true;

        model.addAttribute("saved", saved);
        model.addAttribute("message", "Match prediction  Created successfully");
        model.addAttribute("matchPrediction", new MatchPrediction());
        model.addAttribute("weekNo", tunborUtility.gameWeek());

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
        matchPredictionList = matchPredictionService.listAllMatchPredictions();

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

}
