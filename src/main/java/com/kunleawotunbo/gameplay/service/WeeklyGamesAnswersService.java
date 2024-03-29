/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface WeeklyGamesAnswersService {
    
     WeeklyGamesAnswers findById(Long id);
     
     WeeklyGamesAnswers findById(int id);

    boolean saveWeeklyGamesAnswer(WeeklyGamesAnswers game);
    
    boolean updateWeeklyGamesAnswer(WeeklyGamesAnswers game);

    void deleteWeeklyGamesAnswer(WeeklyGamesAnswers game);

    List<WeeklyGamesAnswers> listWeeklyGamesAnswers(boolean enabled);
    
    List<WeeklyGamesAnswers> listWeeklyGamesAnswersByModifiedDate(boolean enabled);
        
    List<WeeklyGamesAnswers> listAllWeeklyGamesAnswers(); 
    
    public List<WeeklyGamesAnswers> listAllWeeklyGamesCorrectAnswers(String Ans );
    
    public List<WeeklyGamesAnswers> listAllWeeklyGamesWinnersByPhoneNo(String PhoneNumber);
    
    public List<WeeklyGamesAnswers> listAllActiveWeekGamesAnswers(int weekNo);
    
    List<WeeklyGamesAnswers> listAllWeeklyGamesCorrectAnswersbyId (String ans, int id, int NoOfWinners);
         
    Long submittedAnswersByWeek(int weekNo);

    public List<WeeklyGamesAnswers> ActiveWeekGamesAnswersByCategory(int GameWeek, int id);
    
    public List<WeeklyGamesAnswers> listCorrectAnswersByGameId(String gameAnswer, int gameId, int noOfWinners);
    
    public List<WeeklyGamesAnswers> listAnswerByPhoneAndDate(String userPhoneNo, Date startDate, Date endDate);
    
    public List<WeeklyGamesAnswers> listAnswerByCodeAndCountry(int code, String playersCountry);
    
    public List<WeeklyGamesAnswers> listAnswerByCode(int code);
    
    public List<WeeklyGamesAnswers> listAnswersByUserPhoneNo(String userPhoneNo);
    
    public List<WeeklyGamesAnswers> listCorrectAnswersForJPByGameId(String gameAnswer, int gameId, int noOfWinners);
    
    public void updateBulkWeeklyGamesAnswers(List<WeeklyGamesAnswers> items);
    
    public List<WeeklyGamesAnswers> listAllWeeklyGameAnswersByGameId(int gameId);
}
