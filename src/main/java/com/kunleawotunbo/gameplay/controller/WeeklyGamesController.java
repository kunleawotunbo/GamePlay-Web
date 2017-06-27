/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.bean.CustomResponseBody2;
import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import io.swagger.annotations.Api;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Olakunle Awotunbo
 */
//@RestController
@Controller
@RequestMapping("/api/weeklygames/")
@Api(value = "Game", description = "Handles the game management")
public class WeeklyGamesController {

    // http://viralpatel.net/blogs/spring-4-mvc-rest-example-json/
    @Autowired
    private WeeklyGamesService weeklyGamesService;

    @Autowired
    private GamePlayTypeService gamePlayTypeService;
   

    final Logger logger = LoggerFactory.getLogger(getClass());

    CustomResponseBody result = new CustomResponseBody();
    CustomResponseBody2 result2 = new CustomResponseBody2();

    @GetMapping("/weekgame")
    public List getCustomers() {
        return (List) weeklyGamesService.getWeekGameByWeekNo(1, 1);
    }

    @GetMapping("/getWeekGameByWeekNoAndCat/{gameCategory}/{weekNo}")
    public ResponseEntity getWeekGameByWeekNoAndCat(@PathVariable int gameCategory, @PathVariable int weekNo) {
        WeeklyGames weeklyGames = new WeeklyGames();
        weeklyGames = weeklyGamesService.getWeekGameByWeekNo(gameCategory, weekNo);
        if (weeklyGames == null) {
            
            result2.setCode("204");
            result2.setMessage("no weekly games found!");
            result2.setResult(weeklyGames);
            return new ResponseEntity(result2, HttpStatus.NOT_FOUND);
            // return new ResponseEntity(result2, HttpStatus.NO_CONTENT);
        } else {

            result2.setCode("200");
            result2.setMessage("success");
            result2.setResult(weeklyGames);
        }
        return new ResponseEntity(result2, HttpStatus.OK);
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

    /*
    @PostMapping(value = "/create")
    public ResponseEntity createWeeklyGame(@RequestBody WeeklyGames weeklyGames, Errors errors) {

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setCode("" + HttpStatus.BAD_REQUEST);
            result.setMessage("" + errors.getAllErrors().toString());

            return ResponseEntity.badRequest().body(result);

        }
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
    
    @PostMapping(value = "/create")
    public ResponseEntity createWeeklyGame(@RequestBody FileBucket fileBucket , Errors errors) {
        WeeklyGames weeklyGames = new WeeklyGames();
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

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
            /*
            user.setFirstName(fileBucket.getFirstName());
            user.setLastName(fileBucket.getLastName());
            user.setPhoneNumber(fileBucket.getPhoneNumber());
            user.setItemView(fileBucket.getItemView());
            user.setAddress(fileBucket.getAddress());
            user.setPassportPhotograph(photoName);
            user.setImgLocation(imgLocation);
            user.setImgName(photoName);
            user.setImgItemName(itemViewName);

            saved = userService.saveUser(user);

            */
        } else {
            System.out.println("File is empty / No image uploaded");
        }
        
        return fileBucket;
    
    }
    
    /**
     * Set weekly game answer
     * @param weeklyGames
     * @param errors
     * @return 
     */
      @PostMapping(value = "/setanswer")
    public ResponseEntity setWeeklyGameAnswer(@RequestBody WeeklyGames wGames, Errors errors) {

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
        // set the game answer
        weeklyGames.setGameAnswer(wGames.getGameAnswer());
        weeklyGames.setModifiedDate(new Date());
        if (weeklyGamesService.save(weeklyGames)) {
            logger.info("Answer set for question id :: "  + weeklyGames.getId());
            result.setCode("" + HttpStatus.OK);
            result.setMessage("WeeklyGames Created");
           
        }
       
        return ResponseEntity.ok(result);
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

        //if (null == customer) {
        //    return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        // }
        return new ResponseEntity(id, HttpStatus.OK);
    }

}
