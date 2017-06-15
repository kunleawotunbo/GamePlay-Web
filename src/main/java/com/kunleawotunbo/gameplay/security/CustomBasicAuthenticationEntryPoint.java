package com.kunleawotunbo.gameplay.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    
    // http://websystique.com/spring-security/secure-spring-rest-api-using-basic-authentication/
    
    @Override
    public void commence(final HttpServletRequest request, 
    		final HttpServletResponse response, 
    		final AuthenticationException authException) throws IOException, ServletException {
    	
    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Access!");
    	response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 : " + authException.getMessage());
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("MY_TEST_REALM");
        super.afterPropertiesSet();
    }
}