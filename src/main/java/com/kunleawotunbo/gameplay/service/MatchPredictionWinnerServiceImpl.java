/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.MatchPredictionWinnerDao;
import com.kunleawotunbo.gameplay.model.MatchPredictionWinner;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("matchPredictionWinnerService")
public class MatchPredictionWinnerServiceImpl implements MatchPredictionWinnerService{
    
    @Autowired
    private MatchPredictionWinnerDao matchPredictionWinnerDao;

    public MatchPredictionWinner findById(int id) {
        return matchPredictionWinnerDao.findById(id);
    }

    public MatchPredictionWinner findByGameId(int gameId) {
        return matchPredictionWinnerDao.findByGameId(gameId);
    }

    public boolean save(MatchPredictionWinner game) {
        return matchPredictionWinnerDao.save(game);
    }

    public void saveBulkMatchPredictionWinners(List<MatchPredictionWinner> items) {
        matchPredictionWinnerDao.saveBulkMatchPredictionWinners(items);
    }

    public boolean updateMatchPredictionWinner(MatchPredictionWinner matchWinner) {
        return matchPredictionWinnerDao.updateMatchPredictionWinner(matchWinner);
    }

    public void deleteMatchPredictionWinner(MatchPredictionWinner matchWinner) {
        matchPredictionWinnerDao.deleteMatchPredictionWinner(matchWinner);
    }

    public List<MatchPredictionWinner> listAllMatchPredictionWinners() {
        return  matchPredictionWinnerDao.listAllMatchPredictionWinners();
    }

    public List<MatchPredictionWinner> listAllMatchPredictionWinnersByGameId(int gameId) {
        return matchPredictionWinnerDao.listAllMatchPredictionWinnersByGameId(gameId);
    }
    
}
