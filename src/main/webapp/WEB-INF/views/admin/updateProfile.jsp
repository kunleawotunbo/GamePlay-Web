<%-- 
    Document   : updateProfile
    Created on : Aug 13, 2017, 3:21:07 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>User Management</h3>
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
                        <h2>Add User</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <c:if test="${error}">                                  
                            <div class="alert alert-danger" id="success-error">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>${message} </strong>
                            </div>
                        </c:if>  

                        <c:if test="${saved}">        
                            <div class="alert alert-success" id="success-alert">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>${message} </strong>
                            </div>
                        </c:if>  
                        <div id="feedback"></div>

                        <%--<form:form modelAttribute="user" class="form-horizontal form-label-left" id="user-form" data-parsley-validate="">--%>

                        <form:form  class="form-horizontal form-label-left" modelAttribute="userProfile" id="updateprofile-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">First Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="firstName" type="text" class="form-control" placeholder="firstName" id="firstName" name="firstName" required="" />
                                </div>
                            </div> 

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Last Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="lastName" type="text" class="form-control" placeholder="Last Name" id="lastName" name="lastName" required="" />
                                </div>
                            </div> 

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Phone No<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="phoneNumber"  type="number" class="form-control"  id="phoneNumber" required="" />
                                </div>
                            </div> 
                            <span id="error" style="display:none">Password mismatch</span>

                            <input  class="form-control" name="createdBy" value="${userObject.email}" type="hidden" />


                            <%--<form:input path="createdBy" name="createdBy" value="${loggedinuser}" type="hidden" />--%> 
                            <div class="ln_solid"></div>


                            <div class="form-group">
                                <div class="form-group">
                                    <div class="col-md-9 col-md-offset-3">
                                        <button type="reset" class="btn btn-primary">Cancel</button>
                                         <!--<button type="submit" id="updateprofile-bth-submit"  class="btn btn-success" onclick="updateprofile()"> Save Changes</button>-->
                                        <button type="submit" id="bth-submit"  class="btn btn-success" > Save Changes</button>
                                    </div>
                                </div>

                            </div>

                        </form:form>
                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>



        <%@ include file="../includes/footer.jsp" %>

