/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.service.GameService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Olakunle Awotunbo
 */
public class HomeController implements Controller {

    // http://www.codejava.net/frameworks/spring/bootstrapping-a-spring-web-mvc-application-programmatically
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private GameService gameService;

    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        logger.info("inside HomeController");
        
        ModelMap model = new ModelMap();
        
        String test = "This is a test";
         boolean status = true;
         
        model.addAttribute("urlPath", request.getLocalAddr());
        model.addAttribute("request", request);
        model.addAttribute("test", test);
        model.addAttribute("gameList", gameService.listGames(status));
        
        return new ModelAndView("home", model);
    }

    /*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request) {
        System.out.println("I am inside index");
        List<Game> gameList = null;
        boolean status = true;
        //gameWeek();
        //gameList = gameService.listGames(status);
       // logger.info("gameList :: " + gameList);
        model.addAttribute("urlPath", request.getLocalAddr());
        model.addAttribute("request", request);
     //   model.addAttribute("gameList", gameList);

        return "index";
    }
     */
    
  
}
