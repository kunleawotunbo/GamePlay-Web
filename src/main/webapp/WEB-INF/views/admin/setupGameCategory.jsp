<%-- 
    Document   : setupGameCategory
    Created on : 15-Jul-2017, 21:36:38
    Author     : BELLO
--%>
<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Game Setup</h3>
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
        
         <div class="row">
            <!-- form input mask -->
            <!--              <div class="col-md-6 col-sm-12 col-xs-12">--> 
            <div class="col-md-10 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Game List</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>S/N</th>
                                    <th>GAME NAME</th>
                                    <th>GAME CODE</th>
                                   
                                    <th></th>
                                    

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${gameList}" var="item">  
                                    <tr>  
                                        <td><c:out value="${item.id}"/></td>  
                                        <td><c:out value="${item.gameName}"/></td>  
                                        
                                        <td>
                                            <a href="<c:url value='/admin/setup-gameType-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Setup
                                            </a>
                                        </td>
                                        
                                    </tr>  
                                </c:forEach>  
                            </tbody>
                        </table>


                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>       
    <%@ include file="../includes/footer.jsp" %>
