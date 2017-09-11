/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.GameWinner;
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
@Repository("gameWinnerDao")
@Transactional
public class GameWinnerDaoImpl extends AbstractDao<Integer, GameWinner> implements GameWinnerDao {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public GameWinner findById(int id) {
        logger.info("id ::" +  id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (GameWinner) crit.uniqueResult();
    }
    
    public GameWinner findByGameId(int gameId) {
        logger.info("gameId :", gameId);

        Criteria crit = createEntityCriteria().addOrder(Order.asc("dateAnswered"));
        crit.add(Restrictions.eq("gameId", gameId));

        return (GameWinner) crit.uniqueResult();
    }

    public boolean save(GameWinner game) {
        boolean success = false;
        try {            
            saveOrUpdate(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void saveBulkGameWinners(List<GameWinner> items) {
        if(items!=null && items.size()>0){
		 for(GameWinner item: items){
			 saveOrUpdate(item);
		 }
	 }
    }

    public boolean updateGameWinner(GameWinner gameWinner) {
        boolean success = false;
        try {
            update(gameWinner);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteGameWinner(GameWinner gameWinner) {
        delete(gameWinner);
    }

    public List<GameWinner> listAllGameWinners() {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<GameWinner> gameWinnerList = (List<GameWinner>) criteria.list();

        return gameWinnerList;
    }

    public List<GameWinner> listAllGameWinnersByGameId(int gameId) {
        logger.info("gameId :: " +  gameId);

       
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAnswered"));

        criteria.add(Restrictions.eq("gameId", gameId));
        
        List<GameWinner> gameWinnerList = (List<GameWinner>) criteria.list();
       
        return gameWinnerList;
    }


        
}
