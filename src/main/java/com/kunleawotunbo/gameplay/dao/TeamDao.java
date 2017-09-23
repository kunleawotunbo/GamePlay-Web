/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.Team;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface TeamDao {
    
    public Team getTeamById(int id);
    
    public Team getTeamByShortName(String shortName);

    boolean saveTeam(Team team);

    boolean updateTeam(Team team);

    public List<Team> listTeams();
    
    public List<Team> listTeamsByCountryCode(String countryCode);
    
    public List<Team> listTeamsByLeagueCode(String leagueCode);
    
    public void deleteTeam(Team team);
}
