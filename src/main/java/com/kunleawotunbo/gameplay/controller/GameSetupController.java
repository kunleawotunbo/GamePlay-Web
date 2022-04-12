/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
import com.kunleawotunbo.gameplay.model.GameAnswer;
import com.kunleawotunbo.gameplay.service.GamePlayTypeService;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesAnswersService;
import com.kunleawotunbo.gameplay.service.WeeklyGamesService;
import com.kunleawotunbo.gameplay.service.GameAnswerService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author BELLO
 */
@Controller
@RequestMapping("/admin/")
@SessionAttributes("roles")
public class GameSetupController {

    @Autowired
    private GameService gameService;

    @Autowired
    private TunborUtility tunborUtility;

    @Autowired
    private GamePlayTypeService gamePlayTypeService;

    @Autowired
    private WeeklyGamesService weeklyGamesService;

    @Autowired
    private WeeklyGamesAnswersService weeklyGamesAnswersService;

    @Autowired
    private GameAnswerService gamesAnswerService;

    final Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/setupGameCategory", method = RequestMethod.GET)
    public String listGameCategory(ModelMap model, HttpServletRequest request) {

        model.addAttribute("game", new Game());
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/setupGameCategory";
    }

    @RequestMapping(value = {"/setup-gameType-{id}"}, method = RequestMethod.GET)
    public String setupThisGame(@PathVariable int id, ModelMap model) {
        boolean status = true;
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("game", gameService.findById(id));

        return "/admin/setupThisGameCategory";
    }

    @RequestMapping(value = "/listallanswer", method = RequestMethod.GET)
    public String listAllAnswers(ModelMap model, HttpServletRequest request) {
        logger.info("ListAllAnswer");
        List<String> answerStatus = new ArrayList<String>();
        List<String> adminGameAnswer = new ArrayList<String>();
        //String AnswerStatus;

        List<WeeklyGamesAnswers> listWeeklyGamesAnswers = weeklyGamesAnswersService.listAllWeeklyGamesAnswers();
        int WeeklyGameAnswerLength = listWeeklyGamesAnswers.size();
        for (int i = 0; i < WeeklyGameAnswerLength; i++) {
            try {
                String useranswer2 = listWeeklyGamesAnswers.get(i).getUserAnswer();
                String useranswer = "";
                if (useranswer2 == null) {
                    useranswer = "NO ENTRY";
                } else {
                    useranswer = useranswer2.toUpperCase(Locale.ENGLISH);
                }
                int answerid = listWeeklyGamesAnswers.get(i).getGameId();

                GameAnswer weeklyGamesAnswer = gamesAnswerService.findByGameId(answerid);
                String gameanswer2 = weeklyGamesAnswer.getGameAnswer();
                String gameanswer = "";
                if (gameanswer2 == null) {
                    gameanswer = "NOT SET";
                } else {
                    gameanswer = gameanswer2.toUpperCase(Locale.ENGLISH);
                }
                adminGameAnswer.add(gameanswer);

                if (gameanswer == null ? useranswer == null : gameanswer.equals(useranswer)) {
                    answerStatus.add("CORRECT");
                } else {
                    answerStatus.add("WRONG");
                }

                logger.info("Game Answer:" + gameanswer);
                logger.info("User Answer:" + useranswer);

                logger.info(AnswerStatus.get(i));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }

        model.addAttribute("gameanswerlist", listWeeklyGamesAnswers);
        model.addAttribute("answerstatus", AnswerStatus);
        model.addAttribute("admingameanswer", AdminGameAnswer);
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/listallanswers";
    }

    @RequestMapping(value = "/view-all-gameCategory-answer-{id}", method = RequestMethod.GET)
    public String listAllCategoryAnswer(@PathVariable int id, ModelMap model) {
        logger.info("ListCategoryAnswer");
        List<String> AnswerStatus = new ArrayList<String>();
        List<String> AdminGameAnswer = new ArrayList<String>();
        List<Integer> gameCategoryID = new ArrayList<Integer>();
        //String AnswerStatus;

        List<WeeklyGamesAnswers> listWeeklyGamesAnswers = weeklyGamesAnswersService.listAllWeeklyGamesAnswers();
        int WeeklyGameAnswerLength = listWeeklyGamesAnswers.size();
        logger.info("Weekly Answer Game Total Length:" + String.valueOf(WeeklyGameAnswerLength));
        int i = 0;
        while (i < listWeeklyGamesAnswers.size() && !listWeeklyGamesAnswers.isEmpty()) {      
            String useranswer2 = listWeeklyGamesAnswers.get(i).getUserAnswer();
            String useranswer = "";
            if (useranswer2 == null) {
                useranswer = "NO ENTRY";
            } else {
                useranswer = useranswer2.toUpperCase(Locale.ENGLISH);
            }
            int answerid = listWeeklyGamesAnswers.get(i).getGameId();

            GameAnswer weeklyGamesAnswer = gamesAnswerService.findByGameId(answerid);

            String gameanswer = "";
            int gamecategory = 0;

            if (weeklyGamesAnswer == null) {

                GameAnswer weeklyGamesAnswer2 = new GameAnswer();

                weeklyGamesAnswer2.setGameAnswer("NOT SET");
                String gameanswer2 = weeklyGamesAnswer2.getGameAnswer();

                if (gameanswer2 == null) {
                    gameanswer = "NOT SET";
                } else {
                    gameanswer = gameanswer2.toUpperCase(Locale.ENGLISH);
                }
                AdminGameAnswer.add(gameanswer);
            } else {
                String gameanswer2 = weeklyGamesAnswer.getGameAnswer();
                if (gameanswer2 == null) {
                    gameanswer = "NOT SET";
                } else {
                    gameanswer = gameanswer2.toUpperCase(Locale.ENGLISH);
                }
                AdminGameAnswer.add(gameanswer);
                gamecategory = weeklyGamesAnswer.getGameCategoryId();
            }

            gameCategoryID.add(gamecategory);

            if (gameanswer == null ? useranswer == null : gameanswer.equals(useranswer)) {
                AnswerStatus.add("CORRECT");
            } else {
                AnswerStatus.add("WRONG");
            }

            logger.info("Weekly Answer Game ID:" + String.valueOf(listWeeklyGamesAnswers.get(i).getGameId()));
            logger.info("Week Category Name:" + gameService.GamesCategory(id).get(0).getGameName());
            logger.info("Game Answer:" + gameanswer);
            logger.info("User Answer:" + useranswer);
            logger.info("Selected Game Category Id:" + String.valueOf(id));
            logger.info(AnswerStatus.get(i));

            if (gamecategory != id && i > 0) {

                listWeeklyGamesAnswers.remove(i);
                AnswerStatus.remove(i);
                AdminGameAnswer.remove(i);
                i--;
            } else {

            }
            i++;
            logger.info("value of weeklygameanswer length iterator:" + String.valueOf(i));
             }
        try {

            if (listWeeklyGamesAnswers.size() == 1 && (gameCategoryID.get(0) != id)) {
                listWeeklyGamesAnswers.get(0).setUserAnswer("No Answer");
                AdminGameAnswer.set(0, "No Answer");
                AnswerStatus.set(0, "No Answer");
                listWeeklyGamesAnswers.remove(0);
                AnswerStatus.remove(0);
                AdminGameAnswer.remove(0);
            }
            if (listWeeklyGamesAnswers.size() > 1 && (gameCategoryID.get(0) != id)) {
                listWeeklyGamesAnswers.remove(0);
                AnswerStatus.remove(0);
                AdminGameAnswer.remove(0);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        logger.info("WeeklyGameAnswer final list size" + String.valueOf(listWeeklyGamesAnswers.size()));
        logger.info("GameCategory First Value ID" + String.valueOf(gameCategoryID.get(0)));
        model.addAttribute("gameanswerlist", listWeeklyGamesAnswers);
        model.addAttribute("answerstatus", AnswerStatus);
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("answertype", "CategoryAnswer");
        model.addAttribute("admingameanswer", AdminGameAnswer);
        model.addAttribute("gamecategory", gameService.GamesCategory(id).get(0).getGameName());

        return "/admin/listanswerbycategory";
    }

    @RequestMapping(value = "/activeweekanswer", method = RequestMethod.GET)
    public String listActiveWeekCorrectAnswers(ModelMap model, HttpServletRequest request) {
        logger.info("ListAllAnswer");
        int GameWeek = tunborUtility.gameWeek();
        List<String> AnswerStatus = new ArrayList<String>();
        List<String> AdminGameAnswer = new ArrayList<String>();

        List<WeeklyGamesAnswers> listWeeklyGamesAnswers = weeklyGamesAnswersService.listAllActiveWeekGamesAnswers(GameWeek);

        int WeeklyGameAnswerLength = listWeeklyGamesAnswers.size();
        for (int i = 0; i < WeeklyGameAnswerLength; i++) {
            try {
                String useranswer2 = listWeeklyGamesAnswers.get(i).getUserAnswer();
                String useranswer = "";
                if (useranswer2 == null) {
                    useranswer = "NO ENTRY";
                } else {
                    useranswer = useranswer2.toUpperCase(Locale.ENGLISH);
                }
                int answerid = listWeeklyGamesAnswers.get(i).getGameId();
                GameAnswer weeklyGamesAnswer = gamesAnswerService.findByGameId(answerid);
                String gameanswer2 = weeklyGamesAnswer.getGameAnswer();
                String gameanswer = "";
                if (gameanswer2 == null) {
                    gameanswer = "NOT SET";
                } else {
                    gameanswer = gameanswer2.toUpperCase(Locale.ENGLISH);
                }
                AdminGameAnswer.add(gameanswer);

                if (gameanswer == null ? useranswer == null : gameanswer.equals(useranswer)) {
                    AnswerStatus.add("CORRECT");
                } else {
                    AnswerStatus.add("WRONG");
                }

                logger.info("Game Answer:" + gameanswer);
                logger.info("User Answer:" + useranswer);

                logger.info(AnswerStatus.get(i));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }

        model.addAttribute("gameanswerlist", listWeeklyGamesAnswers);
        model.addAttribute("answerstatus", AnswerStatus);
        model.addAttribute("admingameanswer", AdminGameAnswer);
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("activeweek", GameWeek);

        return "/admin/activeweekcorrectanswers";
    }

    @RequestMapping(value = "/listGameCategory", method = RequestMethod.GET)
    public String listAllGameCategory(ModelMap model, HttpServletRequest request) {
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", getPrincipal());

        return "/admin/listGameCategory";
    }

    @RequestMapping(value = "/listGameCategory2", method = RequestMethod.GET)
    public String listAllGameCategory2(ModelMap model, HttpServletRequest request) {
        model.addAttribute("gameList", gameService.listAllGames());
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("categorylisttype", "CategoryforAnswer");

        return "/admin/listGameCategory";
    }

    @RequestMapping(value = "/view-gameCategory-answer-{id}", method = RequestMethod.GET)
    public String listCategoryAnswer(@PathVariable int id, ModelMap model) {

        logger.info("Inside listCategoryAnswer function where category id :: " + id);
        // WeeklyGames weeklyGames = weeklyGamesService.findById(id);
        int GameWeek = tunborUtility.gameWeek();
        List<String> AnswerStatus = new ArrayList<String>();
        List<String> AdminGameAnswer = new ArrayList<String>();
        List<Integer> gameCategoryID = new ArrayList<Integer>();
        List<WeeklyGamesAnswers> listWeeklyGamesAnswers = weeklyGamesAnswersService.listAllActiveWeekGamesAnswers(GameWeek);
        int WeeklyGameAnswerLength = listWeeklyGamesAnswers.size();
        logger.info("Weekly Answer Game Total Length:" + String.valueOf(WeeklyGameAnswerLength));
        int i = 0;
        while (i < listWeeklyGamesAnswers.size() && !listWeeklyGamesAnswers.isEmpty()) {
            String useranswer2 = listWeeklyGamesAnswers.get(i).getUserAnswer();
            String useranswer = "";
            if (useranswer2 == null) {
                useranswer = "NO ENTRY";
            } else {
                useranswer = useranswer2.toUpperCase(Locale.ENGLISH);
            }
            int answerid = listWeeklyGamesAnswers.get(i).getGameId();
            GameAnswer weeklyGamesAnswer = gamesAnswerService.findByGameId(answerid);
            String gameanswer2 = weeklyGamesAnswer.getGameAnswer();
            String gameanswer = " ";
            if (gameanswer2 == null) {
                gameanswer = "NOT SET";
            } else {
                gameanswer = gameanswer2.toUpperCase(Locale.ENGLISH);
            }
            AdminGameAnswer.add(gameanswer);

            int gamecategory = weeklyGamesAnswer.getGameCategoryId();
            gameCategoryID.add(gamecategory);

            if (gameanswer == null ? useranswer == null : gameanswer.equals(useranswer)) {
                AnswerStatus.add("CORRECT");
            } else {
                AnswerStatus.add("WRONG");
            }

            logger.info("Weekly Answer Game ID:" + String.valueOf(listWeeklyGamesAnswers.get(i).getGameId()));
            logger.info("Weekly Game Category:" + String.valueOf(weeklyGamesAnswer.getGameCategoryId()));
            logger.info("Week Category Name:" + gameService.GamesCategory(id).get(0).getGameName());
            logger.info("Game Answer:" + gameanswer);
            logger.info("User Answer:" + useranswer);
            logger.info(AnswerStatus.get(i));
            if (gamecategory != id && i > 0) {

                listWeeklyGamesAnswers.remove(i);
                AnswerStatus.remove(i);
                AdminGameAnswer.remove(i);
                i--;
            } else {

            }
            i++;
        }

        try {

            if (listWeeklyGamesAnswers.size() == 1 && (gameCategoryID.get(0) != id)) {

                listWeeklyGamesAnswers.get(0).setUserAnswer("No Answer");
                AdminGameAnswer.set(0, "No Answer");
                AnswerStatus.set(0, "No Answer");
                listWeeklyGamesAnswers.remove(0);
                AnswerStatus.remove(0);
                AdminGameAnswer.remove(0);

            }

            if (listWeeklyGamesAnswers.size() > 1 && (gameCategoryID.get(0) != id)) {
                listWeeklyGamesAnswers.remove(0);
                AnswerStatus.remove(0);
                AdminGameAnswer.remove(0);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        logger.info("WeeklyGameAnswer final list size" + String.valueOf(listWeeklyGamesAnswers.size()));

        model.addAttribute("gameanswerlist", listWeeklyGamesAnswers);
        model.addAttribute("answerstatus", AnswerStatus);
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("gamecategory", gameService.GamesCategory(id).get(0).getGameName());
        model.addAttribute("activeweek", GameWeek);
        model.addAttribute("answertype", "CategoryActiveWeekAnswer");
        model.addAttribute("admingameanswer", AdminGameAnswer);

        return "/admin/activeweekcorrectanswers";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        System.out.println("Logged in user :: " + userName);
        return userName;
    }

}

class AnswerStatusInfo {

    public String answerstatusinfo;

    public void setanswerstatusinfo(String answer) {
        this.answerstatusinfo = answer;
    }

}
