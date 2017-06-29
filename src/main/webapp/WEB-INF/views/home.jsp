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
                <div id="games" class="row list-group">
                    <!--https://codepen.io/ajaypatelaj/pen/zIBjJ-->        

                    <c:forEach var="item" items="${gameList}" >
                        <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />">
<!--                            <div class="item  col-xs-12 col-md-6">-->
                            <div class="item  col-xs-12 col-md-6" >
                                
                                <div class="thumbnail" style="height:100px; background-color: ${item.color}">
                                    <!--<img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />-->
                                    
                                    <div class="caption">
                                        <h4 class="group inner list-group-item-heading" style=" text-align: center;">
                                            ${item.gameName}</h4>

                                        <!--                                            <p class="group inner list-group-item-text">
                                                                                        Product description... Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
                                                                                        sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                                                                                    <div class="row">
                                                                                        <div class="col-xs-12 col-md-6">
                                                                                            <p class="lead">
                                                                                                $21.000</p>
                                                                                        </div>
                                                                                        <div class="col-xs-12 col-md-6">
                                                                                            <a class="btn btn-success" href="http://www.jquery2dotnet.com">Add to cart</a>
                                                                                        </div>
                                                                                    </div>
                                        -->
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>

                </div>
                <!--                    
                                    <div class="col-xs-8 col-lg-4">
                                        <h2>Heading</h2>
                                        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                                        <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
                                    </div>
                -->
                <!--/.col-xs-6.col-lg-4-->
<!--                <div class=" col-xs-12 ">
                    <h2>Adverts area</h2>
                    <p>Adverts area Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
                </div>-->
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

</script>