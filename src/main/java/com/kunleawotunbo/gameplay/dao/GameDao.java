/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.Game;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface GameDao {

    Game findById(int id);

    boolean save(Game game);
    
    boolean updateGame(Game game);

    void deleteGame(Game game);

    List<Game> listGames(boolean enabled);

    public boolean isGameCodeExist(String gameCode);
    
    List<Game> listAllGames();
    
    public List<Game> GamesCategory(int id);
}
