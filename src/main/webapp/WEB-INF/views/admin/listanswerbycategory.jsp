<%-- 
    Document   : listanswerbycategory
    Created on : 26-Jul-2017, 09:32:21
    Author     : BELLO
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Answers By Category</h3>
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
        
         <div class="row">
            <!-- form input mask -->
            <!--              <div class="col-md-6 col-sm-12 col-xs-12">--> 
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <c:set var = "gameanswertype" scope = "session" value = "CategoryAnswer"/>
                                  <c:choose>
                                      <c:when test = "${answertype == gameanswertype}">
                                          <h2>
                                             <p>All Users Submitted Answer for <c:out value="${gamecategory}"/> Game Category</p></h2>
                                            </c:when>
                                            <c:otherwise>
                                                 <h2>Active Week:<c:out value="${activeweek}"/>  Correct Answers Only List and Potential Winners
                                                 </h2>
                                             </c:otherwise>
                                        </c:choose>
                       

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <table id="datatable-responsive" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            
                               
                                           <thead>
                                        <tr>
                                     <th>S/N</th>
                                    <th>Week No</th>
                                    <th>GAME ID</th>
                                    <th>GAME ANSWER </th>
                                    <th>USER ANSWER</th>
                                    <th>USER PHONE NUMBER</th>
                                     <th>USER ANSWER IS:</th>
                                    
                                    
                                    

                                </tr>
                            </thead>
                            <tbody>
                               <c:set var = "useranswer" scope = "session" value = "CORRECT"/>
                                <c:forEach items="${gameanswerlist}" var="item" varStatus="status"  > 
                                    
                                    <tr>  
                                        
                                         
                                         <td><c:out value="${status.getIndex()}"/> </td>
                                        <td><c:out value="${item.weekNo}"/></td>
                                         <td><c:out value="${item.gameId}"/></td> 
                                         <td><c:out value="${admingameanswer[status.getIndex()]}"/></td> 
                                        <td><c:out value="${item.userAnswer}"/></td>  
                                        <td><c:out value="${item.userPhoneNo}"/></td>
                                        <c:choose>
                                            <c:when test = "${answerstatus[status.getIndex()] != useranswer}">
                                                <td style="color:red"><c:out value="${answerstatus[status.getIndex()]}"/></td>
                                            </c:when>
                                            <c:otherwise>
                                        <td style="color:green"><c:out value="${answerstatus[status.getIndex()]}"/></td>
                                          </c:otherwise>
                                        </c:choose>
                                       
                                        <!--<td>
                                            <a href="<c:url value='/admin/setup-gameType' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Setup
                                            </a>
                                        </td>-->
                                        
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



