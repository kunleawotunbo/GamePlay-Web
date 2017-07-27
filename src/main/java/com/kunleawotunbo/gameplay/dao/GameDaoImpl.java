/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.Game;
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
 * @author olakunle
 */
@Repository("gameDao")
public class GameDaoImpl extends AbstractDao<Integer, Game> implements GameDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Game findById(int id) {
        logger.info("id : {}", id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (Game) crit.uniqueResult();
    }

    public boolean save(Game game) {
        boolean success = false;
        try {
            //persist(game);
            saveOrUpdate(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateGame(Game game) {
        boolean success = false;
        try {
            update(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteGame(Game game) {
        delete(game);
    }

    public List<Game> listGames(boolean enabled) {
       Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("enabled", enabled));

        return crit.list();
    }

    public boolean isGameCodeExist(String gameCode) {
         Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("gameCode", gameCode));

        int count = crit.list().size();
        
        return count > 0;
    }

    public List<Game> listAllGames() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Game> gameList = (List<Game>) criteria.list();
        
        return gameList;
    }
    
    public List<Game> GamesCategory(int id) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.add(Restrictions.eq("id", id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Game> gameList = (List<Game>) criteria.list();
        
        return gameList;
    }

}
