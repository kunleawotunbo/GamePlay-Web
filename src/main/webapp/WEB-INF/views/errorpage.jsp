<%-- 
    Document   : errorpage
    Created on : Jun 22, 2017, 11:43:33 PM
    Author     : Olakunle Awotunbo
--%>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/sidebar-menu.jsp" %>
<%@ include file="includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Something went wrong</h3>
            </div>
            <!--
                        <div class="title_right">
                            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">Go!</button>
                                    </span>
                                </div>
                            </div>
                        </div>-->
        </div>

        <div class="clearfix"></div>

        <div class="row">
            <!-- form input mask -->
            <!--              <div class="col-md-6 col-sm-12 col-xs-12">--> 


            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <!--<h2>Input Mask</h2>-->

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />
                      <div class="x_title">
                        <!--<h2> Something went wrong</h2>-->

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <h1>${errorMsg}</h1>
                        <p>Please contact the administrator.</p>



              
                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>


    

        <%@ include file="includes/footer.jsp" %>             