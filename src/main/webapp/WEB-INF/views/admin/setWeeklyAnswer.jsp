<%-- 
    Document   : setWeeklyAnswer
    Created on : Jun 22, 2017, 1:08:41 PM
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
                        <h2>Set Weekly Game Answer</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />
                       
                        <div id="feedback"></div>

                        <form:form modelAttribute="weeklyGame" class="form-horizontal form-label-left" id="setWeeklyGameAnswer-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />

                           
                              <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Week No<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="text" class="form-control" readonly="readonly" placeholder="Read-Only Input" value="${weekNo}">
                                        <form:hidden path="weekNo" id="weekNo" class="form-control" name="weekNo"  value="${weekNo}" />
                                    </div>
                                </div>   
                            
                               <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Category<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="text" class="form-control" readonly="readonly" placeholder="Read-Only Input" value="${gameCategoryName}">
                                        <form:hidden path="gameCategory" id="gameCategory" class="form-control" name="gameCategory"  value="${gameCategory}" />
                                    </div>
                                </div>    
                                    
                                  <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Date Created<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="text" class="form-control" readonly="readonly" placeholder="Read-Only Input" value="${weeklyGame.createdDate}">
                                        <form:hidden path="createdDate" id="createdDate" class="form-control" name="createdDate"  value="${createdDate}" />
                                    </div>
                                </div>           
                                    
                             <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Prize Of Winners<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="text" class="form-control" readonly="readonly" placeholder="Read-Only Input" value="${weeklyGame.prizeOfWinners}">
                                        <form:hidden path="prizeOfWinners" id="prizeOfWinners" class="form-control" name="prizeOfWinners"  value="${prizeOfWinners}" />
                                    </div>
                                </div>               

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Week Answer<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="gameAnswer" id="gameAnswer" type="text" class="form-control" name="gameName" placeholder="Input game Name" required ="required" />                                  
                                </div>
                            </div>

                            <form:input path="createdBy" name="createdBy" value="${loggedinuser}" type="hidden" /> 
                            <div class="ln_solid"></div>


                            <div class="form-group">
                                <div class="form-group">
                                    <div class="col-md-9 col-md-offset-3">
                                        <button type="reset" class="btn btn-primary">Cancel</button>
                                        <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
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

                $("#setWeeklyGameAnswer-form").submit(function (event) {
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
                var gameAnswer = $('#gameAnswer').val();

                var createdBy = $('#createdBy').val();

                // set a variable
                var gameExpiryDate = new Date();
                console.log("gameExpiryDate ::" + gameExpiryDate);


                var json = {
                    "id": id,
                    "gameAnswer": gameAnswer,
                    "createdBy": createdBy
                };

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "${pageContext.request.contextPath}/api/weeklygames/setanswer",
                    data: JSON.stringify(json),
                    dataType: 'json',
                    timeout: 100000,
                    success: function (data) {
                        console.log("SUCCESS: ", data);
                        //  display(data);
                        //   notify(data);
                        notification("Notification", "Answer added successfully.", "success");
                        //window.location = "/admin/listWeeklyGames";
                        location.href = "<%=request.getContextPath()%>/admin/listWeeklyGames";
                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                        //  display(e);
                        notification("Notification", "Failed to set answer game.", "error");
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
            
              function notification(title, text, type) {

                new PNotify({
                title: title,
                        text: text,
                        type: type,
                        styling: 'bootstrap3'
                });
                }


        </script>
