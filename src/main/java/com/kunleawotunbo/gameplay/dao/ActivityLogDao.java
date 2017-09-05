/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.ActivityLog;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface ActivityLogDao {
   
    ActivityLog findById(int id);

    boolean save(ActivityLog activityLog);
    
    boolean saveActivityLog(String action, int event, String username, String description, Date actionDate,
            String actionItem, String actionResult, String ipaddress, String timezone);
    
    boolean updateGame(ActivityLog activityLog);
    
    public List<ActivityLog> listActivityLog(String startDate, String endDate );
    
    public List<ActivityLog> listActivityLogByEmail(String email, String startDate, String endDate);
}
