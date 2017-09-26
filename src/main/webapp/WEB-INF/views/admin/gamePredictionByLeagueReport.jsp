<%-- 
    Document   : gamePredictionByLeagueReport
    Created on : 26-Sep-2017, 03:14:14
    Author     : BELLO
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Matches Played</h3>
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
             <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Search Matches By Time Played</h2> 

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                       <form:form modelAttribute="matchPrediction" method="POST" enctype="multipart/form-data" class="form-horizontal form-label-left" id="addWeeklyGame-form" data-parsley-validate="">  
                            <form:hidden path="id" id="id" name="id" />
                            
                             <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">League<span class="required">*</span></label>
                                    <div class="col-sm-4">                                         
                                        <form:select id="leagueCode" path="leagueCode" title="Choose League" class="form-control">
                                            <!--<option value="">Choose League</option>-->
                                            <%--<form:options items="${countriesList}" itemValue="leagueCode" itemLabel="countryName"/>--%>
                                            <%--<form:options  itemValue="leagueCode" itemLabel="countryName"/>--%>
                                            <form:option value="" label="Choose League" />
                                            <form:option value="EPL" label="English Premier League" />
                                            <form:option value="SPL" label="Spanish La Liga" />
                                            <form:option value="SERIE A" label="Italian Serie A" />
                                            <form:option value="FL" label="French League 1" />
                                        </form:select>   
                                    </div>
                                </div>
                                 <div class="form-group">
                                                <div class="col-md-9 col-md-offset-3">
                                                    <button type="reset" class="btn btn-primary">Cancel</button>
                                                    <!--<button type="submit" id="bth-submit" class="btn btn-success">Submit</button>-->
                                                    <input type="submit" id="bth-submit" class="btn btn-success" value="Submit" onclick="return submitForm();"/> 
                                                </div>
                                            </div>
                            
                                </form:form> 


                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>         



        <%@ include file="../includes/footer.jsp" %>

        <script>


                  $(function () {
                //  var temp = $(this).datepicker('getDate');
                // var d = new Date(temp);
                // d.setDate(d.getDate() + 1);
                $('#matchGameStartTime').datetimepicker({
                //autoclose: true,
                format: 'DD-MM-YYYY HH:mm:ss'
                        //format: 'dd MMM yyyy HH:mm:ss zzz'
                        // startDate: d

                });
                $('#matchGameEndTime').datetimepicker({
                //autoclose: true,
                format: 'DD-MM-YYYY HH:mm:ss'
                        //format: 'dd MMM yyyy HH:mm:ss zzz'
                        // startDate: d

                });
                });

            

           

           
        </script>



