/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;
import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.bean.GameBean;
import com.kunleawotunbo.gameplay.bean.WeeklyGamesBean;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.model.GameAnswer;
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


@Controller
@RequestMapping("/admin/")
@SessionAttributes("roles")
/**
 *
 * @author BELLO
 */
public class WinnerController {
    
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
    
    @RequestMapping(value = "/genrandomwinners", method = RequestMethod.GET)
    
    public String randomWeeklyGamesList(ModelMap model, HttpServletRequest request) {

        List<WeeklyGames> gameList = null;
        List<WeeklyGamesBean> weeklyGamesBeanList = null;
        String gameCategoryCode = "";
        String gameCatName = "";
        String gameTypeName = "";

        gameList = weeklyGamesService.listWeeklyGames();

        WeeklyGamesBean weeklyGamesBean = null;
        weeklyGamesBeanList = new ArrayList<WeeklyGamesBean>();
        for (WeeklyGames item : gameList) {

            if (item.getGameCategory() != 0) {
                gameCategoryCode = gameService.findById(item.getGameCategory()).getGameCode();
                gameCatName = gameService.findById(item.getGameCategory()).getGameName();

            }
            if (item.getGamePlayType() == 1) {
                gameTypeName = "Image Based";
            } else {
                gameTypeName = "Text Based";
            }

            weeklyGamesBean = new WeeklyGamesBean();

            weeklyGamesBean.setId(item.getId());
            weeklyGamesBean.setWeekNo(item.getWeekNo());
            weeklyGamesBean.setPrizeOfWinners(item.getPrizeOfWinners());
            weeklyGamesBean.setNoOfWinners(item.getNoOfWinners());
            weeklyGamesBean.setGameExpiryDate(item.getGameExpiryDate());
            weeklyGamesBean.setGameRules(item.getGameRules());
            weeklyGamesBean.setGameCategory(item.getGameCategory());
            weeklyGamesBean.setGamePlayType(item.getGamePlayType());
            weeklyGamesBean.setGameText(item.getGameText());
            weeklyGamesBean.setGameImage(item.getGameImage());
            weeklyGamesBean.setGameImgLocation(item.getGameImgLocation());
            weeklyGamesBean.setCreatedDate(item.getCreatedDate());
            weeklyGamesBean.setModifiedDate(item.getModifiedDate());
            weeklyGamesBean.setCreatedBy(item.getCreatedBy());
            weeklyGamesBean.setIsPicture(item.getIsPicture());
            weeklyGamesBean.setGameAnswer(item.getGameAnswer());
            weeklyGamesBean.setGameStartDate(item.getGameStartDate());
            weeklyGamesBean.setEnabled(item.isEnabled());
            weeklyGamesBean.setGameCatCode(gameCategoryCode);
            weeklyGamesBean.setGameCatName(gameCatName);
            weeklyGamesBean.setGameTypeName(gameTypeName);

            weeklyGamesBeanList.add(weeklyGamesBean);
        }

        model.addAttribute("weeklyGamesList", weeklyGamesBeanList);
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/weeklygameslistrandom";
    }
    
    @RequestMapping(value = {"/generate-RandomWinners-{id}"}, method = RequestMethod.GET)
    public String randomWeeklyGamesWinner(@PathVariable int id, ModelMap model) {

        logger.info("Generate Random Winners for WeeklyGames with id :: " + id);
        //byte status = 1;
       // boolean status = true;
        WeeklyGames weeklyGame = weeklyGamesService.findById(id);
         int NoOfWinners = weeklyGame.getNoOfWinners();
        List<WeeklyGamesAnswers> weeklyGameAnswer = null;
       try {

        GameAnswer gameAnswers = gamesAnswerService.findByGameId(id);
        String gametextanswer = gameAnswers.getGameAnswer();
        logger.info("Game Answer:"+ gametextanswer);
         weeklyGameAnswer = weeklyGamesAnswersService.listAllWeeklyGamesCorrectAnswersbyId(gametextanswer, id, NoOfWinners);
        //logger.info("Weekly Answer Game ID:" + String.valueOf(listWeeklyGamesAnswers.get(i).getGameId()));
         //logger.info("Weekly Game ID:" + String.valueOf(weeklyGamesAnswer.getId()));
         
         logger.info("Weekly Game User Answer :" + String.valueOf(weeklyGameAnswer.get(0).getUserAnswer()));
       
        }catch(Exception e){
               System.err.println( e.getMessage());
          }
        model.addAttribute("weeklyGame", weeklyGame);
       // model.addAttribute("weekNo", tunborUtility.gameWeek());
       // model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
       // model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("gameCategoryName", gameService.findById(weeklyGame.getGameCategory()).getGameName());
        model.addAttribute("WeeklyGameText", weeklyGame.getGameText());
        logger.info("Weekly Game Text :" + String.valueOf(weeklyGame.getGameText()));
        //model.addAttribute("edit", true);

        model.addAttribute("weeklyGameAnswer", weeklyGameAnswer);

        return "/admin/weeklygamesrandomwinners";
    }

   @RequestMapping(value = "/latestrandomwinners", method = RequestMethod.GET)
    
    public String latestWeeklyGamesWinners(ModelMap model, HttpServletRequest request) {

        List<WeeklyGames> gameList = new ArrayList<WeeklyGames>();
       // List<WeeklyGames> gameListValid = null;
       // List<Game> validGames = null;
        List<WeeklyGamesAnswers> weeklyGameAnswer = new ArrayList<WeeklyGamesAnswers>();
       // List weeklyGameCategoryName = null;
        List<String> weeklyGameCategoryName = new ArrayList<String>();
        //String gameCategoryCode = "";
        //String gameCatName = "";
        //String gameTypeName = "";
        
         Date todaydate = tunborUtility.getDate("Africa/Nigeria");

         gameList = weeklyGamesService.listWeekActiveGamesByDate(todaydate);
         
         logger.info("Game List Size :" + String.valueOf(gameList.size()));
         
          try {
         // gameList.get(1).getId();
         int NoOfWinners = gameList.get(0).getNoOfWinners();
           GameAnswer gameAnswers = gamesAnswerService.findByGameId(gameList.get(0).getId());
           if(gameAnswers != null){
           String gametextanswer = gameAnswers.getGameAnswer();
                logger.info("Game Answer:"+ gametextanswer);
               // weeklyGameAnswer.addAll(weeklyGameAnswer)
         weeklyGameAnswer = weeklyGamesAnswersService.listAllWeeklyGamesCorrectAnswersbyId(gametextanswer, gameList.get(0).getId(), NoOfWinners);
           }
         }catch(Exception e){
               System.err.println( e.getMessage());
         }
          
          int WeeklyGameLength = gameList.size();
         
         try {
         for(int i=1; i < WeeklyGameLength; i++){
            
             int NoOfWinners1 = gameList.get(i).getNoOfWinners();
           GameAnswer gameAnswers1 = gamesAnswerService.findByGameId(gameList.get(i).getId());
            if(gameAnswers1 != null){
           String gametextanswer1 = gameAnswers1.getGameAnswer();
                logger.info("Game Answer:"+ gametextanswer1);
               // weeklyGameAnswer.addAll(weeklyGameAnswer)
         weeklyGameAnswer.addAll(weeklyGamesAnswersService.listAllWeeklyGamesCorrectAnswersbyId(gametextanswer1, gameList.get(i).getId(), NoOfWinners1));
            }
         //logger.info("Weekly Answer Game ID:" + String.valueOf(listWeeklyGamesAnswers.get(i).getGameId()));
         //logger.info("Weekly Game ID:" + String.valueOf(weeklyGamesAnswer.getId()));
         
        // logger.info("Weekly Game User Answer :" + String.valueOf(weeklyGameAnswer.get(0).getUserAnswer()));
         }
           }catch(Exception e){
               
              System.err.println( e.getMessage());
          }
         
         logger.info("Weekly Game  Length:" + String.valueOf(weeklyGameAnswer.size()));
         
         try {
          for (WeeklyGamesAnswers item : weeklyGameAnswer) {
              
                // gameListValid.add(weeklyGamesService.findById(item.getGameId()));
            WeeklyGamesAnswers weeklyGamesAnswers = weeklyGamesAnswersService.findById(item.getId());
            
            boolean winnerstatus = true;
            
            weeklyGamesAnswers.setIsRandomWinner(winnerstatus);
                 
           // boolean isRandomWinnerSaveStatus =    weeklyGamesAnswersService.saveWeeklyGamesAnswer(weeklyGamesAnswers);
            
            boolean isRandomWinnerSaveStatus =    weeklyGamesAnswersService.updateWeeklyGamesAnswer(weeklyGamesAnswers);
            
           
            logger.info("is Random Winner Weekly Game Save Status:" + isRandomWinnerSaveStatus); 
            
            weeklyGameCategoryName.add(gameService.findById(weeklyGamesService.findById(item.getGameId()).getGameCategory()).getGameName());
              
           WeeklyGames weeklyGame = weeklyGamesService.findById(item.getGameId());
               weeklyGame.setModifiedDate(todaydate);
               
               boolean enabledstatus = false;
               weeklyGame.setEnabled(enabledstatus);
                          // weeklyGame.
                  boolean weeklyGameSaveStatus =    weeklyGamesService.save(weeklyGame);
                  
              logger.info("Weekly Game Save Status:" + weeklyGameSaveStatus);    
                  
             logger.info("Game Category Name:" + gameService.findById(weeklyGamesService.findById(item.getGameId()).getGameCategory()).getGameName());                                 
                }
          }catch(Exception e){
               
              System.err.println( e.getMessage());
          }
         //logger.info("Weekly Game Category :" + weeklyGameCategoryName.get(0));
        //ogger.info("Weekly Game Category :" + weeklyGameCategoryName.get(0));
        //model.addAttribute("weeklyGame", validGames);
        model.addAttribute("weeklyGameAnswer", weeklyGameAnswer);
        model.addAttribute("weeklyGameCategoryName", weeklyGameCategoryName);
        model.addAttribute("loggedinuser", getPrincipal());
        

        return "/admin/allweeklygamelatestwinner";
    } 
    
     @RequestMapping(value = "/allrandomwinners", method = RequestMethod.GET)
    
    public String allWeeklyGamesRandomWinners(ModelMap model, HttpServletRequest request) {
        List<String> weeklyGameCategoryName = new ArrayList<String>();
        
        boolean winnerstatus = true;
        //List<WeeklyGamesAnswers> weeklyGamesRandomWinner = new ArrayList<WeeklyGamesAnswers>();
        
        List<WeeklyGamesAnswers> weeklyGamesRandomWinner = null;
        
       weeklyGamesRandomWinner = weeklyGamesAnswersService.listWeeklyGamesAnswers(winnerstatus);
       
        try {
          for (WeeklyGamesAnswers item : weeklyGamesRandomWinner) {
              
                // gameListValid.add(weeklyGamesService.findById(item.getGameId()));
           // WeeklyGamesAnswers weeklyGamesAnswers = weeklyGamesAnswersService.findById(item.getId());
            
           weeklyGameCategoryName.add(gameService.findById(weeklyGamesService.findById(item.getGameId()).getGameCategory()).getGameName());
              
           logger.info("Game Category Name:" + gameService.findById(weeklyGamesService.findById(item.getGameId()).getGameCategory()).getGameName());                                 
                }
          }catch(Exception e){
               
              System.err.println( e.getMessage());
          }
       
       
       
        model.addAttribute("allWeeklyGameRandomWinner", weeklyGamesRandomWinner);
        //model.addAttribute("weeklyGameAnswer", weeklyGameAnswer);
        model.addAttribute("weeklyGameCategoryName", weeklyGameCategoryName);
        model.addAttribute("loggedinuser", getPrincipal());
        
        return "/admin/allrandomwinners";
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
