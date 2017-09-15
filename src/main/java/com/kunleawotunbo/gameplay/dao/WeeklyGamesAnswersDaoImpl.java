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
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.ThreadLocalRandom;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.transform.Transformers;
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
    private TunborUtility tunborUtility;

    public WeeklyGamesAnswers findById(Long id) {
        logger.info("id : {}", id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (WeeklyGamesAnswers) crit.uniqueResult();
    }

    public WeeklyGamesAnswers findById(int id) {
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

    public List<WeeklyGamesAnswers> listAllWeeklyGamesCorrectAnswersbyId(String Ans, int id, int NoOfWinners ) {
        //Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
         Date todaydate = tunborUtility.getDate("Africa/Nigeria");
         Criteria criteria = createEntityCriteria().add(Restrictions.eq("userAnswer", Ans).ignoreCase());
         criteria.add(Restrictions.eq("gameId", id));
        // criteria.setMaxResults(NoOfWinners);
        // criteria.add(Restrictions.
        // criteria.
         criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
         List<WeeklyGamesAnswers> weeklyGamesAnswersList = (List<WeeklyGamesAnswers>) criteria.list();
         
         List<WeeklyGamesAnswers> weeklyGamesAnswersListRandomWinner = new ArrayList<WeeklyGamesAnswers>();
         
         int i =0;
             
             while(i < weeklyGamesAnswersList.size() ){
                 
              String winnerPhoneNumber =  weeklyGamesAnswersList.get(i).getUserPhoneNo();
              int winnergameid =  weeklyGamesAnswersList.get(i).getGameId();
              String winnerPhoneNumber2 = null;
              int winnergameid2;
                    
                logger.info("i Size :" + String.valueOf(i));
              
                     int j = i + 1;
                    while ( j  < weeklyGamesAnswersList.size()){
                        
                        logger.info("j Size :" + String.valueOf(j));
                        
                                winnerPhoneNumber2 = weeklyGamesAnswersList.get(j).getUserPhoneNo();
                                winnergameid2 =  weeklyGamesAnswersList.get(j).getGameId();
                                
                                 logger.info(" i phone value :" + winnerPhoneNumber + " j phone value :" + winnerPhoneNumber2 );
                                 logger.info(" i game id :" + String.valueOf(winnergameid) + " j game id :" + String.valueOf(winnergameid2) );
                                 if(winnerPhoneNumber.equals(winnerPhoneNumber2) && winnergameid == winnergameid2 ){
                       
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
              for(int k = 0; k < NoOfWinners; k++){
             
             int j = obj.getRandomList(weeklyGamesAnswersList);
             
              logger.info("Generated Random Numbers : {}", j);
              
             
             weeklyGamesAnswersListRandomWinner.add(weeklyGamesAnswersList.get(j));
             
              logger.info("No of Winners :" + String.valueOf(NoOfWinners));
             logger.info("Game List Random Winner Phone Number :" + String.valueOf(weeklyGamesAnswersList.get(j).getUserPhoneNo()));
             
                 WeeklyGamesAnswers weeklyGamesAnswers = weeklyGamesAnswersList.remove(j);
                 
                 logger.info("Game List latest Size :" + String.valueOf(weeklyGamesAnswersList.size()));
                 
                 logger.info("Weekly Random Winner Array Size :" + String.valueOf(weeklyGamesAnswersListRandomWinner.size()));
               
              }
                 }catch(Exception e){
               
              System.err.println( e.getMessage());
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
            
            if (weeklyGamesAnswersList != null && !weeklyGamesAnswersList.isEmpty()){
                logger.info("weeklyGamesAnswersList is not empty :: " + weeklyGamesAnswersList.size());
            int randomIndex = rand.nextInt(weeklyGamesAnswersList.size());
            WeeklyGamesAnswers randomWeeklyGamesAnswer = weeklyGamesAnswersList.get(randomIndex);
            weeklyGamesAnswersList.remove(randomIndex);
            
             logger.info("randomWeeklyGamesAnswer.getId() ::  " + randomWeeklyGamesAnswer.getId());
            // Add each random winner to the arrayList.
            weeklyGamesAnswersRandomWinnerList.add(randomWeeklyGamesAnswer);
            
            }else {
                logger.info("weeklyGamesAnswersList is null or empty");
            }
        }

        
        return weeklyGamesAnswersRandomWinnerList;
    }

}
