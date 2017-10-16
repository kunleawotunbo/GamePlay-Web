<%-- 
    Document   : prediction
    Created on : Sep 11, 2017, 3:32:23 PM
    Author     : Olakunle Awotunbo
--%>



<!--Include outside header-->
<%@ include file="includes/outside/header.jsp" %>
<!--Include outside navigation-->
<%@ include file="includes/outside/navigation.jsp" %>


<div class="container">
    <div class="row content">

        <div class="col-sm-9 ">


            <div class="container-fluid">    

                <div class="row">   


                    <!--Content starts-->

                    <br>
                    <div class="x_content">
<%--
                        <table class="table">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Match</th>
                                    <th>&nbsp; &nbsp; &nbsp;
                                        Home &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                                        Draw &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        Away
                                    </th>

                                </tr>
                            </thead>
                            <tbody>

                                      
                                    <c:forEach items="${activeMatchesList}" var="item" varStatus = "status">                                     
                                        <tr>  
                                            <td>
                                                <div class="dataInizio"> 
                                                    <fmt:formatDate pattern="HH:mm" value = "${item.startTime}" />
                                                </div>
                                            </td> 
                                            <td><strong><c:out value="${item.homeTeamName}"/> - <c:out value="${item.awayTeamName}"/></strong></td>  

                                        <td>
                                            <a href="<c:url value='/set-matchPrediction-1-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Home Win
                                            </a>                                            
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-X-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Draw
                                            </a>
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-2-${item.id}' />" class="btn btn-danger custom-width"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Away Win
                                            </a>                                        

                                        </td>                                    
                                    </tr>  
                                </c:forEach> 
                               

                               
                                <c:forEach items="${eplMatchesList}" var="item" varStatus = "status">                                     
                                    <tr>  
                                        <td>
                                            <div class="dataInizio"> 
                                                <fmt:formatDate pattern="HH:mm" value = "${item.startTime}" />
                                            </div>
                                        </td> 
                                        <td><strong><c:out value="${item.homeTeamName}"/> - <c:out value="${item.awayTeamName}"/></strong></td>  

                                        <td>
                                            <a href="<c:url value='/set-matchPrediction-1-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Home Win
                                            </a>                                            
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-X-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Draw
                                            </a>
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-2-${item.id}' />" class="btn btn-danger custom-width"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Away Win
                                            </a>                                        

                                        </td>                                    
                                    </tr>  
                                </c:forEach>  

                                
                                <c:forEach items="${laligaMatchesList}" var="item" varStatus = "status">                                     
                                    <tr>  
                                        <td>
                                            <div class="dataInizio"> 
                                                <fmt:formatDate pattern="HH:mm" value = "${item.startTime}" />
                                            </div>
                                        </td> 
                                        <td><strong><c:out value="${item.homeTeamName}"/> - <c:out value="${item.awayTeamName}"/></strong></td>  

                                        <td>
                                            <a href="<c:url value='/set-matchPrediction-1-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Home Win
                                            </a>                                            
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-X-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Draw
                                            </a>
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-2-${item.id}' />" class="btn btn-danger custom-width"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Away Win
                                            </a>                                        

                                        </td>                                    
                                    </tr>  
                                </c:forEach>     

                                
                                <c:forEach items="${otherLeagueMatchesList}" var="item" varStatus = "status">                                     
                                    <tr>  
                                        <td>
                                            <div class="dataInizio"> 
                                                <fmt:formatDate pattern="HH:mm" value = "${item.startTime}" />
                                            </div>
                                        </td> 
                                        <td><strong><c:out value="${item.homeTeamName}"/> - <c:out value="${item.awayTeamName}"/></strong></td>  

                                        <td>
                                            <a href="<c:url value='/set-matchPrediction-1-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Home Win
                                            </a>                                            
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-X-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Draw
                                            </a>
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-2-${item.id}' />" class="btn btn-danger custom-width"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Away Win
                                            </a>                                        

                                        </td>                                    
                                    </tr>  
                                </c:forEach>     

                            </tbody>
                        </table>
--%>

                         
                         <!--England EPL-->       
                        <table class="table">
                            <thead class="thead-inverse">
                                <tr>
                                    <th></th>
                                    <th style="width: 400px">${england} - ${epl}</th>
                                    
                                        <!--                 
                                        <th>&nbsp; &nbsp; &nbsp;
                                        Home &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                                        Draw &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        Away                                        
                                    </th>-->
                                    <th>&nbsp; &nbsp; &nbsp;
                                        Home &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                                        Draw &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        Away                                        
                                    </th>
                                </tr>
                            </thead>
                                
                            <tbody>
                                 <c:forEach items="${eplMatchesList}" var="item" varStatus = "status">    
                                <tr>                                   
                                    <tr>  
                                        <td>
                                            <div class="dataInizio"> 
                                                <fmt:formatDate pattern="HH:mm" value = "${item.startTime}" />
                                            </div>
                                        </td> 
                                        <td style="width: 400px"><strong><c:out value="${item.homeTeamName}"/> - <c:out value="${item.awayTeamName}"/></strong></td>  

                                        <td>
                                            <a href="<c:url value='/set-matchPrediction-1-${item.id}' />" class="btn btn-success " title="${item.homeTeamName} Win">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Home Win
                                            </a>                                            
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-X-${item.id}' />" class="btn btn-success custom-width" title="Draw">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Draw
                                            </a>
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-2-${item.id}' />" class="btn btn-danger custom-width" title="${item.awayTeamName} Win"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Away Win
                                            </a>                                        

                                        </td>                                    
                                    </tr>  
                                    
                                </tr>
                                 </c:forEach>  

                            </tbody>
                        </table>
                                    
                                    
                                    <!--Spain LAliga-->
                         <c:if test="${!empty laligaMatchesList}">             
                        <table class="table">
                            <thead class="thead-inverse">
                                <tr>
                                    <th></th>
                                    <th style="width: 400px">${spain} - ${laliga}</th>
                                    
                                    <th>
                                        <!--                                    
                                        &nbsp; &nbsp; &nbsp;
                                        Home &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                                        Draw &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        Away
                                        -->
                                    </th>
                                </tr>
                            </thead>
                                
                            <tbody>
                                 <c:forEach items="${laligaMatchesList}" var="item" varStatus = "status">    
                                <tr>                                   
                                    <tr>  
                                        <td>
                                            <div class="dataInizio"> 
                                                <fmt:formatDate pattern="HH:mm" value = "${item.startTime}" />
                                            </div>
                                        </td> 
                                        <td style="width: 400px"><strong><c:out value="${item.homeTeamName}"/> - <c:out value="${item.awayTeamName}"/></strong></td>  

                                        <td>
                                            <a href="<c:url value='/set-matchPrediction-1-${item.id}' />" class="btn btn-success " title="${item.homeTeamName} Win">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Home Win
                                            </a>                                            
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-X-${item.id}' />" class="btn btn-success custom-width" title="Draw">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Draw
                                            </a>
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-2-${item.id}' />" class="btn btn-danger custom-width" title="${item.awayTeamName} Win"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Away Win
                                            </a>                                        

                                        </td>                                    
                                    </tr>  
                                    
                                </tr>
                                 </c:forEach>  

                            </tbody>
                        </table>
                        </c:if>                           
                                    

                    </div>

                    <!--Content ends --> 

                    <!--Modal--> 


                </div>    

            </div><br>

            <!--Bottom Ads Starts -->               
            <div class="row">   
                <div class="container-fluid bg-3 text-center">    
                    <!--Include bottomadverts-->
                    <%@ include file="includes/outside/bottomadverts.jsp" %>

                </div>
            </div>

            <!--Bottom Ads Ends -->        

        </div>
        <!-- Side bar ads starts--> 
        <div class="col-sm-3 sidenav">
            <%@ include file="includes/outside/sidebar.jsp" %>                      
        </div>
        <!--Side bar ads ends --> 

    </div>
</div>

<!--Include outside footer-->
<%@ include file="includes/outside/footer.jsp" %>
