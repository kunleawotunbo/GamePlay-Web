<%-- 
    Document   : home
    Created on : Jun 19, 2017, 7:28:38 PM
    Author     : Olakunle Awotunbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
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



</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GamePlay |</title>
          <link href="<c:url value='/resources/css/bootstrap.min.css'/>"rel="stylesheet"></link>   
    </head>
    <body>
       
        
        <div class="container">
            <div class="item  col-xs-12 col-md-6">
                <h1>WINGAMEPLAY</h1>
            </div>
                
        </div>
        
   <div class="container">
            
    <div class="well well-sm">
        <strong>Display</strong>
        <div class="btn-group">
            <a href="#" id="list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th-list">
            </span>List</a> <a href="#" id="grid" class="btn btn-default btn-sm"><span
                class="glyphicon glyphicon-th"></span>Grid</a>
        </div>
    </div>
    <div id="products" class="row list-group">
        <!--https://codepen.io/ajaypatelaj/pen/zIBjJ-->        
            
         <c:forEach var="item" items="${gameList}" >
             <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />">
          <div class="item  col-xs-12 col-md-6">
            <div class="thumbnail">
                <!--<img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />-->
                <div class="caption">
                    <h4 class="group inner list-group-item-heading">
                        ${item.gameName}</h4>
                    <p class="group inner list-group-item-text">
                        Product description... Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
                        sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                    <div class="row">
                        <div class="col-xs-12 col-md-6">
                            <p class="lead">
                                $21.000</p>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <a class="btn btn-success" href="http://www.jquery2dotnet.com">Add to cart</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
         </a>
        </c:forEach>
    
    </div>
       
      
</div>

        
        
        <script src="<c:url value='/resources/js/jquery.min.js' />"></script> 
        
        <script>

    $(document).ready(function () {
        $('#list').click(function (event) {
            event.preventDefault();
            $('#products .item').addClass('list-group-item');
        });
        $('#grid').click(function (event) {
            event.preventDefault();
            $('#products .item').removeClass('list-group-item');
            $('#products .item').addClass('grid-group-item');
        });
    });

</script>

        
        
    </body>
</html>

