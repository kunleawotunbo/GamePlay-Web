/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.ActivityLog;
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
@Repository("activityLogDao")
@Transactional
public class ActivityLogDaoImpl extends AbstractDao<Integer, ActivityLog> implements ActivityLogDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ActivityLog findById(int id) {
        logger.info("ActivityLog id : {}", id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (ActivityLog) crit.uniqueResult();
    }

    public boolean save(ActivityLog activityLog) {
        boolean success = false;
        try {

            saveOrUpdate(activityLog);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean saveActivityLog(String action, int event, String username, String description, Date actionDate, String actionItem,
            String actionResult, String ipaddress, String timezone) {
        
        ActivityLog activityLog = new ActivityLog();        
        boolean success = false;
        try {
            
            activityLog.setAction(action);
            activityLog.setEvent(event);
            activityLog.setUsername(username);
            activityLog.setDescription(description);
            activityLog.setActionDate(actionDate);
            activityLog.setActionItem(actionItem);
            activityLog.setActionResult(actionResult);
            activityLog.setIpaddress(ipaddress);
            activityLog.setTimezone(timezone);

            saveOrUpdate(activityLog);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
        
    }
    
    
    public boolean updateGame(ActivityLog activityLog) {
        boolean success = false;
        try {
            update(activityLog);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public List<ActivityLog> listActivityLog(String startDate, String endDate) {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.add(Restrictions.le("actionDate", startDate));
        criteria.add(Restrictions.ge("actionDate", endDate));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<ActivityLog> activityLogList = (List<ActivityLog>) criteria.list();

        return activityLogList;
    }

    public List<ActivityLog> listActivityLogByEmail(String email, String startDate, String endDate) {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.add(Restrictions.le("username", email));
        criteria.add(Restrictions.le("actionDate", startDate));
        criteria.add(Restrictions.ge("actionDate", endDate));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<ActivityLog> activityLogList = (List<ActivityLog>) criteria.list();

        return activityLogList;
    }

    

}
