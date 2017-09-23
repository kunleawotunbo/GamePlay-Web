/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.LeagueDao;
import com.kunleawotunbo.gameplay.model.League;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("leagueService")
public class LeagueServiceImpl implements LeagueService{
    
    @Autowired
    private LeagueDao leagueDao;

    public League getLeagueById(int id) {
        return leagueDao.getLeagueById(id);
    }

    public League getLeagueByCode(String leagueCode) {
        return leagueDao.getLeagueByCode(leagueCode);
    }

    public boolean saveLeague(League league) {
        return leagueDao.saveLeague(league);
    }

    public boolean updateLeague(League league) {
        return leagueDao.updateLeague(league);
    }

    public List<League> listLeagues() {
        return leagueDao.listLeagues();
    }

    public List<League> listLeaguesByCountryCode(String countryCode) {
        return leagueDao.listLeaguesByCountryCode(countryCode);
    }

    public void deleteLeague(League league) {
        leagueDao.deleteLeague(league);
    }
    
}
