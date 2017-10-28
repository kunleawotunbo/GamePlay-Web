<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : gameSectionJackpotNumber
    Created on : 27-Sep-2017, 15:28:04
    Author     : BELLO
--%>

<!--Include outside header-->
<%@ include file="includes/outside/header.jsp" %>
<!--Include outside navigation-->
<%@ include file="includes/outside/navigation.jsp" %>

<!--intl-tel-input-->
<link href="<c:url value='/resources/css/intlTelInput.css' />"  rel="stylesheet"></link> 

<style type="text/css">
    .smallbox { 
        width: 50px;
        margin:  1px;
        display: inline-block;
        border: 3px solid powderblue;
    }
</style>
<div class="container">
    <div class="row content">

        <div class="col-sm-9 text-left">


            <div class="container-fluid bg-3 text-center">    

                <div class="row"> 
                    <h2> You're Playing Jackpot Number Game </h2>
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
                            <br>

                            <form >

                                <div class="form-group">
                                    <c:choose>
                                        <c:when test="${isPicture}">

                                            <div class="form-group row text-left">
                                                <label for="GameImage1" class="col-sm-3 form-control-label m-t-5">GameImage</label>
                                                <div class="col-sm-9">
                                                    <img src="data:image/jpeg;base64,${encodedPictureString}" alt="..."floatRight width="400" height="400">
                                                </div>
                                            </div>                                           

                                            <c:if test="${hasGameImage2}">     

                                                <div class="form-group row text-left" id="gameImage">
                                                    <label for="gameImage" class="col-sm-3 form-control-label m-t-5">GameImage 2</label>
                                                    <div class="col-sm-9">
                                                        <img src="data:image/jpeg;base64,${encodedGameImage2}" alt="..."floatRight width="400" height="400">
                                                    </div>
                                                </div> 

                                            </c:if> 

                                        </c:when>
                                        <c:otherwise>

                                            <div class="form-group row text-left" id="gameText">
                                                <label for="gameImage" class="col-sm-3 form-control-label m-t-5">Game Text</label>
                                                <div class="col-sm-9">
                                                    <h1 class="text-center">   <p class="form-control-static">${weeklyGame.gameText}</p> </h1>
                                                </div>
                                            </div> 
                                            <input type="hidden" id="gameText" name="gameText" value="${weeklyGame.gameText}">                                                


                                        </c:otherwise>
                                    </c:choose>
                                </div>

                                <div class="form-group row text-left" id="weekNo">
                                    <label for="weekNo" class="col-sm-3 form-control-label m-t-5">Week No</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${weeklyGame.weekNo}</p>
                                    </div>
                                </div> 

                                <div class="form-group row text-left" id="prizeOfWinner">
                                    <label for="prizeOfWinner" class="col-sm-3 form-control-label m-t-5">Prize to Winner</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static"> &#x20a6; ${weeklyGame.prizeOfWinners}</p>
                                    </div>
                                </div>    

                                <div class="form-group row text-left" id="noOfWinners">
                                    <label for="noOfWinners" class="col-sm-3 form-control-label m-t-5">No Of Winners</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${weeklyGame.noOfWinners}</p>
                                    </div>
                                </div>  

                                <div class="form-group row text-left" id="gameExpiryDate">
                                    <label for="gameExpiryDate" class="col-sm-3 form-control-label m-t-5">Game Expiry Date</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${weeklyGame.gameExpiryDate}</p>
                                    </div>
                                </div>  

                                <div class="form-group row text-left" id="gameRules">
                                    <label for="gameRules" class="col-sm-3 form-control-label m-t-5">Game Rules </label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${weeklyGame.gameRules}</p>
                                    </div>
                                </div>  
                                <input type="hidden" id="gamePlayType" name="gamePlayType" value="${weeklyGame.gamePlayType}">


                            </form>


                            <form:form modelAttribute="weeklyGamesAnswers" class="form-horizontal form-label-left" id="weeklyGamesAnswers-form" data-parsley-validate="">
                                <form:hidden path="gameId" value="${weeklyGame.id}" id="gameId" name="gameId" />
                                <form:hidden path="weekNo" value="${weeklyGame.weekNo}" id="weekNo2" name="weekNo2" />
                                <form:hidden path="code" value="${weeklyGame.code}" id="code" name="code" />                               


                                <div class="form-group row text-left" id="userPhoneNo2">
                                    <label for="userPhoneNo" class="col-sm-3 form-control-label m-t-5">Phone No </label>
                                    <div class="col-sm-9">
                                        <form:input path="userPhoneNo"  id="userPhoneNo" name="userPhoneNo" type="tel" class="form-control" required ="required" placeholder="Enter your phone number"/>                                 
                                        <p>Your phone number will be used to contact you if you win.</p> 
                                    </div>
                                </div>  


                                <%--                             
                                    <div class="form-group row text-left" id="userAnswer">
                                    <label for="userAnswer" class="col-sm-3 form-control-label m-t-5">Answer To Question </label>
                                    <div class="col-sm-9">
                                        <form:textarea path="userAnswer" id="userAnswer2" name="userAnswer2" type="textarea" class="form-control" rows="4"  placeholder="Input your answer to this question" required ="required" />                                 

                                    </div>
                                </div> 
                                --%>

                                <!--                                <div class="form-group row text-left" id="gameTextJackpot">
                                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Text <span class="required">*</span></label>
                                                                    <div class="col-md-9 col-sm-9 col-xs-9 ">                                   
                                                                        <input name="gameTextJackpot1" id="gameTextJackpot1"  type="text" class="form-control smallbox"  maxlength="2" /> 
                                                                        <input name="gameTextJackpot2" id="gameTextJackpot2"  type="text" class="form-control smallbox" maxlength="2"  /> 
                                                                        <input name="gameTextJackpot3" id="gameTextJackpot3"  type="text" class="form-control smallbox"  maxlength="2" /> 
                                                                        <input name="gameTextJackpot4" id="gameTextJackpot4"  type="text" class="form-control smallbox" maxlength="2"  /> 
                                                                        <input name="gameTextJackpot5" id="gameTextJackpot5"  type="text" class="form-control smallbox" maxlength="2"  /> 
                                                                        <input name="gameTextJackpot6" id="gameTextJackpot6"  type="text" class="form-control smallbox"  maxlength="2" /> 
                                                                        <input name="gameTextJackpot7" id="gameTextJackpot7"  type="text" class="form-control smallbox" maxlength="2"  /> 
                                                                        <input name="gameTextJackpot8" id="gameTextJackpot8"  type="text" class="form-control smallbox" maxlength="2"  /> 
                                
                                                                    </div>
                                                                </div>        -->


                                <div class="form-group row text-left" id="gameTextJackpot">
                                    <!--<label class="control-label col-md-3 col-sm-3 col-xs-3">Game Text <span class="required">*</span></label>-->
                                     <label for="userAnswer" class="col-sm-3 form-control-label m-t-5">Answer To Question </label>
                                    <div class="col-md-9 col-sm-9 col-xs-9 ">           
                                        <c:forEach items="${numberList}" var="item" varStatus = "status">  
                                            <input name="gameTextJackpot${status.index + 1}" id="gameTextJackpot${status.index + 1}"  type="text" class="form-control smallbox"  maxlength="2" value="${item}"/> 
                                            <%--                                      
                                                <input name="gameTextJackpot1" id="gameTextJackpot1"  type="text" class="form-control smallbox"  maxlength="2" /> 
                                                <input name="gameTextJackpot2" id="gameTextJackpot2"  type="text" class="form-control smallbox" maxlength="2" value="${item}" /> 
                                                <input name="gameTextJackpot3" id="gameTextJackpot3"  type="text" class="form-control smallbox"  maxlength="2" value="${item}" /> 
                                                <input name="gameTextJackpot4" id="gameTextJackpot4"  type="text" class="form-control smallbox" maxlength="2" value="${item}" /> 
                                                <input name="gameTextJackpot5" id="gameTextJackpot5"  type="text" class="form-control smallbox" maxlength="2" value="${item}" /> 
                                                <input name="gameTextJackpot6" id="gameTextJackpot6"  type="text" class="form-control smallbox"  maxlength="2" value="${item}" /> 
                                                <input name="gameTextJackpot7" id="gameTextJackpot7"  type="text" class="form-control smallbox" maxlength="2" value="${item}" /> 
                                                <input name="gameTextJackpot8" id="gameTextJackpot8"  type="text" class="form-control smallbox" maxlength="2" value="${item}" /> 
                                            --%>
                                        </c:forEach>  
                                    </div>
                                </div>       


                                <input type="hidden" class="form-control" id="ipAddress" name="ipAddress"  >


                                <div class="form-group">
                                    <div class="col-md-9 col-md-offset-3">
                                        <button type="reset" class="btn btn-primary">Cancel</button>
                                        <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
                                    </div>
                                </div>

                            </form:form> 

                        </c:otherwise>
                    </c:choose>       

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
//                                    $("#phone").intlTelInput();
    //$("#userPhoneNo").intlTelInput();
    $("#userPhoneNo").intlTelInput({
        initialCountry: "auto",
        geoIpLookup: function (callback) {
            $.get('http://ipinfo.io', function () {}, "jsonp").always(function (resp) {
                var countryCode = (resp && resp.country) ? resp.country : "";
                $("#ipAddress").val(resp.ip);
                //callback(countryCode);                
            });
        },
        //utilsScript: "../../build/js/utils.js" // just for formatting/placeholders etc
    });
</script>

<script>

    jQuery(document).ready(function ($) {
        /*
         $.get("http://ipinfo.io", function (response) {
         //alert(response.ip);
         //ipAddress = response.ip;
         //$("#ipAddress").html("" + response.ip);
         $("#ipAddress").val(response.ip);
         }, "jsonp");
         */

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


        // var id = $('#id').val();
        //var userPhoneNo = $('#userPhoneNo').val();
       
        var gameId = $('#gameId').val();
        var userPhoneNo = $("#userPhoneNo").intlTelInput("getNumber");
        var countryData = $("#userPhoneNo").intlTelInput("getSelectedCountryData");
        var weekNo = $('#weekNo2').val();
        var code = $('#code').val();
        var countryName = countryData.name;
        var ipAddress = $('#ipAddress').val();

        var gameTextJackpot1 = $('#gameTextJackpot1').val();
        var gameTextJackpot2 = $('#gameTextJackpot2').val();
        var gameTextJackpot3 = $('#gameTextJackpot3').val();
        var gameTextJackpot4 = $('#gameTextJackpot4').val();
        var gameTextJackpot5 = $('#gameTextJackpot5').val();
        var gameTextJackpot6 = $('#gameTextJackpot6').val();
        var gameTextJackpot7 = $('#gameTextJackpot7').val();
        var gameTextJackpot8 = $('#gameTextJackpot8').val();
       
        var userAnswer = "" + gameTextJackpot1 + " - " + gameTextJackpot2 + " - " + gameTextJackpot3
                + " - " + gameTextJackpot4 + " - " + gameTextJackpot5 + " - " + gameTextJackpot6
                + " - " + gameTextJackpot7 + " - " + gameTextJackpot8;
        //alert("allTinyFields :: " + userAnswer);

        //userAnswer = $('#userAnswer2').val("" + allTinyFields);
        // var userAnswer = $('#userAnswer2').val();


        var json = {

            "userPhoneNo": userPhoneNo,
            "userAnswer": userAnswer,           
            "gameId": gameId,
            "weekNo": weekNo,
            "playersCountry": countryName,
            "ipAddress": ipAddress,
            "code": code

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


