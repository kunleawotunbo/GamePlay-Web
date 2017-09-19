/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;
import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.bean.WinnerBean;
import com.kunleawotunbo.gameplay.bean.GameBean;
import com.kunleawotunbo.gameplay.bean.WeeklyGamesBean;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.model.GameAnswer;
import com.kunleawotunbo.gameplay.model.WinnerNumber;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesAnswersService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.service.GameAnswerService;
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
import org.springframework.web.servlet.tags.form.FormTag;
/**
 *
 * @author BELLO
 */



@Controller
//@RequestMapping("/admin/")
//@SessionAttributes("roles")
/**
 *
 * @author BELLO
 */
public class winnerListController {
    

    
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
    private GameAnswerService gamesAnswerService;


    final Logger logger = LoggerFactory.getLogger(getClass());
    
     @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
     @RequestMapping(value = "/addWinnerNumber", method = RequestMethod.GET)
    public String getaddWeeklyGame(ModelMap model, HttpServletRequest request) {

       // boolean status = true;
        model.addAttribute("winnerNumber", new WinnerNumber());
        //model.addAttribute("weekNo", tunborUtility.gameWeek());
       // model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
       // model.addAttribute("gameList", gameService.listGames(status));
       // model.addAttribute("loggedinuser", getPrincipal());

        return "/addWinnerNumber";
    }
    
    
    
   @RequestMapping(value = "/addWinnerNumber", method = RequestMethod.POST)
  // @RequestMapping(value = "/winnerlist", method = RequestMethod.GET)
    // public String submit(@Valid @ModelAttribute("winnernumber")WinnerNumber winnernumber, BindingResult result,
     //       ModelMap model) {
    public String createWinnerNumber(WinnerNumber winnerNumber, BindingResult result,
            ModelMap model, HttpServletRequest req) {

            System.out.println("Inside Find if Winner :: ");
            
            logger.info("Winner Number :: " + winnerNumber.getWinnerNumber());
            
         List<WeeklyGamesAnswers> weeklyGamesRandomWinner = new ArrayList<WeeklyGamesAnswers>();
         
          List<String> weeklyGameCategoryName = new ArrayList<String>();
          
          String winnerStatus = "false";
         
         try {
        
       weeklyGamesRandomWinner = weeklyGamesAnswersService.listAllWeeklyGamesWinnersByPhoneNo(winnerNumber.getWinnerNumber());
       
        if(  weeklyGamesRandomWinner.size() > 0){
            
            winnerStatus = "true";
        }
       logger.info("Winner Answer :: " + weeklyGamesRandomWinner.get(0).getUserAnswer());
       
       }catch(Exception e){
               
              System.err.println( e.getMessage());
          }
           
         
         try {
          for (WeeklyGamesAnswers item : weeklyGamesRandomWinner) {
              
             // WeeklyGamesAnswers weeklyGamesAnswers = weeklyGamesAnswersService.findById(item.getId());
             weeklyGameCategoryName.add(gameService.findById(weeklyGamesService.findById(item.getGameId()).getGameCategory()).getGameName());
         
            logger.info("Game Category Name:" + gameService.findById(weeklyGamesService.findById(item.getGameId()).getGameCategory()).getGameName());                                 
                }
          }catch(Exception e){
               
              System.err.println( e.getMessage());
          }

            model.addAttribute("weeklyGameAnswer", weeklyGamesRandomWinner);
            model.addAttribute("weeklyGameCategoryName", weeklyGameCategoryName);
            model.addAttribute("winnerstatus", winnerStatus);
            // model.addAttribute("lastWeekTotalAnswers", weeklyGamesAnswersService.submittedAnswersByWeek(tunborUtility.gameWeek() - 1));

            //return "/addWinnerNumber";
            return "/winnerlist";
    }
    
}
    
