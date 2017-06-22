/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
//@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private GameService gameService;
    
    @Autowired
    private TunborUtility tunborUtility;
    
    @Autowired 
    private GamePlayTypeService gamePlayTypeService;
    
    @Autowired
    private WeeklyGamesService weeklyGamesService;
    
        final Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String adminDashboard(ModelMap model, HttpServletRequest request) {
 
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/dashboard";
    }
    
       @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public ModelAndView testGrid(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        logger.info("inside testGrid");
        
        ModelMap model = new ModelMap();
        
        String test = "This is a test";
         boolean status = true;
         
        model.addAttribute("urlPath", request.getLocalAddr());
        model.addAttribute("request", request);
        model.addAttribute("test", test);
        model.addAttribute("gameList", gameService.listGames(status));
        
        return new ModelAndView("grid", model);
    }
    
      @RequestMapping(value = "/listWeeklyGames", method = RequestMethod.GET)
    public String listWeeklyGames(ModelMap model, HttpServletRequest request) {
      
        
        model.addAttribute("weeklyGamesList", weeklyGamesService.listWeeklyGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/listWeeklyGames";
    }
    
      @RequestMapping(value = "/addWeeklyGame", method = RequestMethod.GET)
    public String addWeeklyGame(ModelMap model, HttpServletRequest request) {
  
        boolean status = true;
        model.addAttribute("weeklyGame", new WeeklyGames());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/addWeeklyGame";
    }

       /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/set-weeklyGames-Answer-{id}"}, method = RequestMethod.GET)
    public String setWeeklyGamesAnswer(@PathVariable int id, ModelMap model) {
        
        logger.info("Edit  editWeeklyGames id :: " + id);
        //byte status = 1;
        boolean status = true;
        model.addAttribute("weeklyGame", weeklyGamesService.findById(id));        
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("edit", true);
        
        return "/admin/setWeeklyAnswer";
    }
    
     /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-weeklyGames-{id}"}, method = RequestMethod.GET)
    public String editWeeklyGames(@PathVariable int id, ModelMap model) {
        
        logger.info("Edit  editWeeklyGames id :: " + id);
        //byte status = 1;
        boolean status = true;
        model.addAttribute("weeklyGame", weeklyGamesService.findById(id));        
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("edit", true);
        
        return "/admin/addWeeklyGame";
    }
    
     /**
     * This method will delete an user by it's id value.
     */
    @RequestMapping(value = {"/delete-weeklyGames-{id}"}, method = RequestMethod.GET)
    public String deleteWeklyGames(@PathVariable int id) {
        logger.info("Delete  deleteWeklyGames id :: " + id);
        WeeklyGames weeklyGames = weeklyGamesService.findById(id);
        weeklyGamesService.deleteGame(weeklyGames);
        
        return "redirect:/listWeeklyGames";
    }

    @RequestMapping(value = "/addGameCategory", method = RequestMethod.GET)
    public String addGameCategory(ModelMap model, HttpServletRequest request) {
      
        model.addAttribute("game", new Game());
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/addGameCategory";
    }
    
     @RequestMapping(value = "/addGameCategory/{id}",  method = RequestMethod.GET)
    public ModelAndView getGameCategory(@PathVariable("id") int id, ModelMap map) {
        logger.info("getGameCategory");
        map.addAttribute("game", gameService.findById(id));
        //map.addAttribute("countries", countryService.listCountry());
       // map.addAttribute("listAllBanks", bankService.listAllBanks());
        return new ModelAndView("/admin/addGameCategory", map);
    }
    
     /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-gameCategory-{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable int id, ModelMap model) {
        
        logger.info("Edit  game category");
       
        model.addAttribute("game", gameService.findById(id));
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("edit", true);
        
        return "/admin/addGameCategory";
    }
    
     
    /**
     * This method will delete an user by it's id value.
     */
    @RequestMapping(value = {"/delete-gameCategory-{id}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
        Game game = gameService.findById(id);
        gameService.deleteGame(game);
        return "redirect:/addGameCategory";
    }
    
   
    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
