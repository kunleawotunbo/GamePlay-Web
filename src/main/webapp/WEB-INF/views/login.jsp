<%-- 
    Document   : login
    Created on : Jul 10, 2017, 10:02:37 AM
    Author     : Olakunle Awotunbo
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
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
        <!-- Animate.css -->
        <link href="<c:url value='/resources/css/animate.min.css' />"  rel="stylesheet"></link>         

        <!-- Custom Theme Style -->
        <link href="<c:url value='/resources/css/custom.min.css' />"  rel="stylesheet"></link> 
    </head>

    <body class="login">     
        <div>
            <a class="hiddenanchor" id="signup"></a>
            <a class="hiddenanchor" id="signin"></a>
            <!--<a class="hiddenanchor" id="forgotpassword"></a>-->

            <div class="login_wrapper">
                <div class="animate form login_form">
                    <section class="login_content">                        
                        <div class="login-form">
                            <c:url var="loginUrl" value="/login" />
                            <form action="${loginUrl}" method="post" class="form-horizontal">
                                <h1>Login Form</h1>
                                <c:if test="${param.error != null}">
                                    <div class="alert alert-danger">
                                        <p>Invalid username or password.</p>  
                                        
                                    </div>
                                </c:if>
                                <c:if test="${param.logout != null}">
                                    <div class="alert alert-success">
                                        <p>You have been logged out successfully.</p>
                                    </div>
                                </c:if>
                                
                                <c:if test="${error}">                                  
                            <div class="alert alert-danger" id="success-error">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>${message} </strong>
                            </div>
                        </c:if>  

                        <c:if test="${saved}">        
                            <div class="alert alert-success" id="success-alert">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>${message} </strong>
                            </div>
                        </c:if>  
                                
                                <div>
                                    <input type="text" class="form-control" placeholder="Username" id="username" name="userName" required="" />
                                </div>
                                <div>
                                    <input type="password" class="form-control" placeholder="Password" id="password" name="password" required="" />
                                </div>
                                <div>
                                     <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>  
                                </div>                             
                                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                <div>
                                    <!--<input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">-->
                                    <input type="submit" class="btn btn-primary" value="Log in">
                                    
<!--                                    <a class="btn btn-default submit" type="submit">Log in</a>
                                   
                                    <a class="btn btn-default submit" href="index.html">Log in</a>-->
                                    <a class="reset_pass" href="forgotPassword.html">Lost your password?</a>
                                </div>

                                <div class="clearfix"></div>

                                <div class="separator">
<!--                                    
                                    <p class="change_link">New to site?
                                        <a href="#signup" class="to_register"> Create Account </a>
                                    </p>
                                    -->

                                    <div class="clearfix"></div>
                                    <br />

                                    <div>
                                        <h1><i class="fa fa-paw"></i> GamePlay!</h1>
                                        <p>©2017 All Rights Reserved. GamePlay. Privacy and Terms</p>
                                    </div>
                                </div>
                            </form>
                    </section>
                </div>

                <div id="register" class="animate form registration_form">
                    <section class="login_content">
                        <form>
                            <h1>Create Account</h1>
                            <div>
                                <input type="text" class="form-control" placeholder="Username" required="" />
                            </div>
                            <div>
                                <input type="email" class="form-control" placeholder="Email" required="" />
                            </div>
                            <div>
                                <input type="password" class="form-control" placeholder="Password" required="" />
                            </div>
                            <div>
                                <a class="btn btn-default submit" href="index.html">Submit</a>
                            </div>

                            <div class="clearfix"></div>

                            <div class="separator">
                                <p class="change_link">Already a member ?
                                    <a href="#signin" class="to_register"> Log in </a>
                                </p>

                                <div class="clearfix"></div>
                                <br />

                                <div>
                                    <h1><i class="fa fa-paw"></i> GamePlay!</h1>
                                    <p>©2017 All Rights Reserved. GamePlay. Privacy and Terms</p>
                                </div>
                            </div>
                        </form>
                    </section>
                </div>
                                
<!--                   <div id="forgotpassword" class="animate form registration_form">
                    <section class="login_content">
                        <form>
                            <h1>Forgot Password</h1>
                           
                            <div>
                                <input type="email" class="form-control" placeholder="Email" required="" />
                            </div>                           
                            <div>
                                <a class="btn btn-default submit" href="index.html">Submit</a>
                            </div>

                            <div class="clearfix"></div>

                            <div class="separator">
                                <p class="change_link">Already a member ?
                                    <a href="#signin" class="to_register"> Log in </a>
                                </p>

                                <div class="clearfix"></div>
                                <br />

                                <div>
                                    <h1><i class="fa fa-paw"></i> GamePlay!</h1>
                                    <p>©2017 All Rights Reserved. GamePlay. Privacy and Terms</p>
                                </div>
                            </div>
                        </form>
                    </section>
                </div>    
                                -->
            </div>
        </div>



    </body>
</html>