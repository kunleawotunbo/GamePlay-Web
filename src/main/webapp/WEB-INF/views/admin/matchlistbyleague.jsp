<%-- 
    Document   : matchlistbyleague
    Created on : 26-Sep-2017, 04:02:49
    Author     : BELLO
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Game Matches</h3>
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
             <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Games Matches In /League Name/ </h2> 

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                       <table id="datatable-responsive" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>S/N</th>
                                    <th>HOME TEAM </th>
                                    <th>AWAY TEAM</th>
                                    <th>MATCH RESULT</th>                                    
                                    <th>LEAGUE</th>
                                    <th>COUNTRY</th>
                                    <!--<th></th>
                                    <th></th>
                                    <th></th>-->

                                </tr>
                            </thead>
                            <tbody>
                                 <c:forEach items="${matchPredictionList}" var="item" varStatus = "status">
                                     <tr>  
                                        <td><c:out value="${status.index + 1}"/></td> 
                                        
                                        <td><c:out value="${item.homeTeamName}"/></td>
                                         <td><c:out value="${item.awayTeamName}"/></td>
                                        <td><c:out value="${item.matchResult}"/></td>                                          
                                        <td><c:out value="${item.leagueName}"/></td> 
                                        <td><c:out value="${item.countryName}"/></td>
                                       <!-- <td><c:out value="${item.weekNo}"/></td> --> 
                                         <!-- <td><a href="<c:url value='/admin/generate-RandomWinners-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Generate Random Winners
                                            </a>
                                        </td>
                                        <td><a href="<c:url value='/admin/edit-weeklyGames-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit
                                            </a>
                                        </td>
                                        <td><a href="<c:url value='/admin/delete-weeklyGames-${item.id}' />" class="btn btn-danger custom-width"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Delete
                                            </a>
                                        </td>
                                    </tr>-->  
                                
                               </c:forEach>             
                            </tbody>
                        </table>


                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>         



        <%@ include file="../includes/footer.jsp" %>

        <script>


        </script>




