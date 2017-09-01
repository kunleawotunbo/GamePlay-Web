/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.ActivityLogDao;
import com.kunleawotunbo.gameplay.model.ActivityLog;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("activityLogService")
public class ActivityLogServiceImpl implements ActivityLogService{
    
    @Autowired
    private ActivityLogDao activityLogDao;

    public ActivityLog findById(int id) {
        return activityLogDao.findById(id);
    }

    public boolean save(ActivityLog activityLog) {
        return activityLogDao.save(activityLog);
    }
    
    public boolean saveActivityLog(String action, int event, String username, String description, Date actionDate, String actionItem, String actionResult, String ipaddress, String timezone) {
        return activityLogDao.saveActivityLog(action, event, username, description, actionDate, actionItem, actionResult, ipaddress, timezone);
    }

    public boolean updateGame(ActivityLog activityLog) {
        return activityLogDao.updateGame(activityLog);
    }

    public List<ActivityLog> listActivityLog(String startDate, String endDate) {
        return activityLogDao.listActivityLog(startDate, endDate);
    }

    public List<ActivityLog> listActivityLogByEmail(String email, String startDate, String endDate) {
        return activityLogDao.listActivityLogByEmail(email, startDate, endDate);
    }

    
    
}
