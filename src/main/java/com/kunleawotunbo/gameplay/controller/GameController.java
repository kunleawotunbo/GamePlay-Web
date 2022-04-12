/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.interfaces.Definitions;
import com.kunleawotunbo.gameplay.model.ActivityLog;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.service.ActivityLogService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
@RequestMapping("/api/game/")
@Api(value = "Game", description = "Handles the game management")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    private TunborUtility tunborUtility;

    @Autowired
    private ActivityLogService activityLogService;

    CustomResponseBody result = new CustomResponseBody();
    ActivityLog activityLog = new ActivityLog();

    final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String addGame(ModelMap model, HttpServletRequest request) {
        model.addAttribute("game", new Game());

        return "addCategory";
    }

    /**
     * Retrieve all games
     *
     * @return
     */
    @RequestMapping(value = "/listgames", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> listGames() {
        logger.info("Inside listGames()");
        List<Game> gameList = null;
        List<Game> gameListFinal = null;
        boolean status = true;
        gameWeek();
        gameList = gameService.listGames(status);
        String imageEncodedString = "";

        if (gameList.isEmpty()) {
            return new ResponseEntity<List<Game>>(HttpStatus.NO_CONTENT); //You many decide to return HttpStatus.NOT_FOUND
        }

        Game game = null;
        gameListFinal = new ArrayList<Game>();
        for (Game item : gameList) {

            if (item.getGameImgLocation() != null && item.getGameImage() != null) {
                imageEncodedString = tunborUtility.imageToBase64String(item.getGameImgLocation() + item.getGameImage());
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
        return new ResponseEntity<List<Game>>(gameListFinal, HttpStatus.OK);
    }

    /**
     * Retrieve a single game
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Game> getGame(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Game game = gameService.findById(id);
        if (game == null) {
            System.out.println("Game with id " + id + " not found");
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    /**
     * Create new game
     *
     * @param game
     * @param ucBuilder
     * @param request
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createGame(@RequestBody Game game, UriComponentsBuilder ucBuilder, HttpServletRequest request) {
        boolean created = false;

        System.out.println("Game Id :: " + game.getId());
        game.setCreationDate(tunborUtility.getDate(Definitions.TIMEZONE));
        //game.setCreationDate(new Date());
        created = gameService.save(game);

        if (created) {

            activityLog.setAction("Game Category Creation");
            activityLog.setEvent(Definitions.CREATE);
            activityLog.setUsername(request.getRemoteUser());
            activityLog.setDescription("Game category creation successful");
            activityLog.setActionDate(tunborUtility.getDate(Definitions.TIMEZONE));
           // activityLog.setActionItem("1");
            activityLog.setActionResult(Definitions.SUCCESS);
            activityLog.setIpaddress(request.getRemoteHost());
            activityLog.setTimezone(tunborUtility.getTimeZone(request).toString());

            activityLogService.save(activityLog);

            /*
            
            activityLogService.saveActivityLog("Game Category Creation", 1, request.getRemoteUser(), 
                    description, actionDate,
                    actionItem, actionResult, ipaddress, timezone);
            
             */
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Game not created ");

            activityLog.setAction("Game Category Creation");
            activityLog.setEvent(Definitions.CREATE);
            activityLog.setUsername(request.getRemoteUser());
            activityLog.setDescription("Game category creation failed");
            activityLog.setActionDate(tunborUtility.getDate(Definitions.TIMEZONE));
           // activityLog.setActionItem("1");
            activityLog.setActionResult(Definitions.FAILED);
            activityLog.setIpaddress(request.getRemoteHost());
            activityLog.setTimezone(tunborUtility.getTimeZone(request).toString());

            activityLogService.save(activityLog);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(game.getId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    /**
     * Update game by id
     *
     * @param id
     * @param game
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Game> updateGame(@PathVariable("id") int id, @RequestBody Game game) {
        System.out.println("Updating Game " + id);

        Game currentGame = gameService.findById(id);

        if (currentGame == null) {
            System.out.println("Game with id " + id + " not found");
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }

        currentGame.setGameCode(game.getGameCode());
        currentGame.setGameName(game.getGameName());
        currentGame.setEnabled(game.getEnabled());
        currentGame.setLastModifiedBy(game.getLastModifiedBy());
        currentGame.setLastModificationDate(new Date());

        gameService.updateGame(currentGame);
        return new ResponseEntity<Game>(currentGame, HttpStatus.OK);
    }

    /**
     * Delete game by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Game> deleteGame(@PathVariable("id") int id, HttpServletRequest request) {
        System.out.println("Fetching & Deleting gamme with id " + id);

        Game game = gameService.findById(id);
        if (game == null) {
            System.out.println("Unable to delete. Game with id " + id + " not found");

            activityLog.setAction("Game Category Delete");
            activityLog.setEvent(4);
            activityLog.setUsername(request.getRemoteUser());
            activityLog.setDescription("Game category deletion successful");
            activityLog.setActionDate(tunborUtility.getDate(Definitions.TIMEZONE));
            activityLog.setActionItem("4");
            activityLog.setActionResult("FAILED");
            activityLog.setIpaddress(request.getRemoteHost());
            activityLog.setTimezone(tunborUtility.getTimeZone(request).toString());

            activityLogService.save(activityLog);

            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }

        gameService.deleteGame(game);

        activityLog.setAction("Game Category Delete");
        activityLog.setEvent(4);
        activityLog.setUsername(request.getRemoteUser());
        activityLog.setDescription("Game category deletion successful");
        activityLog.setActionDate(tunborUtility.getDate(Definitions.TIMEZONE));
        activityLog.setActionItem("4");
        activityLog.setActionResult("SUCCESS");
        activityLog.setIpaddress(request.getRemoteHost());
        activityLog.setTimezone(tunborUtility.getTimeZone(request).toString());

        activityLogService.save(activityLog);

        return new ResponseEntity<Game>(HttpStatus.NO_CONTENT);
    }

    /**
     * Create new game
     *
     * @param game
     * @param ucBuilder
     * @param request
     * @return
     * http://localhost:8084/GamePlay/api/game/updatestatus?id=1&enabled=0
     */
    @RequestMapping(value = "/updatestatus", method = RequestMethod.POST)
    public ResponseEntity<Game> updateGameStatus(@RequestBody Game game, HttpServletRequest request) {

        //Game currentGame = gameService.findById(id);
        Game currentGame = gameService.findById(game.getId());

        if (currentGame == null) {
            System.out.println("Game with id " + game.getId() + " not found");
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }
        currentGame.setEnabled(game.getEnabled());
        currentGame.setLastModifiedBy(game.getLastModifiedBy());
        currentGame.setLastModificationDate(new Date());

        gameService.updateGame(currentGame);

        return new ResponseEntity<Game>(currentGame, HttpStatus.OK);
    }

    public void gameWeek() {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int weekNo = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("Week number:"
                + calendar.get(Calendar.WEEK_OF_YEAR));
    }

    @RequestMapping(value = "/createGametest2", method = RequestMethod.POST)
    public ResponseEntity<Void> createGametest2(@RequestBody Game game, UriComponentsBuilder ucBuilder, HttpServletRequest request) {

        System.out.println("createGame2");

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(game.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

 
}
