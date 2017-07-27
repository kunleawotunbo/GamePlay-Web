/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.GameDao;
import com.kunleawotunbo.gameplay.model.Game;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("gameDaoService")
@Transactional
public class GameServiceImpl implements GameService{
    
    @Autowired
    private GameDao gameDao;

    public Game findById(int id) {
        return gameDao.findById(id);
    }

    public boolean save(Game game) {
       return gameDao.save(game);
    }

    public boolean updateGame(Game game) {
       return gameDao.updateGame(game);
    }

    public void deleteGame(Game game) {
       gameDao.deleteGame(game);
    }

    public List<Game> listGames(boolean enabled) {
       return gameDao.listGames(enabled);
    }

    public boolean isGameCodeExist(String gameCode) {
        return gameDao.isGameCodeExist(gameCode);
    }

    public List<Game> listAllGames() {
        return gameDao.listAllGames();
    }
    
    public List<Game> GamesCategory(int id) {
       return gameDao.GamesCategory(id); 
    }
    
}
