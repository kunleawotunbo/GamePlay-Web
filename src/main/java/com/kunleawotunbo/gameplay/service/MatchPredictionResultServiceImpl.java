/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.MatchPredictionResultDao;
import com.kunleawotunbo.gameplay.model.MatchPredictionResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("matchPredictionResultService")
public class MatchPredictionResultServiceImpl implements MatchPredictionResultService{
    
    @Autowired
    private MatchPredictionResultDao matchPredictionResultDao;

    public MatchPredictionResult findById(int id) {
        return matchPredictionResultDao.findById(id);
    }

    public boolean saveMatchPredictionResult(MatchPredictionResult matchPredictionResult) {
        return matchPredictionResultDao.saveMatchPredictionResult(matchPredictionResult);
    }

    public boolean updateMatchPredictionResult(MatchPredictionResult matchPredictionResult) {
        return matchPredictionResultDao.updateMatchPredictionResult(matchPredictionResult);
    }

    public void deleteMatchPredictionResult(MatchPredictionResult matchPredictionResult) {
         matchPredictionResultDao.deleteMatchPredictionResult(matchPredictionResult);
    }

    public List<MatchPredictionResult> listAllMatchPredictionResult() {
        return matchPredictionResultDao.listAllMatchPredictionResult();
    }

    public List<MatchPredictionResult> getByGamesCategory(int id) {
        return matchPredictionResultDao.getByGamesCategory(id);
    }

    public MatchPredictionResult findByMatchPredictionId(int matchPredictionId) {
        return matchPredictionResultDao.findByMatchPredictionId(matchPredictionId);
    }
    
}
