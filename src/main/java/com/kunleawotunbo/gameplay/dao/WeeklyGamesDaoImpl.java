/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.WeeklyGames;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Repository("weeklyGamesDao")
@Transactional
public class WeeklyGamesDaoImpl extends AbstractDao<Integer, WeeklyGames> implements WeeklyGamesDao{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public WeeklyGames findById(int id) {
         logger.info("id : {}", id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (WeeklyGames) crit.uniqueResult();
    }

    public boolean save(WeeklyGames game) {
      boolean success = false;
        try {
            persist(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateWeeklyGame(WeeklyGames game) {
        boolean success = false;
        try {
            update(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteGame(WeeklyGames game) {
         delete(game);
    }

    public List<WeeklyGames> listGames(byte enabled) {
         Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("enabled", enabled));

        return crit.list();
    }

    public boolean isGameCodeExist(String gameCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public WeeklyGames getWeekGameByWeekNo(String gameCategory, int weekNo) {
            
         logger.info("gameCategory : {}", gameCategory);
         logger.info("weekNo : {}", weekNo);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("gameCategory", gameCategory));
        crit.add(Restrictions.eq("weekNo", weekNo));

        return (WeeklyGames) crit.uniqueResult();
    }
    
}
