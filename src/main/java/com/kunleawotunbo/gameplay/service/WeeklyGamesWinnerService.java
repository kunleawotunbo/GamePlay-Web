/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.model.WeeklyGamesWinner;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface WeeklyGamesWinnerService {
    
    public WeeklyGamesWinner findById(int id);
    
    public WeeklyGamesWinner findByGameId(int gameId);

    public boolean save(WeeklyGamesWinner game);
    
    public void saveBulkWeeklyGamesWinners(List<WeeklyGamesWinner> items);

    public boolean updateWeeklyGamesWinner(WeeklyGamesWinner matchWinner);

    public void deleteWeeklyGamesWinner(WeeklyGamesWinner matchWinner);

    public List<WeeklyGamesWinner> listAllWeeklyGamesWinners();
    
    public List<WeeklyGamesWinner> listAllWeeklyGamesWinnersByGameId(int gameId);
    
    public List<WeeklyGamesWinner> listAllWeeklyGamesWinnersByCode(String code);
}
