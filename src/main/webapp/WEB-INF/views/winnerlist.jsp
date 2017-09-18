<%-- 
    Document   : winnerlist
    Created on : 15-Sep-2017, 15:09:54
    Author     : BELLO
--%>

<!--Include outside header-->
<%@ include file="includes/outside/header.jsp" %>
<!--Include outside navigation-->
<%@ include file="includes/outside/navigation.jsp" %>

<style>
    .frontpage_square{
        position:relative;
        overflow:hidden;
        padding-bottom:100%;
    }
    .frontpage_square img{
        position:absolute;

        opacity:1;
        transition: 1s ease;
    }


    .frontpage_square img:hover{
        opacity:0.5;
        transition: 1s ease;
    }   
</style>

<div class="container">

    <!--<div class="row row-offcanvas row-offcanvas-right">-->

        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
        </div>
          
            <div class="row">

                   <p> &nbsp;</p>
                       <p> &nbsp;</p>
                

                    <!--                            <div class="item  col-xs-12 col-md-6">-->
                 <c:set var = "iswinner" scope = "session" value = "true"/>
                    <c:choose>
                         
                         <c:when test = "${winnerstatus == iswinner}">
                                       <div class="col-md-12 col-sm-12 col-xs-12">
                                           <p> &nbsp;</p>
                       <p> &nbsp;</p>
                                  You're a Winner In These Game(s). Congratulations!
                       
                            </c:when>
                       
                      <c:otherwise>
                              <div class="col-md-12 col-sm-12 col-xs-12">
                                  <p> &nbsp;</p>
                       <p> &nbsp;</p>
                                   Oops. You're not yet one of our winners. Please Play One Of Our Games Now 
                                     To Be Winner or Play Again.                       
                       
                          </c:otherwise>
                   </c:choose>
                       <p> &nbsp;</p>
                       <p> &nbsp;</p>
                       
                       
                       <table id="datatable-responsive" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>S/N</th>
                                    <th>GAME CATEGORY</th>
                                    <th>WEEKLY GAME ID</th>
                                    <th>YOUR PHONE NO</th>                                    
                                    <th>YOUR ANSWER</th>
                                    <th>GAME WEEK NO</th>
                                    <!--<th></th>
                                    <th></th>
                                    <th></th>-->

                                </tr>
                            </thead>
                            <tbody>
                                 <c:forEach items="${weeklyGameAnswer}" var="item" varStatus = "status">
                                     <tr>  
                                        <td><c:out value="${status.index + 1}"/></td> 
                                        
                                        <td><c:out value="${weeklyGameCategoryName[status.getIndex()]}"/></td>
                                         <td><c:out value="${item.gameId}"/></td>
                                        <td><c:out value="${item.userPhoneNo}"/></td>                                          
                                        <td><c:out value="${item.userAnswer}"/></td> 
                                        <td><c:out value="${item.weekNo}"/></td> 
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
                        
              
                
                               <p> &nbsp;</p>
                       <p> &nbsp;</p>
                       <p> &nbsp;</p>
                       <p> &nbsp;</p>
                
            </div><!--/row-->   
            <%@ include file="includes/outside/bottomadverts.jsp" %>
        </div><!--/.col-xs-12.col-sm-9-->
</div>
</div>


        <!--Center content ends-->       

        <!--Inculde outer sidebar-->
        

        <!--Iniclude outside footer-->
        <%@ include file="includes/outside/footer.jsp" %>
        <script>
           function submitForm() {

                // getting the user form values
                var gameCategory = $('#gameCategory').val();
                var gameType = $('#gameType').val();
                var prizeOfWinners = $('#prizeOfWinners').val();
                var noOfWinners = $('#noOfWinners').val();
                var file = $('#gameImage1').val();
                var file2 = $('#gameImage2').val();
                // Do some validation
                if (gameCategory === "") {
                alert('Please choose a game category');
                $('#gameCategory').focus();
                return false;
                }
                if (gameType === "") {
                alert('Please choose a game type');
                $('#gameType').focus();
                return false;
                }
                if (prizeOfWinners == 0) {
                alert('Prize must be greater than 0');
                $('#prizeOfWinners').focus();
                return false;
                }
                if (noOfWinners == 0) {
                alert('Number of Winners must be greater than 0');
                $('#noOfWinners').focus();
                return false;
                }


                }
           
        </script>
