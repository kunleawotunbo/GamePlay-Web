/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.schedulling;

import com.kunleawotunbo.gameplay.interfaces.Definitions;
import com.kunleawotunbo.gameplay.model.GameAnswer;
import com.kunleawotunbo.gameplay.model.MatchPrediction;
import com.kunleawotunbo.gameplay.model.MatchPredictionAnswer;
import com.kunleawotunbo.gameplay.model.MatchPredictionResult;
import com.kunleawotunbo.gameplay.model.MatchPredictionWinner;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.service.GameAnswerService;
import com.kunleawotunbo.gameplay.service.GameWinnerService;
import com.kunleawotunbo.gameplay.service.MatchPredictionAnswerService;
import com.kunleawotunbo.gameplay.service.MatchPredictionResultService;
import com.kunleawotunbo.gameplay.service.MatchPredictionService;
import com.kunleawotunbo.gameplay.service.MatchPredictionWinnerService;
import com.kunleawotunbo.gameplay.service.UserService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesAnswersService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author Olakunle Awotunbo
 */
public class MatchPredictionProcessing {

    @Autowired
    private UserService userService;

    @Autowired
    private WeeklyGamesService weeklyGamesService;

    @Autowired
    private GameAnswerService gameAnswerService;

    @Autowired
    private WeeklyGamesAnswersService weeklyGamesAnswersService;

    @Autowired
    private GameWinnerService gameWinnerService;

    @Autowired
    private TunborUtility tunborUtility;

    @Autowired
    private MatchPredictionService matchPredictionService;

    @Autowired
    private MatchPredictionAnswerService matchPredictionAnswerService;

    @Autowired
    private MatchPredictionResultService matchPredictionResultService;

    @Autowired
    private MatchPredictionWinnerService matchPredictionWinnerService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //@Scheduled(fixedRate=5000)
    //@Scheduled(cron = "0 0 0/4 ? *  *", zone = "Africa/Nigeria") // At second :00, at minute :00, every 4 hours starting at 00am, of every day
    public void processWinner() {

        List<WeeklyGames> weeklyGamesList = null;
        List<WeeklyGamesAnswers> randomWeeklyGamesWinnersList = null;
        int status = 0;
        String gameAnswer = "";
        weeklyGamesList = weeklyGamesService.listUnproccessedGames(status, tunborUtility.getDate("Africa/Lagos"));
        /*
        for (User item : userList) {
            System.out.println("First Name :: " + item.getFirstName());
        }
         */
        System.out.println("weeklyGamesList.size() :: " + weeklyGamesList.size());
        if (weeklyGamesList != null && !weeklyGamesList.isEmpty()) {

            for (WeeklyGames weeklyGame : weeklyGamesList) {

                logger.info("Processing List of random winner for game id :: " + weeklyGame.getId());

                //weeklyGame.getGameAnswer();
                GameAnswer gameAnswerObj = gameAnswerService.findByGameId(weeklyGame.getId());
                gameAnswer = gameAnswerObj.getGameAnswer();
                int noOfWinners = weeklyGame.getNoOfWinners();

                // Generate list of random winners
                randomWeeklyGamesWinnersList = weeklyGamesAnswersService.listCorrectAnswersByGameId(gameAnswer, weeklyGame.getId(), noOfWinners);

                // Persist list of random winners for weekly game
                gameWinnerService.saveBulkGameWinners(tunborUtility.weeklyGamesAnswersListToGameWinnerList(randomWeeklyGamesWinnersList));

                // set proccessed to 1.
                //weeklyGamesService.updateWeeklyGame(weeklyGame);
                logger.info("Finished weeklyGameId :: " + weeklyGame.getId());
            }
            System.out.println("I am called by processWinner");

        } else {
            System.out.println("No expired game to process");
        }
    }

   // @Scheduled(fixedDelay = 10000)
    // @Scheduled(cron = "0 0 0/1 1/1 * ? *") // Run every hour
      @Scheduled(cron = "0 0/16 0 ? * * ") // Every 16mins
    //@Scheduled(cron = "0 0 0/4 ? *  *", zone = "Africa/Nigeria") // At second :00, at minute :00, every 4 hours starting at 00am, of every day
   // @Scheduled(cron = "0 0 0/4 ? *  *") 
    public void processMatchPredictionWinners() {
        logger.info("Inside processMatchPredictionWinners to process Match Prediction winners");
        List<MatchPrediction> matchPredictionList = null;
        List<MatchPredictionAnswer> randomMatchPredictionWinnersList = null;
        int status = 0;
        String gameAnswer = "";
        matchPredictionList = matchPredictionService.listUnproccessedGames(status, tunborUtility.getDate(Definitions.TIMEZONE));
        /*
        for (User item : userList) {
            System.out.println("First Name :: " + item.getFirstName());
        }
         */
        System.out.println("matchPredictionList.size() :: " + matchPredictionList.size());
        if (matchPredictionList != null && !matchPredictionList.isEmpty()) {

            for (MatchPrediction matchPrediction : matchPredictionList) {

                logger.info("Processing List of random winner for matchPrediction id :: " + matchPrediction.getId());

                try {                    
                    MatchPredictionResult matchPResultObj = matchPredictionResultService.findByMatchPredictionId(matchPrediction.getId());
                    logger.info("matchPResultObj :: " + matchPResultObj);
                    if (matchPResultObj != null) {

                        gameAnswer = matchPResultObj.getWinner();
                        if (gameAnswer != null && "" != gameAnswer) {
                            int noOfWinners = matchPrediction.getNoOfWinners();
                             logger.info("noOfWinners :: " + noOfWinners);
                            // Generate list of random winners
                            randomMatchPredictionWinnersList = matchPredictionAnswerService.listCorrectAnswersByGameId(gameAnswer, matchPrediction.getId(), noOfWinners);
                            logger.info("randomMatchPredictionWinnersList.size() up :: " + randomMatchPredictionWinnersList.size());
                            if(!randomMatchPredictionWinnersList.isEmpty()){
                                logger.info("randomMatchPredictionWinnersList.size() :: " + randomMatchPredictionWinnersList.size());
                                // Persist list of random winners for weekly game
                                List<MatchPredictionWinner>  winnersList =  tunborUtility.matchPredictionsListToGameWinnerList(randomMatchPredictionWinnersList);
                                matchPredictionWinnerService.saveBulkMatchPredictionWinners(winnersList);

                                // send sms to list of winners.                             
                                String message = "This is to notify you that you have won for the match prediction";
                                
                                for (MatchPredictionWinner item : winnersList) {
                                    tunborUtility.sendSMSSingle(item.getUserPhoneNo(), message);
                                    logger.info("SMS sent to :: " + item.getUserPhoneNo());
                                }
                                
                                
                                // set proccessed to 1.
                                int processedStatus = 1;
                                matchPrediction.setStatus(processedStatus);
                                matchPredictionService.updatePrediction(matchPrediction);

                                logger.info("Finished weeklyGameId :: " + matchPrediction.getId());
                            }else {
                                logger.info("randomMatchPredictionWinnersList is null or empty for matchPredictionId :: " + matchPrediction.getId());
                            }
                            
                        }else {
                            logger.info("Answer not yet set for match with ID :: " + matchPrediction.getId());
                        }

                    } else {
                        logger.info("MatchPredictionResult object is null for game Id :: " + matchPrediction.getId());
                    }

                } catch (Exception e) {
                    logger.info("Error Occurred while processing match with id :: " + matchPrediction.getId());
                    e.printStackTrace();
                }
            }
            System.out.println("I am called by processMatchPredictionWinners");

        } else {
            System.out.println("No expired game to process");
        }
    }

}
