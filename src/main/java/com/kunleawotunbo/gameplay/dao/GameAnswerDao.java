/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.GameAnswer;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface GameAnswerDao {
    
    GameAnswer findById(int id);

    boolean save(GameAnswer gameAnswer);
    
    boolean updateGame(GameAnswer gameAnswer);

    void deleteGame(GameAnswer gameAnswer);
    
    List<GameAnswer> listAllGamesAnswers();
    
    public List<GameAnswer> getByGamesCategory(int id);
    
    GameAnswer findByGameId(int gameId);
}
