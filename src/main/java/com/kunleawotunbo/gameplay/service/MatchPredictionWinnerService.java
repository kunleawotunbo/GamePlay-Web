/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.model.MatchPredictionWinner;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface MatchPredictionWinnerService {
    
    public MatchPredictionWinner findById(int id);
    
    public MatchPredictionWinner findByGameId(int gameId);

    public boolean save(MatchPredictionWinner game);
    
    public void saveBulkMatchPredictionWinners(List<MatchPredictionWinner> items);

    public boolean updateMatchPredictionWinner(MatchPredictionWinner matchWinner);

    public void deleteMatchPredictionWinner(MatchPredictionWinner matchWinner);

    public List<MatchPredictionWinner> listAllMatchPredictionWinners();
    
    public List<MatchPredictionWinner> listAllMatchPredictionWinnersByGameId(int gameId);
}
