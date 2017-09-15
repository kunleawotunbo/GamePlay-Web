<%-- 
    Document   : listMatchPredictionsWinners
    Created on : Sep 15, 2017, 7:12:10 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Match Prediction Winners for game </h3>
            </div>
            <!--
                        <div class="title_right">
                            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">Go!</button>
                                    </span>
                                </div>
                            </div>
                        </div>-->
        </div>

        <div class="clearfix"></div>


        <div class="row">
            <!-- form input mask -->
            <!--              <div class="col-md-6 col-sm-12 col-xs-12">--> 
            <div class="col-md-10 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Match Prediction Winners List</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <!--<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap"  cellspacing="0" width="100%">-->
                        <table id="datatable-responsive" class="table table-striped table-bordered " cellspacing="0" width="100%">       
                            <thead>
                                <tr>
                                    <th>S/N</th>
                                    <th>PHONE NO </th>
                                    <th>DATE ANSWERED</th>  
                                    <th>USER ANSWER</th>
                                    <th>SET ANSWER</th>
                                    <th>PROCESSED DATE</th>
                                    <th></th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${matchPredictionWinnersList}" var="item" varStatus = "status">  
                                    <tr>  
                                        <td><c:out value="${status.index + 1}"/></td> 
                                        <td>${item.userPhoneNo}</td>                                                                           
                                        <td>
                                            <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value = "${item.dateAnswered}" />
                                        </td> 
                                        <td>${item.userAnswer}</td>    
                                        <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value = "${item.proccessedDate}" /></td> 
                                       
                                    </tr>  
                                </c:forEach>  
                            </tbody>
                        </table>


                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>         



        <%@ include file="../includes/footer.jsp" %>
