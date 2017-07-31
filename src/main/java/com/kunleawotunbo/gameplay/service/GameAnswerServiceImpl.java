/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.GameAnswerDao;
import com.kunleawotunbo.gameplay.model.GameAnswer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("gameAnswerService")
@Transactional
public class GameAnswerServiceImpl implements GameAnswerService{

     @Autowired
    private GameAnswerDao gameAnswerDao;
     
    public GameAnswer findById(int id) {
        return gameAnswerDao.findById(id);
    }

    public boolean save(GameAnswer gameAnswer) {
        return gameAnswerDao.save(gameAnswer);
    }

    public boolean updateGame(GameAnswer gameAnswer) {
        return gameAnswerDao.updateGame(gameAnswer);
    }

    public void deleteGame(GameAnswer gameAnswer) {
        gameAnswerDao.deleteGame(gameAnswer);
    }

    public List<GameAnswer> listAllGamesAnswers() {
        return gameAnswerDao.listAllGamesAnswers();
    }

    public List<GameAnswer> getByGamesCategory(int id) {
        return gameAnswerDao.getByGamesCategory(id);
    }

    public GameAnswer findByGameId(int gameId) {
       return gameAnswerDao.findByGameId(gameId);
    }
    
}
