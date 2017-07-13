/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.service.MailService;
import com.kunleawotunbo.gameplay.service.UserProfileService;
import com.kunleawotunbo.gameplay.service.UserService;
import com.kunleawotunbo.gameplay.service.VerificationTokenService;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
}
