<%-- 
    Document   : addWeeklyGame
    Created on : Jun 16, 2017, 9:27:44 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Add Weekly Game</h3>
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
                        <!--<h2>Input Mask</h2>-->

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
                        <form class="form-horizontal form-label-left" id="weeklyGame-form" data-parsley-validate>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Type<span class="required">*</span></label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="gameCategory"  name="gameCategory" required>
                                        <option value="">Choose Game Type</option>>
                                        <c:forEach  var="gameCate" items="${gameList}" varStatus="status" >

                                            <option value="${gameCate.id}">${gameCate.gameName}</option>
                                        </c:forEach>
                                    </select>                                                          
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Type<span class="required">*</span></label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="gameType"  name="gameType" required>
                                        <option value="">Choose Game Category</option>>
                                        <c:forEach  var="gameType" items="${gamePlayTypeList}" varStatus="status" >

                                            <option value="${gameType.id}">${gameType.typeName}</option>
                                        </c:forEach>
                                    </select>                                                          
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Week No<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input type="text" class="form-control" readonly="readonly" value="${weekNo}" required>                               
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">prizeOfWinnerse<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input id="prizeOfWinners" type="number" class="form-control" name="prizeOfWinners" placeholder="Prize Of Winners" required >                                  
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">prizeOfWinnerse<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input id="prizeOfWinners" type="number" class="form-control" name="prizeOfWinners" placeholder="Prize Of Winners" required >                                  
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input id="gameName" type="text" class="form-control" name="gameName" placeholder="Input game Name" required >                                  
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Code<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input id="gameCode" type="text" class="form-control" name="gameCode" placeholder="Game Code" required >                                 
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Enabled</label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input id="enabled" name="enabled" type="checkbox"  data-toggle="toggle" >                                    
                                </div>
                            </div>


                            <input name="createdBy" value="${loggedinuser}" type="hidden"> </input>
                            <div class="ln_solid"></div>

                            <div class="form-group">
                                <div class="col-md-9 col-md-offset-3">
                                    <button type="submit" class="btn btn-primary">Cancel</button>
                                    <button type="submit" class="btn btn-success">Submit</button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>


        <%@ include file="../includes/footer.jsp" %>

        <script>




            jQuery(document).ready(function ($) {

                $("#search-form").submit(function (event) {
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
                    },
                    done: function (e) {
                        console.log("DONE");
                        enableSearchButton(true);
                    }
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

