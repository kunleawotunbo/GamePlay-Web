/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.bean.CustomResponseBody2;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.service.WeeklyGamesAnswersService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import io.swagger.annotations.Api;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

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
    
    @Autowired
    private TunborUtility tunborUtility;
    
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
