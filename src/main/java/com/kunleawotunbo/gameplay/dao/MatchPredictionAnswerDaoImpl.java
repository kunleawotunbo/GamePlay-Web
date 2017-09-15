/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.MatchPredictionAnswer;
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
@Repository("matchPredictionAnswerDao")
@Transactional
public class MatchPredictionAnswerDaoImpl extends AbstractDao<Long, MatchPredictionAnswer> implements MatchPredictionAnswerDao{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public MatchPredictionAnswer findById(int id) {
        logger.info("id : {}", id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (MatchPredictionAnswer) crit.uniqueResult();
    }

    public boolean saveMatchPredictionAnswer(MatchPredictionAnswer game) {
        boolean success = false;
        try {
            saveOrUpdate(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateMatchPredictionAnswer(MatchPredictionAnswer game) {
        boolean success = false;
        try {
            update(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteMatchPredictionAnswer(MatchPredictionAnswer game) {
        delete(game);
    }

    public List<MatchPredictionAnswer> listMatchPredictionAnswers(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("weekNo"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<MatchPredictionAnswer> matchPredictionAnswerList = (List<MatchPredictionAnswer>) criteria.list();

        return matchPredictionAnswerList;
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswers(String ans) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswers(int weekNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswersbyId(String ans, int id, int NoOfWinners) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long submittedAnswersByWeek(int weekNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPredictionAnswer> listCorrectAnswersByGameId(String gameAnswer, int gameId, int noOfWinners) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
