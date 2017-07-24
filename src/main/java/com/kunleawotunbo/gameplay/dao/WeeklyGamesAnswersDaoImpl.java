/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Olakunle Awotunbo
 */
@Repository("weeklyGamesAnswersDao")
public class WeeklyGamesAnswersDaoImpl extends AbstractDao<Long, WeeklyGamesAnswers> implements WeeklyGamesAnswersDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public WeeklyGamesAnswers findById(Long id) {
        logger.info("id : {}", id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (WeeklyGamesAnswers) crit.uniqueResult();
    }

    public boolean saveWeeklyGamesAnswer(WeeklyGamesAnswers game) {
        boolean success = false;
        try {
            saveOrUpdate(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateWeeklyGamesAnswer(WeeklyGamesAnswers game) {
        boolean success = false;
        try {
            update(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteWeeklyGamesAnswer(WeeklyGamesAnswers game) {
        delete(game);
    }

    public List<WeeklyGamesAnswers> listWeeklyGamesAnswers(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<WeeklyGamesAnswers> listAllWeeklyGamesAnswers() {
        //Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
         Criteria criteria = createEntityCriteria().addOrder(Order.desc("weekNo"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();

        return weeklyGamesAnswersList;
    }
    
     public List<WeeklyGamesAnswers> listAllWeeklyGamesCorrectAnswers(String Ans ) {
        //Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
         Criteria criteria = createEntityCriteria().add(Restrictions.eq("userAnswer", Ans));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();

        return weeklyGamesAnswersList;
    }


    public Long submittedAnswersByWeek(int weekNo) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
       
        criteria.add(Restrictions.eq("weekNo", weekNo));
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();
        
        return count;
    }

}
