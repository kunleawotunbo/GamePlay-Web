/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.MatchPredictionAnswer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
        logger.info("gameAnswer :: " + gameAnswer);
        logger.info("gameId :: " + gameId);
        // Criteria criteria = createEntityCriteria();
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAnswered"));
        criteria.add(Restrictions.eq("userAnswer", gameAnswer).ignoreCase());
        criteria.add(Restrictions.eq("gameId", gameId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
         logger.info("crit.toString() :: " + criteria.toString());

        List<MatchPredictionAnswer> matchPredictionAnswerList = (List<MatchPredictionAnswer>) criteria.list();
        List<MatchPredictionAnswer> matchPredictionAnswerRandomWinnerList = new ArrayList<MatchPredictionAnswer>();

        Random rand = new Random();

        for (int i = 0; i < noOfWinners; i++) {
            
            if (matchPredictionAnswerList != null && !matchPredictionAnswerList.isEmpty()){
                logger.info("matchPredictionAnswerList is not empty :: " + matchPredictionAnswerList.size());
            int randomIndex = rand.nextInt(matchPredictionAnswerList.size());
            MatchPredictionAnswer randomWeeklyGamesAnswer = matchPredictionAnswerList.get(randomIndex);
            matchPredictionAnswerList.remove(randomIndex);
            
             logger.info("randomWeeklyGamesAnswer.getId() ::  " + randomWeeklyGamesAnswer.getId());
            // Add each random winner to the arrayList.
            matchPredictionAnswerRandomWinnerList.add(randomWeeklyGamesAnswer);
            
            }else {
                logger.info("matchPredictionAnswerList is null or empty");
            }
        }

        
        return matchPredictionAnswerRandomWinnerList;
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswersByGameId(int gameId) {
        logger.info("gameId :: " +  gameId);

       
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAnswered"));

        criteria.add(Restrictions.eq("gameId", gameId));
        
        List<MatchPredictionAnswer> gameWinnerList = (List<MatchPredictionAnswer>) criteria.list();
       
        return gameWinnerList;
    }
    
    public List<MatchPredictionAnswer> listByCountry(String countryCode) {
        logger.info("status League Code ::" + countryCode);
        //logger.info("status ::" + endDateAndTime);

        Criteria crit = createEntityCriteria();

        crit.add(Restrictions.eq("countryCode", countryCode));       

     

        System.out.println("crit.toString() :: " + crit.toString());

        return crit.list();
    }

    public List<MatchPredictionAnswer> listAnswerByPhoneAndDate(String userPhoneNo, Date startDate, Date endDate) {
         logger.info("userPhoneNo :: " + userPhoneNo);

        Criteria crit = createEntityCriteria();

        crit.add(Restrictions.eq("userPhoneNo", userPhoneNo));

        // crit.add(Restrictions.ge("gameStartDate", date));
        // crit.add(Restrictions.le("gameExpiryDate", date));
        // To get game based on the game expiry date
        crit.add(Restrictions.ge("dateAnswered", startDate));
        crit.add(Restrictions.le("dateAnswered", endDate));

        //System.out.println("crit.toString() :: " + crit.toString());

        return crit.list();
    }

    public List<MatchPredictionAnswer> listAnswerByCodeAndCountry(int code, String countryCode) {
        logger.info("code :: " + code);
        logger.info("countryCode :: " + countryCode);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("code", code));
        crit.add(Restrictions.eq("countryCode", countryCode));

        return crit.list();
    }

    public List<MatchPredictionAnswer> listAnswerByCode(int code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPredictionAnswer> listAnswersByUserPhoneNo(String userPhoneNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPredictionAnswer> listCorrectAnswersForJPByGameId(String gameAnswer, int gameId, int noOfWinners) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateBulkMatchPredictionAnswers(List<MatchPredictionAnswer> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswerByGameId(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
