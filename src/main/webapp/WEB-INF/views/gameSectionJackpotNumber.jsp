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

                            <!--<form >-->
                            <form:form modelAttribute="weeklyGamesAnswers" class="form-horizontal form-label-left" id="weeklyGamesAnswers-form" data-parsley-validate="">
                                
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
                                            <input type="hidden" id="gameText" name="gameText" value="${weeklyGame.gameText}">

                                            <input type="hidden" id="gameId" name="gameText" value="${weeklyGame.id}">
                                            <div class="form-group row text-left" id="gameRules">
                                                <label for="gameRules" class="col-sm-3 form-control-label m-t-5">Game Rules </label>
                                                <div class="col-sm-9">
                                                    <p class="form-control-static">${weeklyGame.gameRules}</p>
                                                </div>
                                            </div>  

                                        </c:otherwise>
                                    </c:choose>
                                </div>

                                <div class="form-group row text-left" id="gameExpiryDate">
                                    <label for="gameExpiryDate" class="col-sm-3 form-control-label m-t-5">Game Expiry Date</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${weeklyGame.gameExpiryDate}</p>
                                    </div>
                                </div>  


                                <div class="form-group row text-left" id="gameTextJackpot">
                                    <!--<label class="control-label col-md-3 col-sm-3 col-xs-3">Game Text <span class="required">*</span></label>-->
                                    <label for="userAnswer" class="col-sm-3 form-control-label m-t-5">Answer To Question </label>
                                    <div class="col-md-9 col-sm-9 col-xs-9 ">           
                                        <c:forEach items="${numberList}" var="item" varStatus = "status">  
                                            <input name="gameTextJackpot${status.index + 1}" id="gameTextJackpot${status.index + 1}"  type="text" class="form-control smallbox"  maxlength="2" value="${item}" required="required"/> 

                                        </c:forEach>  
                                    </div>
                                </div>   


                                <div class="form-group row text-left" id="userPhoneNo2">
                                    <label for="userPhoneNo" class="col-sm-3 form-control-label m-t-5">Phone No </label>
                                    <div class="col-sm-9">
                                        <p>Choose your country flag.</p>
                                        <form:input path="userPhoneNo"  id="userPhoneNo" name="userPhoneNo" type="tel" class="form-control" required ="required" placeholder="Enter your phone number"/>                                 
                                        <p>Your phone number will be used to contact you if you win.</p> 
                                    </div>
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




                                <input type="hidden" id="gamePlayType" name="gamePlayType" value="${weeklyGame.gamePlayType}">
                                <input type="hidden" class="form-control" id="matchStarted" name="matchStarted"  value="${matchStarted}">

                                <!--</form>-->


                                <%--<form:form modelAttribute="weeklyGamesAnswers" class="form-horizontal form-label-left" id="weeklyGamesAnswers-form" data-parsley-validate="">--%>
                                <form:hidden path="gameId" value="${weeklyGame.id}" id="gameId" name="gameId" />
                                <form:hidden path="weekNo" value="${weeklyGame.weekNo}" id="weekNo2" name="weekNo2" />
                                <form:hidden path="code" value="${weeklyGame.code}" id="code" name="code" />                               





                                <input type="hidden" class="form-control" id="ipAddress" name="ipAddress"  >


                                <div class="form-group">
                                    <div class="col-md-9 col-md-offset-3">
                                        <!--<button type="reset" class="btn btn-primary">Cancel</button>-->
                                        <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
                                        <p id="cantPlay">${msg}</p>
                                    </div>
                                </div>

                            </form:form> 

                        </c:otherwise>
                    </c:choose>       

                </div>    

                <ul class="pagination  pagination-lg">
                    <c:forEach items="${weeklyGameList}" var="item" varStatus = "status">                      
                        <li><a id="${item.id}" href="<c:url value='/gameSectionx-${item.id}-${gameCode}' />">${status.index + 1}</a></li>
                        <!--<li class="active"><a href="#">2</a></li>-->
                    </c:forEach>  
                </ul>  

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
    /* https://github.com/jackocnr/intl-tel-input */
//                                    $("#phone").intlTelInput();
    $("#userPhoneNo").intlTelInput();
    /*
     $("#userPhoneNo").intlTelInput({
     initialCountry: "auto",
     
     geoIpLookup: function (callback) {
     $.get('http://ipinfo.io', function () {}, "jsonp").always(function (resp) {
     var countryCode = (resp && resp.country) ? resp.country : "";
     $("#ipAddress").val(resp.ip);
     callback(countryCode);                
     });
     },
     
     //utilsScript: "../../build/js/utils.js" // just for formatting/placeholders etc
     });
     */

</script>

<script>

    jQuery(document).ready(function ($) {
        //alert("I am here 1 " );
        var gameId = $('#gameId').val();

        // $('#' + gameId).addClass('active');
        //$('#' + gameId).hide();
        $('.pagination li').click(function () {
            $('.pagination li').removeClass('active');
            $(this).addClass('active');
        });
        /*
         $.get("http://ipinfo.io", function (response) {
         //alert(response.ip);
         //ipAddress = response.ip;
         //$("#ipAddress").html("" + response.ip);
         $("#ipAddress").val(response.ip);
         }, "jsonp");
         */
        $('#cantPlay').hide();
        //$("#gameTextJackpot2").prop("readonly", true);

        // set fields to readonly if not empty or is a numbers
       // setToReadOnly();
       setToReadOnly2();
        //alert("I am here 2 " );





        var matchStarted = $('#matchStarted').val();

        console.log(" matchStarted :: " + matchStarted);

        //if (matchStarted === 'true') {
        if (matchStarted) {

            $('#cantPlay').show();
            $("#bth-submit").prop("disabled", true);

        } else {
            $("#bth-submit").prop("disabled", false);
        }
        
        

        $("#weeklyGamesAnswers-form").submit(function (event) {
            //var formData = $('addGame-form').serialize();
            // Disble the search button
            enableSearchButton(false);

            // Prevent the form from submitting via the browser.
            event.preventDefault();

            searchViaAjax();

        });

    });
    /*
        function setToReadOnly() {

            var gameTextJackpot1 = $('#gameTextJackpot1').val();
            var gameTextJackpot2 = $('#gameTextJackpot2').val();
            var gameTextJackpot3 = $('#gameTextJackpot3').val();
            var gameTextJackpot4 = $('#gameTextJackpot4').val();
            var gameTextJackpot5 = $('#gameTextJackpot5').val();
            var gameTextJackpot6 = $('#gameTextJackpot6').val();
            var gameTextJackpot7 = $('#gameTextJackpot7').val();
            var gameTextJackpot8 = $('#gameTextJackpot8').val();

    //        if (gameTextJackpot1.trim() !== "") {
    //                $("#gameTextJackpot1").prop("readonly", true); 
    //        }
            if (!isNaN(gameTextJackpot1.trim()) && gameTextJackpot1.trim() !== "") {
                $("#gameTextJackpot1").prop("readonly", true);
                //  alert(gameTextJackpot1.trim());
            }
            if (!isNaN(gameTextJackpot2.trim()) && gameTextJackpot2.trim() !== "") {
                $("#gameTextJackpot2").prop("readonly", true);
            }
            if (!isNaN(gameTextJackpot3.trim()) && gameTextJackpot3.trim() !== "") {
                $("#gameTextJackpot3").prop("readonly", true);
            }
            if (!isNaN(gameTextJackpot4.trim()) && gameTextJackpot4.trim() !== "") {
                $("#gameTextJackpot4").prop("readonly", true);
            }
            if (!isNaN(gameTextJackpot5.trim()) && gameTextJackpot5.trim() !== "") {
                $("#gameTextJackpot5").prop("readonly", true);
            }
            if (!isNaN(gameTextJackpot6.trim()) && gameTextJackpot6.trim() !== "") {
                //alert("in :: " + gameTextJackpot6.trim());
                $("#gameTextJackpot6").prop("readonly", true);
            }
            if (!isNaN(gameTextJackpot7.trim()) && gameTextJackpot7.trim() !== "") {
                $("#gameTextJackpot7").prop("readonly", true);
            }
            if (!isNaN(gameTextJackpot8.trim()) && gameTextJackpot8.trim() !== "") {
                $("#gameTextJackpot8").prop("readonly", true);
            }
        }
    */
   
  /* 
    function setToReadOnly() {

        var gameTextJackpot1 = $('#gameTextJackpot1').val();
        var gameTextJackpot2 = $('#gameTextJackpot2').val();
        var gameTextJackpot3 = $('#gameTextJackpot3').val();
        var gameTextJackpot4 = $('#gameTextJackpot4').val();
        var gameTextJackpot5 = $('#gameTextJackpot5').val();
        var gameTextJackpot6 = $('#gameTextJackpot6').val();
        var gameTextJackpot7 = $('#gameTextJackpot7').val();
        var gameTextJackpot8 = $('#gameTextJackpot8').val();

//        if (gameTextJackpot1.trim() !== "") {
//                $("#gameTextJackpot1").prop("readonly", true); 
//        }
    //alert("gameTextJackpot6 :: " + gameTextJackpot6);
    // alert("gameTextJackpot6.trim() :: " + gameTextJackpot6 === undefined ? '' :  gameTextJackpot6.trim());
        if (!isNaN(gameTextJackpot1.trim()) && gameTextJackpot1.trim() !== "") {
            $("#gameTextJackpot1").prop("readonly", true);
            //  alert(gameTextJackpot1.trim());
        }
        if (!isNaN(gameTextJackpot2.trim()) && gameTextJackpot2.trim() !== "") {
            $("#gameTextJackpot2").prop("readonly", true);
        }
        if (!isNaN(gameTextJackpot3.trim()) && gameTextJackpot3.trim() !== "") {
            $("#gameTextJackpot3").prop("readonly", true);
        }
        if (!isNaN(gameTextJackpot4.trim()) && gameTextJackpot4.trim() !== "") {
            $("#gameTextJackpot4").prop("readonly", true);
        }
        if (!isNaN(gameTextJackpot5.trim()) && gameTextJackpot5.trim() !== "") {
            $("#gameTextJackpot5").prop("readonly", true);
        }
        if (!isNaN(gameTextJackpot6.trim()) && gameTextJackpot6.trim() !== "") {
            //alert("in :: " + gameTextJackpot6.trim());
            $("#gameTextJackpot6").prop("readonly", true);
        }
       
      
        if (!isNaN(gameTextJackpot7.trim()) && gameTextJackpot7.trim() !== "") {
            $("#gameTextJackpot7").prop("readonly", true);
        }
        if (!isNaN(gameTextJackpot8.trim()) && gameTextJackpot8.trim() !== "") {
            $("#gameTextJackpot8").prop("readonly", true);
        }
    }
*/

 function setToReadOnly2() {

        var gameTextJackpot1 = $('#gameTextJackpot1').val();
        var gameTextJackpot2 = $('#gameTextJackpot2').val();
        var gameTextJackpot3 = $('#gameTextJackpot3').val();
        var gameTextJackpot4 = $('#gameTextJackpot4').val();
        var gameTextJackpot5 = $('#gameTextJackpot5').val();
        var gameTextJackpot6 = $('#gameTextJackpot6').val();
        var gameTextJackpot7 = $('#gameTextJackpot7').val();
        var gameTextJackpot8 = $('#gameTextJackpot8').val();

//        if (gameTextJackpot1.trim() !== "") {
//                $("#gameTextJackpot1").prop("readonly", true); 
//        }
    //alert("gameTextJackpot6 :: " + gameTextJackpot6);
    // alert("gameTextJackpot6.trim() :: " + gameTextJackpot6 === undefined ? '' :  gameTextJackpot6.trim());
        if (gameTextJackpot1) {
            $("#gameTextJackpot1").prop("readonly", true);
            //  alert(gameTextJackpot1.trim());
        }
        if (gameTextJackpot2) {
            $("#gameTextJackpot2").prop("readonly", true);
        }
        if (gameTextJackpot3) {
            $("#gameTextJackpot3").prop("readonly", true);
        }
        if (gameTextJackpot4) {
            $("#gameTextJackpot4").prop("readonly", true);
        }
        if (gameTextJackpot5) {
            $("#gameTextJackpot5").prop("readonly", true);
        }
       /* if (!isNaN(gameTextJackpot6.trim()) && gameTextJackpot6.trim() !== "") {
            //alert("in :: " + gameTextJackpot6.trim());
            $("#gameTextJackpot6").prop("readonly", true);
        }
        */
       if ( gameTextJackpot6) {
            //alert("in :: " + gameTextJackpot6.trim());
            $("#gameTextJackpot6").prop("readonly", true);
        }
        if (gameTextJackpot7) {
            $("#gameTextJackpot7").prop("readonly", true);
        }
        if (gameTextJackpot8) {
            $("#gameTextJackpot8").prop("readonly", true);
        }
    }
   
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

        /*
        if (isNaN(gameTextJackpot1.trim()) || gameTextJackpot1.trim() === "") {
            alert('Please fill out this field.');
            $('#gameTextJackpot1').focus();
            return false;
        }
        if (isNaN(gameTextJackpot2.trim()) || gameTextJackpot2.trim() === "") {
            alert('Please fill out this field.');
            $('#gameTextJackpot2').focus();
            return false;
        }
        if (isNaN(gameTextJackpot3.trim()) || gameTextJackpot3.trim() === "") {
            alert('Please fill out this field.');
            $('#gameTextJackpot3').focus();
            return false;
        }
        if (isNaN(gameTextJackpot4.trim()) || gameTextJackpot4.trim() === "") {
            alert('Please fill out this field.');
            $('#gameTextJackpot4').focus();
            return false;
        }
        if (isNaN(gameTextJackpot5.trim()) || gameTextJackpot5.trim() === "") {
            alert('Please fill out this field.');
            return false;
        }
        if (isNaN(gameTextJackpot6.trim()) || gameTextJackpot6.trim() === "") {
            alert('Please fill out this field.');
            $('#gameTextJackpot6').focus();
            return false;
        }
        if (isNaN(gameTextJackpot7.trim()) || gameTextJackpot7.trim() === "") {
            alert('Please fill out this field.');
            $('#gameTextJackpot7').focus();
            return false;
        }
        if (isNaN(gameTextJackpot8.trim()) || gameTextJackpot8.trim() === "") {
            alert('Field is required');
            alert('Please fill out this field.');
            return false;
        }
        */
       
       /*
        alert("gameTextJackpot1 :: " + gameTextJackpot1);
        alert("gameTextJackpot2 :: " + gameTextJackpot2);
         alert("gameTextJackpot3 :: " + gameTextJackpot3);
         alert("gameTextJackpot4 :: " + gameTextJackpot4);
        alert("gameTextJackpot5 :: " + gameTextJackpot5);
         alert("gameTextJackpot6 :: " + gameTextJackpot6);
         alert("gameTextJackpot7 :: " + gameTextJackpot7);
        alert("gameTextJackpot8 :: " + gameTextJackpot8);
        */
       if (!gameTextJackpot1) {
            alert('Please fill out this field.');
            $('#gameTextJackpot1').focus();
            return false;
        }
        if (!gameTextJackpot2) {
            alert('Please fill out this field.');
            $('#gameTextJackpot2').focus();
            return false;
        }
        if (!gameTextJackpot3) {
            alert('Please fill out this field.');
            $('#gameTextJackpot3').focus();
            return false;
        }
        if (!gameTextJackpot4) {
            alert('Please fill out this field.');
            $('#gameTextJackpot4').focus();
            return false;
        }
        if (!gameTextJackpot5) {
            alert('Please fill out this field.');
            return false;
        }
        if (!gameTextJackpot6) {
            alert('Please fill out this field.');
            $('#gameTextJackpot6').focus();
            return false;
        }
        if (!gameTextJackpot7) {
            alert('Please fill out this field.');
            $('#gameTextJackpot7').focus();
            return false;
        }
        if (!gameTextJackpot8) {
           // alert('Field is required');
            alert('Please fill out this field.');
            return false;
        }
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


