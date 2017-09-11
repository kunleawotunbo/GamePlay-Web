<%-- 
    Document   : header
    Created on : Jun 21, 2017, 11:32:00 PM
    Author     : Olakunle Awotunbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    
        <title>GamePlay |</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">        
        <link href="<c:url value='/resources/css/bootstrap.min.css'/>"rel="stylesheet"></link>   
          <!-- PNotify -->
        <link href="<c:url value='/resources/css/pnotify.css' />"  rel="stylesheet"></link> 
        <link href="<c:url value='/resources/css/pnotify.buttons.css' />"  rel="stylesheet"></link> 
        <link href="<c:url value='/resources/css/pnotify.nonblock.css' />"  rel="stylesheet"></link>         
    
        
        <style>
            /* Remove the navbar's default margin-bottom and rounded borders */
            .navbar {
                margin-bottom: 0;
                border-radius: 0;
            }

            /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
            .row.content {height: 450px} 

            /* Set gray background color and 100% height */
            .sidenav {
                padding-top: 20px;
                background-color: #f1f1f1;
                height: 100%;
                margin-top: 20px;
            }

            /* Set black background color, white text and some padding */
            footer {
                background-color: #555;
                color: white;
                padding: 15px;
            }

            img:hover{
                opacity:0.5;
                transition: 1s ease;
            }   

            /* On small screens, set height to 'auto' for sidenav and grid */
            @media screen and (max-width: 767px) {
                .sidenav {
                    height: auto;
                    padding: 15px;
                }
                .row.content {height:auto;}
            }
        </style>
  

  <body>
