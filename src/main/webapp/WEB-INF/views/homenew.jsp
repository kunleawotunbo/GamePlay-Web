<%-- 
    Document   : homenew
    Created on : Aug 21, 2017, 10:08:46 PM
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
          
            <div class="row">


                <c:forEach var="item" items="${gameList}" >

                    <!--                            <div class="item  col-xs-12 col-md-6">-->
                    <div class="item  col-xs-6 col-md-4" >
                        <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />"  >       <h3>${item.gameName}</h3>   </a>
                        <div class="frontpage_square"  >
                            <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />"  >
                                <img src="data:image/jpg;base64,${item.gameImgLocation}" alt="${item.gameName}"  width="300" height="188" data-toggle="tooltip" title="${item.gameName}">
                            </a>
                        </div>

                        <%--<p>${item.gameName}</p>--%>
                    </div>

                </c:forEach>



                <!--https://codepen.io/ajaypatelaj/pen/zIBjJ -->    
          
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
