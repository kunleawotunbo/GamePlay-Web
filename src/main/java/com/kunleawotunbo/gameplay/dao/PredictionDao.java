/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.Prediction;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface PredictionDao {
    
    public Prediction findById(int id);
    
    public Prediction findByGameId(int gameId);

    public boolean save(Prediction game);

    public boolean updatePrediction(Prediction gameWinner);

    public void deleteMatchPrediction(Prediction gameWinner);

    public List<Prediction> listAllGameWinners();
    
    public List<Prediction> listAllGameWinnersByGameId(int gameId);
}
