<%-- 
    Document   : about
    Created on : Aug 14, 2017, 9:11:54 AM
    Author     : Olakunle Awotunbo
--%>

<!--Include outside header-->
<%@ include file="includes/outside/header.jsp" %>
<!--Include outside navigation-->
<%@ include file="includes/outside/navigation.jsp" %>


<div class="container">
    <div class="row content">

        <div class="col-sm-9 text-left">


            <div class="container-fluid bg-3 text-center">    
                 <br>
                <div class="row">   
                    <div class="form-group">
                        
                        <div class="col-md-9 col-sm-9 col-xs-9">                                            
                            <p class="form-control-static">This is about page.</p>
                        </div>     
                    </div>

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
