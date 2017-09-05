/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.GamePlayType;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Olakunle Awotunbo
 */
@Repository("weeklyGamesDao")
@Transactional
public class WeeklyGamesDaoImpl extends AbstractDao<Integer, WeeklyGames> implements WeeklyGamesDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());
   
    public WeeklyGames findById(int id) {
        logger.info("id : {}", id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (WeeklyGames) crit.uniqueResult();
    }

    public boolean save(WeeklyGames game) {
        boolean success = false;
        try {
            //persist(game);
            saveOrUpdate(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateWeeklyGame(WeeklyGames game) {
        boolean success = false;
        try {
            update(game);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteGame(WeeklyGames game) {
        delete(game);
    }

    public List<WeeklyGames> listWeeklyGames() {

        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WeeklyGames> weeklyGamesList = (List<WeeklyGames>) criteria.list();

        return weeklyGamesList;
    }

    public boolean isGameCodeExist(String gameCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public WeeklyGames getWeekGameByWeekNo(int id, int weekNo) {

        logger.info("gameId : {}", id);
        logger.info("weekNo : {}", weekNo);

        boolean enabled = true;

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        //crit.add(Restrictions.eq("weekNo", weekNo));
        crit.add(Restrictions.eq("enabled", enabled));

        // return (WeeklyGames) crit.uniqueResult();
        //return (WeeklyGames) crit.list().get(0);
        return crit.list().size() == 0 ? null : (WeeklyGames) crit.list().get(0);
    }

    public WeeklyGames getWeekGameAnswers(int gameCategory, int weekNo) {

        logger.info("gameCategory : {}", gameCategory);
        logger.info("weekNo : {}", weekNo);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("gameCategory", gameCategory));
        crit.add(Restrictions.eq("weekNo", weekNo));

        // return (WeeklyGames) crit.uniqueResult();
        //return (WeeklyGames) crit.list().get(0);
        return crit.list().size() == 0 ? null : (WeeklyGames) crit.list().get(0);
    }

    public WeeklyGames getWeekGameAnswersbyId(int id) {

        // logger.info("id : {}", id); 
        // logger.info("gameCategory : {}", gameCategory);
        // logger.info("weekNo : {}", weekNo);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        //crit.add(Restrictions.eq("weekNo", weekNo));

        // return (WeeklyGames) crit.uniqueResult();
        //return (WeeklyGames) crit.list().get(0);
        return crit.list().size() == 0 ? null : (WeeklyGames) crit.list().get(0);
    }   
   
    public List<GamePlayType> getGamePlayType() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<GamePlayType> gamePlayTypeList = (List<GamePlayType>) criteria.list();

        return gamePlayTypeList;
    }

    public List<WeeklyGames> listWeekGamesByCateAndDate(int gameCategory, Date date) {
        logger.info("gameCategory : {}", gameCategory);
      
        boolean enabled = true;

        Criteria crit = createEntityCriteria();    

        crit.add(Restrictions.eq("gameCategory", gameCategory));       
        crit.add(Restrictions.eq("enabled", enabled));

      // crit.add(Restrictions.ge("gameStartDate", date));
      // crit.add(Restrictions.le("gameExpiryDate", date));
      
      // To get game based on the game expiry date
       crit.add(Restrictions.ge("gameExpiryDate", date));


      // System.out.println("crit.toString() :: " + crit.toString());
       
        return crit.list();
    }
    
     public List<WeeklyGames> listWeekActiveGamesByDate(Date date) {
        //logger.info("gameCategory : {}", gameCategory);
        logger.info("date : {}", date);
        
        //Date date  = tunborUtility.getDate("Africa/Nigeria");
        
        Date newDate = subtractDays(date, 7);

        boolean enabled = true;

        Criteria crit = createEntityCriteria();
        /*
        crit.add(Restrictions.eq("gameCategory", gameCategory));        
        crit.add(Restrictions.eq("enabled", enabled));
       // crit.add(Restrictions.ge("gameStartDate", date));
       // crit.add(Restrictions.lt("gameExpiryDate", date));
         crit.add(Restrictions.eq("weekNo", 30));

         */

       // crit.add(Restrictions.eq("gameCategory", gameCategory));
        crit.add(Restrictions.eq("enabled", enabled));
        //crit.add(Restrictions.ge("gameStartDate", date));
       crit.add(Restrictions.le("gameExpiryDate", date));
        crit.add(Restrictions.ge("gameExpiryDate", newDate));
        

        return crit.list();
    }
     
    
     
     public WeeklyGames getWeekGameNoOfWinners(int id) {

        //
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        //crit.add(Restrictions.eq("weekNo", weekNo));

        // return (WeeklyGames) crit.uniqueResult();
        //return (WeeklyGames) crit.list().get(0);
        return crit.list().size() == 0 ? null : (WeeklyGames) crit.list().get(0);
    }

      /**
	 * add days to date in java
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}
	
	/**
	 * subtract days to date in java
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
				
		return cal.getTime();
	}

}

