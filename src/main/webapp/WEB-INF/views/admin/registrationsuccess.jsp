<%-- 
    Document   : registrationsuccess
    Created on : Jul 14, 2017, 7:18:39 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Weekly Games</h3>
            </div>

            <!--            <div class="title_right">
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
                        <!--<h2>Add Weekly Game</h2>-->

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />
                       

                        <div class="alert alert-success lead">
                            ${success}
                        </div>

                        <span class="well floatRight">
                            Go to <a href="<c:url value='/admin/adduser' />">Add new User</a>
                        </span>

                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>


        <%@ include file="../includes/footer.jsp" %>


