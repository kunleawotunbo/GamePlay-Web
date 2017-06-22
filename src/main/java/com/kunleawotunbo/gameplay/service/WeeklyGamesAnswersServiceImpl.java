/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.WeeklyGamesAnswersDao;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("weeklyGamesAnswersService")
@Transactional
public class WeeklyGamesAnswersServiceImpl implements WeeklyGamesAnswersService{

    @Autowired
    private WeeklyGamesAnswersDao weeklyGamesAnswersDao;
    
    public WeeklyGamesAnswers findById(int id) {
        return weeklyGamesAnswersDao.findById(id);
    }

    public boolean saveWeeklyGamesAnswer(WeeklyGamesAnswers game) {
        return weeklyGamesAnswersDao.saveWeeklyGamesAnswer(game);
    }

    public boolean updateWeeklyGamesAnswer(WeeklyGamesAnswers game) {
       return weeklyGamesAnswersDao.updateWeeklyGamesAnswer(game);
    }

    public void deleteWeeklyGamesAnswer(WeeklyGamesAnswers game) {
       weeklyGamesAnswersDao.deleteWeeklyGamesAnswer(game);
    }

    public List<WeeklyGamesAnswers> listWeeklyGamesAnswers(boolean enabled) {
        return weeklyGamesAnswersDao.listWeeklyGamesAnswers(enabled);
    }

    public List<WeeklyGamesAnswers> listAllWeeklyGamesAnswers() {
      return weeklyGamesAnswersDao.listAllWeeklyGamesAnswers();
    }
    
}
