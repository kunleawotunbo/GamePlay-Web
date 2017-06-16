/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.GamePlayTypeDao;
import com.kunleawotunbo.gameplay.model.GamePlayType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("gamePlayTypeService")
public class GamePlayTypeServiceImpl implements GamePlayTypeService {
    
    @Autowired 
    GamePlayTypeDao gamePlayTypeDao;
    
    public List<GamePlayType> getGamePlayType() {
        return gamePlayTypeDao.getGamePlayType();
    }
    
}
