/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.configuration;

import com.google.common.base.Strings;
import com.kunleawotunbo.gameplay.utility.TunborUtility;
import java.io.IOException;
import java.util.Enumeration;
import java.util.TimeZone;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author Olakunle Awotunbo
 */
public class TimezoneInterceptor extends HandlerInterceptorAdapter {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TunborUtility tunborUtility;

    /**
     * Executed before actual handler is executed
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws java.lang.Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        TimeZone tz = RequestContextUtils.getTimeZone(request);
        logger.info("tz here :: " + tz);
        
        /*
         String offsetString = tunborUtility.getTimeOffset(request) == null ? "0" : tunborUtility.getTimeOffset(request);
        System.out.println("offsetString interceptor :: " + offsetString);
        TimeZone timeZone = tunborUtility.getTimeZone2(Integer.parseInt(offsetString));
        logger.info("timezone interceptor:: " + timeZone);
        */
        /*
        if (tz == null) {
            System.out.println("Forwarding to js to get timezone offset");
            System.out.println("getRequestURI :: " + request.getRequestURI());
            request.setAttribute("requestedUrl", request.getRequestURI());
            //RequestDispatcher dispatcher =  request.getRequestDispatcher("/tzHandler");
            // RequestDispatcher dispatcher =  request.getRequestDispatcher("/tzHandler");
            //dispatcher.forward(request, response);

            //RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/tzHandler");
            
            
            try {
               // request.getRequestDispatcher("/tzHandler").forward(request, response);
                
               // request.getRequestDispatcher("/WEB-INF/views/tzJsPage.jsp").forward(request, response);
              
              // request.getRequestDispatcher("/tzHandler").forward(request, response);
               response.sendRedirect(request.getContextPath() + "/tzHandler");
              // response.sendRedirect(request.getContextPath() + "/");
              //request.getRequestDispatcher(request.getContextPath() + "/tzHandler").forward(request, response);
                 //RequestDispatcher dispatcher = request.getRequestDispatcher("/tzHandler");
                // dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
            
            //response.sendRedirect(request.getContextPath() + "/tzHandler");

            //tzJsPage
            // request.getRequestDispatcher("/tzHandler").forward(request, response);
            //request.getRequestDispatcher("/tzHandler.jsp").forward(request, response); 
            return false;
        }
        */

        return true;

        /*
        String timeZone = tunborUtility.getTimeOffset(request) == null ? "" : tunborUtility.getTimeOffset(request);
        System.out.println("timeZone prehandler :: " + timeZone);

        logger.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI() + getParameters(request));

        return true;
         */
    }

    public boolean preHandle2(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        //if returned false, we need to make sure 'response' is sent
        return true;
    }

    /**
     * Executed before after handler is executed
     *
     */
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
        logger.info("[postHandle][" + request + "]");
    }

    /**
     * Executed after complete request is finished
     *
     */
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
        if (ex != null) {
            ex.printStackTrace();
        }
        logger.info("[afterCompletion][" + request + "][exception: " + ex + "]");
    }

    private String getParameters(final HttpServletRequest request) {
        final StringBuffer posted = new StringBuffer();
        final Enumeration<?> e = request.getParameterNames();
        if (e != null) {
            posted.append("?");
        }
        while (e != null && e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            }
            final String curr = (String) e.nextElement();
            posted.append(curr).append("=");
            if (curr.contains("password") || curr.contains("answer") || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }

        final String ip = request.getHeader("X-FORWARDED-FOR");
        final String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
        if (!Strings.isNullOrEmpty(ipAddr)) {
            posted.append("&_psip=" + ipAddr);
        }
        return posted.toString();
    }

    private String getRemoteAddr(final HttpServletRequest request) {
        final String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            logger.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }
}
