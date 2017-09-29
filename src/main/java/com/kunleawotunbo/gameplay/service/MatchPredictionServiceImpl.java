/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.MatchPredictionDao;
import com.kunleawotunbo.gameplay.model.MatchPrediction;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("matchPredictionService")
public class MatchPredictionServiceImpl implements MatchPredictionService{
    
    @Autowired
    private MatchPredictionDao matchPredictionDao;

    public MatchPrediction findById(int id) {
        return matchPredictionDao.findById(id);
    }

    public boolean save(MatchPrediction matchPrediction) {
        return matchPredictionDao.save(matchPrediction);
    }

    public boolean updatePrediction(MatchPrediction matchPrediction) {
        return matchPredictionDao.updatePrediction(matchPrediction);
    }

    public void deleteMatchPrediction(MatchPrediction matchPrediction) {
        matchPredictionDao.deleteMatchPrediction(matchPrediction);
    }

    public List<MatchPrediction> listAllMatchPredictions() {
        return matchPredictionDao.listAllMatchPredictions();
    }

    public List<MatchPrediction> listActiveMatches( Date date) {
        return matchPredictionDao.listActiveMatches(date);
    }
    
    
    public List<MatchPrediction> listActiveMatchesByLeagueCode(Date date, String leagueCode) {
        return matchPredictionDao.listActiveMatchesByLeagueCode(date, leagueCode);
    }
    

    public List<MatchPrediction> listWeekGamesByCateAndDate(int gameCategory, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPrediction> listWeekActiveGamesByDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPrediction> listUnproccessedGames(int status, Date date) {
        return matchPredictionDao.listUnproccessedGames(status, date);
    }

}
