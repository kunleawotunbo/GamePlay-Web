/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.User;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
public class PredictionController {
    
    
    final Logger logger = LoggerFactory.getLogger(getClass()); 
    
    @RequestMapping(value = "/prediction", method = RequestMethod.GET)
    public String getPredictionPage(ModelMap model, HttpServletRequest request) {

       
       // model.addAttribute("weekNo", tunborUtility.gameWeek());
       // model.addAttribute("loggedinuser", getPrincipal());
        //model.addAttribute("noOfAnswersSubmitted", weeklyGamesAnswersService.submittedAnswersByWeek(tunborUtility.gameWeek()));
      //  model.addAttribute("lastWeekTotalAnswers", weeklyGamesAnswersService.submittedAnswersByWeek(tunborUtility.gameWeek() - 1));

        return "prediction";
    }
}
