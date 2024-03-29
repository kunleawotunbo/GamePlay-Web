<%-- 
    Document   : header
    Created on : Jun 21, 2017, 11:32:00 PM
    Author     : Olakunle Awotunbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

    <!--Custom css--> 
    <link href="<c:url value='/resources/css/custom_outside.css' />"  rel="stylesheet"></link>  

    <%--<link href="<c:url value='/resources/css/custom2.css' />"  rel="stylesheet"></link>--%>  




    <script type="text/javascript">
        function setTimeZoneInCookie() {
            var _myDate = new Date();
            var _offset = _myDate.getTimezoneOffset();
            //document.getElementById("tzInput").value = offSet;
            document.cookie = "TIMEZONE_COOKIE=" + _offset; //Cookie name with value
            console.log("Cookie set here :: " + "TIMEZONE_COOKIE=" + _offset);
        }
        setTimeZoneInCookie();



//        $(document).ready(function () {
//            //your code here
//            $.ajax({
//                  url: "${pageContext.request.contextPath}/tzValueHandler2",
//                type: "get", //send it through get method
//                data: {
//                    requestedUrl: 'requestedUrl',
//                    timeZoneOffset: _offset,
//
//                },
//                success: function (response) {
//                    //Do Something
//                    console.log("SUCCESS", response);
//                },
//                error: function (xhr) {
//                    //Do Something to handle error
//                    console.log("ERROR ", xhr);
//                }
//            });
//        });
    </script>
    <body>
