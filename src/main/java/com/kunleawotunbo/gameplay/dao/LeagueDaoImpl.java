/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.League;
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
@Repository("leagueDao")
@Transactional
public class LeagueDaoImpl extends AbstractDao<Integer, League> implements LeagueDao{
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public League getLeagueById(int id) {
        logger.info("id :: " +  id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (League) crit.uniqueResult();
    }

    public League getLeagueByCode(String leagueCode) {
         logger.info("leagueCode :: " +  leagueCode);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("leagueCode", leagueCode));

        return (League) crit.uniqueResult();
    }

    public boolean saveLeague(League league) {
         boolean success = false;
        try {
            saveOrUpdate(league);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateLeague(League league) {
        boolean success = false;
        try {
            update(league);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public List<League> listLeagues() {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<League> leagueList = (List<League>) criteria.list();

        return leagueList;
    }

    public List<League> listLeaguesByCountryCode(String countryCode) {
        
        
        logger.info("countryCode :: " +  countryCode);

        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("countryCode", countryCode));
        
        List<League> leagueList = (List<League>) criteria.list();

        return leagueList;
    }

    public void deleteLeague(League league) {
        delete(league);
    }
    
}
