/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.JackpotFieldsBean;
import com.kunleawotunbo.gameplay.bean.SMSConfigBean;
import com.kunleawotunbo.gameplay.interfaces.Definitions;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import com.kunleawotunbo.gameplay.utility.WebServiceUtility;
import java.util.Arrays;
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

    @Autowired
    private SMSConfigBean smsConfigBean;

    WebServiceUtility webServiceUtility = new WebServiceUtility();

    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Get game section page by id and game code, if game in the category is
     * more than one, then got to gamesection list page to list the games
     *
     * @param id
     * @param gameCode
     * @param map
     * @return
     */
    @RequestMapping(value = {"/gameSection-{id}-{gameCode}"}, method = RequestMethod.GET)
    public ModelAndView gameCatSection(@PathVariable("id") int id, @PathVariable("gameCode") String gameCode, ModelMap map) {
        logger.info("gameCatSection - id ::  " + id);

        List<WeeklyGames> weeklyGameList = null;

        // Game jackpotNumberGame = gameService.findByNameReturnGame("Jackpot Number");
        // gameService.
        logger.info("gameCode ::  " + gameCode);
        if (gameCode.equalsIgnoreCase("MPP")) {
            return new ModelAndView("redirect:/prediction");
        } else if (gameCode.equalsIgnoreCase("JNUM")) {

            //}    
            //if(id == jackpotNumberGame.getId()){
            // weeklyGameList = weeklyGamesService.listWeekGamesByCateAndDate(jackpotNumberGame.getId(), tunborUtility.getDate(Definitions.TIMEZONE));
            weeklyGameList = weeklyGamesService.listWeekGamesByCateAndDate(id, tunborUtility.getDate(Definitions.TIMEZONE));

            // check if weeklyGameList is greater than 1
            if (null != weeklyGameList && weeklyGameList.size() > 1) {
                // Show list of games on the page
                map.addAttribute("weeklyGameList", weeklyGameList);
                map.addAttribute("gameCode", gameCode);

                return new ModelAndView("gameSectionListJackpotNumber", map);

            } else {
                WeeklyGames weeklyGame;

                weeklyGame = weeklyGameList.size() == 0 ? null : (WeeklyGames) weeklyGameList.get(0);
                System.out.println("weeklyGame Jackpot Number :: " + weeklyGame);
                boolean isPicture = false;
                boolean hasGameImage2 = false;
                String encodedPictureString = "";
                String encodedGameImage2 = "";

                if (null != weeklyGame && weeklyGame.getIsPicture() == 1) {
                    encodedPictureString = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());
                    if (weeklyGame.getGameImage2() != null) {
                        encodedGameImage2 = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage2());
                        hasGameImage2 = true;
                    }
                    isPicture = true;
                } else {
                    logger.info("No image");
                }

                if (null != weeklyGame && weeklyGame.getGamePlayType() == 3) {
                    // Since itsJackpot game, we need to put the numbers in tiny fields
                    
                    System.out.println("weeklyGame.getGameText() :: " + weeklyGame.getGameText());
                    String[] numberList = weeklyGame.getGameText().split("-");
                    

                    System.out.println("numberList :: " + numberList.toString());
                    
                     map.addAttribute("numberList", numberList);
                } else {
                    logger.info("Not Jackpot game");
                }

                map.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswers());
                map.addAttribute("weeklyGame", weeklyGame);
                map.addAttribute("isPicture", isPicture);
                map.addAttribute("encodedPictureString", encodedPictureString);
                map.addAttribute("encodedGameImage2", encodedGameImage2);
                map.addAttribute("hasGameImage2", hasGameImage2);
                map.addAttribute("gameCode", gameCode);

                return new ModelAndView("gameSectionJackpotNumber", map);
            }

        } else {

            weeklyGameList = weeklyGamesService.listWeekGamesByCateAndDate(id, tunborUtility.getDate(Definitions.TIMEZONE));

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
                boolean hasGameImage2 = false;
                String encodedPictureString = "";
                String encodedGameImage2 = "";

                if (null != weeklyGame && weeklyGame.getIsPicture() == 1) {
                    encodedPictureString = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());
                    if (weeklyGame.getGameImage2() != null) {
                        encodedGameImage2 = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage2());
                        hasGameImage2 = true;
                    }
                    isPicture = true;
                } else {
                    logger.info("No image");
                }

                map.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswers());
                map.addAttribute("weeklyGame", weeklyGame);
                map.addAttribute("isPicture", isPicture);
                map.addAttribute("encodedPictureString", encodedPictureString);
                map.addAttribute("encodedGameImage2", encodedGameImage2);
                map.addAttribute("hasGameImage2", hasGameImage2);
                map.addAttribute("gameCode", gameCode);

                return new ModelAndView("gameSection", map);
            }

        }

    }

    /**
     * Get game section by id and game code
     *
     * @param id
     * @param gameCode
     * @param map
     * @return
     */
    @RequestMapping(value = {"/gameSectionx-{id}-{gameCode}"}, method = RequestMethod.GET)
    public ModelAndView gameSection(@PathVariable("id") int id, @PathVariable("gameCode") String gameCode, ModelMap map) {
        logger.info("gameSection");

        Game jackpotNumberGame = gameService.findByNameReturnGame("Jackpot Number");
        logger.info("jackpotNumberGame.getId() :: " + jackpotNumberGame.getId());
        if (weeklyGamesService.findById(id).getGameCategory() == jackpotNumberGame.getId()) {
            logger.info("Inside here ... ");
            WeeklyGames weeklyGame = weeklyGamesService.getWeekGameByWeekNo(id, tunborUtility.gameWeekNoByDate(tunborUtility.getDate(Definitions.TIMEZONE)));

            boolean isPicture = false;
            String encodedPictureString = "";

            if (null != weeklyGame && weeklyGame.getIsPicture() == 1) {
                encodedPictureString = tunborUtility.imageToBase64String(weeklyGame.getGameImgLocation() + weeklyGame.getGameImage());
                isPicture = true;
            } else {
                logger.info("No image");
            }

            // Since itsJackpot game, we need to put the numbers in tiny fields
            JackpotFieldsBean jackpotFieldsBean = new JackpotFieldsBean();
            System.out.println("weeklyGame.getGameText() :: " + weeklyGame.getGameText());
            String[] numberList = weeklyGame.getGameText().split("-");

            System.out.println("numberList :: " + numberList.toString());

            map.addAttribute("weeklyGamesAnswers", new WeeklyGamesAnswers());
            map.addAttribute("weeklyGame", weeklyGame);
            map.addAttribute("numberList", numberList);
            map.addAttribute("isPicture", isPicture);
            map.addAttribute("encodedPictureString", encodedPictureString);

            return new ModelAndView("gameSectionJackpotNumber", map);

        } else {

            WeeklyGames weeklyGame = weeklyGamesService.getWeekGameByWeekNo(id, tunborUtility.gameWeekNoByDate(tunborUtility.getDate(Definitions.TIMEZONE)));

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
        }

    }

    /**
     * Go to congratulations page
     *
     * @param map
     * @return
     */
    @RequestMapping(value = {"/congratulations.html"}, method = RequestMethod.GET)
    public ModelAndView getCongratlations(ModelMap map) {
        logger.info("congratulations");

        return new ModelAndView("congratulations", map);
    }

    /**
     * Go to Match prediction congratulations page
     *
     * @param map
     * @return
     */
    @RequestMapping(value = {"/mpcongratulations"}, method = RequestMethod.GET)
    public ModelAndView goMpcongratulationsPage(ModelMap map) {
        logger.info("tryanotheranswer");
        boolean status = false;

        if (status) {
            return new ModelAndView("congratulations", map);
        }

        return new ModelAndView("mpcongratulations", map);
    }
}
