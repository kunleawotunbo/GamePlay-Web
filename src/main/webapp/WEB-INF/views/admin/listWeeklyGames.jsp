<%-- 
    Document   : listWeeklyGames
    Created on : Jun 17, 2017, 1:40:55 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Weekly Games</h3>
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
                        </div>
            -->
        </div>

        <div class="clearfix"></div>


        <div class="row">
            <!-- form input mask -->
           
            <div class="col-md-10 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Weekly Game List</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <table id="datatable-responsive" class="table table-striped table-bordered " cellspacing="0" width="100%">       
                            <thead>
                                <tr>
                                    <th>S/N</th>
                                    <th>GAME CATEGORY</th>
                                    <th>GAME WEEK</th>                                    
                                    <th>GAME PLAY TYPE</th>
                                    <th>EXPIRY DATE</th>
                                    <th>SET ANSWER</th>
                                    <th>VIEW WINNERS</th>
                                    <th></th>
                                    <th></th
                                   
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${weeklyGamesList}" var="item" varStatus = "status">  
                                    <tr>  
                                        <td><c:out value="${status.index + 1}"/></td> 
                                        <td><c:out value="${item.gameCatName}"/> [${item.code}]</td>  
                                        <td><c:out value="${item.weekNo}"/></td>                                          
                                        <td><c:out value="${item.gameTypeName}"/></td> 
                                        <td><c:out value="${item.gameExpiryDate}"/></td> 
                                        <td><a href="<c:url value='/admin/set-weeklyGames-Answer-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Set Answer
                                            </a>
                                        </td>
                                        <%--                                        
                                        <td><a href="<c:url value='/admin/listWeeklyGamesWinners-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> VIEW WINNERS
                                            </a>
                                        </td>
                                        --%>
                                        <td>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-danger">Action</button>
                                                <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="<c:url value='/admin/listWeeklyGamesWinners-${item.id}' />" >
                                                            VIEW WINNERS
                                                        </a>
                                                    </li>
                                                    <li><a href="<c:url value='/admin/viewAllAnswersWeeklyGame-${item.id}' />">
                                                            ALL ANSWERS
                                                        </a>
                                                    </li>
                                                    <!--                                             
                                                    <li><a href="#">Something else here</a>
                                                    </li>
                                                    <li class="divider"></li>
                                                    <li><a href="#">Separated link</a>
                                                    </li>
                                                    -->
                                                </ul>
                                            </div>
                                        </td>
                                        <td><a href="<c:url value='/admin/edit-weeklyGames-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit
                                            </a>
                                        </td>
                                        <td><a href="<c:url value='/admin/delete-weeklyGames-${item.id}' />" class="btn btn-danger custom-width"> 
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

      