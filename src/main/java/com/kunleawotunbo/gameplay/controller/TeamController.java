/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody2;
import com.kunleawotunbo.gameplay.model.Country;
import com.kunleawotunbo.gameplay.model.League;
import com.kunleawotunbo.gameplay.model.Team;
import com.kunleawotunbo.gameplay.service.CountryService;
import com.kunleawotunbo.gameplay.service.LeagueService;
import com.kunleawotunbo.gameplay.service.TeamService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
@RequestMapping("/admin/")
public class TeamController {
    
    @Autowired
    private CountryService countryService;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private TunborUtility tunborUtility;
    
    @Autowired
    private TeamService teamService;
    
    CustomResponseBody2 result2 = new CustomResponseBody2();

    final Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/manageTeam", method = RequestMethod.GET)
    public String getManageteam(ModelMap model, HttpServletRequest request) {

        model.addAttribute("team", new Team());
        model.addAttribute("countriesList", countryService.listCountries());
        model.addAttribute("leagueList", leagueService.listLeagues());
        model.addAttribute("teamList", teamService.listTeams());
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/manageTeam";
    }

    /**
     * To save/ create new league
     *
     * @param league
     * @param result
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = {"/manageTeam"}, method = RequestMethod.POST)
    public String saveSaveTeam(Team team, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        boolean saved = false;

        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());

            return "/admin/manageTeam";

        }

        //Country country = countryService.getCountryById(league.getCountryId());

        //league.setCountryName(country.getCountryName());
        //league.setCountryCode(country.getCountryCode());
        //saved = leagueService.saveLeague(league);
        saved = teamService.saveTeam(team);
        // If not saved
        if (!saved) {

            model.addAttribute("error", true);
            model.addAttribute("message", "Team Creation failed");

            return "admin/manageTeam";
        }

        model.addAttribute("saved", saved);
        model.addAttribute("message", "Team Created successfully");
        model.addAttribute("league", new League());

        return "redirect:/admin/manageTeam";
    }

    /**
     * Get edit league page
     */
    @RequestMapping(value = {"/edit-team-{id}"}, method = RequestMethod.GET)
    public String editTeam(@PathVariable int id, ModelMap model) {

        logger.info("Edit team id :: " + id);

        Team team = teamService.getTeamById(id);

        model.addAttribute("edit", true);
        model.addAttribute("team", team);
        model.addAttribute("countriesList", countryService.listCountries());
        model.addAttribute("leagueList", leagueService.listLeagues());
        model.addAttribute("teamList", teamService.listTeams());
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());


        return "/admin/manageTeam";

    }

    @RequestMapping(value = {"/edit-team-{id}"}, method = RequestMethod.POST)
    public String updateTeam(Team team, BindingResult result,
            ModelMap model, HttpServletRequest req) {

        
        //If error, just return a 400 bad request, along with the error message
        if (result.hasErrors()) {
            System.out.println("There is an error");

            System.out.println("Error in form:: " + result.getFieldError());
            model.addAttribute("error", true);
            model.addAttribute("message", "Error occured while updating weekly game");
            // populate field with the current object
            model.addAttribute("team", team);

            return "/admin/manageTeam";

        }

       // Country country = countryService.getCountryById(league.getCountryId());

        //league.setCountryName(country.getCountryName());
        //league.setCountryCode(country.getCountryCode());
        
        //boolean saved = leagueService.updateLeague(league);
        boolean saved = teamService.updateTeam(team);
        if (!saved) {
            // unable to update 

            model.addAttribute("error", true);
            model.addAttribute("message", "Weeklygame Creation failed");

            return "admin/manageTeam";
        }

        /*
        boolean status = true;

        model.addAttribute("saved", saved);
        model.addAttribute("message", "Weeklygame Updated successfully");
        model.addAttribute("weeklyGame", new FileBucket());
        model.addAttribute("weekNo", tunborUtility.gameWeek());
        model.addAttribute("gamePlayTypeList", gamePlayTypeService.getGamePlayType());
        model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("weeklyGame", new FileBucket());

        return "admin/manageLeague";
        
        */
        
        return "redirect:/admin/manageTeam";

    }

    /**
     * *
     * Delete team by team Id
     *
     * @param id Team id to delete
     * @return
     */
    @RequestMapping(value = {"/delete-team-{id}"}, method = RequestMethod.GET)
    public String deleteTeam(@PathVariable int id) {
        logger.info("Delete  deleteTeam id :: " + id);
        Team team = teamService.getTeamById(id);
        teamService.deleteTeam(team);

        return "redirect:/admin/manageTeam";
    }
    
    /***
     * Web service to list teams by league code
     * @param leagueCode
     * @return 
     */
    @GetMapping("/getTeamByLeagueCode/{leagueCode}")
    public ResponseEntity getTeamByLeagueCode(@PathVariable String leagueCode) {
        List<Team> teamList = null;       

        System.out.println("leagueCode :: " + leagueCode);
        teamList = teamService.listTeamsByLeagueCode(leagueCode);

        if (teamList.isEmpty() || teamList == null) {
            logger.info("No team found! ");
            result2.setCode("204");
            result2.setMessage("No team found!");
            result2.setResult(teamList);
            return new ResponseEntity(result2, HttpStatus.NO_CONTENT);
        } else {
            result2.setCode("200");
            result2.setMessage("success");
            result2.setResult(teamList);
            return new ResponseEntity(result2, HttpStatus.OK);
        }

    }
    
}
