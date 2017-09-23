/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.TeamDao;
import com.kunleawotunbo.gameplay.model.Team;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("teamService")
public class TeamServiceImpl implements TeamService{
    
    @Autowired
    private TeamDao teamDao;

    public Team getTeamById(int id) {
        return teamDao.getTeamById(id);
    }

    public Team getTeamByShortName(String shortName) {
        return teamDao.getTeamByShortName(shortName);
    }

    public boolean saveTeam(Team team) {
        return teamDao.saveTeam(team);
    }

    public boolean updateTeam(Team team) {
        return teamDao.updateTeam(team);
    }

    public List<Team> listTeams() {
        return teamDao.listTeams();
    }

    public List<Team> listTeamsByCountryCode(String countryCode) {
        return teamDao.listTeamsByCountryCode(countryCode);
    }
    
    public List<Team> listTeamsByLeagueCode(String leagueCode) {
        return teamDao.listTeamsByLeagueCode(leagueCode);
    }

    public void deleteTeam(Team team) {
       teamDao.deleteTeam(team);
    }

    
    
}
