<%-- 
    Document   : errorpage
    Created on : Jun 22, 2017, 11:43:33 PM
    Author     : Olakunle Awotunbo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>GamePlay! | </title>

        <!-- Bootstrap -->
        <link href="<c:url value='/resources/css/bootstrap.min.css'/>"rel="stylesheet"></link>      
        <!-- Font Awesome -->
        <link href="<c:url value='/resources/css/font-awesome.min.css' />"  rel="stylesheet"></link>        
        <!-- NProgress -->
        <link href="<c:url value='/resources/css/nprogress.css' />"  rel="stylesheet"></link> 

        <!-- Custom Theme Style -->
        <link href="<c:url value='/resources/css/custom.min.css' />"  rel="stylesheet"></link> 

    </head>

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <!-- page content -->
                <div class="col-md-12">
                    <div class="col-middle">
                        <div class="text-center text-center">
                    <!--    <h1 class="error-number">404</h1>-->
                             <h1 class="error-number">${errorCode}</h1>
                            <h2>${errorMsg}</h2>
                            <p>Please contact the administrator.</p>
                            <p>This page you are looking for does not exist <a href="#">Report this?</a>
                            </p>
<!--                            <div class="mid_center">
                                <h3>Search</h3>
                                <form>
                                    <div class="col-xs-12 form-group pull-right top_search">
                                        <div class="input-group">
                                            <input type="text" class="form-control" placeholder="Search for...">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button">Go!</button>
                                            </span>
                                        </div>
                                    </div>
                                </form>
                            </div>-->
                        </div>
                    </div>
                </div>
                <!-- /page content -->
            </div>
        </div>

        <!-- jQuery -->
        <script src="<c:url value='/resources/js/jquery.min.js' />"></script> 
        <!-- Bootstrap -->
        <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script> 
        <!-- FastClick -->
        <script src="<c:url value='/resources/js/fastclick.js' />"></script> 

        <!-- NProgress -->
        <script src="<c:url value='/resources/js/nprogress.js' />"></script> 

        <!-- Custom Theme Scripts -->
        <script src="<c:url value='/resources/js/custom.min.js' />"></script> 

    </body>
</html>
