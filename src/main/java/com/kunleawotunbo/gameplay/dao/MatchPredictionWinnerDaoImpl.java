/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.MatchPredictionWinner;
import java.io.Serializable;
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
@Repository("matchPredictionWinnerDao")
@Transactional
public class MatchPredictionWinnerDaoImpl extends AbstractDao<Integer, MatchPredictionWinner> implements MatchPredictionWinnerDao{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public MatchPredictionWinner findById(int id) {
        logger.info("id :: " +  id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (MatchPredictionWinner) crit.uniqueResult();
    }

    public MatchPredictionWinner findByGameId(int gameId) {
        logger.info("gameId :: " +  gameId);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("gameId", gameId));

        return (MatchPredictionWinner) crit.uniqueResult();
    }

    public boolean save(MatchPredictionWinner game) {
        boolean success = false;
        try {
            saveOrUpdate(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void saveBulkMatchPredictionWinners(List<MatchPredictionWinner> items) {
        if(items!=null && items.size()>0){
		 for(MatchPredictionWinner item: items){
			 saveOrUpdate(item);
		 }
	 }
    }

    public boolean updateMatchPredictionWinner(MatchPredictionWinner matchWinner) {
        boolean success = false;
        try {
            update(matchWinner);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteMatchPredictionWinner(MatchPredictionWinner matchWinner) {
        delete(matchWinner);
    }

    public List<MatchPredictionWinner> listAllMatchPredictionWinners() {
         Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<MatchPredictionWinner> matchPredictionList = (List<MatchPredictionWinner>) criteria.list();

        return matchPredictionList;
    }

    public List<MatchPredictionWinner> listAllMatchPredictionWinnersByGameId(int gameId) {
        logger.info("gameId :: " +  gameId);

       
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAnswered"));

        criteria.add(Restrictions.eq("gameId", gameId));
        
        List<MatchPredictionWinner> matchPredictionList = (List<MatchPredictionWinner>) criteria.list();
       
        return matchPredictionList;
    }
    
}
