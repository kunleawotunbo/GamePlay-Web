<%-- 
    Document   : mpcongratulations
    Created on : Sep 20, 2017, 12:30:43 PM
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

                        <div class="imgCenter">
                            
                            <img src="<c:url value='/resources/images/congrats.jpg' />" alt="congrats">
                             <div class="alert alert-success lead">
                           
                            <!--<h2> Congratulations. </h2>--> 
                            
                            <p>Your answer has been submitted successfully.</p>
                        </div>
                        </div>
                                   
                                   
                       

                        <span class="well floatRight">
                            Go to <a href="<c:url value='/' />">Homepage</a>
                        </span>
                        <span class="well floatLeft">
                            <!--<a href="##" onclick="window.history.back()">Try another answer</a>-->
                            <a href="#" id="tryanotheranswer">Try another answer</a>
                            <!--<a href="#" onClick="goBack();">return false;">Go back</a>-->

                            <%--<a href="<c:url value='/prediction' />">Try another answer</a>--%>
                        </span>
                        <br><br>
                       

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

<script>
    $("#tryanotheranswer").click(function(e) {
    e.preventDefault();
    history.back(1);
});

function goBack() {
    window.history.back();
  }
    </script>