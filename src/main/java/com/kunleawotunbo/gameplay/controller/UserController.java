/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.CustomResponseBody;
import com.kunleawotunbo.gameplay.bean.CustomResponseBody2;
import com.kunleawotunbo.gameplay.bean.PasswordBean;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.model.UserProfile;
import com.kunleawotunbo.gameplay.service.MailService;
import com.kunleawotunbo.gameplay.service.UserProfileService;
import com.kunleawotunbo.gameplay.service.UserService;
import com.kunleawotunbo.gameplay.service.VerificationTokenService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
     * Get user profile update page
     */
    @RequestMapping(value = {"/updateProfile"}, method = RequestMethod.GET)
    public String updateProfile( ModelMap model) {
        //logger.info("Edit user id :: " + id);
        
        String loggedinuser = tunborUtility.getPrincipal();

        
        User user = userService.findUserByEmail(loggedinuser);
       
        model.addAttribute("userProfile", user);
        
        return "/admin/updateProfile";
    }
   
    /**
     * Post method for saving user profile update
     * 
     */
    @RequestMapping(value = {"/updateProfile"}, method = RequestMethod.POST)
    public String updateProfile(User userObject, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            logger.info("Error occurred while trying to update user profile with id :: " + userObject.getId());
            model.addAttribute("error", true);
            model.addAttribute("message", "Unable to update user profile ");
            return "/admin/updateProfile";
        }
        
        User user = userService.findById(userObject.getId());
        user.setFirstName(userObject.getFirstName());
        user.setLastName(userObject.getLastName());
        user.setPhoneNumber(userObject.getPhoneNumber());
        
        userService.updateUser(user);

        userService.updateUser(user);
        logger.info("Updated user:: " + user.getId());

        model.addAttribute("message", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        //model.addAttribute("loggedinuser", tunborUtility.getPrincipal());
        model.addAttribute("saved", true);
        model.addAttribute("userProfile", user);

        return "/admin/updateProfile";
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
            logger.info("Error occurred while trying to update user with id :: "  +id);
            model.addAttribute("error", true);
            model.addAttribute("message", "Unable to update user");
            return "/admin/user";
        }

        userService.updateUser(user);
        logger.info("Updated user:: " + id);

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
    
    @RequestMapping(value = "/updateUserProfile", method = RequestMethod.POST)
    public String updateUserProfile(@RequestBody @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,@RequestParam("phoneNumber") String phoneNumber, 
            @RequestParam("createdBy") String userEmail, HttpServletRequest request) {

     
        
        ModelMap model = new ModelMap();
        boolean saved;
        
        //User user = userService.findUserByEmail(tunborUtility.getPrincipal());
        User user = userService.findUserByEmail(userEmail);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        
        userService.updateUser(user);
        
       

        result2.setCode("200");
        result2.setMessage("Password Changed Successfully!");
        result2.setResult(user);

        //return new ResponseEntity(result2, HttpStatus.OK);
        //return ResponseEntity.ok(result2);
        
        
        
        saved = true;
        model.addAttribute("saved", saved);
        model.addAttribute("message", "  Password Changed Successfully!");
        return "redirect:/admin/changeMyPassword";
    }

    
    /**
     * This method allows user to change password
     */
    @RequestMapping(value = {"/changeMyPassword"}, method = RequestMethod.GET)
    public String changeMyPassword(ModelMap model) {
        //User user = new User();
        String loggedinuser = tunborUtility.getPrincipal();

        model.addAttribute("user", new User());
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", loggedinuser);
      
        

        return "/admin/changeMyPassword";
    }

    /**
     * Change user password
     *
     * @param game
     * @param ucBuilder
     * @param request
     * @return
     * 
     */  
      @RequestMapping(value = "/changeMyPassword", method = RequestMethod.POST)
    public ResponseEntity saveChangeMyPassword(@RequestBody PasswordBean passwordBean, HttpServletRequest request) {

        
        ModelMap model = new ModelMap();
        boolean saved;
        String userEmail = tunborUtility.getPrincipal();
        System.out.println("oldPassword :: " + passwordBean.getOldPassword());
        System.out.println("Password :: " + passwordBean.getNewPassword());
        System.out.println("userEmail:: " + userEmail);
        //User user = userService.findUserByEmail(tunborUtility.getPrincipal());
        User user = userService.findUserByEmail(userEmail);
        System.out.println("User :: " + user.getFirstName());
        
        if (!userService.checkIfValidOldPassword(user, passwordBean.getOldPassword())) {
            //throw new InvalidOldPasswordException();
            System.out.println("Password does not match old pass");
            result2.setCode("404");
            result2.setMessage("Invalid old password!");
            result2.setResult(user);
            //return new ResponseEntity(result2, HttpStatus.NOT_FOUND);
           // return ResponseEntity.badRequest().body(result2);
           model.addAttribute("error", true);
            model.addAttribute("message", " Unable to  Create game category");
           
           return ResponseEntity.badRequest().body(result2);
         

        }
        userService.changeUserPassword(user, passwordBean.getNewPassword());

        result2.setCode("200");
        result2.setMessage("Password Changed Successfully!");
        result2.setResult(user);

        //return new ResponseEntity(result2, HttpStatus.OK);
        //return ResponseEntity.ok(result2);
         System.out.println(" Password Changed Successfully!");
        // log out user after password changed
        tunborUtility.logoutUser(request);
        
        saved = true;
        model.addAttribute("saved", saved);
        model.addAttribute("message", "  Password Changed Successfully!");
       
        return ResponseEntity.ok(result2);

        //return "redirect:/login";
    }

    public String getURLBase(HttpServletRequest request) throws MalformedURLException {

        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        //return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port + request.getContextPath();

    }
    
    
}
