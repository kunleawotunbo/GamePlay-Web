<%-- 
    Document   : congratulations
    Created on : Jul 6, 2017, 11:15:37 PM
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

            <div class="row">


                <div class="panel-body">

                    <div class="col-md-6 ">

                        <div class="alert alert-success lead">

                            <h2> Congratulations. </h2> 

                            <p>Your answer has been submitted successfully.</p>
                        </div>

                        <span class="well floatRight">
                            Go to <a href="<c:url value='/' />">Homepage</a>
                        </span>
                        <span class="well floatLeft">
                            <a href="##" onclick="goBack()">Try another answer</a>
                        </span>

                        <!--                        
                                            Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                             Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                              Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                               Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                                Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                             Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                              Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                               Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                                Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                             Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                              Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                                               Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem
                        -->
                    </div>
                </div>
                <!--                
                                <div class="alert alert-success lead">
                                                        
                                    Congratulations.
                                </div>
                                
                              
                -->




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
            function goBack() {
                window.history.back();
            }
        </script>