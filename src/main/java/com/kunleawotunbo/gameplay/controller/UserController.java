/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.bean.CustomResponseBody2;
import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.model.UserProfile;
import com.kunleawotunbo.gameplay.service.MailService;
import com.kunleawotunbo.gameplay.service.UserProfileService;
import com.kunleawotunbo.gameplay.service.UserService;
import com.kunleawotunbo.gameplay.service.VerificationTokenService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
@RequestMapping("/admin/")
public class UserController {

    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
    @Autowired
    MailService mailService;
    @Autowired
    TunborUtility tunborUtility;
    @Autowired
    VerificationTokenService verificationTokenService;
    @Autowired
    UserProfileService userProfileService;

    @Autowired
    private MessageSource messages;

    CustomResponseBody2 result2 = new CustomResponseBody2();

    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/adduser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        //User user = new User();
        model.addAttribute("user", new User());
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/user";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/adduser"}, method = RequestMethod.POST)
    public String createUser(User user, BindingResult result,
            ModelMap model, HttpServletRequest request) {

        if (result.hasErrors()) {
            System.out.println("Error occurred " + result.toString());
            return "registration";
        }

        System.out.println("Creating User " + user.getFirstName());
        boolean created = false;
        String appUrl = "";

        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getEmail() + " already exist");
            model.addAttribute("error", true);
            model.addAttribute("message", "A User with email " + user.getEmail() + " already exist");
            // populate field with the current object
            model.addAttribute("user", user);
            return "/admin/user";
        }

        user.setUserName(user.getEmail());
        created = userService.saveUser(user);
        logger.info("About to send mail to ::" + user.getEmail());

        if (!created) {
            // Something went wrong

            model.addAttribute("error", true);
            model.addAttribute("message", "Unable to create user " + user.getFirstName());
            model.addAttribute("user", new User());
            return "/admin/user";
        }

        if (created) {
            appUrl = request.getContextPath();

            try {
                final String token = UUID.randomUUID().toString();
                verificationTokenService.createVerificationTokenForUser(user, token);

                final String confirmationUrl = getURLBase(request) + "/registrationConfirm.html?token=" + token;
                user.setUserName(confirmationUrl);
                tunborUtility.sendMail(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User not created ");
        }

        model.addAttribute("message", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully. Verification mail has been sent.");
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());
        model.addAttribute("saved", created);
        //return "/admin/registrationsuccess";

        return "/admin/user";
    }

    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    /**
     * List users
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public String listUsers(ModelMap model, HttpServletRequest request) {

        model.addAttribute("userList", userService.findAllUsers());

        //model.addAttribute("loggedinuser", getPrincipal());
        return "/admin/listUsers";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-user-{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable int id, ModelMap model) {
        logger.info("Edit user id :: " + id);

        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());
        return "/admin/user";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-user-{id}"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
            ModelMap model, @PathVariable int id) {

        if (result.hasErrors()) {
            logger.info("Error occurred while trying to update user with id :: " + +id);
            model.addAttribute("error", true);
            model.addAttribute("message", "Unable to update user");
            return "/admin/user";
        }

        userService.updateUser(user);
        logger.info("Updated user:: " + +id);

        model.addAttribute("message", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());
        model.addAttribute("saved", true);
        model.addAttribute("user", new User());

        return "/admin/user";
    }

    /**
     * This method will delete an user by it's id value.
     */
    @RequestMapping(value = {"/delete-user-{id}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
        // userService.deleteUserByUsername(id);
        return "redirect:/list";
    }

    /**
     * This method allows user to change password
     */
    @RequestMapping(value = {"/changeMyPassword"}, method = RequestMethod.GET)
    public String changeMyPassword(ModelMap model) {
        //User user = new User();
        model.addAttribute("user", new User());
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());

        return "/admin/changeMyPassword";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    
    /*
    @RequestMapping(value = {"/changeMyPassword"}, method = RequestMethod.POST)
    public String saveChangeMyPassword1(@Valid User user, BindingResult result,
            ModelMap model, @PathVariable int id) {

        if (result.hasErrors()) {
            logger.info("Error occurred while trying to update user with id :: " + +id);
            model.addAttribute("error", true);
            model.addAttribute("message", "Unable to update user");
            return "/admin/changeMyPassword";
        }

        user = userService.findUserByEmail(tunborUtility.getPrincipal());

        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
            //throw new InvalidOldPasswordException();
        }
        userService.changeUserPassword(user, password);
        userService.updateUser(user);
        logger.info("Updated user password:: " + +id);

        model.addAttribute("message", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", tunborUtility.getPrincipal());
        model.addAttribute("saved", true);
        model.addAttribute("user", new User());

        return "/admin/user";
    }
    */

    /**
     * Create user password
     *
     * @param game
     * @param ucBuilder
     * @param request
     * @return
     * 
     */
    @RequestMapping(value = "/changeMyPassword", method = RequestMethod.POST)
    public String saveChangeMyPassword(@RequestBody @RequestParam("password") String password,
            @RequestParam("oldpassword") String oldPassword, @RequestParam("createdBy") String userEmail, HttpServletRequest request) {

     
        
        ModelMap model = new ModelMap();
        boolean saved;
        
        //User user = userService.findUserByEmail(tunborUtility.getPrincipal());
        User user = userService.findUserByEmail(userEmail);
        System.out.println("User :: " + user.getFirstName());
        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
            //throw new InvalidOldPasswordException();
            result2.setCode("404");
            result2.setMessage("Invalid old password!");
            result2.setResult(user);
            //return new ResponseEntity(result2, HttpStatus.NOT_FOUND);
           // return ResponseEntity.badRequest().body(result2);
           model.addAttribute("error", true);
            model.addAttribute("message", " Unable to  Create game category");
           return "/admin/changeMyPassword";

        }
        userService.changeUserPassword(user, password);

        result2.setCode("200");
        result2.setMessage("Password Changed Successfully!");
        result2.setResult(user);

        //return new ResponseEntity(result2, HttpStatus.OK);
        //return ResponseEntity.ok(result2);
        
        // log out user after password changed
        logoutUser(request);
        
        saved = true;
        model.addAttribute("saved", saved);
        model.addAttribute("message", "  Password Changed Successfully!");
        return "redirect:/login";
    }

    public String getURLBase(HttpServletRequest request) throws MalformedURLException {

        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        //return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port + request.getContextPath();

    }
    
    public void logoutUser(HttpServletRequest request){
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }

    }

}
