/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.bean.CustomResponseBody2;
import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.service.WeeklyGamesAnswersService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import io.swagger.annotations.Api;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Olakunle Awotunbo
 */

@RestController
//@Controller
@RequestMapping("/api/weeklygamesanswers/")
@Api(value = "Game", description = "Handles the game management")
@SessionAttributes("roles")



public class WeeklyGamesAnswersController {
    
    @Autowired
    private WeeklyGamesService weeklyGamesService;
    
    @Autowired
    private WeeklyGamesAnswersService weeklyGamesAnswersService;
    
    CustomResponseBody result = new CustomResponseBody();
    CustomResponseBody2 result2 = new CustomResponseBody2();
    
    final Logger logger = LoggerFactory.getLogger(getClass());    
  
    
     @GetMapping("/weeklyGameById/{id}")
    public ResponseEntity getWeeklyGamesAnswers(@PathVariable("id") Long id) {

        WeeklyGamesAnswers weeklyGamesAnswers = weeklyGamesAnswersService.findById(id);

        if (weeklyGamesAnswers == null) {
            return new ResponseEntity("No WeeklyGamesAnswers found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(weeklyGamesAnswers, HttpStatus.OK);
    }
    
    /**
     * 
     * @param weeklyGames
     * @param errors
     * @return 
     */
    
    @PostMapping(value = "/setanswer")
    public ResponseEntity createWeeklyGame(@RequestBody WeeklyGamesAnswers weeklyGamesAnswers, Errors errors) {
        
        // set answer submitted date
        weeklyGamesAnswers.setDateAnswered(new Date());

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setCode("" + HttpStatus.BAD_REQUEST);
            result.setMessage("" + errors.getAllErrors().toString());

            return ResponseEntity.badRequest().body(result);

        }
        if (weeklyGamesAnswersService.saveWeeklyGamesAnswer(weeklyGamesAnswers)) {           
            result.setCode("" + HttpStatus.OK);
            result.setMessage("Answer submitted");
            //result.setResult((List<?>) weeklyGames);
        }
       
        return ResponseEntity.ok(result);
    }
    
    
   

                    

                
   
         
        
    /*
    
   @PostMapping(value = "/create2")
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

            //System.out.println("bytes ::" + bytes);
          
        } else {
            System.out.println("File is empty / No image uploaded");
        }

        return fileBucket;

    }
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
