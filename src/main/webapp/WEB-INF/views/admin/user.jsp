<%-- 
    Document   : user
    Created on : Jul 9, 2017, 10:11:10 PM
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

                        <form:form method="POST" modelAttribute="user" class="form-horizontal" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">First Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="firstName" id="firstName" type="text" class="form-control" name="gameName" placeholder="First Name" required ="required" />                                  
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Last Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="lastName" id="lastName" type="text" class="form-control" name="lastName" placeholder="Last Name" required ="required" />                                 
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Email<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="email" id="email" name="email" type="text" class="form-control"  placeholder="Email" required ="required" />                                 
                                </div>
                            </div>    


                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Phone Number<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="phoneNumber" id="phoneNumber" name="phoneNumber"  type="number"  class="form-control" required ="required" />                                 
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Roles<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" required ="required"/>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Password<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="password" id="password" type="password" class="form-control" name="password" placeholder="" required ="required" />                                 
                                </div>
                            </div>   

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Confirm Password<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <%--<form:input  id="confirm_password" type="password" class="form-control" name="confirm_password" placeholder="" required ="required" />--%>     
                                    <input  id="confirm_password" type="password" class="form-control" name="confirm_password" placeholder="" required ="required" />
                                    <span id='message'></span>
                                </div>

                            </div>   



                            <form:input path="userName" name="userName"  type="hidden" /> 
                            <form:input path="createdBy" name="createdBy" value="${loggedinuser}" type="hidden" /> 
                            <div class="ln_solid"></div>

                            <%--
                                <div class="row">
                                    <div class="form-actions floatRight">
                                        <c:choose>
                                            <c:when test="${edit}">
                                                <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="submit" value="Register" onclick="return submitUserForm();" class ="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                
                            --%>
                            <div class="form-group">
                                <c:choose>
                                    <c:when test="${edit}">

                                        <div class="form-group">
                                            <div class="col-md-9 col-md-offset-3">
                                                <button type="reset" class="btn btn-primary">Cancel</button>
                                                <input type="submit" bth-search value="Update" class="btn btn-success "/> 

                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-group">
                                            <div class="col-md-9 col-md-offset-3">
                                                <button type="reset" class="btn btn-primary">Cancel</button>
                                                <input type="submit" value="Register" onclick="return submitUserForm();" class ="btn btn-success"/>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>



        <%@ include file="../includes/footer.jsp" %>


        <script type="text/javascript">
            function submitUserForm() {

                var password = $('#password').val();
                var confirm_password = $('#confirm_password').val();
                if (password != confirm_password) {
                    alert('Password does not match');
                    $('#confirm_password').focus();
                    return false;
                }

                /*
                 $('#password, #confirm_password').on('keyup', function () {
                 if ($('#password').val() == $('#confirm_password').val()) {
                 $('#message').html('Matching').css('color', 'green');
                 } else
                 $('#message').html('Not Matching').css('color', 'red');
                 });
                 
                 var password = $('#password').val();
                 var confirm_password = $('#confirm_password').val();
                 if (password != confirm_password) {
                 alert('Please enter name');
                 $('#confirm_password').focus();
                 return false;
                 }
                 
                 */

            }


        </script>

        <script>
            // Date time picker
            $(function () {
                //  var temp = $(this).datepicker('getDate');
                // var d = new Date(temp);
                // d.setDate(d.getDate() + 1);
                $('#gameExpiryDate').datetimepicker({
                    //autoclose: true,
                    //format: 'dd/mm/yyyy',
                    // startDate: d
                });
            });


            $('#password, #confirm_password').on('keyup', function () {
                if ($('#password').val() == $('#confirm_password').val()) {
                    $('#message').html('Matching').css('color', 'green');
                } else
                    $('#message').html('Not Matching').css('color', 'red');
            });

            jQuery(document).ready(function ($) {

                // success alert
                $("#success-alert").fadeTo(2000, 500).slideUp(500, function () {
                    $("#success-alert").slideUp(500);
                });

                // error alert
                $("#success-error").fadeTo(2000, 500).slideUp(500, function () {
                    $("#success-error").slideUp(500);
                });
                $("#user-form").submit(function (event) {
                    //var formData = $('addGame-form').serialize();
                    // Disble the search button
                    enableSearchButton(false);
                    // Prevent the form from submitting via the browser.
                    event.preventDefault();
                    searchViaAjax();
                });
            });
            function searchViaAjax() {
                var id = $('#id').val();
                var firstName = $('#firstName').val();
                var lastName = $('#lastName').val();
                var email = $('#email').val();
                var phoneNumber = $('#phoneNumber').val();
                var createdBy = $('#createdBy').val();
                var json = {
                    "id": id,
                    "firstName": firstName,
                    "lastName": lastName,
                    "email": email,
                    "phoneNumber": phoneNumber,
                    "createdBy": createdBy
                };
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "${pageContext.request.contextPath}/admin/register/",
                    data: JSON.stringify(json),
                    dataType: 'json',
                    timeout: 100000,
                    success: function (data) {
                        console.log("SUCCESS: ", data);
                        //  display(data);
                        notification("Notification", "User created successfully.", "success");
                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                        //  display(e);
                        notification("Notification", "Failed to create user", "error");
                    },
                    done: function (e) {
                        console.log("DONE");
                        enableSearchButton(true);
                    }
                });
                $("#user-form")[0].reset();
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


