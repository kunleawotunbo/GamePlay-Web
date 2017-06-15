/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.service.GameService;
import io.swagger.annotations.Api;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Retrieve all games
     *
     * @return
     */
    @RequestMapping(value = "/listgames", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> listGames() {
        logger.info("Inside listGames()");
        List<Game> gameList = null;
        byte status = 1;
        gameWeek();
        gameList = gameService.listGames(status);

        if (gameList.isEmpty()) {
            return new ResponseEntity<List<Game>>(HttpStatus.NO_CONTENT); //You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Game>>(gameList, HttpStatus.OK);
    }

    /**
     * Retrieve a single game
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Game> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Game game = gameService.findById(id);
        if (game == null) {
            System.out.println("User with id " + id + " not found");
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

        if (gameService.isGameCodeExist(game.getGameCode())) {
            logger.error("Game code " + game.getGameCode() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        game.setCreationDate(new Date());
        created = gameService.save(game);

        if (created) {

            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Game not created ");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(game.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
    public ResponseEntity<Game> deleteGame(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting gamme with id " + id);

        Game game = gameService.findById(id);
        if (game == null) {
            System.out.println("Unable to delete. Game with id " + id + " not found");
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }

        gameService.deleteGame(game);
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
}
