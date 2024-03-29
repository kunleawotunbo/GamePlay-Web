/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;
import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.bean.GameBean;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.model.GameAnswer;
import com.kunleawotunbo.gameplay.model.WinnerNumber;
import com.kunleawotunbo.gameplay.model.MatchPrediction;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesAnswersService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.service.GameAnswerService;
import com.kunleawotunbo.gameplay.service.MatchPredictionService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author BELLO
 */
@Controller


/**
 *
 * @author BELLO
 */
public class MatchPredictionReportController {
    
     @Autowired
    private GameService gameService;

    @Autowired
    private TunborUtility tunborUtility;

    @Autowired
    private GamePlayTypeService gamePlayTypeService;

    @Autowired
    private WeeklyGamesService weeklyGamesService;
    
    @Autowired
    private WeeklyGamesAnswersService weeklyGamesAnswersService;
    
    @Autowired
    private MatchPredictionService matchPredictionService;


    final Logger logger = LoggerFactory.getLogger(getClass());
    
     @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
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
    
    @RequestMapping(value = "/admin/gamePredictionDurationReport", method = RequestMethod.GET)
    public String gamePredictionDurationReport(ModelMap model, HttpServletRequest request) {
        
         model.addAttribute("matchPrediction", new MatchPrediction());
        //model.addAttribute("urlPath", request.getLocalAddr());
        //model.addAttribute("loggedinuser", getPrincipal());
       // model.addAttribute("game", new Game());
       // model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/gamePredictionDurationReport";
    }
    
    @RequestMapping(value = "/admin/gamePredictionDurationReport", method = RequestMethod.POST)
  // @RequestMapping(value = "/winnerlist", method = RequestMethod.GET)
    // public String submit(@Valid @ModelAttribute("winnernumber")WinnerNumber winnernumber, BindingResult result,
     //       ModelMap model) {
    public String gamePredictionDurationReportReturn(MatchPrediction matchPrediction, BindingResult result,
            ModelMap model, HttpServletRequest req) {

            System.out.println("Inside game prediction report contoller :: ");
            
            logger.info("Date Range :: " + matchPrediction.getEndTime());
            
           List<MatchPrediction> matchPredictionList = new ArrayList<MatchPrediction>();
            
           matchPredictionList = matchPredictionService.listByTimePlayedPeriod(matchPrediction.getStartTime(), matchPrediction.getEndTime());
            
             
           logger.info("List Length :: " + matchPredictionList.size());
           model.addAttribute("matchPredictionList", matchPredictionList);
           model.addAttribute("startTime", matchPrediction.getStartTime());
           model.addAttribute("endTime", matchPrediction.getEndTime());
            model.addAttribute("loggedinuser", getPrincipal());
             
            return "/admin/matchlistbyplayperiod";
    }
    
    
     @RequestMapping(value = "/admin/gamePredictionByLeagueReport", method = RequestMethod.GET)
    public String gamePredictionByLeagueReportGet(ModelMap model, HttpServletRequest request) {
        
         model.addAttribute("matchPrediction", new MatchPrediction());
        //model.addAttribute("urlPath", request.getLocalAddr());
        //model.addAttribute("loggedinuser", getPrincipal());
       // model.addAttribute("game", new Game());
       // model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/gamePredictionByLeagueReport";
    }
    
    @RequestMapping(value = "/admin/gamePredictionByLeagueReport", method = RequestMethod.POST)
  // @RequestMapping(value = "/winnerlist", method = RequestMethod.GET)
    // public String submit(@Valid @ModelAttribute("winnernumber")WinnerNumber winnernumber, BindingResult result,
     //       ModelMap model) {
    public String gamePredictionByLeagueReportPost(MatchPrediction matchPrediction, BindingResult result,
            ModelMap model, HttpServletRequest req) {

            System.out.println("Inside game prediction by league report contoller :: ");
            
            logger.info("League Code :: " + matchPrediction.getLeagueCode());
            
           List<MatchPrediction> matchPredictionList = new ArrayList<MatchPrediction>();
            
           matchPredictionList = matchPredictionService.listByLeague(matchPrediction.getLeagueCode());
            
             
           logger.info("List Length :: " + matchPredictionList.size());
           logger.info("League Name :: " + matchPredictionList.get(0).getLeagueName());
            model.addAttribute("matchPredictionList", matchPredictionList);
            model.addAttribute("LeagueName", matchPredictionList.get(0).getLeagueName());
             model.addAttribute("loggedinuser", getPrincipal());
             
            return "/admin/matchlistbyleague";
    }
    
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        System.out.println("Logged in user :: " + userName);
        return userName;
    }
    
}
