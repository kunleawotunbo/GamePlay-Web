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

                        Selected Answer - ${selectedAnswer}
                        Match ::  <strong> ${matchPredictionObject.homeTeamName} - ${matchPredictionObject.awayTeamName} </strong>

                        <br><br>
                        <form:form modelAttribute="matchPredictionAnswer" class="form-horizontal">
                            <div class="form-group row text-right">
                                <label for="email-h-f" class="col-sm-3 form-control-label m-t-5">Email</label>
                                <div class="col-sm-9">
                                    <input path="userPhoneNo"  id="userPhoneNo" name="userPhoneNo" type="tel" class="form-control" required ="required" placeholder="Enter your phone number"/>                          
                                </div>
                            </div>
                            <div class="form-group row text-right">
                                <label for="password-h-f" class="col-sm-3 form-control-label m-t-5">Password</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="password-h-f">
                                </div>
                            </div>
                            <div class="form-group row text-right">
                                <label for="re-password-h-f" class="col-sm-3 form-control-label m-t-5">Re Password</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="re-password-h-f">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-9 offset-sm-3">
                                    <label class="custom-control custom-checkbox">
                                        <input class="custom-control-input checkbox-main" type="checkbox" checked="" >
                                        <span class="custom-control-indicator"></span>
                                        <span class="custom-control-description align-middle">Keep me signed in</span>
                                    </label>
                                    <button class="btn btn-main pull-right" type="submit">Sign in</button>
                                </div>
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