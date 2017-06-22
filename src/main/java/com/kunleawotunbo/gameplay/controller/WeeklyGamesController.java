/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.bean.CustomResponseBody2;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.GamePlayType;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import io.swagger.annotations.Api;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    
    /**
     * Set weekly game answer
     * @param weeklyGames
     * @param errors
     * @return 
     */
      @PostMapping(value = "/setanswer")
    public ResponseEntity setWeeklyGameAnswer(@RequestBody WeeklyGames weeklyGames, Errors errors) {

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setCode("" + HttpStatus.BAD_REQUEST);
            result.setMessage("" + errors.getAllErrors().toString());

            return ResponseEntity.badRequest().body(result);

        }
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
