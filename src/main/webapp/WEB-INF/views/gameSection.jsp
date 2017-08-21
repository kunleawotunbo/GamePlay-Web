<%-- 
    Document   : gameSection
    Created on : Jun 21, 2017, 4:15:14 PM
    Author     : Olakunle Awotunbo
--%>


<!--Include outside header-->
<%@ include file="includes/outside/header.jsp" %>
<!--Include outside navigation-->
<%@ include file="includes/outside/navigation.jsp" %>

<!--intl-tel-input-->
<link href="<c:url value='/resources/css/intlTelInput.css' />"  rel="stylesheet"></link> 

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>

            <div class="row">

                <!--The choose tag checks if object weeklyGame is null-->
                <c:choose>
                    <c:when test="${empty weeklyGame}">  
                        <form class="form-horizontal form-label-left">
                            <!--<h2>No Game available for this category yet.<h2>-->
                             
                                     <img src="<c:url value='/resources/images/notavailable.png' />" alt="notavailable">
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3"><span class="required"></span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">                                            
                                            <p class="form-control-static">No Game available for this category yet.</p>
                                        </div>     
                                    </div>
                        </form>
<!--                        <h2>No Game available for this category yet.<h2>-->
                    </c:when>
                            <c:otherwise>                    

                                <form class="form-horizontal form-label-left">

                                    <div class="form-group">
                                        <c:choose>
                                            <c:when test="${isPicture}">

                                                <div class="form-group" id="gameImage" >
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">GameImage<span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <img src="data:image/jpeg;base64,${encodedPictureString}" alt="..."floatRight width="400" height="400">
                                                    </div>
                                                </div>
                                                    
                                                <div class="form-group" id="gameImage" >
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">GameImage 2<span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <img src="data:image/jpeg;base64,${encodedGameImage2}" alt="..."floatRight width="400" height="400">
                                                    </div>
                                                </div>    
                                            </c:when>
                                            <c:otherwise>

                                                <div class="form-group" id="gameText">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Text <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <p class="form-control-static">${weeklyGame.gameText}</p>
                                                    </div>
                                                </div>

                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Week No<span class="required">*</span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">                                            
                                            <p class="form-control-static">${weeklyGame.weekNo}</p>
                                        </div>     
                                    </div>

                                     <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Prize to Win<span class="required">*</span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <p class="form-control-static"> &#x20a6; ${weeklyGame.prizeOfWinners}</p>
                                        </div>
                                    </div>  

                                        
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">No Of Winners<span class="required">*</span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <p class="form-control-static">${weeklyGame.noOfWinners}</p>
                                        </div>
                                    </div>  


                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Expiry Date<span class="required">*</span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                           <p class="form-control-static"> ${weeklyGame.gameExpiryDate}</p>
                                        </div>
                                    </div>                  


                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Rules <span class="required">*</span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <p class="form-control-static"> ${weeklyGame.gameRules}</p>
                                        </div>
                                    </div>

                                </form>


                                <form:form modelAttribute="weeklyGamesAnswers" class="form-horizontal form-label-left" id="weeklyGamesAnswers-form" data-parsley-validate="">
                                    <form:hidden path="gameId" value="${weeklyGame.id}" id="gameId" name="gameId" />
                                    <form:hidden path="weekNo" value="${weeklyGame.weekNo}" id="weekNo" name="weekNo" />
                                    <%--              
                                                    <div class="form-group" >
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Phone No<span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <form:input path="userPhoneNo"  id="userPhoneNo" name="userPhoneNo" type="number" class="form-control" required ="required" />                                 
                                                    </div>
                                                </div>
                                    --%>

                                    <div class="form-group" >
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Phone No<span class="required">*</span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <form:input path="userPhoneNo"  id="userPhoneNo" name="userPhoneNo" type="tel" class="form-control" required ="required" placeholder="Enter your phone number"/>                                 
                                            <p>Your phone number will be used to contact you if you win.</p> 
                                        </div>
                                       
                                    </div>


                                    <!--<input type="tel" id="phone">-->

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Answer To Question<span class="required">*</span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <form:textarea path="userAnswer" id="userAnswer" name="userAnswer" type="textarea" class="form-control" rows="4"  placeholder="Input your answer to this question" required ="required" />                                 

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-md-9 col-md-offset-3">
                                            <button type="reset" class="btn btn-primary">Cancel</button>
                                            <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
                                        </div>
                                    </div>

                                </form:form> 

                            </c:otherwise>
                        </c:choose>       



                        <!--Include bottomadverts-->
                        <%@ include file="includes/outside/bottomadverts.jsp" %>
                        </div><!--/row-->
                        </div><!--/.col-xs-12.col-sm-9-->


                        <!--Center content ends-->       

                        <!--Inculde outer sidebar-->
                        <%@ include file="includes/outside/sidebar.jsp" %>

                        <!--Iniclude outside footer-->
                        <%@ include file="includes/outside/footer.jsp" %>

                        <!--intlTelInput-->
                        <script src="<c:url value='/resources/js/intlTelInput.js' />"></script> 
                        <script src="<c:url value='/resources/js/utils.js' />"></script> 

                        <script>
                            // https://github.com/jackocnr/intl-tel-input
//                                    $("#phone").intlTelInput();
                            //$("#userPhoneNo").intlTelInput();
                            $("#userPhoneNo").intlTelInput({
                                initialCountry: "auto",
                                geoIpLookup: function (callback) {
                                    $.get('http://ipinfo.io', function () {}, "jsonp").always(function (resp) {
                                        var countryCode = (resp && resp.country) ? resp.country : "";
                                        callback(countryCode);
                                    });
                                },
                                //utilsScript: "../../build/js/utils.js" // just for formatting/placeholders etc
                            });
                        </script>

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
                                //var userPhoneNo = $('#userPhoneNo').val();
                                var userAnswer = $('#userAnswer').val();
                                var gameId = $('#gameId').val();
                                var userPhoneNo = $("#userPhoneNo").intlTelInput("getNumber");
                                var countryData = $("#userPhoneNo").intlTelInput("getSelectedCountryData");
                                var weekNo = $('#weekNo').val();

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
                                    "weekNo": weekNo


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
                                        notification("Notification", "Congratulations your answer has been submitted.", "success");
                                        
                                        window.location = 'congratulations.html';
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

                            function notification(title, text, type) {

                                new PNotify({
                                    title: title,
                                    text: text,
                                    type: type,
                                    styling: 'bootstrap3'
                                });
                            }
                        </script>


