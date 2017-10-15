/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.WeeklyGamesWinnerDao;
import com.kunleawotunbo.gameplay.model.WeeklyGamesWinner;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("weeklyGamesWinnerService")
@Transactional
public class WeeklyGamesWinnerServiceImpl implements WeeklyGamesWinnerService{

    @Autowired
    private WeeklyGamesWinnerDao weeklyGamesWinnerDao;
    
    public WeeklyGamesWinner findById(int id) {
        return weeklyGamesWinnerDao.findById(id);
    }

    public WeeklyGamesWinner findByGameId(int gameId) {
        return weeklyGamesWinnerDao.findByGameId(gameId);
    }

    public boolean save(WeeklyGamesWinner game) {
        return weeklyGamesWinnerDao.save(game);
    }

    public void saveBulkWeeklyGamesWinners(List<WeeklyGamesWinner> items) {
         weeklyGamesWinnerDao.saveBulkWeeklyGamesWinners(items);
    }

    public boolean updateWeeklyGamesWinner(WeeklyGamesWinner matchWinner) {
        return weeklyGamesWinnerDao.updateWeeklyGamesWinner(matchWinner);
    }

    public void deleteWeeklyGamesWinner(WeeklyGamesWinner matchWinner) {
        weeklyGamesWinnerDao.deleteWeeklyGamesWinner(matchWinner);
    }

    public List<WeeklyGamesWinner> listAllWeeklyGamesWinners() {
        return weeklyGamesWinnerDao.listAllWeeklyGamesWinners();
    }

    public List<WeeklyGamesWinner> listAllWeeklyGamesWinnersByGameId(int gameId) {
        return weeklyGamesWinnerDao.listAllWeeklyGamesWinnersByGameId(gameId);
    }

    public List<WeeklyGamesWinner> listAllWeeklyGamesWinnersByCode(String code) {
        return weeklyGamesWinnerDao.listAllWeeklyGamesWinnersByCode(code);
    }
    
}
