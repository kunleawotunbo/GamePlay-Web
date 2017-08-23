/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.bean.CustomResponseBody2;
import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.GameAnswer;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.service.GameAnswerService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import io.swagger.annotations.Api;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Olakunle Awotunbo
 */
//@RestController
@Controller
@RequestMapping("/api/weeklygames/")
@Api(value = "WeeklyGames", description = "Handles the game management")
public class WeeklyGamesController {
    
    @Autowired
    private WeeklyGamesService weeklyGamesService;

    @Autowired
    private TunborUtility tunborUtility;

    @Autowired
    private GameAnswerService gameAnswerService;

    final Logger logger = LoggerFactory.getLogger(getClass());

    CustomResponseBody result = new CustomResponseBody();
    CustomResponseBody2 result2 = new CustomResponseBody2();

    @GetMapping("/weekgame")
    public List getCustomers() {
        return (List) weeklyGamesService.getWeekGameByWeekNo(1, 1);
    }

    @GetMapping("/getWeekGameByWeekNoAndCat/{gameCategory}/{dateString}")
    public ResponseEntity getWeekGameByWeekNoAndCat(@PathVariable int gameCategory, @PathVariable String dateString) {
        List<WeeklyGames> weeklyGameList = null;
        List<WeeklyGames> weeklyGameListFinal = null;

        System.out.println("dateString :: " + dateString);

        DateFormat inputFormat = new SimpleDateFormat(
                "E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);
        Date date = null;
        try {
            date = inputFormat.parse(dateString);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(WeeklyGamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("date :: " +  date);
        int weekNo = tunborUtility.gameWeekNoByDate(date);
        // weeklyGames = weeklyGamesService.getWeekGameByWeekNo(id, weekNo);
        weeklyGameList = weeklyGamesService.listWeekGamesByCateAndDate(gameCategory, tunborUtility.getDate("Africa/Nigeria"));

        String encodedPictureString = "";
        String encodedGameImage2 = "";

        if (weeklyGameList.isEmpty() || weeklyGameList == null) {
            logger.info("No weekly games found! ");
            result2.setCode("204");
            result2.setMessage("No weekly games found!");
            result2.setResult(weeklyGameList);
            return new ResponseEntity(result2, HttpStatus.NO_CONTENT);
        } else {
            WeeklyGames weeklyGame = null;
            weeklyGameListFinal = new ArrayList<WeeklyGames>();
            for (WeeklyGames item : weeklyGameList) {
                weeklyGame = new WeeklyGames();
                if (item.getGameImgLocation() != null && item.getGameImage() != null) {
                    encodedPictureString = tunborUtility.imageToBase64String(item.getGameImgLocation() + item.getGameImage());
                    weeklyGame.setGameImgLocation(encodedPictureString);
                    if (item.getGameImage2() != null) {                      
                    encodedGameImage2 = tunborUtility.imageToBase64String(item.getGameImgLocation() + item.getGameImage2());
                    
                    }
                    weeklyGame.setGameImage2(encodedGameImage2);

                }

                weeklyGame.setId(item.getId());
                weeklyGame.setWeekNo(item.getWeekNo());
                weeklyGame.setPrizeOfWinners(item.getPrizeOfWinners());
                weeklyGame.setNoOfWinners(item.getNoOfWinners());
                weeklyGame.setGameExpiryDate(item.getGameExpiryDate());
                weeklyGame.setGameRules(item.getGameRules());
                weeklyGame.setGameCategory(item.getGameCategory());
                weeklyGame.setGamePlayType(item.getGamePlayType());
                weeklyGame.setGameText(item.getGameText());
                weeklyGame.setGameImage(item.getGameImage());
                // weeklyGame.setGameImgLocation(item.getGameImgLocation());
                weeklyGame.setCreatedDate(item.getCreatedDate());
                weeklyGame.setModifiedDate(item.getModifiedDate());
                weeklyGame.setCreatedBy(item.getCreatedBy());
                weeklyGame.setIsPicture(item.getIsPicture());
                weeklyGame.setGameAnswer(item.getGameAnswer());
                // weeklyGame.setGameImgLocation(imageEncodedString);

                weeklyGameListFinal.add(weeklyGame);
            }

            result2.setCode("200");
            result2.setMessage("success");
            //result2.setResult(weeklyGameList);
            result2.setResult(weeklyGameListFinal);
            return new ResponseEntity(result2, HttpStatus.OK);
        }

    }

    @GetMapping("/getWeekGameByWeekNoAndId/{weeklyGameId}/{dateString}")
    public ResponseEntity getWeekGameByWeekNoAndId(@PathVariable int weeklyGameId, @PathVariable String dateString) {
        WeeklyGames weeklyGame = new WeeklyGames();
        //List<WeeklyGames> weeklyGameList = null;

        System.out.println("dateString :: " + dateString);

        DateFormat inputFormat = new SimpleDateFormat(
                "E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);
        Date date = null;
        try {
            date = inputFormat.parse(dateString);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(WeeklyGamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(date);

        int weekNo = tunborUtility.gameWeekNoByDate(date);
        // weeklyGames = weeklyGamesService.getWeekGameByWeekNo(id, weekNo);
        // weeklyGameList = weeklyGamesService.listWeekGamesByCateAndDate(gameCategory, tunborUtility.getDate("Africa/Nigeria"));
        //weeklyGame = weeklyGamesService.getWeekGameByWeekNo(weeklyGameId, tunborUtility.gameWeekNoByDate(new Date()));
        weeklyGame = weeklyGamesService.findById(weeklyGameId);

        String encodedPictureString = "";
        String encodedGameImage2 = "";

        if (weeklyGame == null) {

            result2.setCode("204");
            result2.setMessage("no weekly games found!");
            result2.setResult(weeklyGame);
            //return new ResponseEntity(result2, HttpStatus.NOT_FOUND);
            return new ResponseEntity(result2, HttpStatus.NO_CONTENT);
        } else {

            System.out.println("weeklyGame :: " + weeklyGame);
            boolean isPicture = false;

            if (null != weeklyGame && weeklyGame.getIsPicture() == 1) {
                encodedPictureString = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());
                isPicture = true;
                weeklyGame.setGameImgLocation(encodedPictureString);
                if (weeklyGame.getGameImage2() != null) {
                    encodedGameImage2 = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage2());
                    }
                    weeklyGame.setGameImage2(encodedGameImage2);
            } else {
                logger.info("No image");
            }

            result2.setCode("200");
            result2.setMessage("success");
            result2.setResult(weeklyGame);

            return new ResponseEntity(result2, HttpStatus.OK);
        }

    }

    @GetMapping("/listWeeklyGames")
    public ResponseEntity<?> listWeeklyGames() {

        List<WeeklyGames> weeklyGames = weeklyGamesService.listWeeklyGames();

        if (weeklyGames.isEmpty()) {
            result.setCode("204");
            result.setMessage("no weekly games found!");
        } else {
            result.setCode("200");
            result.setMessage("success");
            result.setResult(weeklyGames);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/weeklyGameById/{id}")
    public ResponseEntity weeklyGameById(@PathVariable("id") int id) {

        WeeklyGames weeklyGames = weeklyGamesService.findById(id);

        if (weeklyGames == null) {
            return new ResponseEntity("No WeeklyGames found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(weeklyGames, HttpStatus.OK);
    }

 


    //@RequestMapping(value = "/create", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    @PostMapping(value = "/create")
    public ResponseEntity createWeeklyGame(@RequestBody FileBucket fileBucket, Errors errors) {
        System.out.println("I am here oooo");
    
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
            result.setCode("" + HttpStatus.OK);
            result.setMessage("WeeklyGames Created");
            
        }

        return ResponseEntity.ok(result);
    }

    public FileBucket fileUpload(FileBucket fileBucket) {

        // MultipartFile[] files = fileBucket.getFiles();
        MultipartFile file = fileBucket.getFile();
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

        if (file != null && !file.isEmpty()) {
            // for (int i = 0; i < files.length; i++) {
            try {

                byte[] bytes = null;
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                FilenameUtils fileUTIL = new FilenameUtils();

                imgLocation = dir + File.separator;
                // get files name in the array

                gameImage = file.getOriginalFilename();
                bytes = file.getBytes();
                serverFileName = imgLocation + gameImage;
                System.out.println("gameImage:: " + gameImage);
            
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
            // }

            fileBucket.setGameImage(gameImage);
            fileBucket.setGameImgLocation(imgLocation);

        } else {
            System.out.println("File is empty / No image uploaded");
        }

        return fileBucket;

    }

    /**
     * Set weekly game answer
     *
     * @param weeklyGames
     * @param errors
     * @return
     */
    @PostMapping(value = "/setanswer")
    public ResponseEntity setWeeklyGameAnswer(@RequestBody WeeklyGames wGames, Errors errors) {
        boolean saved;
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setCode("" + HttpStatus.BAD_REQUEST);
            result.setMessage("" + errors.getAllErrors().toString());

            return ResponseEntity.badRequest().body(result);

        }

        WeeklyGames weeklyGames = weeklyGamesService.findById(wGames.getId());

        if (weeklyGames == null) {
            return new ResponseEntity("No WeeklyGames found for ID " + wGames.getId(), HttpStatus.NOT_FOUND);
        }

        GameAnswer gameAnswer = new GameAnswer();
        GameAnswer findGame = gameAnswerService.findByGameId(wGames.getId());
        if (findGame != null) {
            logger.info("Updating answer for game :: " + wGames.getId());
            gameAnswer.setId(findGame.getId());
            gameAnswer.setGameId(weeklyGames.getId());
            gameAnswer.setGameCategoryId(weeklyGames.getGameCategory());
            gameAnswer.setWeekNo(weeklyGames.getWeekNo());
            gameAnswer.setGameAnswer(wGames.getGameAnswer());
            gameAnswer.setCreatedBy(findGame.getCreatedBy());
            //gameAnswer.setCreatedDate(tunborUtility.getDate("Africa/Nigeria"));
            gameAnswer.setModifiedBy(wGames.getCreatedBy());
            gameAnswer.setModifiedDate(tunborUtility.getDate("Africa/Nigeria"));
            saved = gameAnswerService.updateGame(gameAnswer);

        } else {
            // set weekly answer for games/question

            gameAnswer.setGameId(weeklyGames.getId());
            gameAnswer.setGameCategoryId(weeklyGames.getGameCategory());
            gameAnswer.setWeekNo(weeklyGames.getWeekNo());
            gameAnswer.setGameAnswer(wGames.getGameAnswer());
            gameAnswer.setCreatedBy(wGames.getCreatedBy());
            gameAnswer.setCreatedDate(tunborUtility.getDate("Africa/Nigeria"));
            //gameAnswer.setModifiedBy(modifiedBy);
            //gameAnswer.setModifiedDate(modifiedDate);
            saved = gameAnswerService.save(gameAnswer);

        }

        if (saved) {
            logger.info("Answer set for question id :: " + weeklyGames.getId());
            result.setCode("" + HttpStatus.OK);
            result.setMessage("Answer set for question");

            return ResponseEntity.ok(result);
        } else {
            result.setCode("" + HttpStatus.BAD_REQUEST);
            result.setMessage("Answer set for question");
            return ResponseEntity.ok(result);
        }

    }

    @DeleteMapping("/deleteWeeklyGameById/{id}")
    public ResponseEntity deleteCustomer(@PathVariable int id) {

        WeeklyGames weeklyGames = weeklyGamesService.findById(id);

        if (null == weeklyGames) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }
        weeklyGamesService.deleteGame(weeklyGames);

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/updateWeeklyGame/{id}")
    public ResponseEntity updateWeeklyGame(@PathVariable Long id, @RequestBody WeeklyGames weeklyGames) {

        // weeklyGames =
        weeklyGamesService.updateWeeklyGame(weeklyGames);

        return new ResponseEntity(id, HttpStatus.OK);
    }    

}
