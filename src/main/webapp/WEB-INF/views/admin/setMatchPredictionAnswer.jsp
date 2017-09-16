<%-- 
    Document   : setMatchPredictionAnswer
    Created on : Sep 14, 2017, 3:27:21 PM
    Author     : Olakunle Awotunbo
--%>


<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Match Prediction</h3>
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
                        <h2>Set Answer for Match Between <strong>${matchPrediction.homeTeamName} - ${matchPrediction.awayTeamName}</strong></h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <div id="feedback"></div>

                        <form:form modelAttribute="matchPrediction" class="form-horizontal form-label-left" id="setMatchPredictionAnswer-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />


                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Week No<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input type="text" class="form-control" readonly="readonly" placeholder="Read-Only Input" value="${matchPrediction.weekNo}">
                                    <form:hidden path="weekNo" id="weekNo" class="form-control" name="weekNo"  value="${matchPrediction.weekNo}" />
                                </div>
                            </div>                               


                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Match<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <p class="form-control-static"><strong>${matchPrediction.homeTeamName} - ${matchPrediction.awayTeamName}</strong></p>
                                </div>
                            </div>           

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Prize Of Winners<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input type="text" class="form-control" readonly="readonly" placeholder="Read-Only Input" value="${matchPrediction.prizeOfWinners}">
                                    <form:hidden path="prizeOfWinners" id="prizeOfWinners" class="form-control" name="prizeOfWinners"  value="${prizeOfWinners}" />
                                </div>
                            </div>               

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Home Score<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input type="text" class="form-control" id="homeScore" required="required">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Away Score<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input type="text" class="form-control" id="awayScore" required="required">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Match Winner<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <!--<input type="text" class="form-control"  id="winner" required="required">-->

                                    <!--                               
                                        <select class="form-control" name="winner" >
                                        <option value="0"> Select Match Outcome</option>
                                        <option value="1">Home Win - 1</option>
                                        <option value="X">Draw - X </option>
                                        <option value="2">Away Win - 2</option>

                                    </select>-->

                                    <div class="form-group">
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                        <!--
                                        <input type="radio" class="flat" name="winner" value="1"> <strong> Home Win </strong>
                                            &nbsp;
                                            <input type="radio" class="flat" name="winner" value="X"> <strong> Draw </strong>
                                            &nbsp;
                                            <input type="radio" class="flat" name="winner" value="2"> <strong>  Away Win </strong>
                                        -->
                                        <input type="radio" name="winner" value="1"> <strong> Home Win </strong>
                                            &nbsp;
                                            <input type="radio"  name="winner" value="X"> <strong> Draw </strong>
                                            &nbsp;
                                            <input type="radio"  name="winner" value="2"> <strong>  Away Win </strong>

                                        </div>
                                    </div>


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



            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Current Answer for this Match</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <div id="feedback"></div>
                        <form id="matchPredictionResult" data-parsley-validate class="form-horizontal form-label-left">
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Match Result<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <p class="form-control-static">
                                        <strong>${matchPrediction.homeTeamName} 
                                            ${matchPredictionResult.homeScore} - ${matchPredictionResult.awayScore} 
                                            ${matchPrediction.awayTeamName}
                                        </strong>
                                    </p>
                                </div>
                            </div>  
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Winner<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <p class="form-control-static">
                                        <strong>${matchPredictionResult.winner}</strong>
                                    </p>
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

                $("#setMatchPredictionAnswer-form").submit(function (event) {

                    // Disble the search button
                    enableSearchButton(false);

                    // Prevent the form from submitting via the browser.
                    event.preventDefault();

                    searchViaAjax();

                });

            });

            function searchViaAjax() {


                var id = $('#id').val();
                var homeScore = $('#homeScore').val();
                var awayScore = $('#awayScore').val();
                var weekNo = $('#weekNo').val();
                var createdBy = $('#createdBy').val();               
                var winner = $("input[name='winner']:checked").val();
                //var matchResult = '' + homeScore ' - ' + '' + awayScore;
                //alert("Winner :: " + winner);

              
                

                var json = {
                    "id": id,
                    "homeScore": homeScore,
                    "awayScore": awayScore,
                    "createdBy": createdBy,
                     "winner" : winner,
                     "weekNo": weekNo
                };

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "${pageContext.request.contextPath}/api/weeklygames/setUserAnswerForMatcPrediction",
                    data: JSON.stringify(json),
                    dataType: 'json',
                    timeout: 100000,
                    success: function (data) {
                        console.log("SUCCESS: ", data);
                        //  display(data);
                        //   notify(data);
                        notification("Notification", "Answer added successfully.", "success");
                        //window.location = "/admin/listWeeklyGames";
                        location.href = "<%=request.getContextPath()%>/admin/listMatchPredictions";
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

                $("#setMatchPredictionAnswer-form")[0].reset();

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
