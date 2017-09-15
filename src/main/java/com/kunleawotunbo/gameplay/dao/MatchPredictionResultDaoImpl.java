/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.MatchPredictionResult;
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
@Repository("matchPredictionResultDao")
@Transactional
public class MatchPredictionResultDaoImpl extends AbstractDao<Integer, MatchPredictionResult> implements MatchPredictionResultDao{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MatchPredictionResult findById(int id) {
        logger.info("id :: ", id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (MatchPredictionResult) crit.uniqueResult();
    }

    public boolean saveMatchPredictionResult(MatchPredictionResult matchPredictionResult) {
        boolean success = false;
        try {
            saveOrUpdate(matchPredictionResult);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateMatchPredictionResult(MatchPredictionResult matchPredictionResult) {
        boolean success = false;
        try {
            update(matchPredictionResult);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteMatchPredictionResult(MatchPredictionResult matchPredictionResult) {
        delete(matchPredictionResult);
    }

    public List<MatchPredictionResult> listAllMatchPredictionResult() {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<MatchPredictionResult> matchPredictionResultList = (List<MatchPredictionResult>) criteria.list();

        return matchPredictionResultList;
    }

    public List<MatchPredictionResult> getByGamesCategory(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MatchPredictionResult findByMatchPredictionId(int matchPredictionId) {
        logger.info("matchPredictionId ::", matchPredictionId);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("matchPredictionId", matchPredictionId));

        return (MatchPredictionResult) crit.uniqueResult();
    }
    
    
}
