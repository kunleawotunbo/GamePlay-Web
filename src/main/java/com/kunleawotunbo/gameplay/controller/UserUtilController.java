/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.bean.PasswordBean;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.model.VerificationToken;
import com.kunleawotunbo.gameplay.service.MailService;
import com.kunleawotunbo.gameplay.service.PasswordResetTokenService;
import com.kunleawotunbo.gameplay.service.UserProfileService;
import com.kunleawotunbo.gameplay.service.UserService;
import com.kunleawotunbo.gameplay.service.VerificationTokenService;
import com.kunleawotunbo.gameplay.utility.GenericResponse;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
public class UserUtilController {

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
    PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private MessageSource messages;

    final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/registrationConfirm.html", method = RequestMethod.GET)
    public String confirmRegistration(final Locale locale, final Model model, @RequestParam("token")
            final String token) throws UnsupportedEncodingException {
        /*
        System.out.println("Inside confirmRegistration");
        model.addAttribute("message", "Account varified");
           // return "redirect:/login?lang=" + locale.getLanguage();
            return "redirect:/login";
         */

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
            System.out.println("Send a mail that verification successful");
            return "redirect:/login";
        }

        model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
        //model.addAttribute("message", messages.getMess);
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return "redirect:/badUser.html?lang=" + locale.getLanguage();

    }

    @RequestMapping(value = "/forgotPassword.html", method = RequestMethod.GET)
    public String getResetPassword(ModelMap model, HttpServletRequest request) {

        model.addAttribute("weekNo", tunborUtility.gameWeek());

        return "forgotPassword";
    }
    
      @RequestMapping(value = "/updatePassword.html", method = RequestMethod.GET)
    public String updatePassword(ModelMap model, HttpServletRequest request) {

       
        return "updatePassword";
    }
    
    @RequestMapping(value = {"/updatePassword.html"}, method = RequestMethod.POST)
    public String updatePassword(@Valid PasswordBean passwordBean,
            ModelMap model, HttpServletRequest request) {
        
        final User user = (User) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
        userService.changeUserPassword(user, passwordBean.getNewPassword());
        System.out.println("Updating password for email :: " + user.getEmail());
        
         model.addAttribute("saved", true);
            model.addAttribute("message", "Password reset is successful");
            
        //return new GenericResponse(messages.getMessage("message.resetPasswordSuc", null, locale));
        
        return "login";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving forgotPassword.html in database.
     */
    @RequestMapping(value = {"/forgotPassword.html"}, method = RequestMethod.POST)
    public String resetUserPassword(@RequestParam("email") final String userEmail,
            ModelMap model, HttpServletRequest request) {
        System.out.println("userEmail :: " + userEmail);
        boolean saved = false;
        final User user = userService.findUserByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            String appUrl = null;
            try {
                appUrl = tunborUtility.getURLBase(request);
            } catch (MalformedURLException ex) {
                java.util.logging.Logger.getLogger(UserUtilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
            //tunborUtility.sendMail(tunborUtility.constructResetTokenEmail(appUrl, request.getLocale(), token, user));
            //tunborUtility.sendMail(tunborUtility.constructResetTokenEmail(appUrl, request.getLocale(), token, user));
            tunborUtility.mailSender(tunborUtility.constructResetTokenEmail(appUrl, request.getLocale(), token, user));
            saved = true;
            model.addAttribute("saved", saved);
            model.addAttribute("message", "Reset mail sent");

            return "forgotPassword";

        }

        model.addAttribute("error", true);
        model.addAttribute("message", "Email does not exist");

        return "forgotPassword";

    }
    
      @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(final Locale locale, final Model model, @RequestParam("id") final long id, @RequestParam("token") final String token) {
        final String result = passwordResetTokenService.validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("error", true);
            model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
    }

    // Reset password
    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        final User user = userService.findUserByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            String appUrl = null;
            try {
                appUrl = tunborUtility.getURLBase(request);
            } catch (MalformedURLException ex) {
                java.util.logging.Logger.getLogger(UserUtilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
            //tunborUtility.sendMail(tunborUtility.constructResetTokenEmail(appUrl, request.getLocale(), token, user));
            //tunborUtility.sendMail(tunborUtility.constructResetTokenEmail(appUrl, request.getLocale(), token, user));
            tunborUtility.mailSender(tunborUtility.constructResetTokenEmail(appUrl, request.getLocale(), token, user));
        }
        return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
    }
    
      @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePassword(final Locale locale, @Valid PasswordBean passwordBean) {
        final User user = (User) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
        userService.changeUserPassword(user, passwordBean.getNewPassword());
        return new GenericResponse(messages.getMessage("message.resetPasswordSuc", null, locale));
    }
    
        // change user password
    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse changeUserPassword(final Locale locale, @Valid PasswordBean passwordDto) {
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getEmail());
        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            //throw new InvalidOldPasswordException();
            System.out.println("Invalid Ola Passworod");
        }
        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return new GenericResponse(messages.getMessage("message.updatePasswordSuc", null, locale));
    }
    
    // user activation - verification

    @RequestMapping(value = "/user/resendRegistrationToken", method = RequestMethod.GET)
    @ResponseBody
    public GenericResponse resendRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken) {
        final VerificationToken newToken = verificationTokenService.generateNewVerificationToken(existingToken);
        final User user = userService.getUser(newToken.getToken());
       
        String appUrl = "";
        //  mailSender.send(constructResendVerificationTokenEmail(getAppUrl(request), request.getLocale(), newToken, user));
        try {
            appUrl = tunborUtility.getURLBase(request);
            } catch (MalformedURLException ex) {
                java.util.logging.Logger.getLogger(UserUtilController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
            tunborUtility.mailSender(tunborUtility.constructResendVerificationTokenEmail(appUrl, request.getLocale(), newToken, user));
            
            
        return new GenericResponse(messages.getMessage("message.resendToken", null, request.getLocale()));
    }
}
