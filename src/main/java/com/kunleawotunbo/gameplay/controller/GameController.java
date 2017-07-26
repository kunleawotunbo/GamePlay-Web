/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import io.swagger.annotations.Api;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
@RequestMapping("/api/game/")
@Api(value = "Game", description = "Handles the game management")
public class GameController {

    @Autowired
    GameService gameService;    
     
     @Autowired
    private TunborUtility tunborUtility;

    
    CustomResponseBody result = new CustomResponseBody();

    final Logger logger = LoggerFactory.getLogger(getClass());

    
    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String addGame(ModelMap model, HttpServletRequest request) {

        //model.addAttribute("urlPath", request.getLocalAddr());
        //model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("game", new Game());

        return "addCategory";
    }
    
    
    /**
     * Retrieve all games
     *
     * @return
     */
    @RequestMapping(value = "/listgames", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> listGames() {
        logger.info("Inside listGames()");
        List<Game> gameList = null;
        List<Game> gameListFinal = null;
        boolean status = true;
        gameWeek();
        gameList = gameService.listGames(status);
        String imageEncodedString = "";
        
        if (gameList.isEmpty()) {
            return new ResponseEntity<List<Game>>(HttpStatus.NO_CONTENT); //You many decide to return HttpStatus.NOT_FOUND
        }
        
        Game game = null;
         gameListFinal = new ArrayList<Game>();
          for (Game item : gameList) {
              
              if (item.getGameImgLocation() != null && item.getGameImage() != null){
                  imageEncodedString = tunborUtility.imageToBase64String(item.getGameImgLocation() + item.getGameImage());
                  String path = item.getGameImgLocation() + item.getGameImage();
                  try {
                      System.out.println( "URL :: " +   new File(path).toURI().toURL());
                  } catch (MalformedURLException ex) {
                      java.util.logging.Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
            
            game = new Game();

            game.setId(item.getId());
            game.setGameName(item.getGameName());
            game.setGameCode(item.getGameCode());
            game.setEnabled(item.getEnabled());
            game.setCreationDate(item.getCreationDate());
            game.setLastModificationDate(item.getLastModificationDate());           
            game.setLastModifiedBy(item.getLastModifiedBy());
            game.setColor(item.getColor());
            game.setGameRules(item.getGameRules());
            game.setGameImage(item.getGameImage());
             game.setGameImgLocation(imageEncodedString);

            gameListFinal.add(game);
        }
        //return new ResponseEntity<List<Game>>(gameList, HttpStatus.OK);
        return new ResponseEntity<List<Game>>(gameListFinal, HttpStatus.OK);
    }

    /**
     * Retrieve a single game
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Game> getGame(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Game game = gameService.findById(id);
        if (game == null) {
            System.out.println("Game with id " + id + " not found");
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    /**
     * Create new game
     *
     * @param game
     * @param ucBuilder
     * @param request
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createGame(@RequestBody Game game, UriComponentsBuilder ucBuilder, HttpServletRequest request) {
        boolean created = false;
        
        System.out.println("Game Id :: " + game.getId());
        /*
        if (gameService.isGameCodeExist(game.getGameCode())) {
            logger.error("Game code " + game.getGameCode() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        */
        game.setCreationDate(new Date());
        created = gameService.save(game);

        if (created) {

            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Game not created ");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(game.getId()).toUri());
        //return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    /**
     * Update game by id
     *
     * @param id
     * @param game
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Game> updateGame(@PathVariable("id") int id, @RequestBody Game game) {
        System.out.println("Updating Game " + id);

        Game currentGame = gameService.findById(id);

        if (currentGame == null) {
            System.out.println("Game with id " + id + " not found");
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }

        currentGame.setGameCode(game.getGameCode());
        currentGame.setGameName(game.getGameName());
        currentGame.setEnabled(game.getEnabled());
        currentGame.setLastModifiedBy(game.getLastModifiedBy());
        currentGame.setLastModificationDate(new Date());

        gameService.updateGame(currentGame);
        return new ResponseEntity<Game>(currentGame, HttpStatus.OK);
    }

    /**
     * Delete game by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Game> deleteGame(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting gamme with id " + id);

        Game game = gameService.findById(id);
        if (game == null) {
            System.out.println("Unable to delete. Game with id " + id + " not found");
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }

        gameService.deleteGame(game);
        return new ResponseEntity<Game>(HttpStatus.NO_CONTENT);
    }

    /**
     * Create new game
     *
     * @param game
     * @param ucBuilder
     * @param request
     * @return
     * http://localhost:8084/GamePlay/api/game/updatestatus?id=1&enabled=0
     */
    @RequestMapping(value = "/updatestatus", method = RequestMethod.POST)
    public ResponseEntity<Game> updateGameStatus(@RequestBody Game game, HttpServletRequest request) {

        //Game currentGame = gameService.findById(id);
        Game currentGame = gameService.findById(game.getId());

        if (currentGame == null) {
            System.out.println("Game with id " + game.getId() + " not found");
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }
        currentGame.setEnabled(game.getEnabled());
        currentGame.setLastModifiedBy(game.getLastModifiedBy());
        currentGame.setLastModificationDate(new Date());

        gameService.updateGame(currentGame);

        return new ResponseEntity<Game>(currentGame, HttpStatus.OK);
    }

    public void gameWeek() {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int weekNo = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("Week number:"
                + calendar.get(Calendar.WEEK_OF_YEAR));
    }
    
    
     @RequestMapping(value = "/createGametest2", method = RequestMethod.POST)
    public ResponseEntity<Void> createGametest2(@RequestBody Game game, UriComponentsBuilder ucBuilder, HttpServletRequest request) {
        
        System.out.println("createGame2");
        
                HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(game.getId()).toUri());
        //return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
    
    /*
    //@PostMapping(value = "/create")  
    
     @RequestMapping(value = "/createtest", method = RequestMethod.POST)
    public ResponseEntity createWeeklyGame(@RequestBody FileBucket fileBucket, Errors errors) {
        WeeklyGames weeklyGames = new WeeklyGames();
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            System.out.println("There is an error");

            result.setCode("" + HttpStatus.BAD_REQUEST);
            result.setMessage("" + errors.getAllErrors().toString());

            return ResponseEntity.badRequest().body(result);

        }
        FileBucket fb = new FileBucket();

        fb = fileUpload(fileBucket);
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

        if (weeklyGamesService.save(weeklyGames)) {
            // result.getResult("WeeklyGames Created");
            result.setCode("" + HttpStatus.OK);
            result.setMessage("WeeklyGames Created");
            //result.setResult((List<?>) weeklyGames);
        }

        //return new ResponseEntity(weeklyGames, HttpStatus.OK);
        return ResponseEntity.ok(result);
    }    
    
    */
    
    
    /*
    public FileBucket fileUpload(FileBucket fileBucket){
        
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
}
