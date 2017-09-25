/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.League;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface LeagueDao {
    
    public League getLeagueById(int id);
    
    public League getLeagueByCode(String leagueCode);

    boolean saveLeague(League league);

    boolean updateLeague(League league);

    public List<League> listLeagues();
    
    public List<League> listLeaguesByCountryCode(String countryCode);
    
    public void deleteLeague(League league);
}
