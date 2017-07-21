<%-- 
    Document   : forgotPassword
    Created on : Jul 21, 2017, 3:26:19 PM
    Author     : Olakunle Awotunbo
--%>

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

                            <form method="POST" class="form-horizontal">
                                <h1>Reset Password</h1>
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
                                    <input type="email" class="form-control" placeholder="Email" id="email" name="email" required="" />
                                </div>

                                <div>

                                    <!--<input type="submit" class="btn btn-primary" value="Reset Password"  onclick="savePass()" >-->
                                    <button class="btn btn-primary" type="submit" onclick="savePass()" >submit</button>

                                </div>

                                <div class="clearfix"></div>

                                <div class="separator">
                                    <p class="change_link">Already a member ?
                                        <a href="login" class="to_register"> Log in </a>
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


            </div>
        </div>

        <!-- jQuery -->
        <script src="<c:url value='/resources/js/jquery.min.js' />"></script> 

        <script type="text/javascript">

                                        jQuery(document).ready(function ($) {

                                        //$("#success-alert").hide();
                                        // success alert
                                        $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
                                        $("#success-alert").slideUp(500);
                                        });
                                                // error alert
                                                $("#success-error").fadeTo(2000, 500).slideUp(500, function(){
                                        $("#success-error").slideUp(500);
                                        });
                                        }
                                        
                                        var serverContext = [[@{/}]];
                                                function resetPass() {
                                                //alert('I am here');
                                                var email = $("#email").val();
                                                        $.post(serverContext + "user/resetPassword", {email: email},
                                                                function (data) {
                                                                window.location.href =
                                                                        serverContext + "login?message=" + data.message;
                                                                })
                                                        .fail(function (data) {
                                                        if (data.responseJSON.error.indexOf("MailError") > - 1)
                                                        {
                                                        window.location.href = serverContext + "emailError.html";
                                                        } else {
                                                        window.location.href =
                                                                serverContext + "login?message=" + data.responseJSON.message;
                                                        }
                                                        });
                                                }

        </script>

    </body>
</html>