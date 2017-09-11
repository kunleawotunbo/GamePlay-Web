<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Home</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
    </head>
    <body>


        <nav class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<%=request.getContextPath()%>">WINGAMEPLAY</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="<%=request.getContextPath()%>">Home</a></li>
                        <li ><a href="<%=request.getContextPath()%>/prediction">Prediction</a></li>
                        <li><a href="<%=request.getContextPath()%>/about">About</a></li>
                        <li><a href="<%=request.getContextPath()%>/contact">Contact</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!--<div class="container-fluid text-center">-->
        <div class="container">
            <div class="row content">

                <!--
                    <div class="col-sm-2 sidenav">
                      <p><a href="#">Link</a></p>
                      <p><a href="#">Link</a></p>
                      <p><a href="#">Link</a></p>
                    </div>
                
                -->
                <div class="col-sm-9 text-left">


                    <!--<div class="container-fluid bg-3 text-center">-->    
                    <div class="container-fluid bg-3 text-center">    

                        <div class="row">   
                            <c:forEach var="item" items="${gameList}" >


                                <div class="col-sm-4">
                                    <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />"  >       <h3>${item.gameName}</h3>   </a>
                                    <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />"  >
                                        <img src="data:image/jpg;base64,${item.gameImgLocation}" alt="${item.gameName}"   class="img-responsive" style="width:100%" alt="${item.gameName}">
                                    </a> 
                                </div>



                            </c:forEach>

                        </div>    

                    </div><br>

                    <!--Bottom Ads Starts -->               
                    <div class="row">   
                        <div class="container-fluid bg-3 text-center">     
                            Bottom Ads
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et 
                                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit,
                                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>

                        </div>
                    </div>

                    <!--Bottom Ads Ends -->        

                </div>
                <!-- Side bar ads starts--> 
                <div class="col-sm-3 sidenav">
                    <div class="well">
                        <p>ADS</p>
                    </div>
                    <div class="well">
                        <p>ADS</p>
                    </div>
                </div>
                <!--Side bar ads ends --> 

            </div>
        </div>

        <footer class="container-fluid text-center">
            <p>Footer Text</p>
        </footer>

    </body>
</html>
