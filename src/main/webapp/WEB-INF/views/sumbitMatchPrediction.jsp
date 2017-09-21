<%-- 
    Document   : sumbitMatchPrediction
    Created on : Sep 13, 2017, 12:10:33 AM
    Author     : Olakunle Awotunbo
--%>

<!--Include outside header-->
<%@ include file="includes/outside/header.jsp" %>
<!--Include outside navigation-->
<%@ include file="includes/outside/navigation.jsp" %>

<!--intl-tel-input-->
<link href="<c:url value='/resources/css/intlTelInput.css' />"  rel="stylesheet"></link> 

<div class="container">
    <div class="row content">

        <div class="col-sm-9 ">


            <div class="container-fluid">    

                <div class="row">   


                    <!--Content starts-->

                    <br>
                    <div class="x_content">


                        <%--Match ::  <strong> ${matchPredictionObject.homeTeamName} - ${matchPredictionObject.awayTeamName} </strong>--%>


                        <form:form modelAttribute="matchPredictionAnswer" class="form-horizontal" id="matchPredictionAnswer-form">
                            <form:hidden path="gameId" value="${matchPredictionObject.id}" id="gameId" name="gameId" />
                            <form:hidden path="userAnswer" value="${selectedAnswer}" id="userAnswer" name="userAnswer" />
                            <form:hidden path="weekNo" value="${matchPredictionObject.weekNo}" id="weekNo" name="weekNo" />
                            <div class="form-group row text-left">
                                <label for="match" class="col-sm-3 form-control-label m-t-5">Match</label>
                                <div class="col-sm-9">
                                    <p> <strong> ${matchPredictionObject.homeTeamName} - ${matchPredictionObject.awayTeamName} </strong></p> 
                                </div>
                            </div>
                            <div class="form-group row text-left">
                                <label for="match" class="col-sm-3 form-control-label m-t-5">Your Answer</label>
                                <div class="col-sm-9">
                                    <p> <strong> ${userAnswer}  </strong></p> 
                                </div>
                            </div>
                            <div class="form-group row text-left">
                                <label for="phone-h-f" class="col-sm-3 form-control-label m-t-5">Phone Number</label>
                                <div class="col-sm-9">
                                    <input path="userPhoneNo"  id="userPhoneNo" name="userPhoneNo" type="tel" class="form-control" required ="required" placeholder="Enter your phone number"/>                          
                                    <p>Your phone number will be used to contact you if you win.</p> 
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="matchStarted" name="matchStarted"  value="${matchStarted}">
                            <input type="hidden" class="form-control" id="ipAddress" name="ipAddress"  >
                            <!--<p id="ipAddress">This is another paragraph.</p>-->

                            <div class="form-group">
                                <div class="col-md-9 col-md-offset-3">
                                    <button type="button" onclick="window.history.back()" class="btn btn-primary">Cancel</button>
                                    <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
                                    <p id="cantPlay">Match already started, you can't play the game. Please try another game</p>
                                </div>
                            </div>

                            <div class="form-group row text-left">

                            </div>
                        </form:form> 

                    </div>

                    <!--Content ends --> 

                    <!--Modal--> 


                </div>    

            </div><br>

            <!--Bottom Ads Starts -->               
            <div class="row">   
                <div class="container-fluid bg-3 text-center">    
                    <!--Include bottomadverts-->
                    <%@ include file="includes/outside/bottomadverts.jsp" %>

                </div>
            </div>

            <!--Bottom Ads Ends -->        

        </div>
        <!-- Side bar ads starts--> 
        <div class="col-sm-3 sidenav">
            <%@ include file="includes/outside/sidebar.jsp" %>                      
        </div>
        <!--Side bar ads ends --> 

    </div>
</div>

<!--Include outside footer-->
<%@ include file="includes/outside/footer.jsp" %>


<!--intlTelInput-->
<script src="<c:url value='/resources/js/intlTelInput.js' />"></script> 
<script src="<c:url value='/resources/js/utils.js' />"></script> 

<script>
                                        // https://github.com/jackocnr/intl-tel-input

                                        $("#userPhoneNo").intlTelInput({
                                            initialCountry: "auto",
                                            geoIpLookup: function (callback) {
                                                $.get('http://ipinfo.io', function () {}, "jsonp").always(function (resp) {
                                                    var countryCode = (resp && resp.country) ? resp.country : "";
                                                    callback(countryCode);
                                                });
                                            },

                                        });
</script>


<script>


    jQuery(document).ready(function ($) {
        $('#cantPlay').hide();
          $.get("http://ipinfo.io", function (response) {
            //alert(response.ip);
             //ipAddress = response.ip;
             //$("#ipAddress").html("" + response.ip);
             $("#ipAddress").val(response.ip);
        }, "jsonp");

        var matchStarted = $('#matchStarted').val();

        //console.log(" matchStarted :: " + matchStarted);

        if (matchStarted === 'true') {

            // disable button
            console.log(" matchStarted has started::" + matchStarted);
            // $("#bth-submit").button("enable");
            $('#cantPlay').show();
            $("#bth-submit").prop("disabled", true);

        } else {
            //console.log(" Mactch not  ::" + matchStarted);
            $("#bth-submit").prop("disabled", false);

        }

        $("#matchPredictionAnswer-form").submit(function (event) {
            //var formData = $('addGame-form').serialize();
            // Disble the search button
            enableSearchButton(false);

            // Prevent the form from submitting via the browser.
            event.preventDefault();

            submitViaAjax();

        });

    });

    function submitViaAjax() {


       
        
        var id = $('#id').val();
        //var userPhoneNo = $('#userPhoneNo').val();
        var userAnswer = $('#userAnswer').val();
        var gameId = $('#gameId').val();
        var userPhoneNo = $("#userPhoneNo").intlTelInput("getNumber");
        var countryData = $("#userPhoneNo").intlTelInput("getSelectedCountryData");
        var weekNo = $('#weekNo').val();
        var ipAddress = $('#ipAddress').val();
       

        // console.log("countryData  " + countryData);
        // console.log("countryData.name  " + countryData.name);
        // console.log("countryData.iso2s  " + countryData.iso2);
        // console.log("userAnswer:  " + userAnswer)

        // set a variable
        var gameExpiryDate = new Date();
        // console.log("userPhoneNo ::" + userPhoneNo);

        var json = {

            "userPhoneNo": userPhoneNo,
            "userAnswer": userAnswer,
            "gameId": gameId,
            "weekNo": weekNo,
            "ipAddress": ipAddress


        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/api/matchpredictionanswer/submitanswer",
            data: JSON.stringify(json),
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                //  display(data);
                //   notify(data);
                notification("Notification", "Congratulations your answer has been submitted.", "success");

                // Redirect to Match Prediction Congratulation page
                //window.location = 'congratulations.html';
                window.location = 'mpcongratulations';
                /*
                 if (noerrors) {
                 window.location = 'congratulations.html';
                 }
                 */
            },
            error: function (e) {
                console.log("ERROR: ", e);
                //  display(e);
                notification("Notification", "Unable to save your answer. Please try again later", "error");

            },
            done: function (e) {
                console.log("DONE");
                enableSearchButton(true);
            }
        });

        $("#matchPredictionAnswer-form")[0].reset();

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


