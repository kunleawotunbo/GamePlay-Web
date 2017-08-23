/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.Game;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.model.UserProfile;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.UserProfileService;
import com.kunleawotunbo.gameplay.service.UserService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author olakunle
 */
@Controller
//@RequestMapping("/")
//@SessionAttribute("roles")
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
    
    @Autowired
    private GameService gameService;
    
     @Autowired
    private TunborUtility tunborUtility;

    
    static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }
    
    @RequestMapping(value = "Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPAge(ModelMap model){
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        logger.info("Inside loginPage()");
        if(isCurrentAuthentcationAnanymous()){
            return "login";
        }else {                     
            return "redirect:/admin/dashboard";
        }        
    }
    
    @RequestMapping(value ="/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        logger.info("Logging Out user :: " + getPrincipal());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            // new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }
    
    
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String aboutPage() {
          
        return "about";
    }
    
     @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactPage() {
          
        return "contact";
    }
    
    @RequestMapping(value = "/homenew", method = RequestMethod.GET)
    public ModelAndView homenew(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        logger.info("inside HomenewController testing");
      
        ModelMap model = new ModelMap();
        
        String test = "This is a test";
        List<Game> gameList = null;
        List<Game> gameListFinal = null;
         boolean status = true;
         gameList = gameService.listGames(status);
         String imageEncodedString = "";
         
         Game game = null;
         gameListFinal = new ArrayList<Game>();
          for (Game item : gameList) {
              game = new Game();
              
              if (item.getGameImgLocation() != null && item.getGameImage() != null){
                  imageEncodedString = tunborUtility.imageToBase64String(item.getGameImgLocation() + item.getGameImage());
                  //String path = item.getGameImgLocation() + item.getGameImage();
                  
                  game.setGameImgLocation(imageEncodedString);
             
                  
              }else {
                  System.out.println("item.getGameImgLocation() is null" );
              }
            
            

            game.setId(item.getId());
            game.setGameName(item.getGameName());
            game.setGameCode(item.getGameCode());
            game.setEnabled(item.getEnabled());
            game.setCreationDate(item.getCreationDate());
            game.setLastModificationDate(item.getLastModificationDate());           
            game.setLastModifiedBy(item.getLastModifiedBy());
            game.setColor(item.getColor());
            game.setGameRules(item.getGameRules());
            game.setGameImage(item.getGameImage());
            //game.setGameImgLocation(imageEncodedString);

            gameListFinal.add(game);
        }
         
        model.addAttribute("urlPath", request.getLocalAddr());
        model.addAttribute("request", request);
        model.addAttribute("test", test);
        //model.addAttribute("gameList", gameService.listGames(status));
        model.addAttribute("gameList", gameListFinal);
        
        return new ModelAndView("homenew", model);
    }

    

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    private boolean isCurrentAuthentcationAnanymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

}
