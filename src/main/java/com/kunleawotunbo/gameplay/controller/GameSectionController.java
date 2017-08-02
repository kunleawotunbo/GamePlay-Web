/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
public class GameSectionController {

    @Autowired
    private GameService gameService;

    @Autowired
    private TunborUtility tunborUtility;

    @Autowired
    private WeeklyGamesService weeklyGamesService;

    final Logger logger = LoggerFactory.getLogger(getClass());

    /*
    @RequestMapping(value = {"/gameSection-{id}-{gameCode}"}, method = RequestMethod.GET)
    public ModelAndView gameSection(@PathVariable("id") int id, @PathVariable("gameCode") String gameCode, ModelMap map) {
        logger.info("getGameCategory");

        //WeeklyGames weeklyGame = weeklyGamesService.getWeekGameByWeekNo(id, tunborUtility.gameWeek());
        WeeklyGames weeklyGame = weeklyGamesService.getWeekGameByWeekNo(id, tunborUtility.gameWeekNoByDate(new Date()));
        boolean isPicture = false;
        String encodedPictureString = "";
        
        if (null != weeklyGame && weeklyGame.getIsPicture() == 1) {
           encodedPictureString = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());
           isPicture = true;
        }else {
            logger.info("No image");
        }


        map.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswers());
        map.addAttribute("weeklyGame", weeklyGame);
        map.addAttribute("isPicture", isPicture);
        map.addAttribute("encodedPictureString", encodedPictureString);

        return new ModelAndView("gameSection", map);
    }
    
     */
    @RequestMapping(value = {"/gameSection-{id}-{gameCode}"}, method = RequestMethod.GET)
    public ModelAndView gameCatSection(@PathVariable("id") int id, @PathVariable("gameCode") String gameCode, ModelMap map) {
        logger.info("gameCatSection- id ::  " + id);

        List<WeeklyGames> weeklyGameList = null;
        //weeklyGameList = weeklyGamesService.listWeekGamesByCateAndDate(id, new Date());
        weeklyGameList = weeklyGamesService.listWeekGamesByCateAndDate(id, tunborUtility.getDate("Africa/Nigeria"));

        // check if weeklyGameList is greater than 1
        if (null != weeklyGameList && weeklyGameList.size() > 1) {
            // Show list of games on the page
            map.addAttribute("weeklyGameList", weeklyGameList);
            map.addAttribute("gameCode", gameCode);

            return new ModelAndView("gameSectionList", map);

        } else {
            WeeklyGames weeklyGame;
       
           weeklyGame = weeklyGameList.size() == 0 ? null : (WeeklyGames) weeklyGameList.get(0);
           System.out.println("weeklyGame :: " + weeklyGame);
            boolean isPicture = false;
            String encodedPictureString = "";

            if (null != weeklyGame && weeklyGame.getIsPicture() == 1) {
                encodedPictureString = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());
                isPicture = true;
            } else {
                logger.info("No image");
            }

            map.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswers());
            map.addAttribute("weeklyGame", weeklyGame);
            map.addAttribute("isPicture", isPicture);
            map.addAttribute("encodedPictureString", encodedPictureString);
            map.addAttribute("gameCode", gameCode);

            return new ModelAndView("gameSection", map);
        }
            

        /*
        
        WeeklyGames weeklyGame = weeklyGamesService.getWeekGameByWeekNo(id, tunborUtility.gameWeekNoByDate(new Date()));
        boolean isPicture = false;
        String encodedPictureString = "";

        if (null != weeklyGame && weeklyGame.getIsPicture() == 1) {
            encodedPictureString = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());
            isPicture = true;
        } else {
            logger.info("No image");
        }

        map.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswers());
        map.addAttribute("weeklyGame", weeklyGame);
        map.addAttribute("isPicture", isPicture);
        map.addAttribute("encodedPictureString", encodedPictureString);

        return new ModelAndView("gameSection", map);
        
        */
    }
    
        @RequestMapping(value = {"/gameSectionx-{id}-{gameCode}"}, method = RequestMethod.GET)
    public ModelAndView gameSection(@PathVariable("id") int id, @PathVariable("gameCode") String gameCode, ModelMap map) {
        logger.info("getGameCategory");

        //WeeklyGames weeklyGame = weeklyGamesService.getWeekGameByWeekNo(id, tunborUtility.gameWeek());
        WeeklyGames weeklyGame = weeklyGamesService.getWeekGameByWeekNo(id, tunborUtility.gameWeekNoByDate(new Date()));
        //weeklyGameList = weeklyGamesService.listWeekGamesByCateAndDate(id, tunborUtility.getDate("Africa/Nigeria"));
        boolean isPicture = false;
        String encodedPictureString = "";
        
        if (null != weeklyGame && weeklyGame.getIsPicture() == 1) {
           encodedPictureString = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());
           isPicture = true;
        }else {
            logger.info("No image");
        }


        map.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswers());
        map.addAttribute("weeklyGame", weeklyGame);
        map.addAttribute("isPicture", isPicture);
        map.addAttribute("encodedPictureString", encodedPictureString);

        return new ModelAndView("gameSection", map);
    }

    @RequestMapping(value = {"/congratulations.html"}, method = RequestMethod.GET)
    public ModelAndView getCongratlations(ModelMap map) {
        logger.info("congratulations");

        return new ModelAndView("congratulations", map);
    }
}
