<%-- 
    Document   : gamepredictiondurationreportstart
    Created on : 25-Sep-2017, 09:24:52
    Author     : BELLO
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
             <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>All Time Weekly Games Random Winners List </h2> 

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                       <form:form modelAttribute="weeklyGame" method="POST" enctype="multipart/form-data" class="form-horizontal form-label-left" id="addWeeklyGame-form" data-parsley-validate="">  
                            <form:hidden path="id" id="id" name="id" />
                            
                             <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Start Date<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="gameStartDate" id="gameStartDate" name="gameStartDate" type="text" class="form-control"  placeholder="Game Start date" required ="required" />                                 
                                    </div>
                                </div>    

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Expiry Date<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="gameExpiryDate" id="gameExpiryDate" name="gameExpiryDate" type="text" class="form-control"  placeholder="Game Expiry date" required ="required" />                                 
                                    </div>
                                </div>
                                 <div class="form-group">
                                                <div class="col-md-9 col-md-offset-3">
                                                    <button type="reset" class="btn btn-primary">Cancel</button>
                                                    <!--<button type="submit" id="bth-submit" class="btn btn-success">Submit</button>-->
                                                    <input type="submit" id="bth-submit" class="btn btn-success" value="Submit" onclick="return submitForm();"/> 
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

                $("#gameCategory-form").submit(function (event) {
                    var formData = $('addGame-form').serialize();
                    // Disble the search button
                    enableSearchButton(false);

                    // Prevent the form from submitting via the browser.
                    event.preventDefault();

                    searchViaAjax();

                });

            });

            function searchViaAjax() {
                /*
                 var search = {}
                 search["gameName"] = $("#gameName").val();
                 search["gameCode"] = $("#gameCode").val();
                 // search["createdBy"] = $("#createdBy").val();
                 search["createdBy"] = "test user";
                 //  search["enabled"] = $("#enabled").val();
                 */
                var id = $('#id').val();
                var gameName = $('#gameName').val();
                var gameCode = $('#gameCode').val();
                var createdBy = "test user";
                var enabled = $("#enabled").is(":checked");


                if (enabled) {
                    enabled = 1;
                } else {
                    enabled = 0;
                }
                console.log("enabled ", enabled);

                var json = {
                    "id": id,
                    "gameName": gameName,
                    "gameCode": gameCode,
                    "createdBy": createdBy,
                    "enabled": enabled

                };

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "${pageContext.request.contextPath}/api/game/create",
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
                        jQuery("#submitResponse").css("display", "none");
                    },
                    done: function (e) {
                        console.log("DONE");
                        enableSearchButton(true);
                    }
                    
                    $("#gameCategory-form")[0].reset();
                });

            }

            function enableSearchButton(flag) {
                $("#btn-search").prop("disabled", flag);
            }

            function display(data) {
                var json = "<h4>Ajax Response</h4><pre>"
                        + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);
            }
        </script>


