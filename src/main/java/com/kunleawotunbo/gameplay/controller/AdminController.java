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
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String adminDashboard(ModelMap model, HttpServletRequest request) {
        List<Game> gameList = null;
        byte status = 1;
//       
//        gameList = gameService.listGames(status);
//
//        model.addAttribute("urlPath", request.getLocalAddr());
//        model.addAttribute("gameplaytype", gamePlayTypeService.getGamePlayType());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/dashboard";
    }

    @RequestMapping(value = "/addGameCategory", method = RequestMethod.GET)
    public String addGameCategory(ModelMap model, HttpServletRequest request) {
        List<Game> gameList = null;
        byte status = 1;
        gameList = gameService.listGames(status);

        model.addAttribute("gameList", gameList);
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/addGameCategory";
    }
    
     @RequestMapping(value = "/addWeeklyGame", method = RequestMethod.GET)
    public String addWeeklyGame(ModelMap model, HttpServletRequest request) {
        //List<Game> gameList = null;
        byte status = 1;
      //  gameList = gameService.listGames(status);
        
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/addWeeklyGame";
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
