<%-- 
    Document   : home
    Created on : Jun 19, 2017, 7:28:38 PM
    Author     : Olakunle Awotunbo
--%>

<!--Include outside header-->
<%@ include file="includes/outside/header.jsp" %>
<!--Include outside navigation-->
<%@ include file="includes/outside/navigation.jsp" %>


<div class="container">
    <div class="row content">

        <div class="col-sm-9 text-left">


            <div class="container-fluid bg-3 text-center">    

                <div class="row">   
                    <c:forEach var="item" items="${gameList}" >

                        <!--<div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">-->
                        <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                            <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />"  >       <h3>${item.gameName}</h3>   </a>
                            <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />"  >
                            <img src="data:image/jpg;base64,${item.gameImgLocation}" alt="${item.gameName}"   class="img-responsive" style="width:100%" alt="${item.gameName}">
                            </a> 
                        </div>
                        <%--
                                                        <div class="col-sm-4">
                                                            <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />"  >       <h3>${item.gameName}</h3>   </a>
                                                            <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />"  >
                                                                <img src="data:image/jpg;base64,${item.gameImgLocation}" alt="${item.gameName}"   class="img-responsive" style="width:100%" alt="${item.gameName}">
                                                            </a> 
                                                        </div>
                        --%>



                    </c:forEach>

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
