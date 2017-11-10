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


                        <!--                        
                        <div class="cal-wrap" data-type="date-bar" style="">
                                                    <div class="cal">
                                                        <div class="clear"> 
                                                            <div class="col-5">                                         
                                                                <a class="col-4" href="" id="d1"></a>
                                                                <a class="col-4" href="" id="d2"></a> 
                                                                <a class="col-4 br" href="" id="d3"></a> 
                                                            </div>
                                                            <a class="col-2" href="" id="today"></a>
                                                            <div class="col-5"> 
                                                                <a class="col-4" href="" id="d4"></a> 
                                                                <a class="col-4" href="" id="d5"></a>
                                                                <a class="col-4" href="" id="d6"></a>
                                                            </div>
                                                        </div>
                                                    </div>
                          </div>
                        -->




                        <table class="table">
                            <thead class="thead-inverse">
                                <tr>
                                    <th><a class="col-4" href="" id="d1"></a></th>
                                    <th><a class="col-4" href="" id="d2"></a> </th>
                                    <th><a class="col-4" href="" id="d3"></a> </th>
                                    <th><a class="col-4" href="" id="today"></a> </th>
                                    <th><a class="col-4" href="" id="d4"></a></th>
                                    <th><a class="col-4" href="" id="d5"></a> </th>
                                    <th><a class="col-4" href="" id="d6"></a> </th>

                                </tr>
                            </thead>


                        </table>

                        <!--England EPL-->                      
                        <table class="table">
                            <thead class="thead-inverse">
                                <tr>
                                    <th></th>
                                    <th style="width: 400px">${england} - ${epl}</th>
                                    <!--                                    <th>&nbsp; &nbsp; &nbsp;
                                                                            Home &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                                                                            Draw &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                                                            Away                                        
                                                                        </th>-->
                                    <th>
                                        <div class="row">
                                            <div class="col-sm-4">Home</div>
                                            <div class="col-sm-4">Draw</div>
                                            <div class="col-sm-4">Away</div>
                                        </div>
                                    </th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach items="${eplMatchesList}" var="item" varStatus = "status">    
                                <div class="row">

                                    <tr>  
                                    <div class="col-sm-4">
                                        <td>
                                            <div class="dataInizio"> 
                                                <fmt:formatDate pattern="HH:mm" value = "${item.startTime}" />
                                            </div>
                                        </td> 
                                        <td style="width: 400px"><strong><c:out value="${item.homeTeamName}"/> - <c:out value="${item.awayTeamName}"/></strong></td>  
                                    </div>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.matchExpired}">
                                                <div class="row">
                                                    <a href="<c:url value='/set-matchPrediction-1-${item.id}' />" class="btn btn-success " title="${item.homeTeamName} Win" disabled>
                                                        <i class="fa fa-check-square-o" aria-hidden="true"></i> Home Win
                                                    </a>                                            
                                                    &nbsp;
                                                    <a href="<c:url value='/set-matchPrediction-X-${item.id}' />" class="btn btn-success custom-width" title="Draw" disabled>
                                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Draw
                                                    </a>
                                                    &nbsp;
                                                    <a href="<c:url value='/set-matchPrediction-2-${item.id}' />" class="btn btn-danger custom-width" title="${item.awayTeamName} Win" disabled> 
                                                        <i class="f fa fa-trash-o" aria-hidden="true"></i> Away Win
                                                    </a>     

                                                </div> 
                                                <p>Match not available to play.</p>                        
                                            </c:when>
                                            <c:otherwise>                                            

                                                <div class="row">
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

                                                </div> 
                                            </c:otherwise>

                                        </c:choose>       
                                    </td>
                                    </tr>  

                                </div>
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
                                            <td>
                                                <div class="dataInizio"> 
                                                    <fmt:formatDate pattern="HH:mm" value = "${item.startTime}" />
                                                </div>
                                            </td> 
                                            <td style="width: 400px"><strong><c:out value="${item.homeTeamName}"/> - <c:out value="${item.awayTeamName}"/></strong></td>  

                                            <td>
                                                <c:choose>
                                                    <c:when test="${item.matchExpired}">
                                                        <div class="row">
                                                            <a href="<c:url value='/set-matchPrediction-1-${item.id}' />" class="btn btn-success " title="${item.homeTeamName} Win" disabled>
                                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Home Win
                                                            </a>                                            
                                                            &nbsp;
                                                            <a href="<c:url value='/set-matchPrediction-X-${item.id}' />" class="btn btn-success custom-width" title="Draw" disabled>
                                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Draw
                                                            </a>
                                                            &nbsp;
                                                            <a href="<c:url value='/set-matchPrediction-2-${item.id}' />" class="btn btn-danger custom-width" title="${item.awayTeamName} Win" disabled> 
                                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Away Win
                                                            </a>     

                                                        </div> 
                                                        <p>Match not available to play.</p>                        
                                                    </c:when>
                                                    <c:otherwise>                                            

                                                        <div class="row">
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

                                                        </div> 
                                                    </c:otherwise>

                                                </c:choose>                                   

                                            </td>                                    
                                        </tr>  

                                    </c:forEach>  

                                </tbody>
                            </table>
                        </c:if>  

                        <!--Champions League-->

                        <c:if test="${!empty champLeaguesMatchesList}">             
                            <table class="table">
                                <thead class="thead-inverse">
                                    <tr>
                                        <th></th>
                                        <th style="width: 400px">${championsLeagues} - ${championsLeagues}</th>

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
                                    <c:forEach items="${champLeaguesMatchesList}" var="item" varStatus = "status">    

                                        <tr>  
                                            <td>
                                                <div class="dataInizio"> 
                                                    <fmt:formatDate pattern="HH:mm" value = "${item.startTime}" />
                                                </div>
                                            </td> 
                                            <td style="width: 400px"><strong><c:out value="${item.homeTeamName}"/> - <c:out value="${item.awayTeamName}"/></strong></td>  

                                            <td>
                                                <c:choose>
                                                    <c:when test="${item.matchExpired}">
                                                        <div class="row">
                                                            <a href="<c:url value='/set-matchPrediction-1-${item.id}' />" class="btn btn-success " title="${item.homeTeamName} Win" disabled>
                                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Home Win
                                                            </a>                                            
                                                            &nbsp;
                                                            <a href="<c:url value='/set-matchPrediction-X-${item.id}' />" class="btn btn-success custom-width" title="Draw" disabled>
                                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Draw
                                                            </a>
                                                            &nbsp;
                                                            <a href="<c:url value='/set-matchPrediction-2-${item.id}' />" class="btn btn-danger custom-width" title="${item.awayTeamName} Win" disabled> 
                                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Away Win
                                                            </a>     

                                                        </div> 
                                                        <p>Match not available to play.</p>                        
                                                    </c:when>
                                                    <c:otherwise>                                            

                                                        <div class="row">
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

                                                        </div> 
                                                    </c:otherwise>

                                                </c:choose>   


                                            </td>                                    
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
<script src="<c:url value='/resources/js/moment.min.js' />"></script> 
<script>

    jQuery(document).ready(function ($) {

        var d = new Date();
        var n = d.getDay();
        var day = d.getDate();
        var month = d.getMonth() + 1;
        var year = d.getFullYear();
        // var monthName = d.getMonthName();

        //var objDate = new Date().toLocaleString("en-us", {month: "short"}); // result: Aug
        var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        ];

        // var d = new Date();
        var monthShortName = monthNames[d.getMonth()];
        //document.write("The current month is " + monthNames[d.getMonth()]);
        /*
         document.getElementById("d1").innerHTML = " " + objDate + " " + (day - 3);
         document.getElementById("d2").innerHTML = " " + objDate + " " + (day - 2);
         document.getElementById("d3").innerHTML = " " + objDate + " " + (day - 1);
         document.getElementById("today").innerHTML = "Today";
         document.getElementById("d4").innerHTML = " " + objDate + " " + (day + 1);
         document.getElementById("d5").innerHTML = " " + objDate + " " + (day + 2);
         document.getElementById("d6").innerHTML = " " + objDate + " " + (day + 3);
         */

        document.getElementById("d1").innerHTML = " " + monthShortName + " " + (day - 3);
        document.getElementById("d2").innerHTML = " " + monthShortName + " " + (day - 2);
        document.getElementById("d3").innerHTML = " " + monthShortName + " " + (day - 1);
        document.getElementById("today").innerHTML = "Today";
        document.getElementById("d4").innerHTML = " " + monthShortName + " " + (day + 1);
        document.getElementById("d5").innerHTML = " " + monthShortName + " " + (day + 2);
        document.getElementById("d6").innerHTML = " " + monthShortName + " " + (day + 3);


        // console.log("moment :: " + moment.monthsShort());
        //document.write("The current month is " + monthNames[d.getMonth()]);
        //console.log("date :: " + objDate.toString() + " " + (day + 3));
        //console.log("date2 :: " + objDate + " " + (day + 3));

        //document.getElementById("date1").innerHTML =  year + "-" + month + "-" + (day - 3);
        $("#d1").prop("href", "${pageContext.request.contextPath}/soccer/" + year + "-" + month + "-" + (day - 3));
        $("#d2").prop("href", "${pageContext.request.contextPath}/soccer/" + year + "-" + month + "-" + (day - 2));
        $("#d3").prop("href", "${pageContext.request.contextPath}/soccer/" + year + "-" + month + "-" + (day - 1));
        $("#today").prop("href", "${pageContext.request.contextPath}/soccer/" + year + "-" + month + "-" + (day));
        $("#d4").prop("href", "${pageContext.request.contextPath}/soccer/" + year + "-" + month + "-" + (day + 1));
        $("#d5").prop("href", "${pageContext.request.contextPath}/soccer/" + year + "-" + month + "-" + (day + 2));
        $("#d6").prop("href", "${pageContext.request.contextPath}/soccer/" + year + "-" + month + "-" + (day + 3));


    });

</script>