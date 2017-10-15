/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.model.WeeklyGamesWinner;
import java.io.Serializable;
import java.util.Date;
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
@Repository("weeklyGamesWinnerDao")
@Transactional
public class WeeklyGamesWinnerDaoImpl extends AbstractDao<Serializable, WeeklyGamesWinner> implements WeeklyGamesWinnerDao{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public WeeklyGamesWinner findById(int id) {
         logger.info("id :: " +  id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (WeeklyGamesWinner) crit.uniqueResult();
    }

    public WeeklyGamesWinner findByGameId(int gameId) {
        logger.info("gameId :: " +  gameId);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("gameId", gameId));

        return (WeeklyGamesWinner) crit.uniqueResult();
    }

    public boolean save(WeeklyGamesWinner game) {
        boolean success = false;
        try {
            saveOrUpdate(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void saveBulkWeeklyGamesWinners(List<WeeklyGamesWinner> items) {
         if(items!=null && items.size()>0){
		 for(WeeklyGamesWinner item: items){
			 saveOrUpdate(item);
		 }
	 }
    }

    public boolean updateWeeklyGamesWinner(WeeklyGamesWinner matchWinner) {
        boolean success = false;
        try {
            update(matchWinner);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteWeeklyGamesWinner(WeeklyGamesWinner matchWinner) {
        delete(matchWinner);
    }

    public List<WeeklyGamesWinner> listAllWeeklyGamesWinners() {
          Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<WeeklyGamesWinner> weeklyGamesWinnerList = (List<WeeklyGamesWinner>) criteria.list();

        return weeklyGamesWinnerList;
    }

    public List<WeeklyGamesWinner> listAllWeeklyGamesWinnersByGameId(int gameId) {
        logger.info("gameId :: " +  gameId);
       
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAnswered"));

        criteria.add(Restrictions.eq("gameId", gameId));
        
        List<WeeklyGamesWinner> weeklyGamesWinnerList = (List<WeeklyGamesWinner>) criteria.list();
       
        return weeklyGamesWinnerList;
    }

    public List<WeeklyGamesWinner> listAllWeeklyGamesWinnersByCode(String code) {
        logger.info("code :: " +  code);
       
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAnswered"));

        criteria.add(Restrictions.eq("code", code));
        
        List<WeeklyGamesWinner> weeklyGamesWinnerList = (List<WeeklyGamesWinner>) criteria.list();
       
        return weeklyGamesWinnerList;
    }

    
}
