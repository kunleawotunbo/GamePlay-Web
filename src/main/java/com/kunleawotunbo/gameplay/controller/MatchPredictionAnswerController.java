/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.bean.CustomResponseBody2;
import com.kunleawotunbo.gameplay.interfaces.Definitions;
import com.kunleawotunbo.gameplay.model.MatchPredictionAnswer;
import com.kunleawotunbo.gameplay.service.MatchPredictionAnswerService;
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
@RequestMapping("/api/matchpredictionanswer/")
@Api(value = "Match Prediction", description = "Handles the match prediction answer")
//@SessionAttributes("roles")
public class MatchPredictionAnswerController {  
    
    @Autowired
    private MatchPredictionAnswerService matchPredictionAnswerService;
    
    @Autowired
    private TunborUtility tunborUtility;
    
    CustomResponseBody result = new CustomResponseBody();
    CustomResponseBody2 result2 = new CustomResponseBody2();
    
    final Logger logger = LoggerFactory.getLogger(getClass()); 
    
     @GetMapping("/matchPredictionById/{id}")
    public ResponseEntity getmatchPredictionById(@PathVariable("id") int id) {

        MatchPredictionAnswer matchPredictionAnswer = matchPredictionAnswerService.findById(id);

        if (matchPredictionAnswer == null) {
            return new ResponseEntity("No matchPredictionAnswer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(matchPredictionAnswer, HttpStatus.OK);
    }
    
    /**
     * 
     * @param weeklyGames
     * @param errors
     * @return 
     */
    
    @PostMapping(value = "/submitanswer")
    public ResponseEntity submitMactPredictionAnswer(@RequestBody MatchPredictionAnswer matchPredictionAnswer, Errors errors) {
        
        // set answer submitted date
        matchPredictionAnswer.setDateAnswered(tunborUtility.getDate(Definitions.TIMEZONE));

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setCode("" + HttpStatus.BAD_REQUEST);
            result.setMessage("" + errors.getAllErrors().toString());

            return ResponseEntity.badRequest().body(result);

        }
        if (matchPredictionAnswerService.saveMatchPredictionAnswer(matchPredictionAnswer)) {           
            result.setCode("" + HttpStatus.OK);
            result.setMessage("Answer submitted");
            
        }
       
        return ResponseEntity.ok(result);
    }   
    
}