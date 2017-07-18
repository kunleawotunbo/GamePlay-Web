/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesAnswersService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
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

    final Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String adminDashboard(ModelMap model, HttpServletRequest request) {

        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("noOfAnswersSubmitted", weeklyGamesAnswersService.submittedAnswersByWeek(tunborUtility.gameWeek()));
        model.addAttribute("lastWeekTotalAnswers", weeklyGamesAnswersService.submittedAnswersByWeek(tunborUtility.gameWeek() - 1));

        return "/admin/dashboard";
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public ModelAndView testGrid(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        logger.info("inside testGrid");

        ModelMap model = new ModelMap();

        String test = "This is a test";
        boolean status = true;

        model.addAttribute("urlPath", request.getLocalAddr());
        model.addAttribute("request", request);
        model.addAttribute("test", test);
        model.addAttribute("gameList", gameService.listGames(status));

        return new ModelAndView("grid", model);
    }

    @RequestMapping(value = "/listWeeklyGames", method = RequestMethod.GET)
    public String listWeeklyGames(ModelMap model, HttpServletRequest request) {

        model.addAttribute("weeklyGamesList", weeklyGamesService.listWeeklyGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/listWeeklyGames";
    }

    /**
     * This method will provide the medium to add a new user on the home page.
     */
    @RequestMapping(value = {"/testupload"}, method = RequestMethod.GET)
    public String getHome(ModelMap model) {

        FileBucket weeklyGame = new FileBucket();

        boolean status = true;
        // model.addAttribute("weeklyGame", new WeeklyGames());
        model.addAttribute("weeklyGame", new FileBucket());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/testupload";
    }

    /*
    @RequestMapping(value = {"/testupload"}, method = RequestMethod.POST)
    public String createWeeklyGame(@ModelAttribute("weeklyGame") FileBucket fileBucket, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        logger.info("Expiry date: " + fileBucket.getGameExpiryDate());
        MultipartFile[] files = fileBucket.getFiles();
        
        FileBucket fb = new FileBucket();
        WeeklyGames weeklyGames = new WeeklyGames();

        if (result.hasErrors()) {
            System.out.println(result.getFieldErrors());
            //return "registration";
            System.out.println("There is an error");
        }

        //System.out.println("files :: " + files.length);
        
        if (fileBucket.getGamePlayType() == 1) {
            byte yesPicture = 1;
            weeklyGames.setIsPicture(yesPicture);
            logger.info("Is picture : " + yesPicture);
            fb = fileUpload(fileBucket);
            
        }else {
            logger.info("No image is uploaded, type is question");
            fb = fileBucket;
            byte isPicture = 0;
            weeklyGames.setIsPicture(isPicture);
        }
        
        //fb = fileUpload(fileBucket);
        
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
        weeklyGames.setGameImgLocation(fb.getGameImgLocation());
        weeklyGames.setCreatedDate(fb.getCreatedDate());
        weeklyGames.setModifiedDate(fb.getModifiedDate());
        weeklyGames.setCreatedBy(fb.getCreatedBy());        
        weeklyGames.setGameAnswer(fb.getGameAnswer());

        if (weeklyGamesService.save(weeklyGames)) {
            // result.getResult("WeeklyGames Created");
            // result.setCode("" + HttpStatus.OK);
            // result.setMessage("WeeklyGames Created");
            //result.setResult((List<?>) weeklyGames);
            System.out.println("Weekly game created");
        }

        // return "redirect:/testupload";
        return "redirect:/addWeeklyGame"; 
    }
    
    */
/*
    public FileBucket fileUpload(FileBucket fileBucket) {

        MultipartFile[] files = fileBucket.getFiles();
        String originalImgPath = "";
        String resizedImgPath = "";
        //String serverFileName = "";
        String gameImage = "";
        String itemViewName = "";
        String imgLocation = "";
        int width = 580;
        int height = 450;
        boolean saved = false;
        String serverFileName = "";

        FileBucket fb = new FileBucket();

        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {

                    byte[] bytes = null;
                    // Creating the directory to store file
                    String rootPath = System.getProperty("catalina.home");
                    File dir = new File(rootPath + File.separator + "tmpFiles");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    FilenameUtils fileUTIL = new FilenameUtils();

                    //String path = req.getServletContext().getRealPath("/image");
                    //String ext = fileUTIL.getExtension(file.getOriginalFilename());
                    //String baseName = fileUTIL.getBaseName(file.getOriginalFilename());
                    imgLocation = dir + File.separator;
                    // get files name in the array
                    if (i == 0) {
                        gameImage = files[i].getOriginalFilename();
                        bytes = files[i].getBytes();
                        serverFileName = imgLocation + gameImage;
                        System.out.println("gameImage:: " + gameImage);
                    } else if (i == 1) {
                        itemViewName = files[i].getOriginalFilename();
                        bytes = files[i].getBytes();
                        serverFileName = imgLocation + itemViewName;
                        System.out.println("itemViewName:: " + itemViewName);
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

        } else {
            System.out.println("File is empty / No image uploaded");
        }

        return fileBucket;

    }
*/

    @RequestMapping(value = "/addWeeklyGame", method = RequestMethod.GET)
    public String addWeeklyGame(ModelMap model, HttpServletRequest request) {

        boolean status = true;
        //model.addAttribute("weeklyGame", new WeeklyGames());
        model.addAttribute("weeklyGame", new FileBucket());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/addWeeklyGame";
    }
    
           /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/addWeeklyGame"}, method = RequestMethod.POST)
    public String addWeeklyGame( FileBucket fileBucket, BindingResult result,
            ModelMap model, HttpServletRequest req) {

             System.out.println("Inside registerUser :: "  );
        /*
             
        MultipartFile[] files = fileBucket.getFiles();
        String originalImgPath = "";
        String resizedImgPath = "";
        //String serverFileName = "";
        String photoName = "";
        String itemViewName = "";
        String imgLocation = "";
        int width = 580;
        int height = 450;
        boolean saved = false;
        String serverFileName = "";

        FileBucket fb = new FileBucket();
        //User user = new User();
        if (result.hasErrors()) {
            System.out.println("Error in form:: " +   result.getFieldError());

            return "/admin/addWeeklyGame";
        }
        if (files != null && files.length > 0) {
             System.out.println("Step 3:: " +   files);
            for (int i = 0; i < files.length; i++) {
                try {
                    
                    System.out.println("Step 4" );
                    byte[] bytes = null;
                    // Creating the directory to store file
                    String rootPath = System.getProperty("catalina.home");
                    File dir = new File(rootPath + File.separator + "tmpFiles");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    FilenameUtils fileUTIL = new FilenameUtils();

                    String path = req.getServletContext().getRealPath("/image");
                    //String ext = fileUTIL.getExtension(file.getOriginalFilename());
                    //String baseName = fileUTIL.getBaseName(file.getOriginalFilename());

                    imgLocation = dir + File.separator;
                    // get files name in the array
                    if (i == 0) {
                        photoName = files[i].getOriginalFilename();
                        bytes = files[i].getBytes();
                        serverFileName = imgLocation + photoName;
                        System.out.println("photoName:: " + photoName);
                    } else if (i == 1) {
                        itemViewName = files[i].getOriginalFilename();
                        bytes = files[i].getBytes();
                        serverFileName = imgLocation + itemViewName;
                        System.out.println("itemViewName:: " + itemViewName);
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
         
        } else {
            System.out.println("File is empty / No image uploaded");
        }

        //model.addAttribute("user", user);
        model.addAttribute("user", fb);
        //model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " saved successfully");
        //model.addAttribute("saved", saved);

        //return "adduser";
        //return "redirect:/adduser";
        return "redirect:/admin/addWeeklyGame";
        
        */
        
         WeeklyGames weeklyGames = new WeeklyGames();
        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");
            
                        System.out.println("Error in form:: " +   result.getFieldError());

            return "/admin/addWeeklyGame";

            //result.setCode("" + HttpStatus.BAD_REQUEST);
            //result.setMessage("" + errors.getAllErrors().toString());

           // return ResponseEntity.badRequest().body(result);

        }
        FileBucket fb = new FileBucket();
        
        if (fileBucket.getGamePlayType() == 1) {
            byte yesPicture = 1;
            fileBucket.setIsPicture(yesPicture);
            logger.info("Is picture : " + yesPicture);
            
            // Do the upload 
            fb = fileUpload(fileBucket);
            
        }else {
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
        weeklyGames.setGameImgLocation(fb.getGameImgLocation());
        weeklyGames.setCreatedDate(fb.getCreatedDate());
        weeklyGames.setModifiedDate(fb.getModifiedDate());
        weeklyGames.setCreatedBy(fb.getCreatedBy());
        weeklyGames.setIsPicture(fb.getIsPicture());
        weeklyGames.setGameAnswer(fb.getGameAnswer());

        boolean saved = weeklyGamesService.save(weeklyGames);
        if (saved) {
            // result.getResult("WeeklyGames Created");
           // result.setCode("" + HttpStatus.OK);
           // result.setMessage("WeeklyGames Created");
            //result.setResult((List<?>) weeklyGames);
            model.addAttribute("saved", saved);
            model.addAttribute("success", "Weeklygame Created successfully");
            //return "admin/addWeeklyGame";
            
            return "redirect:/admin/addWeeklyGame";
        }

        //return new ResponseEntity(weeklyGames, HttpStatus.OK);
        //return ResponseEntity.ok(result);
        // return "admin/addWeeklyGame";
        
         return "redirect:/admin/addWeeklyGame";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/set-weeklyGames-Answer-{id}"}, method = RequestMethod.GET)
    public String setWeeklyGamesAnswer(@PathVariable int id, ModelMap model) {

        logger.info("Edit  editWeeklyGames id :: " + id);
        //byte status = 1;
        boolean status = true;
        model.addAttribute("weeklyGame", weeklyGamesService.findById(id));
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("edit", true);

        return "/admin/setWeeklyAnswer";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-weeklyGames-{id}"}, method = RequestMethod.GET)
    public String editWeeklyGames(@PathVariable int id, ModelMap model) {

        logger.info("Edit  editWeeklyGames id :: " + id);
        //byte status = 1;
        boolean status = true;
        model.addAttribute("weeklyGame", weeklyGamesService.findById(id));
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("edit", true);

        return "/admin/addWeeklyGame";
    }

    /**
     * This method will delete an user by it's id value.
     */
    @RequestMapping(value = {"/delete-weeklyGames-{id}"}, method = RequestMethod.GET)
    public String deleteWeklyGames(@PathVariable int id) {
        logger.info("Delete  deleteWeklyGames id :: " + id);
        WeeklyGames weeklyGames = weeklyGamesService.findById(id);
        weeklyGamesService.deleteGame(weeklyGames);

        return "redirect:/listWeeklyGames";
    }

    @RequestMapping(value = "/addGameCategory", method = RequestMethod.GET)
    public String addGameCategory(ModelMap model, HttpServletRequest request) {

        model.addAttribute("game", new Game());
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/addGameCategory";
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
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-gameCategory-{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable int id, ModelMap model) {

        logger.info("Edit  game category");

        model.addAttribute("game", gameService.findById(id));
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("edit", true);

        return "/admin/addGameCategory";
    }

    /**
     * This method will delete an user by it's id value.
     */
    @RequestMapping(value = {"/delete-gameCategory-{id}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
        Game game = gameService.findById(id);
        gameService.deleteGame(game);
        return "redirect:/addGameCategory";
    }
    
    public FileBucket fileUpload(FileBucket fileBucket) {

        MultipartFile[] files = fileBucket.getFiles();
        String originalImgPath = "";
        String resizedImgPath = "";
        //String serverFileName = "";
        String gameImage = "";
        String itemViewName = "";
        String imgLocation = "";
        int width = 580;
        int height = 450;
        boolean saved = false;
        String serverFileName = "";

        FileBucket fb = new FileBucket();

        System.out.println("files.length :: " + files.length);
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {

                    byte[] bytes = null;
                    // Creating the directory to store file
                    String rootPath = System.getProperty("catalina.home");
                    File dir = new File(rootPath + File.separator + "tmpFiles");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    FilenameUtils fileUTIL = new FilenameUtils();

                    //String path = req.getServletContext().getRealPath("/image");
                    //String ext = fileUTIL.getExtension(file.getOriginalFilename());
                    //String baseName = fileUTIL.getBaseName(file.getOriginalFilename());
                    imgLocation = dir + File.separator;
                    // get files name in the array
                    if (i == 0) {
                        gameImage = files[i].getOriginalFilename();
                        bytes = files[i].getBytes();
                        serverFileName = imgLocation + gameImage;
                        System.out.println("gameImage:: " + gameImage);
                    } else if (i == 1) {
                        itemViewName = files[i].getOriginalFilename();
                        bytes = files[i].getBytes();
                        serverFileName = imgLocation + itemViewName;
                        System.out.println("itemViewName:: " + itemViewName);
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

            //System.out.println("bytes ::" + bytes);
          
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
