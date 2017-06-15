/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Olakunle Awotunbo
 */
@Repository("gamePlayTypeDao")
public class GamePlayTypeDaoImpl extends AbstractDao<Integer, GamePlayTypeDao> implements GamePlayTypeDao{

    public List<GamePlayTypeDao> getGamePlayType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
