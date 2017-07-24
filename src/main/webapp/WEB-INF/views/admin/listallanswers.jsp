<%-- 
    Document   : listallanswers
    Created on : 21-Jul-2017, 03:24:19
    Author     : BELLO
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Answers</h3>
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
            <div class="col-md-10 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Submitted Answers</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                            
                                <tr>
                                    <td><table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="80%">
                                           <thead>
                                        <tr>
                                     <th>S/N</th>
                                    <th>Week No</th>
                                    <th>GAME ID</th>
                                    <th>USER ANSWER</th>
                                    <th>USER PHONE NUMBER</th>
                                    
                                    
                                    

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${gameanswerlist}" var="item"  
                                      > 
                                    
                                    <tr>  
                                         <td>&nbsp;</td>
                                        <td><c:out value="${item.weekNo}"/></td>
                                         <td><c:out value="${item.gameId}"/></td> 
                                        <td><c:out value="${item.userAnswer}"/></td>  
                                        <td><c:out value="${item.userPhoneNo}"/></td>
                                        
                                        
                                        <!--<td>
                                            <a href="<c:url value='/admin/setup-gameType' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Setup
                                            </a>
                                        </td>-->
                                        
                                    </tr>  
                                    
                                </c:forEach>  
                                     
                            </tbody></table></td><td><table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="20%">
                            <thead>
                                <tr>
                                    
                                    <th>USER ANSWER IS:</th>
                                    
                                    

                                </tr>
                            </thead>
                            <tbody>
                               
                                   <c:forEach items="${answerstatus}" var="item2"  
                                      >  
                                    <tr>  
                                        <c:set var = "useranswer" scope = "session" value = "WRONG"/>
                                        <c:choose>
                                            <c:when test = "${item2 == useranswer}">
                                                <td style="color:red"><c:out value="${item2}"/></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td style="color:green"><c:out value="${item2}"/></td>
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
                        </table> </td> </tr>
                        </table>
                           
                        


                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>       
    <%@ include file="../includes/footer.jsp" %>

