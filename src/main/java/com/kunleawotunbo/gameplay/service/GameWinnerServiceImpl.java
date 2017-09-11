/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.GameWinnerDao;
import com.kunleawotunbo.gameplay.model.GameWinner;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("gameWinnerService")
public class GameWinnerServiceImpl implements GameWinnerService{
    
    @Autowired
    private GameWinnerDao gameWinnerDao;

    public GameWinner findById(int id) {
        return gameWinnerDao.findById(id);
    }

    public GameWinner findByGameId(int gameId) {
        return gameWinnerDao.findByGameId(gameId);
    }

    public boolean save(GameWinner game) {
       return gameWinnerDao.save(game);
    }

    public void saveBulkGameWinners(List<GameWinner> items) {
        gameWinnerDao.saveBulkGameWinners(items);
    }

    public boolean updateGameWinner(GameWinner gameWinner) {
        return gameWinnerDao.updateGameWinner(gameWinner);
    }

    public void deleteGameWinner(GameWinner gameWinner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<GameWinner> listAllGameWinners() {
        return gameWinnerDao.listAllGameWinners();
    }

    public List<GameWinner> listAllGameWinnersByGameId(int gameId) {
        return gameWinnerDao.listAllGameWinnersByGameId(gameId);
    }
    
}
