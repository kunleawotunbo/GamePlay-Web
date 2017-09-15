/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.GameAnswer;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Repository("gameAnswerDao")
@Transactional
public class GameAnswerDaoImpl extends AbstractDao<Integer, GameAnswer> implements GameAnswerDao{
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public GameAnswer findById(int id) {
        logger.info("id : {}", id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (GameAnswer) crit.uniqueResult();
    }

    public boolean save(GameAnswer gameAnswer) {
         boolean success = false;
        try {
            saveOrUpdate(gameAnswer);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateGame(GameAnswer gameAnswer) {
         boolean success = false;
        try {
            //update(gameAnswer);
            saveOrUpdate(gameAnswer);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteGame(GameAnswer gameAnswer) {
        delete(gameAnswer);
    }

    public List<GameAnswer> listAllGamesAnswers() {
           Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<GameAnswer> gameGameAnswerList = (List<GameAnswer>) criteria.list();
        
        return gameGameAnswerList;
    }

    public List<GameAnswer> getByGamesCategory(int id) {
       Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.add(Restrictions.eq("id", id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<GameAnswer> gameGameAnswerList = (List<GameAnswer>) criteria.list();
        
        return gameGameAnswerList;
    }
    
    public List<GameAnswer> getByGamesIdandAnswer(int id, String answer) {
       Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.add(Restrictions.eq("id", id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<GameAnswer> gameGameAnswerList = (List<GameAnswer>) criteria.list();
        
        return gameGameAnswerList;
    }

    public GameAnswer findByGameId(int gameId) {
         logger.info("gameId : {}", gameId);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("gameId", gameId));

        return (GameAnswer) crit.uniqueResult();
    }
    
}
