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
                        
                        <table class="table">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Match</th>
                                    <th>&nbsp; &nbsp; &nbsp;
                                        1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                                        X &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        2
                                    </th>

                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${activeMatchesList}" var="item" varStatus = "status">  
                                    <%--                                 
                                      <tr> <div class="date"> 
                                            <fmt:formatDate pattern="dd-MM-yyyy" value = "${item.startTime}" />
                                            
                                            </div></tr>
                                    --%>

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
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> X
                                            </a>
                                            &nbsp;
                                            <a href="<c:url value='/set-matchPrediction-2-${item.id}' />" class="btn btn-danger custom-width"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Away Win
                                            </a>

                                            &nbsp;
                                            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal </button>

                                        </td>

                                        <!-- Modal -->
                                <div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="false">
                                    <div class="modal-dialog">

                                        <!-- Modal content -->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title"><strong><c:out value="${item.homeTeamName}"/> - <c:out value="${item.awayTeamName}"/></strong></h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>Some text in the modal</p>
                                                <form>
                                                    <div class="form-group">
                                                        <label for="phoneNumber">Name:</label>
                                                        <input type="text" class="form-control" id="phoneNumber">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="pwd">Password:</label>
                                                        <input type="password" class="form-control" id="pwd">
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>




                                </tr>  
                            </c:forEach>  

                            </tbody>
                        </table>

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
