<%-- 
    Document   : home
    Created on : Jun 19, 2017, 7:28:38 PM
    Author     : Olakunle Awotunbo
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

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
            <!--        
            <div class="jumbotron">
                        <h1>Hello, world!</h1>
                        <p>This is an example to show the potential of an offcanvas layout pattern in Bootstrap. Try some responsive-range viewport sizes to see it in action.</p>
            </div>
            -->
            <div class="row">


                <c:forEach var="item" items="${gameList}" >

                    <!--                            <div class="item  col-xs-12 col-md-6">-->
                    <div class="item  col-xs-6 col-md-4" >

                        <div class="frontpage_square"  >
                            <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />"  >
                                <!--<img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />-->
                                <%--<img src="<c:url value='/resources/images/09.jpg' />" class="img img-responsive full-width" />--%>
                                <img src="data:image/jpg;base64,${item.gameImgLocation}" alt="${item.gameName}"  width="300" height="188" data-toggle="tooltip" title="${item.gameName}">
                            </a>
                        </div>


                        <%--<p>${item.gameName}</p>--%>
                    </div>

                </c:forEach>



                <!--https://codepen.io/ajaypatelaj/pen/zIBjJ -->    
                <%-- 
                                <div id="games" class="row list-group">
                                       

                    <c:forEach var="item" items="${gameList}" >
                        <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />">
                            <div class="item  col-xs-6 col-md-4" >
                        
                                
                                <div class="thumbnail" style="height:100px; background-color: ${item.color}">                                   
                                    
                                    <div class="caption">
                                        <h4 class="group inner list-group-item-heading" style=" text-align: center;">
                                            ${item.gameName}</h4>
                                     
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>

                </div>
 
                --%>

                <!--https://jsfiddle.net/9e87kvzp/-->
                <!--/.col-xs-6.col-lg-4-->

                <!--Include bottomadverts-->
                <%@ include file="includes/outside/bottomadverts.jsp" %>
            </div><!--/row-->                            
        </div><!--/.col-xs-12.col-sm-9-->


        <!--Center content ends-->       

        <!--Inculde outer sidebar-->
        <%@ include file="includes/outside/sidebar.jsp" %>

        <!--Iniclude outside footer-->
        <%@ include file="includes/outside/footer.jsp" %>
        <script>

            $(document).ready(function () {
                $('#list').click(function (event) {
                    event.preventDefault();
                    $('#games .item').addClass('list-group-item');
                });
                $('#grid').click(function (event) {
                    event.preventDefault();
                    $('#games .item').removeClass('list-group-item');
                    $('#games .item').addClass('grid-group-item');
                });
            });

            // For the tooltip
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });

        </script>