package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.UserBean;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.service.GameService;
import com.kunleawotunbo.gameplay.service.MailService;
import com.kunleawotunbo.gameplay.service.UserService;
import com.kunleawotunbo.gameplay.service.VerificationTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(value = "users", description = "Endpoint for user management")
public class AppController {

    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work

    @Autowired
    MailService mailService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    private MessageSource messages;
    
     @Autowired
    private GameService gameService;

    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        System.out.println("Inside listUsers");
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userslist";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        System.out.println("I am inside test()");

        return "login";
    }

    //-------------------Retrieve All Users--------------------------------------------------------
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String listAllUsers() {
        System.out.println("I am inside listAllUsers()");

        return "ok";
    }

    //-------------------Retrieve Single User--------------------------------------------------------
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBean> getUser(@ApiParam(value = "testId",
            required = true) @PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        UserBean user = new UserBean();
        user.setFirstName("Olakunle");
        user.setLastName("Awotunbo");
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserBean>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserBean>(user, HttpStatus.OK);

    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        //model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createUser(@RequestBody User user) {
        boolean success = false;
        userService.saveUser(user);
        logger.info("Inside createUser");

        mailService.sendMail(user);

        return "User successfully created";
    }

    @RequestMapping(value = "/registrationConfirm2", method = RequestMethod.GET)
    public String confirmRegistration2(final Locale locale, final Model model, @RequestParam("token")
            final String token) throws UnsupportedEncodingException {
       
        final String result = userService.validateVerificationToken(token);
        System.out.println("result :: " + result);
        if (result.equals("valid")) {
            final User user = userService.getUser(token);
            System.out.println(user);
            if (user.isUsing2FA()) {
                model.addAttribute("qr", userService.generateQRUrl(user));
                return "redirect:/qrcode.html?lang=" + locale.getLanguage();
            }
            model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
            //return "redirect:/login?lang=" + locale.getLanguage();
            return "redirect:/login";
        }

        model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return "redirect:/badUser.html?lang=" + locale.getLanguage();

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

}
