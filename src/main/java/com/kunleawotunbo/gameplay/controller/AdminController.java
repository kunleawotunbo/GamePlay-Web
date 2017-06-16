/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Game;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
}
