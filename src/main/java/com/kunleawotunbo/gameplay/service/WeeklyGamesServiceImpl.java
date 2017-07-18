/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.GamePlayTypeDao;
import com.kunleawotunbo.gameplay.dao.WeeklyGamesDao;
import com.kunleawotunbo.gameplay.model.GamePlayType;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("weeklyGamesService")
public class WeeklyGamesServiceImpl implements WeeklyGamesService {

    @Autowired
    private WeeklyGamesDao weeklyGamesDao;
    
    @Autowired GamePlayTypeDao gamePlayTypeDao;
    
    public WeeklyGames findById(int id) {
       return weeklyGamesDao.findById(id);
    }

    public boolean save(WeeklyGames game) {
       return weeklyGamesDao.save(game);
    }

    public boolean updateWeeklyGame(WeeklyGames game) {
        return weeklyGamesDao.updateWeeklyGame(game);
    }

    public void deleteGame(WeeklyGames game) {
        weeklyGamesDao.deleteGame(game);
    }

    public List<WeeklyGames> listWeeklyGames() {
       return weeklyGamesDao.listWeeklyGames();
    }

    public boolean isGameCodeExist(String gameCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public WeeklyGames getWeekGameByWeekNo(int gameCategory, int weekNo) {
        return weeklyGamesDao.getWeekGameByWeekNo(gameCategory, weekNo);
    }

    public List<GamePlayType> getGamePlayType() {
        //return weeklyGamesDao.getGamePlayType();
        return gamePlayTypeDao.getGamePlayType();
    }   
    
}
