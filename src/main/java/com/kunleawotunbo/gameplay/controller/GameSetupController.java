/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import javax.servlet.http.HttpServletRequest;
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
 * @author BELLO
 */
@Controller
public class GameSetupController {
    
     @Autowired
    private GameService gameService;

    @Autowired
    private TunborUtility tunborUtility;

    @Autowired
    private GamePlayTypeService gamePlayTypeService;

    @Autowired
    private WeeklyGamesService weeklyGamesService;

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    
    
    @RequestMapping(value = "/setupGameCategory", method = RequestMethod.GET)
    public String listGameCategory(ModelMap model, HttpServletRequest request) {

        //model.addAttribute("urlPath", request.getLocalAddr());
        //model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("game", new Game());
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/setupGameCategory";
    }
    
    
    
    @RequestMapping(value = "/setup-gameType-{id}", method = RequestMethod.GET)
    public String setupThisGame(@PathVariable int id, ModelMap model)  {

        //model.addAttribute("urlPath", request.getLocalAddr());
        //model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("game", gameService.findById(id));
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/setupThisGameCategory";
    }

    
    
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
