/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.bean.GameBean;
import com.kunleawotunbo.gameplay.bean.WeeklyGamesBean;
import com.kunleawotunbo.gameplay.interfaces.Definitions;
import com.kunleawotunbo.gameplay.model.ActivityLog;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.GameAnswer;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.model.WeeklyGamesWinner;
import com.kunleawotunbo.gameplay.service.ActivityLogService;
import com.kunleawotunbo.gameplay.service.GameAnswerService;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.UserService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesAnswersService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesWinnerService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
@RequestMapping("/admin/")
@SessionAttributes("roles")
public class AdminController {

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
    private GameAnswerService gameAnswerService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    private WeeklyGamesWinnerService weeklyGamesWinnerService;

    ActivityLog activityLog = new ActivityLog();

    final Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String adminDashboard(ModelMap model, HttpServletRequest request) {

        // set user object in session
        String userName = getPrincipal();
        System.out.println("userName :: " + userName);
        User user = userService.findUserByEmail(userName);
        request.getSession().setAttribute("userObject", user);

        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("noOfAnswersSubmitted", weeklyGamesAnswersService.submittedAnswersByWeek(tunborUtility.gameWeek()));
        model.addAttribute("lastWeekTotalAnswers", weeklyGamesAnswersService.submittedAnswersByWeek(tunborUtility.gameWeek() - 1));

        return "/admin/dashboard";
    }

    /**
     * List weekly games
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/listWeeklyGames", method = RequestMethod.GET)
    public String listWeeklyGames(ModelMap model, HttpServletRequest request) {

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

                try {
                    gameCategoryCode = gameService.findById(item.getGameCategory()).getGameCode();
                    gameCatName = gameService.findById(item.getGameCategory()).getGameName();

                } catch (Exception e) {
                    e.printStackTrace();
                }

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
            weeklyGamesBean.setCode(item.getCode());

            weeklyGamesBeanList.add(weeklyGamesBean);
        }

        model.addAttribute("weeklyGamesList", weeklyGamesBeanList);
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/listWeeklyGames";
    }

    @RequestMapping(value = "/addWeeklyGame", method = RequestMethod.GET)
    public String getaddWeeklyGame(ModelMap model, HttpServletRequest request) {

        boolean status = true;
        model.addAttribute("weeklyGame", new FileBucket());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        //model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("gameList", gameService.listGames(status, "MPP"));
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/addWeeklyGame";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving addWeeklyGame in database.
     */
    @RequestMapping(value = {"/addWeeklyGame"}, method = RequestMethod.POST)
    public String createWeeklyGame(FileBucket fileBucket, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        System.out.println("Inside create weeklygame ");

        WeeklyGames weeklyGames = new WeeklyGames();
        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());

            return "/admin/addWeeklyGame";

        }
        FileBucket fb = new FileBucket();

        if (fileBucket.getGamePlayType() == 1 || fileBucket.getGamePlayType() == 3) {
            byte yesPicture = 1;
            fileBucket.setIsPicture(yesPicture);
            logger.info("Is picture : " + yesPicture);

            // Do the upload 
            fb = fileUpload(fileBucket);

        } else {
            logger.info("No image is uploaded, type is question");
            fb = fileBucket;
            byte isPicture = 0;

            fb.setIsPicture(isPicture);
        }

        //fb = fileUpload(fileBucket);
        System.out.println("fb.getIsPicture() :: " + fb.getIsPicture());
        weeklyGames.setId(fb.getId());
        weeklyGames.setWeekNo(fb.getWeekNo());
        weeklyGames.setPrizeOfWinners(fb.getPrizeOfWinners());
        weeklyGames.setNoOfWinners(fb.getNoOfWinners());
        weeklyGames.setGameExpiryDate(fb.getGameExpiryDate());
        weeklyGames.setGameRules(fb.getGameRules());
        weeklyGames.setGameCategory(fb.getGameCategory());
        weeklyGames.setGamePlayType(fb.getGamePlayType());
        weeklyGames.setGameText(fb.getGameText());
        weeklyGames.setGameImage(fb.getGameImage());
        weeklyGames.setGameImage2(fb.getGameImage2());
        weeklyGames.setGameImgLocation(fb.getGameImgLocation());
        weeklyGames.setCreatedDate(tunborUtility.getDate(Definitions.TIMEZONE));
        weeklyGames.setModifiedDate(fb.getModifiedDate());
        weeklyGames.setCreatedBy(fb.getCreatedBy());
        weeklyGames.setIsPicture(fb.getIsPicture());
        weeklyGames.setGameAnswer(fb.getGameAnswer());
        weeklyGames.setGameStartDate(fb.getGameStartDate());
        weeklyGames.setEnabled(fb.isEnabled());
        weeklyGames.setCode(tunborUtility.getRandomNumber());

        boolean saved = weeklyGamesService.save(weeklyGames);
        // If not saved
        if (!saved) {

            model.addAttribute("error", true);
            model.addAttribute("message", "Weeklygame Creation failed");

            return "admin/addWeeklyGame";
        }

        boolean status = true;

        model.addAttribute("saved", saved);
        model.addAttribute("message", "Weeklygame Created successfully");
        model.addAttribute("weeklyGame", new FileBucket());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("weeklyGame", new FileBucket());

        return "admin/addWeeklyGame";

    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/set-weeklyGames-Answer-{id}"}, method = RequestMethod.GET)
    public String setWeeklyGamesAnswer(@PathVariable int id, ModelMap model) {

        logger.info("set-weeklyGames-Answer id :: " + id);
        //byte status = 1;
        boolean status = true;
        WeeklyGames weeklyGame = null;
        try {
            weeklyGame = weeklyGamesService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != weeklyGame && weeklyGame.getGamePlayType() == 3) {
           
            String[] numberList = weeklyGame.getGameText().split("-");
            System.out.println("numberList :: " + numberList.toString());
            for (String item : numberList){
                System.out.print(item);
            }

            model.addAttribute("numberList", numberList);
        } else {
            logger.info("Not Jackpot game");
        }
        
        boolean hasExpired = false;
         // If match has expired, if not, admin can not set answer until game expire        
        if (null != weeklyGame && tunborUtility.getDate(Definitions.TIMEZONE).before(weeklyGame.getGameExpiryDate()) ) {
            hasExpired = false;
            logger.info("hasExpired :: " + hasExpired);
            logger.info("Game has not ended, Please wait till match ends before setting answer");
           
            model.addAttribute("hasExpired", hasExpired);
             model.addAttribute("msg", "Game has not ended, Please wait till game ends before setting answer");    
           // model.addAttribute("msg", "Game has expired, admin can set answer");      
        } else {
            hasExpired = true;   
             logger.info("hasExpired :: " + hasExpired);
             model.addAttribute("hasExpired", hasExpired);
              
             
            logger.info("Game has expired, admin can set answer");
        }

        model.addAttribute("weeklyGame", weeklyGame);
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("gameCategoryName", gameService.findById(weeklyGame.getGameCategory()).getGameName());
        //model.addAttribute("edit", true);

        model.addAttribute("gameAnswerObject", gameAnswerService.findByGameId(id));

        return "/admin/setWeeklyAnswer";
    }

    /**
     * List weekly games
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/listWeeklyGamesWinners-{gameId}", method = RequestMethod.GET)
    public String listWeeklyGamesWinners(@PathVariable int gameId, ModelMap model, HttpServletRequest request) {

        List<WeeklyGamesWinner> weeklyGamesWinnersList = null;
        WeeklyGames weeklyGames = null;
        GameAnswer gameAnswerObj = null;

        try {
            weeklyGames = weeklyGamesService.findById(gameId);
            //gameAnswer = matchPredictionResultService.findByMatchPredictionId(gameId);
            gameAnswerObj = gameAnswerService.findByGameId(gameId);
            //weeklyGamesWinnersList = matchPredictionWinnerService.listAllMatchPredictionWinnersByGameId(gameId);
            weeklyGamesWinnersList = weeklyGamesWinnerService.listAllWeeklyGamesWinnersByGameId(gameId);
            logger.info("weeklyGamesWinnersList.size() :: " + weeklyGamesWinnersList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("weeklyGames", weeklyGames);
        model.addAttribute("gameAnswerObj", gameAnswerObj);
        model.addAttribute("weeklyGamesWinnersList", weeklyGamesWinnersList);

        return "/admin/listWeeklyGamesWinners";
    }

    /**
     * List weekly games
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/viewAllAnswersWeeklyGame-{gameId}", method = RequestMethod.GET)
    public String viewAllAnswersWeeklyGame(@PathVariable int gameId, ModelMap model, HttpServletRequest request) {

        List<WeeklyGamesAnswers> weeklyGamesAnswersList = null;
        WeeklyGames weeklyGames = null;
        GameAnswer gameAnswerObj = null;
        Game gameCategory = null;

        try {
            weeklyGames = weeklyGamesService.findById(gameId);
            weeklyGamesAnswersList = weeklyGamesAnswersService.listAllWeeklyGameAnswersByGameId(gameId);
            gameAnswerObj = gameAnswerService.findByGameId(gameId);
            gameCategory = gameService.findById(weeklyGames.getGameCategory());
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("weeklyGames", weeklyGames);
        model.addAttribute("gameAnswerObj", gameAnswerObj);
        model.addAttribute("gameCategory", gameCategory);
        model.addAttribute("weeklyGamesAnswersList", weeklyGamesAnswersList);

        return "/admin/viewAllAnswersWeeklyGame";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-weeklyGames-{id}"}, method = RequestMethod.GET)
    public String editWeeklyGames(@PathVariable int id, ModelMap model) {

        logger.info("Edit  editWeeklyGames id :: " + id);
        //byte status = 1;
        boolean status = true;
        WeeklyGames weeklyGame = weeklyGamesService.findById(id);

        int gamecategory = weeklyGame.getGameCategory();

        Game jackpotNumberGame = gameService.findByNameReturnGame("Jackpot Number");

        if (gamecategory == jackpotNumberGame.getId()) {

            String encodedPictureString = "";
            String encodedGameImage2 = "";
            //encodedPictureString = tunborUtility.imageToBase64tring(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());
            FileBucket fbWeeklyGame = new FileBucket();

            // If type is picture
            if (weeklyGame.getIsPicture() == 1 || weeklyGame.getGamePlayType() == 3) {

                encodedPictureString = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());

                if (weeklyGame.getGameImage2() != null) {
                    encodedGameImage2 = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage2());
                }

            }

            fbWeeklyGame.setId(weeklyGame.getId());
            fbWeeklyGame.setGameCategory(weeklyGame.getGameCategory());
            fbWeeklyGame.setGamePlayType(weeklyGame.getGamePlayType());
            fbWeeklyGame.setGameText(weeklyGame.getGameText());
            fbWeeklyGame.setGameImage(weeklyGame.getGameImage());
            fbWeeklyGame.setWeekNo(weeklyGame.getWeekNo());
            fbWeeklyGame.setPrizeOfWinners(weeklyGame.getPrizeOfWinners());
            fbWeeklyGame.setNoOfWinners(weeklyGame.getNoOfWinners());
            fbWeeklyGame.setGameExpiryDate(weeklyGame.getGameExpiryDate());
            fbWeeklyGame.setGameRules(weeklyGame.getGameRules());
            fbWeeklyGame.setCreatedBy(weeklyGame.getCreatedBy());
            fbWeeklyGame.setIsPicture(weeklyGame.getIsPicture());
            fbWeeklyGame.setGameStartDate(weeklyGame.getGameStartDate());
            fbWeeklyGame.setEnabled(weeklyGame.isEnabled());
            fbWeeklyGame.setCode(weeklyGame.getCode());

            
            model.addAttribute("weeklyGame", fbWeeklyGame);
            model.addAttribute("weekNo", tunborUtility.gameWeek());
            model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
            //model.addAttribute("gamePlayTypeList", gamePlayType);
            model.addAttribute("gameList", gameService.listGames(status));
            model.addAttribute("loggedinuser", getPrincipal());
            model.addAttribute("edit", true);
            model.addAttribute("encodedPictureString", encodedPictureString);
            model.addAttribute("encodedGameImage2", encodedGameImage2);

            return "/admin/addWeeklyGameJackpotNumber";
        } else {

            String encodedPictureString = "";
            String encodedGameImage2 = "";
            //encodedPictureString = tunborUtility.imageToBase64tring(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());
            FileBucket fbWeeklyGame = new FileBucket();

            // If type is picture
            if (weeklyGame.getIsPicture() == 1) {

                encodedPictureString = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());

                if (weeklyGame.getGameImage2() != null) {
                    encodedGameImage2 = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage2());
                }

            }

            fbWeeklyGame.setId(weeklyGame.getId());
            fbWeeklyGame.setGameCategory(weeklyGame.getGameCategory());
            fbWeeklyGame.setGamePlayType(weeklyGame.getGamePlayType());
            fbWeeklyGame.setGameText(weeklyGame.getGameText());
            fbWeeklyGame.setGameImage(weeklyGame.getGameImage());
            fbWeeklyGame.setWeekNo(weeklyGame.getWeekNo());
            fbWeeklyGame.setPrizeOfWinners(weeklyGame.getPrizeOfWinners());
            fbWeeklyGame.setNoOfWinners(weeklyGame.getNoOfWinners());
            fbWeeklyGame.setGameExpiryDate(weeklyGame.getGameExpiryDate());
            fbWeeklyGame.setGameRules(weeklyGame.getGameRules());
            fbWeeklyGame.setCreatedBy(weeklyGame.getCreatedBy());
            fbWeeklyGame.setIsPicture(weeklyGame.getIsPicture());
            fbWeeklyGame.setGameStartDate(weeklyGame.getGameStartDate());
            fbWeeklyGame.setEnabled(weeklyGame.isEnabled());
            fbWeeklyGame.setCode(weeklyGame.getCode());

            //model.addAttribute("weeklyGame", weeklyGamesService.findById(id));
            model.addAttribute("weeklyGame", fbWeeklyGame);
            model.addAttribute("weekNo", tunborUtility.gameWeek());
            model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
            model.addAttribute("gameList", gameService.listGames(status));
            model.addAttribute("loggedinuser", getPrincipal());
            model.addAttribute("edit", true);
            model.addAttribute("encodedPictureString", encodedPictureString);
            model.addAttribute("encodedGameImage2", encodedGameImage2);

            return "/admin/addWeeklyGame";
        }
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating addWeeklyGame in database.
     */
    @RequestMapping(value = {"/edit-weeklyGames-{id}"}, method = RequestMethod.POST)
    public String updateWeeklyGames(FileBucket fileBucket, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        System.out.println("updateWeeklyGames :: " + fileBucket.getId());

        WeeklyGames weeklyGames = new WeeklyGames();
        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());
            model.addAttribute("error", true);
            model.addAttribute("message", "Error occured while updating weekly game");
            // populate field with the current object
            model.addAttribute("weeklyGame", fileBucket);

            return "/admin/addWeeklyGame";

        }
        FileBucket fb = new FileBucket();

        if (fileBucket.getGamePlayType() == 1 || fileBucket.getGamePlayType() == 3) {
            byte yesPicture = 1;
            fileBucket.setIsPicture(yesPicture);
            logger.info("Is picture : " + yesPicture);

            // Do the upload 
            fb = fileUpload(fileBucket);

        } else {
            logger.info("No image is uploaded, type is question");
            fb = fileBucket;
            byte isPicture = 0;

            fb.setIsPicture(isPicture);
        }

        //fb = fileUpload(fileBucket);
        weeklyGames = weeklyGamesService.findById(fb.getId());
        
        System.out.println("fb.getIsPicture() :: " + fb.getIsPicture());
        //weeklyGames.setId(fb.getId());
        weeklyGames.setWeekNo(fb.getWeekNo());
        weeklyGames.setPrizeOfWinners(fb.getPrizeOfWinners());
        weeklyGames.setNoOfWinners(fb.getNoOfWinners());
        weeklyGames.setGameExpiryDate(fb.getGameExpiryDate());
        weeklyGames.setGameRules(fb.getGameRules());
        weeklyGames.setGameCategory(fb.getGameCategory());
        weeklyGames.setGamePlayType(fb.getGamePlayType());
        weeklyGames.setGameText(fb.getGameText());
        weeklyGames.setGameImage(fb.getGameImage());
        weeklyGames.setGameImage2(fb.getGameImage2());
        weeklyGames.setGameImgLocation(fb.getGameImgLocation());
        //weeklyGames.setCreatedDate(fb.getCreatedDate());
        weeklyGames.setModifiedDate(tunborUtility.getDate(Definitions.TIMEZONE));
        //weeklyGames.setCreatedBy(fb.getCreatedBy());
        weeklyGames.setIsPicture(fb.getIsPicture());
        weeklyGames.setGameAnswer(fb.getGameAnswer());
        weeklyGames.setGameStartDate(fb.getGameStartDate());
        weeklyGames.setEnabled(fb.isEnabled());
        //weeklyGames.setCode(fb.getCode());

        boolean saved = weeklyGamesService.updateWeeklyGame(weeklyGames);
        if (!saved) {
            // unable to update 

            model.addAttribute("error", true);
            model.addAttribute("message", "Weeklygame Creation failed");

            return "admin/addWeeklyGame";
        }

        boolean status = true;

        model.addAttribute("saved", saved);
        model.addAttribute("message", "Weeklygame Updated successfully");
        model.addAttribute("weeklyGame", new FileBucket());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("weeklyGame", new FileBucket());

        return "admin/addWeeklyGame";
    }

    /**
     * This method will delete an user by it's id value.
     */
    @RequestMapping(value = {"/delete-weeklyGames-{id}"}, method = RequestMethod.GET)
    public String deleteWeklyGames(@PathVariable int id) {
        logger.info("Delete  deleteWeklyGames id :: " + id);
        WeeklyGames weeklyGames = weeklyGamesService.findById(id);
        weeklyGamesService.deleteGame(weeklyGames);

        return "redirect:/admin/listWeeklyGames";
    }

    @RequestMapping(value = "/addGameCategory", method = RequestMethod.GET)
    public String addGameCategory(ModelMap model, HttpServletRequest request) {

        //model.addAttribute("game", new Game());
        model.addAttribute("game", new GameBean());
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/addGameCategory";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving addWeeklyGame in database.
     */
    @RequestMapping(value = {"/addGameCategory"}, method = RequestMethod.POST)
    public String createGameCategory(GameBean gameBean, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        System.out.println("Inside addGameCategory :: ");

        Game game = new Game();
        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());

            return "/admin/addWeeklyGame";

            //result.setCode("" + HttpStatus.BAD_REQUEST);
            //result.setMessage("" + errors.getAllErrors().toString());
            // return ResponseEntity.badRequest().body(result);
        }
        //FileBucket fb = new FileBucket();   
        GameBean fb = new GameBean();

        // fb = fileUpload(fileBucket);
        fb = tunborUtility.fileUpload(gameBean);

        game.setId(fb.getId());
        game.setGameName(fb.getGameName());
        game.setGameCode(fb.getGameCode());
        game.setEnabled(fb.isEnabled());
        game.setColor(fb.getColor());
        game.setGameRules(fb.getGameRules());
        game.setGameImage(fb.getGameImage());
        game.setGameImgLocation(fb.getGameImgLocation());
        //game.setCreationDate(fb.getCreationDate());
        game.setCreationDate(tunborUtility.getDate(Definitions.TIMEZONE));
        game.setLastModificationDate(fb.getLastModificationDate());
        game.setCreatedBy(fb.getCreatedBy());

        boolean saved = gameService.save(game);
        // If not created
        if (!saved) {

            /*
            
            activityLog.setAction("Game Category Creation");
            activityLog.setEvent(Definitions.CREATE);
            activityLog.setUsername(req.getRemoteUser());
            activityLog.setDescription("Game category creation failed");
            activityLog.setActionDate(tunborUtility.getDate(Definitions.TIMEZONE));
            // activityLog.setActionItem("1");
            activityLog.setActionResult(Definitions.FAILED);
            activityLog.setIpaddress(req.getRemoteHost());
            activityLog.setTimezone(tunborUtility.getTimeZone(req).toString());

            activityLogService.save(activityLog);

             */
            model.addAttribute("error", true);
            model.addAttribute("message", " Unable to  Create game category");
            return "admin/addGameCategory";
        }

        model.addAttribute("saved", saved);
        model.addAttribute("message", fb.getGameName() + "  Created successfully");
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("game", new GameBean());

        /*
        activityLog.setAction("Game Category Creation");
        activityLog.setEvent(Definitions.CREATE);
        activityLog.setUsername(req.getRemoteUser());
        activityLog.setDescription("Game category creation successful");
        activityLog.setActionDate(tunborUtility.getDate(Definitions.TIMEZONE));
        // activityLog.setActionItem("1");
        activityLog.setActionResult(Definitions.SUCCESS);
        activityLog.setIpaddress(req.getRemoteHost());
        //activityLog.setTimezone(tunborUtility.getTimeZone(req).toString());
        activityLog.setTimezone(Definitions.TIMEZONE);

        System.out.println("Definitions.CREATE :: " + Definitions.CREATE);
        System.out.println("req.getRemoteUser() :: " + req.getRemoteUser());
        System.out.println("tunborUtility.getDate :: " + tunborUtility.getDate(Definitions.TIMEZONE));
        System.out.println("Definitions.SUCCESS :: " + Definitions.SUCCESS);
        System.out.println("req.getRemoteHost() :: " + req.getRemoteHost());
        
        System.out.println("tunborUtility.getTimeZone(req).toString() :: " + tunborUtility.getTimeZone(req).toString());
        e
        activityLogService.save(activityLog);
        
         */
        return "redirect:/admin/addGameCategory";
    }

    @RequestMapping(value = "/addGameCategory/{id}", method = RequestMethod.GET)
    public ModelAndView getGameCategory(@PathVariable("id") int id, ModelMap map) {
        logger.info("getGameCategory");
        map.addAttribute("game", gameService.findById(id));
        //map.addAttribute("countries", countryService.listCountry());
        // map.addAttribute("listAllBanks", bankService.listAllBanks());
        return new ModelAndView("/admin/addGameCategory", map);
    }

    /**
     * This method will provide the medium to update an game category user.
     */
    @RequestMapping(value = {"/edit-gameCategory-{id}"}, method = RequestMethod.GET)
    public String editgameCategory(@PathVariable int id, ModelMap model) {

        logger.info("Edit  game category");

        Game game = gameService.findById(id);
        String encodedPictureString = "";
        encodedPictureString = tunborUtility.imageToBase64String(game.getGameImgLocation() + game.getGameImage());

        GameBean gameBean = new GameBean();

        gameBean.setId(game.getId());
        gameBean.setGameName(game.getGameName());
        gameBean.setGameCode(game.getGameCode());
        gameBean.setGameRules(game.getGameRules());
        gameBean.setEnabled(game.getEnabled());
        gameBean.setColor(game.getColor());
        gameBean.setColor(game.getColor());
        gameBean.setCreatedBy(game.getCreatedBy());
        //gameBean.setFile();

        //model.addAttribute("game", game);
        model.addAttribute("game", gameBean);
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("edit", true);
        model.addAttribute("encodedPictureString", encodedPictureString);

        return "/admin/addGameCategory";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving addWeeklyGame in database.
     */
    @RequestMapping(value = {"/edit-gameCategory-{id}"}, method = RequestMethod.POST)
    public String updategameCategory(GameBean gameBean, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        System.out.println("Inside editgameCategory :: ");

        Game game = new Game();
        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());

            model.addAttribute("error", true);
            model.addAttribute("message", " Error in form");
            return "admin/addGameCategory";
        }
        GameBean fb = new GameBean();

        fb = tunborUtility.fileUpload(gameBean);

        game.setId(fb.getId());
        game.setGameName(fb.getGameName());
        game.setGameCode(fb.getGameCode());
        game.setEnabled(fb.isEnabled());
        game.setColor(fb.getColor());
        game.setGameRules(fb.getGameRules());
        game.setGameImage(fb.getGameImage());
        game.setGameImgLocation(fb.getGameImgLocation());
        game.setCreationDate(fb.getCreationDate());
        game.setLastModificationDate(tunborUtility.getDate(Definitions.TIMEZONE));
        game.setCreatedBy(fb.getCreatedBy());

        boolean saved = gameService.updateGame(game);
        if (!saved) {

            model.addAttribute("error", true);
            model.addAttribute("message", " Unable to  Update game category");
            return "admin/addGameCategory";
        }

        model.addAttribute("saved", saved);
        model.addAttribute("message", fb.getGameName() + "  Updated successfully");
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("game", new GameBean());

        return "admin/addGameCategory";
    }

    /**
     * This method will delete an user by it's id value.
     */
    @RequestMapping(value = {"/delete-gameCategory-{id}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
        Game game = gameService.findById(id);
        gameService.deleteGame(game);
        return "redirect:/admin/addGameCategory";
    }

    public FileBucket fileUpload(FileBucket fileBucket) {

        MultipartFile[] files = fileBucket.getFiles();
        String originalImgPath = "";
        String resizedImgPath = "";
        //String serverFileName = "";
        String gameImage = "";
        String gameImage2 = "";
        String imgLocation = "";
        int width = 580;
        int height = 450;
        boolean saved = false;
        String serverFileName = "";

        FileBucket fb = new FileBucket();
        String batchid = null;

        //System.out.println("files.length :: " + files.length);
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                batchid = String.valueOf(System.currentTimeMillis());
                try {

                    byte[] bytes = null;
                    // Creating the directory to store file
                    String rootPath = System.getProperty("catalina.home");
                    File dir = new File(rootPath + File.separator + "tmpFiles");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    imgLocation = dir + File.separator;
                    // get files name in the array
                    if (i == 0) {
                        gameImage = files[i].getOriginalFilename();
                        bytes = files[i].getBytes();
                        serverFileName = imgLocation  + "_" + batchid + "_" + gameImage;
                        System.out.println("gameImage:: " + gameImage);
                    } else if (i == 1) {
                        gameImage2 = files[i].getOriginalFilename();
                        bytes = files[i].getBytes();
                        serverFileName = imgLocation +  "_" + batchid + "_" + gameImage2;
                        System.out.println("gameImage2:: " + gameImage2);
                    }

                    System.out.println("serverFileName :: " + serverFileName);

                    // resize image
                    //utility.resize(originalImgPath, resizedImgPath, width, height);
                    //create the file on server
                    File serverFile = new File(serverFileName);
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            fileBucket.setGameImage(gameImage);
            fileBucket.setGameImgLocation(imgLocation);
            fileBucket.setGameImage2(gameImage2);

            System.out.println("fileBucket.get ::" + fileBucket.getGameImage2());
        } else {
            System.out.println("File is empty / No image uploaded");
        }

        return fileBucket;

    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
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
