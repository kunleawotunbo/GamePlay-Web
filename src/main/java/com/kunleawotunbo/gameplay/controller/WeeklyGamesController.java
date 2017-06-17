/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.GamePlayType;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import io.swagger.annotations.Api;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Olakunle Awotunbo
 */
//@RestController
@Controller
@RequestMapping("/api/weeklygames/")
@Api(value = "Game", description = "Handles the game management")
public class WeeklyGamesController {

    // http://viralpatel.net/blogs/spring-4-mvc-rest-example-json/
    @Autowired
    private WeeklyGamesService weeklyGamesService;

    @Autowired
    private GamePlayTypeService gamePlayTypeService;

    @Autowired
    private GameService gameService;

    final Logger logger = LoggerFactory.getLogger(getClass());

    CustomResponseBody result = new CustomResponseBody();

    @GetMapping("/weekgame")
    public List getCustomers() {
        return (List) weeklyGamesService.getWeekGameByWeekNo(1, 1);
    }

    @GetMapping("/test")
    public List getGameType() {
        // List<GamePlayType> list = gamePlayTypeService.getGamePlayType();
        WeeklyGames weeklyGames = new WeeklyGames();
        Integer a = 1;
        weeklyGames.setCreatedBy("admin");
        weeklyGames.setCreatedDate(new Date());
        weeklyGames.setGameCategory(1);
        weeklyGames.setGameExpiryDate(new Date());
        weeklyGames.setGameImage("fddfds");
        weeklyGames.setGameImgLocation("gfgfgffg");
        weeklyGames.setGamePlayType(a);
        weeklyGames.setGameText("sssa");
        weeklyGames.setGameRules("dfsdfsf");
        weeklyGames.setNoOfWinners(100);
        weeklyGames.setPrizeOfWinners("10000");
        weeklyGames.setWeekNo(24);
        //  weeklyGames.set

        weeklyGamesService.save(weeklyGames);

        return gamePlayTypeService.getGamePlayType();
    }

    @GetMapping("/listWeeklyGames")
    public ResponseEntity<?> listWeeklyGames() {

        List<WeeklyGames> weeklyGames = weeklyGamesService.listWeeklyGames();

        if (weeklyGames.isEmpty()) {
            result.setCode("204");
            result.setMsg("no weekly games found!");
        } else {
            result.setCode("200");
            result.setMsg("success");
            result.setResult(weeklyGames);
        }
        

        return ResponseEntity.ok(result);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getCustomer(@PathVariable("id") int id) {

        WeeklyGames weeklyGames = weeklyGamesService.findById(id);

        if (weeklyGames == null) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(weeklyGames, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity createCustomer( @RequestBody WeeklyGames weeklyGames, Errors errors) {

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setCode("" + HttpStatus.BAD_REQUEST);
            result.setMsg("" + errors.getAllErrors().toString());

            return ResponseEntity.badRequest().body(result);

        }
        if (weeklyGamesService.save(weeklyGames)) {
            // result.getResult("WeeklyGames Created");
            result.setCode("" + HttpStatus.OK);
            result.setMsg("WeeklyGames Created");
            //result.setResult((List<?>) weeklyGames);
        }

        //return new ResponseEntity(weeklyGames, HttpStatus.OK);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity deleteCustomer(@PathVariable int id) {

        WeeklyGames weeklyGames = weeklyGamesService.findById(id);

        if (null == weeklyGames) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }
        weeklyGamesService.deleteGame(weeklyGames);

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/customers/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody WeeklyGames weeklyGames) {

        // weeklyGames =
        weeklyGamesService.updateWeeklyGame(weeklyGames);

        //if (null == customer) {
        //    return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        // }
        return new ResponseEntity(id, HttpStatus.OK);
    }

}
