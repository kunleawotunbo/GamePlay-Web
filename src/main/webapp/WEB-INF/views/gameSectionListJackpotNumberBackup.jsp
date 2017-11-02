<%-- 
    Document   : gameSectionListJackpotNumberBackup
    Created on : Nov 2, 2017, 5:00:22 PM
    Author     : OLAKUNLE
--%>


<!--Include outside header-->
<%@ include file="includes/outside/header.jsp" %>
<!--Include outside navigation-->
<%@ include file="includes/outside/navigation.jsp" %>

<!--intl-tel-input-->
<link href="<c:url value='/resources/css/intlTelInput.css' />"  rel="stylesheet"></link> 

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>

            <div class="row">

                <!--The choose tag checks if object weeklyGame is null-->
                <c:choose>
                    <c:when test="${empty weeklyGameList}">  
                        <form class="form-horizontal form-label-left">
                            <!--<h2>No Game available for this category yet.<h2>-->
                             
                                     <img src="<c:url value='/resources/images/notavailable.png' />" alt="notavailable">
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3"><span class="required"></span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">                                            
                                            <p class="form-control-static">No Game available for this category yet.</p>
                                        </div>     
                                    </div>
                        </form>
<!--                        <h2>No Game available for this category yet.<h2>-->
                    </c:when>
                            <c:otherwise>        
                                 <c:forEach items="${weeklyGameList}" var="item">  
                                

                                <div class="well">
                                    
                                    <h3><a href="<c:url value='/gameSectionx-${item.id}-${gameCode}' />">${item.gameRules}</a></h3>
                                </div>
                                
                                </c:forEach>  

                            </c:otherwise>
                        </c:choose>       



                        <!--Include bottomadverts-->
                        <%@ include file="includes/outside/bottomadverts.jsp" %>
                        </div><!--/row-->
                        </div><!--/.col-xs-12.col-sm-9-->


                        <!--Center content ends-->       

                        <!--Inculde outer sidebar-->
                        <%@ include file="includes/outside/sidebar.jsp" %>

                        <!--Iniclude outside footer-->
                        <%@ include file="includes/outside/footer.jsp" %>

  
