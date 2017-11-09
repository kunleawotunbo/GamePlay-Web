<%-- 
    Document   : setWeeklyAnswer
    Created on : Jun 22, 2017, 1:08:41 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<style type="text/css">
    .smallbox { 
        width: 50px;
        margin:  1px;
        display: inline-block;
        border: 3px solid powderblue;
    }
</style>
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

                            <input type="hidden" id="gamePlayType" name="gamePlayType" value="${weeklyGame.gamePlayType}">     
                            <div class="form-group" id="gameAnswer">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Answer<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="gameAnswer" id="gameAnswer" type="text" class="form-control" name="gameAnswer" placeholder="Set game answer here"  />                                  
                                </div>
                            </div>

                            <div class="form-group " id="gameTextJackpot">
                                <label for="userAnswer" class="control-label col-md-3 col-sm-3 col-xs-3">Game Answer </label>
                                <div class="col-md-9 col-sm-9 col-xs-9 ">           
                                    <c:forEach items="${numberList}" var="item" varStatus = "status">  
                                        <input name="gameTextJackpot${status.index + 1}" id="gameTextJackpot${status.index + 1}"  type="text" class="form-control smallbox"  maxlength="5" value="${item}"/> 

                                    </c:forEach>  
                                </div>
                            </div>          

                            <form:input path="createdBy" name="createdBy" value="${loggedinuser}" type="hidden" /> 
                             <input type="hidden" class="form-control" id="matchStarted" name="matchStarted"  value="${matchStarted}">
                            <div class="ln_solid"></div>


                            <div class="form-group">
                                <div class="form-group">
                                    <div class="col-md-9 col-md-offset-3">
                                        <button type="reset" class="btn btn-primary">Cancel</button>
                                        <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
                                        
                                    </div>
                                    <br>
                                    <br>
                                        <p id="cantPlay">${msg}</p>
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
                        <h2>Current Answer for this game</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <div id="feedback"></div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-3">Answer<span class="required">*</span></label>
                            <div class="col-md-9 col-sm-9 col-xs-9">
                                <input type="text" class="form-control" readonly="readonly" placeholder="" value="${gameAnswerObject.gameAnswer}">
                                <%--<form:hidden path="gameAnswer" id="gameAnswer" class="form-control" name="gameAnswer"  value="${gameAnswer}" />--%>
                            </div>
                        </div>   




                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>



        <%@ include file="../includes/footer.jsp" %>

        <script>

            jQuery(document).ready(function ($) {
                var gamePlayType = $('#gamePlayType').val();
                 $('#cantPlay').hide();                

                if (gamePlayType === "3") {
                    //console.log(" gamePlayType is Question ::" + gamePlayType);

                    // Since gameType is text question, then Hide gameImage div and remove required attribute

                    $('#gameAnswer').hide();
                    $("#gameAnswer").prop("required", false);

                    $('#gameTextJackpot').show();

                } else {
                    $('#gameTextJackpot').hide();
                    $("#gameTextJackpot").prop("required", false);

                    $('#gameAnswer').show();
                }

                var matchStarted = $('#matchStarted').val();

                console.log(" matchStarted :: " + matchStarted);

               // if (matchStarted === 'true') {
                     if (matchStarted === 'true') {

                    $('#cantPlay').show();
                    $("#bth-submit").prop("disabled", true);

                } else {
                    $("#bth-submit").prop("disabled", false);
                }
                
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
                // var gameAnswer = $('#gameAnswer').val();
                var gameAnswer;
                var createdBy = $('#createdBy').val();
                var gamePlayType = $('#gamePlayType').val();

                if (gamePlayType === "3") {
                    var gameTextJackpot1 = $('#gameTextJackpot1').val();
                    var gameTextJackpot2 = $('#gameTextJackpot2').val();
                    var gameTextJackpot3 = $('#gameTextJackpot3').val();
                    var gameTextJackpot4 = $('#gameTextJackpot4').val();
                    var gameTextJackpot5 = $('#gameTextJackpot5').val();
                    var gameTextJackpot6 = $('#gameTextJackpot6').val();
                    var gameTextJackpot7 = $('#gameTextJackpot7').val();
                    var gameTextJackpot8 = $('#gameTextJackpot8').val();

                    gameAnswer = "" + gameTextJackpot1 + " - " + gameTextJackpot2 + " - " + gameTextJackpot3
                            + " - " + gameTextJackpot4 + " - " + gameTextJackpot5 + " - " + gameTextJackpot6
                            + " - " + gameTextJackpot7 + " - " + gameTextJackpot8;


                } else {
                    gameAnswer = $('#gameAnswer').val();
                }

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
                        notification("Notification", "Answer added successfully, winners will be processed.", "success");
                        //window.location = "/admin/listWeeklyGames";
                        //location.href = "<%=request.getContextPath()%>/admin/listWeeklyGames";
                        // location.href = "<%=request.getContextPath()%>/admin/listWeeklyGames";
                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                        //  display(e);
                        //notification("Notification", "Failed to set answer game.", "error");
                        notification("Notification", e.responseJSON.message, "error");
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
