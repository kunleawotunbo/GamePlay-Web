/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.model.MatchPrediction;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface MatchPredictionService {
    
    public MatchPrediction findById(int id);

    public boolean save(MatchPrediction matchPrediction);

    public boolean updatePrediction(MatchPrediction matchPrediction);

    public void deleteMatchPrediction(MatchPrediction matchPrediction);

    public List<MatchPrediction> listAllMatchPredictions();
    
    List<MatchPrediction> listActiveMatches( Date date);
    
    
     List<MatchPrediction> listWeekGamesByCateAndDate(int gameCategory, Date date);

    public List<MatchPrediction> listWeekActiveGamesByDate(Date date);
    
    public List<MatchPrediction> listUnproccessedGames(int status, Date date);
    
    public List<MatchPrediction> listByTimePlayedPeriod(Date startDateAndTime, Date endDateAndTime);
    
    public List<MatchPrediction> listByLeague(String leagueCode);
    
    public List<MatchPrediction> listByCountry(String countryCode);
}
