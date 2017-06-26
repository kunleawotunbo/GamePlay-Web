<%-- 
    Document   : gameSection
    Created on : Jun 21, 2017, 4:15:14 PM
    Author     : Olakunle Awotunbo
--%>


<!--Include outside header-->
<%@ include file="includes/outside/header.jsp" %>
<!--Include outside navigation-->
<%@ include file="includes/outside/navigation.jsp" %>

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>

            <div class="row">

                <form class="form-horizontal form-label-left">
                    <div class="form-group" id="gameText">
                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Text <span class="required">*</span></label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            ${weeklyGame.gameText}
                        </div>
                    </div>

                    <div class="form-group" id="gameImage" >
                        <label class="control-label col-md-3 col-sm-3 col-xs-3">GameImage<span class="required">*</span></label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            ${weeklyGame.gameImage}
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Week No<span class="required">*</span></label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            ${weeklyGame.weekNo}
                        </div>     
                    </div>


                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-3">No Of Winners<span class="required">*</span></label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            ${weeklyGame.noOfWinners}
                        </div>
                    </div>  


                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Expiry Date<span class="required">*</span></label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            ${weeklyGame.gameExpiryDate}
                        </div>
                    </div>                  


                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Rules <span class="required">*</span></label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            ${weeklyGame.gameRules}
                        </div>
                    </div>

                </form>

      
                <form:form modelAttribute="weeklyGamesAnswers" class="form-horizontal form-label-left" id="weeklyGamesAnswers-form" data-parsley-validate="">
                     <form:hidden path="gameId" value="${weeklyGame.id}" id="gameId" name="gameId" />
                    <div class="form-group" >
                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Phone No<span class="required">*</span></label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <form:input path="userPhoneNo"  id="userPhoneNo" name="userPhoneNo" type="number" class="form-control" required ="required" />                                 
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Answer To Question<span class="required">*</span></label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <form:input path="userAnswer" id="userAnswer" type="text" class="form-control" name="userAnswer" required ="required"  />         
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-9 col-md-offset-3">
                            <button type="reset" class="btn btn-primary">Cancel</button>
                            <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
                        </div>
                    </div>

                </form:form>    

                <!--Include bottomadverts-->
                <%@ include file="includes/outside/bottomadverts.jsp" %>
            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->


        <!--Center content ends-->       

        <!--Inculde outer sidebar-->
        <%@ include file="includes/outside/sidebar.jsp" %>

        <!--Iniclude outside footer-->
        <%@ include file="includes/outside/footer.jsp" %>


        <script>

            jQuery(document).ready(function ($) {

                $("#weeklyGamesAnswers-form").submit(function (event) {
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
                var userPhoneNo = $('#userPhoneNo').val();
                var userAnswer = $('#userAnswer').val();
                var gameId = $('#gameId').val();

                // set a variable
                var gameExpiryDate = new Date();
                console.log("userPhoneNo ::" + userPhoneNo);

                var json = {
                   
                    "userPhoneNo": userPhoneNo,
                    "userAnswer": userAnswer,
                    "gameId": gameId


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
                        //  display(data);
                        //   notify(data);

                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                        //  display(e);
                    },
                    done: function (e) {
                        console.log("DONE");
                        enableSearchButton(true);
                    }
                });

                $("#weeklyGamesAnswers-form")[0].reset();

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
