/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.support.RequestContextUtils;

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
        
         String offsetString = tunborUtility.getTimeOffset(request) == null ? "0" : tunborUtility.getTimeOffset(request);
        System.out.println("offsetString :: " + offsetString);
        TimeZone timeZone = tunborUtility.getTimeZone2(Integer.parseInt(offsetString));
        logger.info("timezone :: " + timeZone);
        request.getSession().setAttribute("timezone", timeZone);
        
        List<Game> gameList = null;
        List<Game> gameListFinal = null;
         boolean status = true;
         gameList = gameService.listGames(status);
         String imageEncodedString = "";
         
         /*
         if(gameService.findByName("Jackpot Number") == false)  {
             
        Game jackpotNumbergame = new Game(); 
        jackpotNumbergame.setGameCode("JNUM");
        jackpotNumbergame.setGameName("Jackpot Number");
        jackpotNumbergame.setEnabled(true);
        jackpotNumbergame.setCreationDate(new Date());
        jackpotNumbergame.setCreatedBy(tunborUtility.getPrincipal());
        
        //boolean saveStatus = gameService.save(jackpotNumbergame);
         gameService.save(jackpotNumbergame); 
         }
         */
         
         Game game = null;
         gameListFinal = new ArrayList<Game>();
          for (Game item : gameList) {
              game = new Game();
              
              if (item.getGameImgLocation() != null && item.getGameImage() != null){
                  imageEncodedString = tunborUtility.imageToBase64String(item.getGameImgLocation() + item.getGameImage());
                  //String path = item.getGameImgLocation() + item.getGameImage();
                  
                  game.setGameImgLocation(imageEncodedString);
             
                  
              }else {
                  System.out.println("item.getGameImgLocation() is null" );
              }
            
            

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
            //game.setGameImgLocation(imageEncodedString);

            gameListFinal.add(game);
        }
         
        model.addAttribute("urlPath", request.getLocalAddr());
        model.addAttribute("request", request);        
        model.addAttribute("gameList", gameListFinal);
        
        return new ModelAndView("home", model);
        
    }

    
  
}
