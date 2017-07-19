/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
    
     @Autowired
    private TunborUtility tunborUtility;

    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        logger.info("inside HomeController");
        
        ModelMap model = new ModelMap();
        
        String test = "This is a test";
        List<Game> gameList = null;
        List<Game> gameListFinal = null;
         boolean status = true;
         gameList = gameService.listGames(status);
         String imageEncodedString = "";
         
         Game game = null;
         gameListFinal = new ArrayList<Game>();
          for (Game item : gameList) {
              
              if (item.getGameImgLocation() != null && item.getGameImage() != null){
                  imageEncodedString = tunborUtility.imageToBase64tring(item.getGameImgLocation() + item.getGameImage());
                  String path = item.getGameImgLocation() + item.getGameImage();
                  try {
                      System.out.println( "URL :: " +   new File(path).toURI().toURL());
                  } catch (MalformedURLException ex) {
                      java.util.logging.Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
         
        model.addAttribute("urlPath", request.getLocalAddr());
        model.addAttribute("request", request);
        model.addAttribute("test", test);
        //model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("gameList", gameListFinal);
        
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
