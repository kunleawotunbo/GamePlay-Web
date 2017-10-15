<%-- 
    Document   : listMatchPredictions
    Created on : Sep 12, 2017, 1:51:15 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Match Prediction</h3>
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
                        <h2>Match Prediction List</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <!--<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap"  cellspacing="0" width="100%">-->
                        <table id="datatable-responsive" class="table table-striped table-bordered " cellspacing="0" width="100%">       
                            <thead>
                                <tr>
                                    <th>S/N</th>
                                    <th>GAME </th>
                                    <th>PRIZE OF WINNER</th>                                    
                                    <th>START TIME</th>
                                    <th>END TIME</th>
                                    <th>SET ANSWER</th>
                                    <th>VIEW WINNERS</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${matchPredictionList}" var="item" varStatus = "status">  
                                    <tr>  
                                        <td><c:out value="${status.index + 1}"/></td> 
                                        <td><c:out value="${item.homeTeamName}"/> vs <c:out value="${item.awayTeamName}"/>  [${item.code}]</td>  
                                        <td><c:out value="${item.prizeOfWinners}"/></td>                                          
                                        <td>
                                            <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value = "${item.startTime}" />
                                        </td> 
                                        <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value = "${item.endTime}" /></td> 
                                        <td><a href="<c:url value='/admin/set-matchPrediction-Answer-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Set Answer
                                            </a>
                                        </td>
                                        <td><a href="<c:url value='/admin/listMatchPredictionsWinners-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> VIEW WINNERS
                                            </a>
                                        </td>
                                        <td><a href="<c:url value='/admin/viewAllAnswersMatchPredictions-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i>ALL ANSWERS
                                            </a>
                                        </td>
                                        <td><a href="<c:url value='/admin/edit-matchPrediction-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit
                                            </a>
                                        </td>
                                        <td><a href="<c:url value='/admin/delete-matchPrediction-${item.id}' />" class="btn btn-danger custom-width"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Delete
                                            </a>
                                        </td>
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
