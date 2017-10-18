/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.MatchPrediction;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Repository("matchPredictionDao")
@Transactional
public class MatchPredictionDaoImpl extends AbstractDao<Integer, MatchPrediction> implements MatchPredictionDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MatchPrediction findById(int id) {
        logger.info("id :: " + id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (MatchPrediction) crit.uniqueResult();
    }

    public boolean save(MatchPrediction matchPrediction) {
        boolean success = false;
        try {
            saveOrUpdate(matchPrediction);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updatePrediction(MatchPrediction matchPrediction) {
        boolean success = false;
        try {
            update(matchPrediction);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteMatchPrediction(MatchPrediction matchPrediction) {
        delete(matchPrediction);
    }

    public List<MatchPrediction> listAllMatchPredictions(int start, int limit) {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult(start);
        criteria.setMaxResults(limit);
        List<MatchPrediction> matchPredictionList = (List<MatchPrediction>) criteria.list();

        return matchPredictionList;
    }

    public List<MatchPrediction> listActiveMatches(Date date) {

        boolean enabled = true;

        //Criteria crit = createEntityCriteria();
        Criteria crit = createEntityCriteria().addOrder(Order.asc("countryCode"));
        crit.add(Restrictions.eq("enabled", enabled));

        crit.add(Restrictions.ge("endTime", date));

        // group by
        //crit.add(Projections.groupProperty("countryCode"));
        /*
        crit.setProjection(Projections.projectionList()
                .add(Projections.groupProperty("countryCode"))
              //  .add(Projections.property("id"))
                .add(Projections.property("endTime"))
                .add(Projections.property("homeTeamName"))
                .add(Projections.property("awayTeamName"))
        );
         */
        // System.out.println("crit.toString() :: " + crit.toString());
        return crit.list();
    }

    public List<MatchPrediction> listActiveMatchesByLeagueCode(Date date, String leagueCode) {
        boolean enabled = true;

        Criteria crit = createEntityCriteria().addOrder(Order.asc("leagueCode"));
        crit.add(Restrictions.eq("leagueCode", leagueCode));
        crit.add(Restrictions.eq("enabled", enabled));

        crit.add(Restrictions.ge("endTime", date));

        return crit.list();
    }

    public List<MatchPrediction> listWeekGamesByCateAndDate(int gameCategory, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPrediction> listWeekActiveGamesByDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchPrediction> listUnproccessedGames(int status, Date date) {
        logger.info("status ::" + status);

        Criteria crit = createEntityCriteria();

        crit.add(Restrictions.eq("status", status));

        // crit.add(Restrictions.ge("gameStartDate", date));
        // crit.add(Restrictions.le("gameExpiryDate", date));
        // To get game based on the game expiry date
        crit.add(Restrictions.le("endTime", date));

        System.out.println("crit.toString() :: " + crit.toString());

        return crit.list();

    }

    public List<MatchPrediction> listByTimePlayedPeriod(Date startDateAndTime, Date endDateAndTime) {
        logger.info("status ::" + startDateAndTime);
        logger.info("status ::" + endDateAndTime);

        Criteria crit = createEntityCriteria();

        //  crit.add(Restrictions.eq("status", status));       
        // crit.add(Restrictions.ge("gameStartDate", date));
        // crit.add(Restrictions.le("gameExpiryDate", date));
        // To get game based on the game expiry date
        crit.add(Restrictions.ge("startTime", startDateAndTime));
        crit.add(Restrictions.le("endTime", endDateAndTime));

        System.out.println("crit.toString() :: " + crit.toString());

        return crit.list();
    }

    public List<MatchPrediction> listByLeague(String leagueCode) {
        logger.info("status League Code ::" + leagueCode);
        //logger.info("status ::" + endDateAndTime);

        Criteria crit = createEntityCriteria();

        crit.add(Restrictions.eq("leagueCode", leagueCode));

        System.out.println("crit.toString() :: " + crit.toString());

        return crit.list();
    }

    public List<MatchPrediction> listByCountry(String countryCode) {
        logger.info("status League Code ::" + countryCode);
        //logger.info("status ::" + endDateAndTime);

        Criteria crit = createEntityCriteria();

        crit.add(Restrictions.eq("countryCode", countryCode));

        System.out.println("crit.toString() :: " + crit.toString());

        return crit.list();
    }

}
