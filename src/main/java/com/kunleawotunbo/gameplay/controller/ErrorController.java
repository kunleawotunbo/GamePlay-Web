/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Olakunle Awotunbo
 */
@Controller
public class ErrorController {
    
     final Logger logger = LoggerFactory.getLogger(getClass());
     
    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
         
        ModelAndView errorPage = new ModelAndView("errorpage");
        String errorMsg = "";
        String errorCode = "";
        int httpErrorCode = getErrorCode(httpRequest);
        logger.info("httpErrorCode :: " + httpErrorCode);
        switch (httpErrorCode) {
            case 400: {
                //errorMsg = "Http Error Code: 400. Bad Request";
                errorMsg = "Bad Request";
                errorCode = "400";
                break;
            }
            case 401: {
                //errorMsg = "Http Error Code: 401. Unauthorized";
                errorMsg = "Unauthorized";
                errorCode = "401";
                break;
            }
            case 404: {
                //errorMsg = "Http Error Code: 404. Resource not found";
                errorMsg = "Resource not found";
                errorCode = "404";
                break;
            }
            case 500: {
                //errorMsg = "Http Error Code: 500. Internal Server Error";
                errorMsg = "Internal Server Error";
                errorCode = "500";
                break;
            }
            
            default:
                errorMsg = "Something went wrong";
                errorCode = "" + httpErrorCode;
                break;
                
        }
        errorPage.addObject("errorMsg", errorMsg);
        errorPage.addObject("errorCode", errorCode);
        return errorPage;
    }
     
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
}
