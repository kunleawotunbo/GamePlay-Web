/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.MatchPredictionAnswer;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface MatchPredictionAnswerDao {
    
   // MatchPredictionAnswer findById(Long id);
    
    MatchPredictionAnswer findById(int id);

    boolean saveMatchPredictionAnswer(MatchPredictionAnswer game);
    
    boolean updateMatchPredictionAnswer(MatchPredictionAnswer game);

    void deleteMatchPredictionAnswer(MatchPredictionAnswer game);

    List<MatchPredictionAnswer> listMatchPredictionAnswers(boolean enabled);
        
    List<MatchPredictionAnswer> listAllMatchPredictionAnswers();
    
    List<MatchPredictionAnswer> listAllMatchPredictionAnswers(String ans);
    
    List<MatchPredictionAnswer> listAllMatchPredictionAnswers(int weekNo);
   
    List<MatchPredictionAnswer> listAllMatchPredictionAnswersbyId (String ans, int id, int NoOfWinners);
    
    Long submittedAnswersByWeek(int weekNo);
    
    public List<MatchPredictionAnswer> listCorrectAnswersByGameId(String gameAnswer, int gameId, int noOfWinners);
    
    List<MatchPredictionAnswer> listAllMatchPredictionAnswersByGameId(int gameId);
    
    public List<MatchPredictionAnswer> listByCountry(String countryCode);
    
    public List<MatchPredictionAnswer> listAnswerByPhoneAndDate(String userPhoneNo, Date startDate, Date endDate);
    
    public List<MatchPredictionAnswer> listAnswerByCodeAndCountry(int code, String countryCode);
    
    public List<MatchPredictionAnswer> listAnswerByCode(int code);
    
    public List<MatchPredictionAnswer> listAnswersByUserPhoneNo(String userPhoneNo);
    
    public List<MatchPredictionAnswer> listCorrectAnswersForJPByGameId(String gameAnswer, int gameId, int noOfWinners);
    
    public void updateBulkMatchPredictionAnswers(List<MatchPredictionAnswer> items);
    
    public List<MatchPredictionAnswer> listAllMatchPredictionAnswerByGameId(int gameId);
}
