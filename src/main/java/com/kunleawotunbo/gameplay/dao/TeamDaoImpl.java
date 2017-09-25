/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.Team;
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
@Repository("teamDao")
@Transactional
public class TeamDaoImpl extends AbstractDao<Integer, Team> implements TeamDao{
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Team getTeamById(int id) {
         logger.info("id :: " +  id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (Team) crit.uniqueResult();
    }

    public Team getTeamByShortName(String shortName) {
           logger.info("shortName :: " +  shortName);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("shortName", shortName));

        return (Team) crit.uniqueResult();
    }

    public boolean saveTeam(Team team) {
         boolean success = false;
        try {
            saveOrUpdate(team);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateTeam(Team team) {
        boolean success = false;
        try {
            update(team);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public List<Team> listTeams() {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Team> list = (List<Team>) criteria.list();

        return list;
    }

    public List<Team> listTeamsByCountryCode(String countryCode) {
        logger.info("countryCode :: " +  countryCode);

        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("countryCode", countryCode));
        
        List<Team> list = (List<Team>) criteria.list();

        return list;
    }
    
    public List<Team> listTeamsByLeagueCode(String leagueCode) {
        logger.info("leagueCode :: " +  leagueCode);

        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("leagueCode", leagueCode));
        
        List<Team> list = (List<Team>) criteria.list();

        return list;
    }

    public void deleteTeam(Team team) {
        delete(team);
    }
    
    
}
