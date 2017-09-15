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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<style>

    .glyphicon { margin-right:5px; }
    .thumbnail
    {
        margin-bottom: 20px;
        padding: 0px;
        -webkit-border-radius: 0px;
        -moz-border-radius: 0px;
        border-radius: 0px;
    }

    .item.list-group-item
    {
        float: none;
        width: 100%;
        background-color: #fff;
        margin-bottom: 10px;
    }
    .item.list-group-item:nth-of-type(odd):hover,.item.list-group-item:hover
    {
        background: #428bca;
    }

    .item.list-group-item .list-group-image
    {
        margin-right: 10px;
    }
    .item.list-group-item .thumbnail
    {
        margin-bottom: 0px;
    }
    .item.list-group-item .caption
    {
        padding: 9px 9px 0px 9px;
    }
    .item.list-group-item:nth-of-type(odd)
    {
        background: #eeeeee;
    }

    .item.list-group-item:before, .item.list-group-item:after
    {
        display: table;
        content: " ";
    }

    .item.list-group-item img
    {
        float: left;
    }
    .item.list-group-item:after
    {
        clear: both;
    }
    .list-group-item-text
    {
        margin: 0 0 11px;
    }

    /*The below is for another thing: Making the div clickable*/
    div.feature {
        position: relative;
    }

    div.feature a {
        position: absolute;
        width: 100%;
        height: 100%;
        top: 0;
        left: 0;
        text-decoration: none; /* No underlines on the link */
        z-index: 10; /* Places the link above everything else in the div */
        background-color: #FFF; /* Fix to make div clickable in IE */
        opacity: 0; /* Fix to make div clickable in IE */
        filter: alpha(opacity=1); /* Fix to make div clickable in IE */
    }

    /*- https://codepen.io/ajaypatelaj/pen/zIBjJ*/      


</style>



<style>
    html,
    body {
        overflow-x: hidden; /* Prevent scroll on narrow devices */
    }
    body {
        padding-top: 70px;
    }
    footer {
        padding: 30px 0;
    }


    /*
     * Off Canvas
     * --------------------------------------------------
     */
    @media screen and (max-width: 767px) {
        .row-offcanvas {
            position: relative;
            -webkit-transition: all .25s ease-out;
            -o-transition: all .25s ease-out;
            transition: all .25s ease-out;
        }

        .row-offcanvas-right {
            right: 0;
        }

        .row-offcanvas-left {
            left: 0;
        }

        .row-offcanvas-right
        .sidebar-offcanvas {
            right: -60%; /* 6 columns */
        }

        .row-offcanvas-left
        .sidebar-offcanvas {
            left: -60%; /* 6 columns */
        }

        .row-offcanvas-right.active {
            right: 60%; /* 6 columns */
        }

        .row-offcanvas-left.active {
            left: 60%; /* 6 columns */
        }

        .sidebar-offcanvas {
            position: absolute;
            top: 0;
            width: 60%; /* 6 columns */
        }
    }
</style>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GamePlay |</title>
        <link href="<c:url value='/resources/css/bootstrap.min.css'/>"rel="stylesheet"></link>   
          <!-- PNotify -->
        <link href="<c:url value='/resources/css/pnotify.css' />"  rel="stylesheet"></link> 
        <link href="<c:url value='/resources/css/pnotify.buttons.css' />"  rel="stylesheet"></link> 
        <link href="<c:url value='/resources/css/pnotify.nonblock.css' />"  rel="stylesheet"></link> 
        
    </head>
    <body>
