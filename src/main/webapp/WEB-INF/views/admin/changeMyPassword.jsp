<%-- 
    Document   : changeMyPassword
    Created on : Aug 10, 2017, 1:04:04 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>User</h3>
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
         
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Change Password</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <div id="feedback"></div>

                        <form:form  class="form-horizontal form-label-left" id="changeMyPassword-form" data-parsley-validate="">

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Current Password<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input type="password" class="form-control" placeholder="password" id="oldpass" name="oldpassword" required="" />
                                </div>
                            </div> 

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">New Password<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input type="password" class="form-control" placeholder="New password" id="pass" name="password" required="" />
                                </div>
                            </div> 

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Retype New Password<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input type="password" class="form-control" placeholder="Confirm new password" id="passConfirm" required="" />
                                </div>
                            </div> 
                            <span id="error" style="display:none">Password mismatch</span>

                            <input  class="form-control" name="createdBy" id="createdBy"  value="${loggedinuser}" type="hidden" />


                            <%--<form:input path="createdBy" name="createdBy" value="${loggedinuser}" type="hidden" />--%> 
                            <div class="ln_solid"></div>


                            <div class="form-group">
                                <div class="form-group">
                                    <div class="col-md-9 col-md-offset-3">
                                        <button type="reset" class="btn btn-primary">Cancel</button>
                                        <!-- <button type="submit" id="bth-submit"  class="btn btn-success" onclick="savePass()"> Change Password</button>-->
                                        <button type="submit" id="bth-submit"  class="btn btn-success" > Change Password</button>
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

        <script>
       
            jQuery(document).ready(function ($) {

                $("#changeMyPassword-form").submit(function (event) {
                    //var formData = $('addGame-form').serialize();
                    // Disble the search button
                    enableSearchButton(false);

                    // Prevent the form from submitting via the browser.
                    event.preventDefault();

                    searchViaAjax();

                });

            

            });

            function searchViaAjax() {


                var createdBy = $('#createdBy').val();
                var pass = $("#pass").val();
                var valid = pass == $("#passConfirm").val();
                var oldpassword = $("#oldpass").val();
                var userEmail = $("#createdBy").val();
                if (!valid) {
                    $("#error").show();
                    return;
                }



                var json = {
                    "newPassword": pass,
                    
                    "oldPassword": oldpassword,
                   // "createdBy": createdBy
                };

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                   // url: "${pageContext.request.contextPath}/api/weeklygames/doPasschange",
                    url: "${pageContext.request.contextPath}/admin/changeMyPassword",
                            
                   
                    data: JSON.stringify(json),
                    dataType: 'json',
                    timeout: 100000,
                    success: function (data) {
                        console.log("SUCCESS: ", data);
                        //  display(data);
                        //   notify(data);
                        notification("Notification", "Password changed successfully.", "success");
                        //window.location = "/admin/listWeeklyGames";
                        //location.href = "<%=request.getContextPath()%>/admin/listWeeklyGames";
                        location.href = "<%=request.getContextPath()%>/login";
                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                        //  display(e);
                        notification("Notification", "Failed to change password.", "error");
                    },
                    done: function (e) {
                        console.log("DONE");
                        enableSearchButton(true);
                    }
                });

                $("#changeMyPassword-form")[0].reset();

            }

            function enableSearchButton(flag) {
                $("#btn-submit").prop("disabled", flag);
            }

            function display(data) {
                var json = "<h4>Ajax Response</h4><pre>"
                        + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);
            }

            function notification(title, text, type) {

                new PNotify({
                    title: title,
                    text: text,
                    type: type,
                    styling: 'bootstrap3'
                });
            }


        </script>
