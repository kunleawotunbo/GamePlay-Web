/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.MatchPredictionAnswerDao;
import com.kunleawotunbo.gameplay.model.MatchPredictionAnswer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("matchPredictionAnswerService")
public class MatchPredictionAnswerServiceImpl implements MatchPredictionAnswerService{

    @Autowired 
    private MatchPredictionAnswerDao matchPredictionAnswerDao;
    
    public MatchPredictionAnswer findById(int id) {
        return matchPredictionAnswerDao.findById(id);
    }

    public boolean saveMatchPredictionAnswer(MatchPredictionAnswer game) {
        return matchPredictionAnswerDao.saveMatchPredictionAnswer(game);
    }

    public boolean updateMatchPredictionAnswer(MatchPredictionAnswer game) {
        return matchPredictionAnswerDao.updateMatchPredictionAnswer(game);
    }

    public void deleteMatchPredictionAnswer(MatchPredictionAnswer game) {
        matchPredictionAnswerDao.deleteMatchPredictionAnswer(game);
    }

    public List<MatchPredictionAnswer> listMatchPredictionAnswers(boolean enabled) {
        return matchPredictionAnswerDao.listMatchPredictionAnswers(enabled);
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswers() {
        return matchPredictionAnswerDao.listAllMatchPredictionAnswers();
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswers(String ans) {
        return matchPredictionAnswerDao.listAllMatchPredictionAnswers(ans);
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswers(int weekNo) {
        return matchPredictionAnswerDao.listAllMatchPredictionAnswers(weekNo);
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswersbyId(String ans, int id, int NoOfWinners) {
        return matchPredictionAnswerDao.listAllMatchPredictionAnswersbyId(ans, id, NoOfWinners);
    }

    public Long submittedAnswersByWeek(int weekNo) {
        return matchPredictionAnswerDao.submittedAnswersByWeek(weekNo);
    }

    public List<MatchPredictionAnswer> listCorrectAnswersByGameId(String gameAnswer, int gameId, int noOfWinners) {
        return matchPredictionAnswerDao.listCorrectAnswersByGameId(gameAnswer, gameId, noOfWinners);
    }

    public List<MatchPredictionAnswer> listAllMatchPredictionAnswersByGameId(int gameId) {
        return matchPredictionAnswerDao.listAllMatchPredictionAnswersByGameId(gameId);
    }
    
    public List<MatchPredictionAnswer> listByCountry(String countryCode){
        
        return matchPredictionAnswerDao.listByCountry(countryCode);
        
    }
    
}
