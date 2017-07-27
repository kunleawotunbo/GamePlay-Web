/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface WeeklyGamesAnswersService {
    
     WeeklyGamesAnswers findById(Long id);

    boolean saveWeeklyGamesAnswer(WeeklyGamesAnswers game);
    
    boolean updateWeeklyGamesAnswer(WeeklyGamesAnswers game);

    void deleteWeeklyGamesAnswer(WeeklyGamesAnswers game);

    List<WeeklyGamesAnswers> listWeeklyGamesAnswers(boolean enabled);
        
    List<WeeklyGamesAnswers> listAllWeeklyGamesAnswers(); 
    
    public List<WeeklyGamesAnswers> listAllWeeklyGamesCorrectAnswers(String Ans );
    
    public List<WeeklyGamesAnswers> listAllActiveWeekGamesAnswers(int weekNo);
         
    Long submittedAnswersByWeek(int weekNo);

    public List<WeeklyGamesAnswers> ActiveWeekGamesAnswersByCategory(int GameWeek, int id);
}
