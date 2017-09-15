/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.GameWinner;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface GameWinnerDao {

    public GameWinner findById(int id);
    
    public GameWinner findByGameId(int gameId);

    public boolean save(GameWinner game);
    
    public void saveBulkGameWinners(List<GameWinner> items);

    public boolean updateGameWinner(GameWinner gameWinner);

    public void deleteGameWinner(GameWinner gameWinner);

    public List<GameWinner> listAllGameWinners();
    
    public List<GameWinner> listAllGameWinnersByGameId(int gameId);
}
