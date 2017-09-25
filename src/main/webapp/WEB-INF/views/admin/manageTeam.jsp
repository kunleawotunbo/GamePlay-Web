<%-- 
    Document   : manageTeam
    Created on : Sep 23, 2017, 9:14:15 AM
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
                        <h2>Manage Team</h2>

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

                        <form:form modelAttribute="team" method="POST" class="form-horizontal form-label-left" id="country-form" data-parsley-validate="">  
                            <form:hidden path="id" id="id" name="id" />

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Country<span class="required">*</span></label>
                                <div class="col-sm-4">                                         
                                    <form:select id="countryCode" path="countryCode" title="Choose Country" class="form-control">
                                        <option value="">Choose Country</option>
                                        <form:options items="${countriesList}" itemValue="countryCode" itemLabel="countryName"/>
                                    </form:select>   
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">League<span class="required">*</span></label>
                                <div class="col-sm-4">                                         
                                    <form:select id="leagueCode" path="leagueCode" title="Choose League" class="form-control">
                                        <!--<option value="">Choose League</option>-->
                                        <%--<form:options items="${countriesList}" itemValue="leagueCode" itemLabel="countryName"/>--%>
                                        <%--<form:options  itemValue="leagueCode" itemLabel="countryName"/>--%>
                                        <form:option value="" label="Choose League" />
                                    </form:select>   
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Team Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="teamName" id="teamName" type="text" class="form-control" name="teamName" placeholder="" required ="required" />                                  
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Team Short Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="shortName" id="shortName" type="text" class="form-control" name="shortName" placeholder="" required ="required" />                                 
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Team Crest Url<span class="required"></span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="crestUrl" id="crestUrl" type="text" class="form-control" name="crestUrl" placeholder="" required ="required" />                                 
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
                                        <th>TEAM NAME</th>
                                        <th>COUNTRY CODE</th>  
                                        <th>LEAGUE CODE</th>
                                        <th></th>
                                        <th></th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${teamList}" var="item">  
                                        <tr>  
                                            <td><c:out value="${item.id}"/></td>  
                                            <td><c:out value="${item.teamName}"/></td>  
                                            <td><c:out value="${item.countryCode}"/></td>  
                                            <td><c:out value="${item.leagueCode}"/></td>  
                                            <td>
                                                <a href="<c:url value='/admin/edit-team-${item.id}' />" class="btn btn-success custom-width">
                                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit
                                                </a>
                                            </td>
                                            <td>
                                                <a href="<c:url value='/admin/delete-team-${item.id}' />" class="btn btn-danger custom-width">
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

            <script>

                /*
                 $(document).ready(function () {
                 $('#countryCode').change(
                 var countryCode = $('#countryCode').val();
                 console.log("countryCode :: " + countryCode);
                 //trying to reset values
                 function () {
                 //alert($(this).val());
                 $('#menuitemscontainer').html('&nbsp;');
                 $('#menuitemscontainer2').html('&nbsp;');
                 $.getJSON('getLeagueByCountry/' + countryCode, {
                 usergroup: $(this).val(),
                 ajax: 'true'
                 }, function (data) {
                 var html = '';
                 var html2 = '';
                 var html3 = '';
                 var html4 = '';
                 var itemCheck = '';
                 var itemDisabled = '';
                 var usergrpmdlsK = data.usergrpmdls;
                 var modulemenusK = data.modulemenus;
                 var reportmenusK = data.reportmenus;
                 var subscribedcoopmdlsK = data.subscribedcoopmdls;
                 }
                 });
                 )});
                 
                 */

                jQuery(document).ready(function ($) {


                    $("#countryCode").change(function (event) {
                        
                        $('#leagueCode').html("");
                        var countryCode = $('#countryCode').val();
                        console.log(" countryCode ::" + countryCode);




                        $.ajax({
                            type: "GET",
                            contentType: "application/json",
                            url: "${pageContext.request.contextPath}/admin/getLeagueByCountry/" + countryCode,
                            timeout: 600000,
                            success: function (data) {
                                console.log("SUCCESS: ", data);
                                //display(data);              
                                var records = data.result;
                               // console.log(records.length);
                                $.each(records, function (i, item) {

                                    $('#leagueCode')
                                            .append($("<option></option>")
                                                    .attr("value", item.leagueCode)
                                                    .text(item.leagueName));
                                });
                                //notification("Notification", "Weekly game added successfully.", "success");
                            },
                            error: function (e) {
                                console.log("ERROR: ", e);
                                //display(e);
                                // notification("Notification", "Failed to add Weekly game.", "error");
                            },
                            done: function (e) {
                                console.log("DONE");
                                enableSearchButton(true);
                            }
                        });








                    });
                    
                                    //var leagueCode = $("#leagueCode").val();
                                   // console.log("leagueCode :: " + leagueCode);
                                    //alert(leagueCode);

                });

            </script>
