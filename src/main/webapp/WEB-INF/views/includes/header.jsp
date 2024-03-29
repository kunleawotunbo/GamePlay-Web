<%-- 
    Document   : header
    Created on : Jun 16, 2017, 9:13:01 AM
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
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>GamePlay | </title>
        <!-- Bootstrap -->
        <link href="<c:url value='/resources/css/bootstrap.min.css'/>"rel="stylesheet"></link>      
        <!-- Font Awesome -->
        <link href="<c:url value='/resources/css/font-awesome.min.css' />"  rel="stylesheet"></link>        
        <!-- NProgress -->
        <link href="<c:url value='/resources/css/nprogress.css' />"  rel="stylesheet"></link> 
        <!-- iCheck -->
        <link href="<c:url value='/resources/css/green.css' />"  rel="stylesheet"></link> 

        <!-- bootstrap-progressbar -->
        <link href="<c:url value='/resources/css/bootstrap-progressbar-3.3.4.min.css' />"  rel="stylesheet"></link> 
        <!-- JQVMap -->
        <link href="<c:url value='/resources/css/jqvmap.min.css' />"  rel="stylesheet"></link> 
        <!-- bootstrap-daterangepicker -->
        <link href="<c:url value='/resources/css/daterangepicker.css' />"  rel="stylesheet"></link> 
        <!-- Custom Theme Style -->
        <link href="<c:url value='/resources/css/custom.min.css' />"  rel="stylesheet"></link> 

        <!-- Datatables -->
        <link href="<c:url value='/resources/css/dataTables.bootstrap.min.css' />"  rel="stylesheet"></link> 
        <link href="<c:url value='/resources/css/buttons.bootstrap.min.css' />"  rel="stylesheet"></link> 
        <link href="<c:url value='/resources/css/fixedHeader.bootstrap.min.css' />"  rel="stylesheet"></link> 
        <link href="<c:url value='/resources/css/responsive.bootstrap.min.css' />"  rel="stylesheet"></link> 
        <link href="<c:url value='/resources/css/scroller.bootstrap.min.css' />"  rel="stylesheet"></link> 
        
        <!--bootstraptoggle-->
        <link href="<c:url value='/resources/css/bootstrap-toggle.min.css' />"  rel="stylesheet"></link> 

          <!-- PNotify -->
        <link href="<c:url value='/resources/css/pnotify.css' />"  rel="stylesheet"></link> 
        <link href="<c:url value='/resources/css/pnotify.buttons.css' />"  rel="stylesheet"></link> 
        <link href="<c:url value='/resources/css/pnotify.nonblock.css' />"  rel="stylesheet"></link> 
        
       <!--Boostrap  Datetime picker-->
       <link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css' />"  rel="stylesheet"></link> 
          
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/3.2.1/css/font-awesome.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/fonts/fontawesome-webfont.woff2">

    </head>

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="dashboard" class="site_title"><i class="fa fa-paw"></i> <span>GamePlay</span></a>
                        </div>

                        <div class="clearfix"></div>
<script type="text/javascript">
    function setTimeZoneInCookie() {
        var _myDate = new Date();
        var _offset = _myDate.getTimezoneOffset();
        document.cookie = "TIMEZONE_COOKIE=" + _offset; //Cookie name with value
    }
    
    setTimeZoneInCookie();
</script>