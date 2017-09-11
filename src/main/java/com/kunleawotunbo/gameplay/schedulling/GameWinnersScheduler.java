/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.schedulling;

import com.kunleawotunbo.gameplay.model.GameAnswer;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.model.WeeklyGames;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.service.GameAnswerService;
import com.kunleawotunbo.gameplay.service.GameWinnerService;
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
public class GameWinnersScheduler {

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
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    //@Scheduled(fixedRate=5000)
     @Scheduled(cron = "0 0 0/4 ? *  *") // At second :00, at minute :00, every 4 hours starting at 00am, of every day
   // @Scheduled(cron = "0 0 0/4 ? *  *", zone = "Africa/Nigeria") // At second :00, at minute :00, every 4 hours starting at 00am, of every day
    public void printMessage() {
        List<User> userList = userService.findAllUsers();
        for (User item : userList) {
            System.out.println("First Name :: " + item.getFirstName());
        }
        System.out.println("I am called by Spring scheduler");
    }

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
        if (weeklyGamesList != null && !weeklyGamesList.isEmpty()){
            
        for (WeeklyGames weeklyGame : weeklyGamesList) {
           
           logger.info("Processing List of random winner for game id :: " + weeklyGame.getId());
           
            //weeklyGame.getGameAnswer();
            GameAnswer gameAnswerObj = gameAnswerService.findByGameId(weeklyGame.getId());
            gameAnswer = gameAnswerObj.getGameAnswer();
            int noOfWinners = weeklyGame.getNoOfWinners();
            
            // Generate list of random winners
            randomWeeklyGamesWinnersList = weeklyGamesAnswersService.listCorrectAnswersByGameId(gameAnswer, noOfWinners, noOfWinners);
            
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

}
