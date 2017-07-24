/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.model.GamePlayType;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface WeeklyGamesService {
    
     WeeklyGames findById(int id);

    boolean save(WeeklyGames game);

    boolean updateWeeklyGame(WeeklyGames game);

    void deleteGame(WeeklyGames game);

    List<WeeklyGames> listWeeklyGames();

    public boolean isGameCodeExist(String gameCode);
    
    WeeklyGames getWeekGameByWeekNo(int gameCategory, int weekNo);
    
    WeeklyGames getWeekGameAnswersbyId(int id);
    
     List<GamePlayType> getGamePlayType();
}
