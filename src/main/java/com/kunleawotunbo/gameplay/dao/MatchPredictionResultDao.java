/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.MatchPredictionResult;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface MatchPredictionResultDao {
    
    public MatchPredictionResult findById(int id);

    public boolean saveMatchPredictionResult(MatchPredictionResult matchPredictionResult);
    
    public boolean updateMatchPredictionResult(MatchPredictionResult matchPredictionResult);

    public void deleteMatchPredictionResult(MatchPredictionResult matchPredictionResult);
    
    public List<MatchPredictionResult> listAllMatchPredictionResult();
    
    public List<MatchPredictionResult> getByGamesCategory(int id);
    
    public MatchPredictionResult findByMatchPredictionId(int matchPredictionId);
}
