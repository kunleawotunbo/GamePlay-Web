<%-- 
    Document   : about
    Created on : Aug 14, 2017, 9:11:54 AM
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
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-3"><span class="required"></span></label>
                    <div class="col-md-9 col-sm-9 col-xs-9">                                            
                        <p class="form-control-static">This is about page.</p>
                    </div>     
                </div>



                <!--Include bottomadverts-->
                <%@ include file="includes/outside/bottomadverts.jsp" %>
            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->


        <!--Center content ends-->       

        <!--Inculde outer sidebar-->
        <%@ include file="includes/outside/sidebar.jsp" %>

        <!--Iniclude outside footer-->
        <%@ include file="includes/outside/footer.jsp" %>



