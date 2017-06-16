/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.GamePlayType;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import io.swagger.annotations.Api;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    GameService gameService;
    
    
        @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String adminDashboard(ModelMap model, HttpServletRequest request) {
        List<Game> gameList = null;
        byte status = 1;
//       
//        gameList = gameService.listGames(status);
//
//        model.addAttribute("urlPath", request.getLocalAddr());
//        model.addAttribute("gameplaytype", gamePlayTypeService.getGamePlayType());
//        model.addAttribute("game", new WeeklyGames());
//        model.addAttribute("gameList", gameList);

        return "/admin/dashboard";
    }
    
       @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(ModelMap model, HttpServletRequest request) {
        List<Game> gameList = null;
        byte status = 1;
//       
//        gameList = gameService.listGames(status);
//
//        model.addAttribute("urlPath", request.getLocalAddr());
//        model.addAttribute("gameplaytype", gamePlayTypeService.getGamePlayType());
//        model.addAttribute("game", new WeeklyGames());
//        model.addAttribute("gameList", gameList);

        return "test";
    }
    
      @RequestMapping(value = "/addGame", method = RequestMethod.GET)
    public String addGame(ModelMap model, HttpServletRequest request) {
        List<Game> gameList = null;
        byte status = 1;
       
        gameList = gameService.listGames(status);

        model.addAttribute("urlPath", request.getLocalAddr());
        model.addAttribute("gameplaytype", gamePlayTypeService.getGamePlayType());
        model.addAttribute("game", new WeeklyGames());
        model.addAttribute("gameList", gameList);

        return "addgame";
    }

    @GetMapping("/weekgame")
    public List getCustomers() {
        return (List) weeklyGamesService.getWeekGameByWeekNo(1, 1);
    }

    @GetMapping("/gametype")
    public List getGameType() {
        // return  weeklyGamesService.getGamePlayType();
        List<GamePlayType> list = gamePlayTypeService.getGamePlayType();
        //System.out.println("list :: " + list);
        //        return  list;

        return gamePlayTypeService.getGamePlayType();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getCustomer(@PathVariable("id") int id) {
        
        WeeklyGames weeklyGames = weeklyGamesService.findById(id);        
        
        if (weeklyGames == null) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(weeklyGames, HttpStatus.OK);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity createCustomer(@RequestBody WeeklyGames weeklyGames) {

        weeklyGamesService.save(weeklyGames);

        return new ResponseEntity(weeklyGames, HttpStatus.OK);
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
