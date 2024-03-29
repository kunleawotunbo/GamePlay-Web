/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Repository("weeklyGamesAnswersDao")
@Transactional
public class WeeklyGamesAnswersDaoImpl extends AbstractDao<Long, WeeklyGamesAnswers> implements WeeklyGamesAnswersDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
     @Lazy // to avoid error of circular dependency
    private TunborUtility tunborUtility;

    public WeeklyGamesAnswers findById(Long id) {
        logger.info("id :: " + id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (WeeklyGamesAnswers) crit.uniqueResult();
    }

    public WeeklyGamesAnswers findById(int id) {
        logger.info("id ::" + id);

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
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("dateAnswered"));
        // Criteria criteria = createEntityCriteria();
        // criteria.addOrder(Order.desc("dateAnswered"));
        criteria.add(Restrictions.eq("isRandomWinner", enabled));
        //ProjectionList projectionList = Projections.projectionList();
        //criteria.setProjection(Projections.distinct(projectionList.add(Projections.property("userPhoneNo"))));
        //criteria.setProjection(Projections.distinct(Projections.property("userPhoneNo")));
        // criteria.setProjection(Projections.distinct("userPhoneNo"));
        // criteria.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("userPhoneNo"))));
        // criteria.       
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        //projectionList.add(Projections.distinct(projectionList.add(Projections.property("userPhoneNo"))));
        // projectionList.add(Projections.countDistinct("userPhoneNo"));
        // projectionList.add(Projections.distinct("userPhoneNo"));
        //criteria.setProjection(projectionList);
        //criteria.setResultTransformer(Transformers.aliasToBean(WeeklyGamesAnswers.class));
        // List<WeeklyGamesAnswers> weeklyGamesAnswersList = criteria.list();
        // List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();
        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();

        //int WeeklyGameWinnerLength = weeklyGamesAnswersList.size();
        int i = 0;

        while (i < weeklyGamesAnswersList.size()) {

            String winnerPhoneNumber = weeklyGamesAnswersList.get(i).getUserPhoneNo();
            String winnerPhoneNumber2 = null;

            logger.info("i Size :" + String.valueOf(i));

            int j = i + 1;
            while (j < weeklyGamesAnswersList.size()) {

                logger.info("j Size :" + String.valueOf(j));

                winnerPhoneNumber2 = weeklyGamesAnswersList.get(j).getUserPhoneNo();

                logger.info(" i phone value :" + winnerPhoneNumber + " j phone value :" + winnerPhoneNumber2);

                if (winnerPhoneNumber.equals(winnerPhoneNumber2)) {

                    weeklyGamesAnswersList.remove(j);
                    j--;
                    // i--;
                }
                j++;
            }

            /* if( i + 1  < weeklyGamesAnswersList.size()){
                  
                    winnerPhoneNumber2 = weeklyGamesAnswersList.get(i + 1).getUserPhoneNo();
                      
                   if(winnerPhoneNumber.equals(winnerPhoneNumber2)){
                     
                        weeklyGamesAnswersList.remove(i + 1);
                        
                       // i--;
                      }
                 }*/
            i++;
        }
        return weeklyGamesAnswersList;

    }

    public List<WeeklyGamesAnswers> listWeeklyGamesAnswersByModifiedDate(boolean enabled) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Date todaydate = tunborUtility.getDate("Africa/Nigeria");

        Date newDate = subtractDays(todaydate, 7);

        Criteria criteria = createEntityCriteria().addOrder(Order.desc("dateAnswered"));

        // boolean winnerstatus = true;
        criteria.add(Restrictions.eq("isRandomWinner", enabled));

        criteria.add(Restrictions.le("modifiedDate", todaydate));
        criteria.add(Restrictions.ge("modifiedDate", newDate));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.

        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();

        //int WeeklyGameWinnerLength = weeklyGamesAnswersList.size();
        int i = 0;

        while (i < weeklyGamesAnswersList.size()) {

            String winnerPhoneNumber = weeklyGamesAnswersList.get(i).getUserPhoneNo();
            String winnerPhoneNumber2 = null;

            logger.info("i Size :" + String.valueOf(i));

            int j = i + 1;
            while (j < weeklyGamesAnswersList.size()) {

                logger.info("j Size :" + String.valueOf(j));

                winnerPhoneNumber2 = weeklyGamesAnswersList.get(j).getUserPhoneNo();

                logger.info(" i phone value :" + winnerPhoneNumber + " j phone value :" + winnerPhoneNumber2);

                if (winnerPhoneNumber.equals(winnerPhoneNumber2)) {

                    weeklyGamesAnswersList.remove(j);
                    j--;

                }
                j++;
            }

            i++;
        }
        return weeklyGamesAnswersList;

    }

    /**
     * add days to date in java
     *
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
     *
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

    public List<WeeklyGamesAnswers> listAllWeeklyGamesAnswers() {
        //Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("weekNo"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();

        return weeklyGamesAnswersList;
    }

    public List<WeeklyGamesAnswers> listAllActiveWeekGamesAnswers(int weekNo) {
        //Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));

        Criteria criteria = createEntityCriteria().addOrder(Order.desc("weekNo"));
        criteria.add(Restrictions.eq("weekNo", weekNo));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();

        return weeklyGamesAnswersList;
    }

    public List<WeeklyGamesAnswers> ActiveWeekGamesAnswersByCategory(int weekNo, int category) {
        //Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));

        Criteria criteria = createEntityCriteria().addOrder(Order.desc("weekNo"));
        //criteria.add(Restrictions.eq("weekNo", weekNo));
        criteria.add(Restrictions.eq("gameId", category));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();

        return weeklyGamesAnswersList;
    }

    public List<WeeklyGamesAnswers> listAllWeeklyGamesCorrectAnswers(String Ans) {
        //Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));

        Criteria criteria = createEntityCriteria().add(Restrictions.eq("userAnswer", Ans));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();

        return weeklyGamesAnswersList;
    }

    public List<WeeklyGamesAnswers> listAllWeeklyGamesWinnersByPhoneNo(String PhoneNumber) {
        //Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        boolean winnerstatus = true;
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("userPhoneNo", PhoneNumber));
        criteria.add(Restrictions.eq("isRandomWinner", winnerstatus));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();

        return weeklyGamesAnswersList;
    }

    public List<WeeklyGamesAnswers> listAllWeeklyGamesCorrectAnswersbyId(String Ans, int id, int NoOfWinners) {
        //Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        //Date todaydate = tunborUtility.getDate("Africa/Nigeria");
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("userAnswer", Ans).ignoreCase());
        criteria.add(Restrictions.eq("gameId", id));
        // criteria.setMaxResults(NoOfWinners);
        // criteria.add(Restrictions.
        // criteria.
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();

        List<WeeklyGamesAnswers> weeklyGamesAnswersListRandomWinner = new ArrayList<WeeklyGamesAnswers>();

        int i = 0;

        while (i < weeklyGamesAnswersList.size()) {

            String winnerPhoneNumber = weeklyGamesAnswersList.get(i).getUserPhoneNo();
            int winnergameid = weeklyGamesAnswersList.get(i).getGameId();
            String winnerPhoneNumber2 = null;
            int winnergameid2;

            logger.info("i Size :" + String.valueOf(i));

            int j = i + 1;
            while (j < weeklyGamesAnswersList.size()) {

                logger.info("j Size :" + String.valueOf(j));

                winnerPhoneNumber2 = weeklyGamesAnswersList.get(j).getUserPhoneNo();
                winnergameid2 = weeklyGamesAnswersList.get(j).getGameId();

                logger.info(" i phone value :" + winnerPhoneNumber + " j phone value :" + winnerPhoneNumber2);
                logger.info(" i game id :" + String.valueOf(winnergameid) + " j game id :" + String.valueOf(winnergameid2));
                if (winnerPhoneNumber.equals(winnerPhoneNumber2) && winnergameid == winnergameid2) {

                    weeklyGamesAnswersList.remove(j);
                    j--;

                }
                j++;
            }
            i++;
        }

        //List<WeeklyGamesAnswers> weeklyGamesAnswersListRandomWinner = null;
        //  int WeeklyGameAnswerLength = weeklyGamesAnswersList.size();
        logger.info("Game List  Start Size :" + String.valueOf(weeklyGamesAnswersList.size()));

        WeeklyGamesAnswersDaoImpl obj = new WeeklyGamesAnswersDaoImpl();
        //for(int i = 0; i < 10; i++){
        // System.out.println(obj.getRandomList(weeklyGamesAnswersList));
        //}

        try {
            for (int k = 0; k < NoOfWinners; k++) {

                int j = obj.getRandomList(weeklyGamesAnswersList);

                logger.info("Generated Random Numbers : {}", j);

                weeklyGamesAnswersListRandomWinner.add(weeklyGamesAnswersList.get(j));

                logger.info("No of Winners :" + String.valueOf(NoOfWinners));
                logger.info("Game List Random Winner Phone Number :" + String.valueOf(weeklyGamesAnswersList.get(j).getUserPhoneNo()));

                WeeklyGamesAnswers weeklyGamesAnswers = weeklyGamesAnswersList.remove(j);

                logger.info("Game List latest Size :" + String.valueOf(weeklyGamesAnswersList.size()));

                logger.info("Weekly Random Winner Array Size :" + String.valueOf(weeklyGamesAnswersListRandomWinner.size()));

            }
        } catch (Exception e) {

            System.err.println(e.getMessage());
        }

        // return weeklyGamesAnswersList;
        return weeklyGamesAnswersListRandomWinner;
    }

    public Long submittedAnswersByWeek(int weekNo) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));

        criteria.add(Restrictions.eq("weekNo", weekNo));
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();

        return count;
    }

    public int getRandomList(List<WeeklyGamesAnswers> list) {

        //0-4
        int index = ThreadLocalRandom.current().nextInt(list.size());
        System.out.println("\nIndex :" + index);
        return index;

    }

    public List<WeeklyGamesAnswers> listCorrectAnswersByGameId(String gameAnswer, int gameId, int noOfWinners) {
        logger.info("gameAnswer :: " + gameAnswer);
        logger.info("gameId :: " + gameId);
        // Criteria criteria = createEntityCriteria();
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAnswered"));
        criteria.add(Restrictions.eq("userAnswer", gameAnswer).ignoreCase());
        criteria.add(Restrictions.eq("gameId", gameId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        logger.info("crit.toString() :: " + criteria.toString());

        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();
        List<WeeklyGamesAnswers> weeklyGamesAnswersRandomWinnerList = new ArrayList<WeeklyGamesAnswers>();

        Random rand = new Random();

        for (int i = 0; i < noOfWinners; i++) {

            if (weeklyGamesAnswersList != null && !weeklyGamesAnswersList.isEmpty()) {
                logger.info("weeklyGamesAnswersList is not empty :: " + weeklyGamesAnswersList.size());
                int randomIndex = rand.nextInt(weeklyGamesAnswersList.size());
                WeeklyGamesAnswers randomWeeklyGamesAnswer = weeklyGamesAnswersList.get(randomIndex);
                weeklyGamesAnswersList.remove(randomIndex);

                logger.info("randomWeeklyGamesAnswer.getId() ::  " + randomWeeklyGamesAnswer.getId());
                // Add each random winner to the arrayList.
                weeklyGamesAnswersRandomWinnerList.add(randomWeeklyGamesAnswer);

            } else {
                logger.info("weeklyGamesAnswersList is null or empty");
            }
        }

        return weeklyGamesAnswersRandomWinnerList;
    }

    public List<WeeklyGamesAnswers> listAnswerByPhoneAndDate(String userPhoneNo, Date startDate, Date endDate) {
        logger.info("userPhoneNo :: " + userPhoneNo);

        Criteria crit = createEntityCriteria();

        crit.add(Restrictions.eq("userPhoneNo", userPhoneNo));

        // crit.add(Restrictions.ge("gameStartDate", date));
        // crit.add(Restrictions.le("gameExpiryDate", date));
        // To get game based on the game expiry date
        crit.add(Restrictions.ge("dateAnswered", startDate));
        crit.add(Restrictions.le("dateAnswered", endDate));

        System.out.println("crit.toString() :: " + crit.toString());

        return crit.list();
    }

    public List<WeeklyGamesAnswers> listAnswerByCodeAndCountry(int code, String playersCountry) {
        logger.info("code :: " + code);
        logger.info("playersCountry :: " + playersCountry);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("code", code));
        crit.add(Restrictions.eq("playersCountry", playersCountry));

        return crit.list();
    }

    public List<WeeklyGamesAnswers> listAnswerByCode(int code) {
        logger.info("code :: " + code);

        Criteria crit = createEntityCriteria().addOrder(Order.desc("dateAnswered"));
        crit.add(Restrictions.eq("code", code));

        return crit.list();
    }

    public List<WeeklyGamesAnswers> listAnswersByUserPhoneNo(String userPhoneNo) {
        logger.info("userPhoneNo :: " + userPhoneNo);

        Criteria crit = createEntityCriteria().addOrder(Order.asc("dateAnswered"));
        crit.add(Restrictions.eq("userPhoneNo", userPhoneNo));

        return crit.list();
    }

    public List<WeeklyGamesAnswers> listCorrectAnswersForJPByGameId(String gameAnswer, int gameId, int noOfWinners) {
        logger.info("gameAnswer :: " + gameAnswer);
        logger.info("gameId :: " + gameId);
        
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAnswered"));        
        criteria.add(Restrictions.eq("gameId", gameId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        logger.info("crit.toString() :: " + criteria.toString());

        // List all answers by the gameId
        List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();
        List<WeeklyGamesAnswers> weeklyGamesAnswersRandomWinnerList = new ArrayList<WeeklyGamesAnswers>();
        List<WeeklyGamesAnswers> correctAnswersList = new ArrayList<WeeklyGamesAnswers>();
        WeeklyGamesAnswers wkGamesAnswerObj = null;

        // Check the user answer against the correct answer
        // weeklyGamesAnswersList
        boolean answerCorrect = false;
        for (WeeklyGamesAnswers item : weeklyGamesAnswersList) {
            logger.info("Checking WeeklyGamesAnswers id ::  " + item.getId());
          
            String[] v1 = gameAnswer.split("-");
            String[] v2 = item.getUserAnswer().split("-");
           
            Arrays.sort(v1);
            Arrays.sort(v2);
            answerCorrect = Arrays.equals(v1, v2);
           
            logger.info("Answer equal for WeeklyGamesAnswers id   :: " + item.getId() + " ::  " + Arrays.equals(v1, v2));
            // If answer is correct, update the item and set to correct
            if (answerCorrect){
                logger.info("Setting isCorrect for WeeklyGamesAnswers id :: " + item.getId());
                wkGamesAnswerObj = new WeeklyGamesAnswers();
                
                wkGamesAnswerObj.setId(item.getId());
                wkGamesAnswerObj.setGameId(item.getGameId());
                wkGamesAnswerObj.setUserPhoneNo(item.getUserPhoneNo());
                wkGamesAnswerObj.setUserAnswer(item.getUserAnswer());
                wkGamesAnswerObj.setDateAnswered(item.getDateAnswered());
                wkGamesAnswerObj.setCreatedDate(item.getCreatedDate());
                wkGamesAnswerObj.setModifiedDate(item.getModifiedDate());
                wkGamesAnswerObj.setWeekNo(item.getWeekNo());
                wkGamesAnswerObj.setIsRandomWinner(item.getIsRandomWinner());
                wkGamesAnswerObj.setIsWinner(item.getIsWinner());
                wkGamesAnswerObj.setIpAddress(item.getIpAddress());
                wkGamesAnswerObj.setPlayersCountry(item.getPlayersCountry());                
                wkGamesAnswerObj.setCode(item.getCode());
                wkGamesAnswerObj.setCountryCode(item.getCountryCode());
                wkGamesAnswerObj.setCity(item.getCity());
                wkGamesAnswerObj.setCorrect(true);
            
               
                item.setCorrect(true);
              // correctAnswersList.add(wkGamesAnswerObj);    
              correctAnswersList.add(item);    
                
            }
            
            // The correctAnswersList will be updated as correct answers and shall be used to generate list of winners
           // correctAnswersList.add(wkGamesAnswerObj);      
            // correctAnswersList.add(item);      
        }
         // Updaate the game answer to processed
            updateBulkWeeklyGamesAnswers(correctAnswersList);


        Random rand = new Random();

        for (int i = 0; i < noOfWinners; i++) {

            if (correctAnswersList != null && !correctAnswersList.isEmpty()) {
                logger.info("correctAnswersList is not empty :: " + correctAnswersList.size());
                int randomIndex = rand.nextInt(correctAnswersList.size());
                WeeklyGamesAnswers randomWeeklyGamesAnswer = correctAnswersList.get(randomIndex);
                // Remove the randomIndex that was selected from the list
                correctAnswersList.remove(randomIndex);

                logger.info("randomWeeklyGamesAnswer.getId() ::  " + randomWeeklyGamesAnswer.getId());
                // Add each random winner to the arrayList.
                weeklyGamesAnswersRandomWinnerList.add(randomWeeklyGamesAnswer);

            } else {
                logger.info("weeklyGamesAnswersList is null or empty");
            }
        }

        return weeklyGamesAnswersRandomWinnerList;
    }

    public void updateBulkWeeklyGamesAnswers(List<WeeklyGamesAnswers> items) {
         if(items!=null && items.size()>0){
		 for(WeeklyGamesAnswers item: items){
			 saveOrUpdate(item);
                         logger.info("Update item to correct answer WeeklyGamesAnswers id :: " + item.getId());
                         //update(item);
		 }
	 }
    }

    public List<WeeklyGamesAnswers> listAllWeeklyGameAnswersByGameId(int gameId) {
        logger.info("gameId :: " +  gameId);

       
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("dateAnswered"));

        criteria.add(Restrictions.eq("gameId", gameId));
        
        List<WeeklyGamesAnswers> gameAnswersList = (List<WeeklyGamesAnswers>) criteria.list();
       
        return gameAnswersList;
    }

}
