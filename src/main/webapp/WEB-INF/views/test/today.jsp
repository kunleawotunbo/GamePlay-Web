<%-- 
    Document   : today
    Created on : Jun 22, 2017, 11:04:50 PM
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
                        <h2>Set Weekly Game Answer</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />
                        <div class="bs-callout bs-callout-warning hidden">
                            <h4>Oh snap!</h4>
                            <p>This form seems to be invalid :(</p>
                        </div>

                        <div class="bs-callout bs-callout-info hidden">
                            <h4>Yay!</h4>
                            <p>Everything seems to be ok :)</p>
                        </div>

                        <form:form modelAttribute="weeklyGame" class="form-horizontal form-label-left" id="setWeeklyGameAnswer-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />                            


                            <div class="form-group">  
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Week No<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="weekNo" id="weekNo" type="text" class="form-control" name="weekNo" value="${weekNo}" required ="required" readonly="readonly" />                                  
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Answer <span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:textarea path="gameAnswer" id="gameAnswer" name="gameAnswer" type="textarea" rows="3" class="form-control"  placeholder="Game Answer" />                                 
                                    </div>
                                </div>

                                <form:input path="createdBy" name="createdBy" value="${loggedinuser}" type="hidden" /> 
                                
                                <div class="ln_solid"></div>


                                <div class="form-group">
                                    <div class="form-group">
                                        <div class="col-md-9 col-md-offset-3">
                                            <button type="reset" class="btn btn-primary">Cancel</button>
                                            <button type="submit" id="bth-submit" class="btn btn-success">Submit</button>
                                        </div>
                                    </div>
                                    <%--                                    
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
                                                    <button type="submit" id="bth-submit" class="btn btn-success">Submit</button>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                               
                                    --%>
                                </div>

                            </form:form>    

                        </div>
                    </div>
                </div>
                <!-- /form input mask -->

            </div>


            <%@ include file="../includes/footer.jsp" %>

            <script>

                $("#setWeeklyGameAnswer-form").submit(function (event) {
                //var formData = $('addGame-form').serialize();
                // Disble the search button
                enableSearchButton(false);
                        // Prevent the form from submitting via the browser.
                        event.preventDefault();
                        searchViaAjax();
                });
                }
                );
                function searchViaAjax() {

                    var id = $('#id').val();
                    var gameAnswer = $('#gameAnswer').val();
                    var createdBy = "test user";
                    var json = {
                        "id": id,
                        "gameAnswer": gameAnswer,
                        "createdBy": createdBy

                    };
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "${pageContext.request.contextPath}/api/weeklygamesanswers/setanswer",
                        data: JSON.stringify(json),
                        dataType: 'json',
                        timeout: 100000,
                        success: function (data) {
                            console.log("SUCCESS: ", data);
                            display(data);
                        },
                        error: function (e) {
                            console.log("ERROR: ", e);
                            display(e);
                        },
                        done: function (e) {
                            console.log("DONE");
                            enableSearchButton(true);
                        }
                    });
                    $("#setWeeklyGameAnswer-form")[0].reset();
                }

                function enableSearchButton(flag) {
                    $("#btn-submit").prop("disabled", flag);
                }

                function display(data) {
                    var json = "<h4>Ajax Response</h4><pre>"
                            + JSON.stringify(data, null, 4) + "</pre>";
                    $('#feedback').html(json);
                }
            </script>

