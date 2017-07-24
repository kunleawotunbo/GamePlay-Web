/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesAnswersService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/admin/")
@SessionAttributes("roles")
public class GameSetupController {
    
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


    final Logger logger = LoggerFactory.getLogger(getClass());
    
     @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping(value = "/setupGameCategory", method = RequestMethod.GET)
    public String listGameCategory(ModelMap model, HttpServletRequest request) {

        //model.addAttribute("urlPath", request.getLocalAddr());
        //model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("game", new Game());
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/setupGameCategory";
    }
    
    
    
    @RequestMapping(value = {"/setup-gameType-{id}"}, method = RequestMethod.GET)
    public String setupThisGame(@PathVariable int id, ModelMap model)  {
             boolean status = true;
        //model.addAttribute("urlPath", request.getLocalAddr());
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("game", gameService.findById(id));
        //model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/setupThisGameCategory";
    }
    
     @RequestMapping(value = "/listallanswer", method = RequestMethod.GET)
   // public ResponseEntity listWeeklyGame(@RequestBody WeeklyGamesAnswers weeklyGamesAnswers, Errors errors) {
    
    public String listAllAnswers(ModelMap model, HttpServletRequest request) {  
           logger.info("ListAllAnswer");
          List<String> AnswerStatus = new ArrayList<String>();
          //String AnswerStatus;
                      
         List<WeeklyGamesAnswers> listWeeklyGamesAnswers = weeklyGamesAnswersService.listAllWeeklyGamesAnswers();
         int WeeklyGameAnswerLength = listWeeklyGamesAnswers.size();
         // String[] AnswerStatus = new String[WeeklyGameAnswerLength];
         for(int i=0; i < WeeklyGameAnswerLength; i++){
            //listWeeklyGamesAnswers.get(i).getGameId();
           // int j=0;
            String useranswer = listWeeklyGamesAnswers.get(i).getUserAnswer();
            int answerid = listWeeklyGamesAnswers.get(i).getGameId();
         WeeklyGames weeklyGamesAnswer = weeklyGamesService.getWeekGameAnswersbyId(answerid);    
        
         String gameanswer = weeklyGamesAnswer.getGameAnswer();
         
          if( gameanswer == null ? useranswer == null : gameanswer.equals(useranswer)){
             
            //listWeeklyGamesAnswers.add(i, element);
            AnswerStatus.add("CORRECT");
         //    AnswerStatus[i] = "CORRECT";
             
                        }else {
             
            AnswerStatus.add("WRONG"); 
        //    AnswerStatus[i] = "WRONG";
           
                      }
         
        // logger.info("Weekly Answer Game ID:" + String.valueOf(listWeeklyGamesAnswers.get(i).getGameId()));
         //logger.info("Weekly Game ID:" + String.valueOf(weeklyGamesAnswer.getId()));
         logger.info("Game Answer:"+ gameanswer);
         logger.info("User Answer:" + useranswer);
         
       
            logger.info(AnswerStatus.get(i));
          
         }
         //WeeklyGames weeklyGamesAnswer = weeklyGamesService.getWeekGameAnswersbyId(WeeklyGameAnswerLength);
         //String.valueOf(WeeklyGameAnswerLength)
         //logger.info(String.valueOf(WeeklyGameAnswerLength));
         //model.addAttribute("gameanswerlist", listWeeklyGamesAnswers);
         //model.addAttribute("gameanswerlist", weeklyGamesAnswersService.listAllWeeklyGamesAnswers());
         //logger.info("Answer Status Length:" +String.valueOf(AnswerStatus.size()));
         model.addAttribute("gameanswerlist", listWeeklyGamesAnswers);
         model.addAttribute("answerstatus", AnswerStatus);
         model.addAttribute("loggedinuser", getPrincipal());

         return "/admin/listallanswers";
    }
    
    @RequestMapping(value = "/listallcorrectanswer", method = RequestMethod.GET)
   // public ResponseEntity listWeeklyGame(@RequestBody WeeklyGamesAnswers weeklyGamesAnswers, Errors errors) {
    
    public String listAllCorrectAnswers(ModelMap model, HttpServletRequest request) {  
           logger.info("ListAllAnswer");
          
       // List<WeeklyGamesAnswers> listWeeklyGamesAnswers = weeklyGamesAnswersService.listAllWeeklyGamesAnswers();

         //model.addAttribute("gameanswerlist", listWeeklyGamesAnswers);
         model.addAttribute("gameanswerlist", weeklyGamesAnswersService.listAllWeeklyGamesAnswers());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/listallanswers";
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

class AnswerStatusInfo {
    
    public String answerstatusinfo;
    
    public void setanswerstatusinfo(String answer) {
        this.answerstatusinfo = answer;
    }
    
    
}
