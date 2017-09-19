<%-- 
    Document   : addWinnerNumber
    Created on : 14-Sep-2017, 14:29:17
    Author     : BELLO
--%>

<!--Include outside header-->
<%@ include file="includes/outside/header.jsp" %>
<!--Include outside navigation-->
<%@ include file="includes/outside/navigation.jsp" %>



<style>
    .frontpage_square{
        position:relative;
        overflow:hidden;
        padding-bottom:100%;
    }
    .frontpage_square img{
        position:absolute;

        opacity:1;
        transition: 1s ease;
    }


    .frontpage_square img:hover{
        opacity:0.5;
        transition: 1s ease;
    }   
</style>

<div class="container">

   <!-- <div class="row row-offcanvas row-offcanvas-right">-->
 
        <div class="row" >
        <!--<div class="col-xs-12 col-sm-9">-->
           <!-- <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>-->
          
            


                

                    <!--                            <div class="item  col-xs-12 col-md-6">-->
                    <!--<div class="item  col-xs-6 col-md-4" >-->
                    
                   <!--<div class="col-md-12 col-sm-12 col-xs-12">-->
                 <div> 
                        <p> &nbsp;</p>
                        
                        <p> &nbsp;</p>
                        <p> &nbsp;</p>
                        
               
                
  
                
            </div> 
            <p> &nbsp;</p>
            
         
                        <p> Want To Know Whether You're a Winner Today?</p>
                        
                         <p> &nbsp;</p>
                       <form:form modelAttribute="winnerNumber"  method="POST" enctype="multipart/form-data" class="form-horizontal form-label-left" id="addWeeklyGame-form" > 

       <div class="form-group">
                           
       <form:label path="winnerNumber" class="control-label col-md-3 col-sm-3 col-xs-3">Enter Your Phone Number<span class="required">*</span></form:label>
                      <div class="col-md-6 col-sm-6 col-xs-6">
                    <form:input  path="winnerNumber" class="form-control"  placeholder="e.g. 0803*******89" required ="required" />                                 
                                    </div>
                                </div> 
                                    
                                    <p> &nbsp;</p>
                          
                                      <div class="form-group">
                                               <!-- <div class="col-md-9 col-md-offset-3">-->
                                               <div class="col-md-9 col-sm-9 col-xs-9" style=" text-align: center">
                                                    <button type="reset" class="btn btn-primary">Cancel</button>
                                                    <!--<button type="submit" id="bth-submit" class="btn btn-success">Submit</button>-->
                                                    <input type="submit" id="bth-submit" class="btn btn-success" value="Search" onclick="return submitForm();"/> 
                                                </div>
                          
                           </form:form>  
                      
            
          <!--/.col-xs-12.col-sm-9-->
                     </div>  
                                    
                                    <p> &nbsp;</p>
                        
                        <p> &nbsp;</p>
                        <p> &nbsp;</p>
                        <p> &nbsp;</p>
                        
                        <p> &nbsp;</p>
                        <p> &nbsp;</p>

                 </div>
          
            
          <%@ include file="includes/outside/bottomadverts.jsp" %>
        <!--Center content ends-->       
</div>
        <!--Inculde outer sidebar-->
        

        <!--Include outside footer-->
        <%@ include file="includes/outside/footer.jsp" %>
        <script>
           function submitForm() {

                // getting the user form values
                var gameCategory = $('#gameCategory').val();
                var gameType = $('#gameType').val();
                var prizeOfWinners = $('#prizeOfWinners').val();
                var noOfWinners = $('#noOfWinners').val();
                var file = $('#gameImage1').val();
                var file2 = $('#gameImage2').val();
                // Do some validation
                if (gameCategory === "") {
                alert('Please choose a game category');
                $('#gameCategory').focus();
                return false;
                }
                if (gameType === "") {
                alert('Please choose a game type');
                $('#gameType').focus();
                return false;
                }
                if (prizeOfWinners == 0) {
                alert('Prize must be greater than 0');
                $('#prizeOfWinners').focus();
                return false;
                }
                if (noOfWinners == 0) {
                alert('Number of Winners must be greater than 0');
                $('#noOfWinners').focus();
                return false;
                }


                }
           
        </script>
