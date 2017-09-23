<%-- 
    Document   : addCountry
    Created on : Sep 20, 2017, 11:27:03 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Settings</h3>
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
                        <h2>Manage Country</h2>

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

                        <form:form modelAttribute="country" method="POST" class="form-horizontal form-label-left" id="country-form" data-parsley-validate="">  
                            <form:hidden path="id" id="id" name="id" />
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Country Code<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="countryName" id="countryName" type="text" class="form-control" name="countryName" placeholder="" required ="required" />                                  
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Country Code<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="countryCode" id="countryCode" type="text" class="form-control" name="countryCode" placeholder="Country Code" required ="required" />                                 
                                </div>
                            </div>

                            <form:input path="createdBy" name="createdBy" value="${loggedinuser}" type="hidden" /> 
                            <div class="ln_solid"></div>


                            <div class="form-group">
                                <c:choose>
                                    <c:when test="${edit}">

                                        <div class="form-group">
                                            <div class="col-md-9 col-md-offset-3">
                                                <button type="reset" class="btn btn-primary">Cancel</button>
                                                <input type="submit" bth-search value="Update" class="btn btn-success" /> 

                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-group">
                                            <div class="col-md-9 col-md-offset-3">
                                                <button type="reset" class="btn btn-primary">Cancel</button>
                                                <!--<button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>-->
                                                <input type="submit" id="bth-submit" class="btn btn-success" /> 
                                            </div>
                                        </div>                                    
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </form:form>
                    </div>



                <div>

               
            </div>
        </div>
        <!-- /form input mask -->

    </div>


    <div class="row">
        <!-- form input mask -->
        <!--              <div class="col-md-6 col-sm-12 col-xs-12">--> 
        <div class="col-md-10 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>List of Countries</h2>

                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <br />

                    <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>S/N</th>
                                <th>COUNTRY NAME</th>
                                <th>COUNTRY CODE</th>                                    
                                <th></th>
                                <th></th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${countriesList}" var="item">  
                                <tr>  
                                    <td><c:out value="${item.id}"/></td>  
                                    <td><c:out value="${item.countryName}"/></td>  
                                    <td><c:out value="${item.countryCode}"/></td>                                    
                                    <td>
                                        <a href="<c:url value='/admin/edit-country-${item.id}' />" class="btn btn-success custom-width">
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="<c:url value='/admin/delete-country-${item.id}' />" class="btn btn-danger custom-width">
                                            <i class="f fa fa-trash-o" aria-hidden="true"></i> Delete
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
