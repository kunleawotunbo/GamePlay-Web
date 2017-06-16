/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.GamePlayType;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Repository("gamePlayTypeDao")
@Transactional
public class GamePlayTypeDaoImpl extends AbstractDao<Integer, GamePlayType> implements GamePlayTypeDao{

    public List<GamePlayType> getGamePlayType() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<GamePlayType> gamePlayTypeList = (List<GamePlayType>) criteria.list();
        
        System.out.println("gamePlayTypeList :: " + gamePlayTypeList);

        
        return gamePlayTypeList;
    }
    
    
}
