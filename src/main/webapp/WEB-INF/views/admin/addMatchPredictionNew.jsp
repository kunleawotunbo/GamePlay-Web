<%-- 
    Document   : addMatchPredictionNew
    Created on : Sep 23, 2017, 3:46:19 PM
    Author     : Olakunle Awotunbo
--%>


<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Match Prediction New v2</h3>
            </div>

            <!--            <div class="title_right">
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


            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Add Match Prediction New v2</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />
                        <div class="bs-callout bs-callout-warning hidden">
                            <h4>Oh snap!</h4>
                            <p>This form seems to be invalid :(</p>
                        </div>


                        <c:if test="${error}">                                  
                            <div class="alert alert-danger" id="success-error">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>${message} </strong>
                            </div>
                        </c:if>  

                        <c:if test="${saved}">        
                            <div class="alert alert-success" id="success-alert">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>${message} </strong>
                            </div>
                        </c:if>  


                        <%--<form:form modelAttribute="weeklyGame" class="form-horizontal form-label-left" id="addWeeklyGame-form" data-parsley-validate="">--%>
                        <form:form modelAttribute="matchPrediction" method="POST"  class="form-horizontal form-label-left" id="matchPrediction-form" data-parsley-validate="">  
                            <form:hidden path="id" id="id" name="id" />

                            <div class="form-group">

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Country<span class="required">*</span></label>
                                    <div class="col-sm-4">                                         
                                        <form:select id="countryCode" path="countryCode" title="Choose Country" class="form-control">
                                            <option value="">Choose Country</option>
                                            <form:options items="${countriesList}" itemValue="countryCode" itemLabel="countryName"/>
                                        </form:select>   
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">League<span class="required">*</span></label>
                                    <div class="col-sm-4">                                         
                                        <form:select id="leagueCode" path="leagueCode" title="Choose League" class="form-control">

                                            <form:option value="0" label="Choose League" />
                                        </form:select>   
                                    </div>
                                </div>

                                <%--                             
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">HomeTeamId<span class="required">*</span></label>
                                    <div class="col-sm-4">                                         
                                        <form:input path="homeTeamId" id="homeTeamId" type="number" class="form-control" name="homeTeamId" placeholder="" required ="required" />                        
                                    </div>
                                </div>
                                --%>                                
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">HomeTeam<span class="required">*</span></label>
                                    <div class="col-sm-4">                                         
                                        <form:select id="homeTeamId" path="homeTeamId" title="Choose Team" class="form-control">
                                            <!--<option value="">Choose League</option>-->
                                            <%--<form:options items="${teamList}" itemValue="id" itemLabel="teamName"/>--%>
                                            <%--<form:options  itemValue="leagueCode" itemLabel="countryName"/>--%>
                                            <form:option value="0" label="Choose Home Team" />
                                        </form:select>   
                                    </div>
                                </div>                              

                                <%--
                               <div class="form-group">
                                   <label class="control-label col-md-3 col-sm-3 col-xs-3">Home Team Name<span class="required">*</span></label>
                                   <div class="col-md-9 col-sm-9 col-xs-9">
                                       <form:input path="homeTeamName" id="homeTeamName" type="text" class="form-control" name="homeTeamName" placeholder="" required ="required" />                                 
                                   </div>
                               </div> 
                                --%>


                                <%--                                
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Away Team Id<span class="required">*</span></label>
                                    <div class="col-sm-4">                                         
                                        <form:input path="awayTeamId" id="awayTeamId" type="number" class="form-control" name="awayTeamId" placeholder="" required ="required" />                        
                                    </div>
                                </div>
                                --%>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">HomeTeam<span class="required">*</span></label>
                                    <div class="col-sm-4">                                         
                                        <form:select id="awayTeamId" path="awayTeamId" title="Choose Away Team" class="form-control">                                           
                                            <form:option value="0" label="Choose Away Team" />
                                        </form:select>   
                                    </div>
                                </div>    

                                <%--       
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Away Team Name<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="awayTeamName" id="awayTeamName" type="text" class="form-control" name="awayTeamName" placeholder="" required ="required" />                                 
                                    </div>
                                </div>
                                --%>





                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Week No<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="text" class="form-control" readonly="readonly" placeholder="Read-Only Input" value="${weekNo}">
                                        <form:hidden path="weekNo" id="weekNo" class="form-control" name="weekNo"  value="${weekNo}" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Prize Of Winners<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="prizeOfWinners" id="prizeOfWinners" type="number" class="form-control" name="prizeOfWinners" placeholder="" required ="required" />                                 
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">No Of Winners<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="noOfWinners" id="noOfWinners" type="number" class="form-control" name="noOfWinners" placeholder="" required ="required" />                                 
                                    </div>
                                </div>  

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Start Date<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="startTime" id="startTime" name="startTime" type="text" class="form-control"  placeholder="Game Start time" required ="required" />                                 
                                    </div>
                                </div>    

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Expiry Date<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="endTime" id="endTime" name="endTime" type="text" class="form-control"  placeholder="Game Expiry time" required ="required" />                                 
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Enabled</label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:checkbox path="enabled" id="enabled" name="enabled"  data-toggle="toggle" />                                    
                                    </div>
                                </div>    


                                <form:input path="createdBy" name="createdBy" value="${loggedinuser}" type="hidden" /> 
                                <div class="ln_solid"></div>


                                <div class="form-group">
                                    <c:choose>
                                        <c:when test="${edit}">

                                            <div class="form-group">
                                                <div class="col-md-9 col-md-offset-3">
                                                    <button type="reset" class="btn btn-primary">Cancel</button>
                                                    <input type="submit" bth-search value="Update" class="btn btn-success "/> 

                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="form-group">
                                                <div class="col-md-9 col-md-offset-3">
                                                    <button type="reset" class="btn btn-primary">Cancel</button>
                                                    <!--<button type="submit" id="bth-submit" class="btn btn-success">Submit</button>-->
                                                    <input type="submit" id="bth-submit" class="btn btn-success" value="Submit" onclick="return submitForm();"/> 
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                            </form:form>    

                        </div>
                    </div>
                </div>
                <!-- /form input mask -->

            </div>


            <%@ include file="../includes/footer.jsp" %>

            <script>

                // Date time picker
                $(function () {
                    //  var temp = $(this).datepicker('getDate');
                    // var d = new Date(temp);
                    // d.setDate(d.getDate() + 1);
                    $('#startTime').datetimepicker({
                        //autoclose: true,
                        format: 'DD-MM-YYYY HH:mm:ss'
                                //format: 'dd MMM yyyy HH:mm:ss zzz'
                                // startDate: d

                    });
                    $('#endTime').datetimepicker({
                        //autoclose: true,
                        format: 'DD-MM-YYYY HH:mm:ss'
                                //format: 'dd MMM yyyy HH:mm:ss zzz'
                                // startDate: d

                    });
                });


                jQuery(document).ready(function ($) {

                    // success alert
                    $("#success-alert").fadeTo(2000, 500).slideUp(500, function () {
                        $("#success-alert").slideUp(500);
                    });
                    // error alert
                    $("#success-error").fadeTo(2000, 500).slideUp(500, function () {
                        $("#success-error").slideUp(500);
                    });


                    // On country drop down change
                    $("#countryCode").change(function (event) {

                        $('#leagueCode').html("");
                        $('#homeTeamId').html("Choose Home Team");
                        $('#awayTeamId').html("Choose Away Team");

                        /*
                         $('#homeTeamId')
                         .append($("<option></option>")
                         .attr("value", "0")
                         .text("Choose Home Team"));
                         
                         */

                        var countryCode = $('#countryCode').val();
                        console.log(" countryCode ::" + countryCode);

                        $.ajax({
                            type: "GET",
                            contentType: "application/json",
                            url: "${pageContext.request.contextPath}/admin/getLeagueByCountry/" + countryCode,
                            timeout: 600000,
                            success: function (data) {
                                console.log("SUCCESS: ", data);
                                //display(data);              
                                var records = data.result;
                                console.log(records.length);
                                $.each(records, function (i, item) {

                                    $('#leagueCode')
                                            .append($("<option></option>")
                                                    .attr("value", item.leagueCode)
                                                    .text(item.leagueName));
                                });
                                //notification("Notification", "Weekly game added successfully.", "success");
                            },
                            error: function (e) {
                                console.log("ERROR: ", e);
                                //display(e);
                                // notification("Notification", "Failed to add Weekly game.", "error");
                            },
                            done: function (e) {
                                console.log("DONE");
                                enableSearchButton(true);
                            }
                        });

                    });
                    // On change end for country dropdown

                    // On change for league drop down change
                    // This will list teams in the league
                    $("#leagueCode").change(function (event) {

                        $('#homeTeamId').html("");
                        $('#awayTeamId').html("");
                        var leagueCode = $('#leagueCode').val();
                        console.log(" leagueCode ::" + leagueCode);

                        $.ajax({
                            type: "GET",
                            contentType: "application/json",
                            url: "${pageContext.request.contextPath}/admin/getTeamByLeagueCode/" + leagueCode,
                            timeout: 600000,
                            success: function (data) {
                                console.log("SUCCESS: ", data);
                                //display(data);              
                                var records = data.result;
                                console.log(records.length);

                                
                                 $.each(records, function (i, item) {
                                 
                                 // Home team
                                 $('#homeTeamId')
                                 .append($("<option></option>")
                                 .attr("value", item.id)
                                 .text(item.teamName));
                                 // Away team
                                 $('#awayTeamId')
                                 .append($("<option></option>")
                                 .attr("value", item.id)
                                 .text(item.teamName));
                                 });
                                 
                                
                                
                                /*
                                var listItems = "";
                                for (var i = 0; i < records; i++) {
                                    listItems += "<option value='" + records[i].stateid + "'>" + jsonList.Table[i].statename + "</option>";
                                }
                                $("#DLState").html(listItems);

                                */
                                //notification("Notification", "Weekly game added successfully.", "success");
                            },
                            error: function (e) {
                                console.log("ERROR: ", e);
                                //display(e);
                                // notification("Notification", "Failed to add Weekly game.", "error");
                            },
                            done: function (e) {
                                console.log("DONE");
                                enableSearchButton(true);
                            }
                        });

                    });
                    // On change end for league drop down







                });



            </script>

            <script type="text/javascript">
                function submitForm() {
                    /*
                    alert("yess ");
                    // getting the user form values
                    var homeTeamId = $('#homeTeamId').val();
                    var awayTeamId = $('#awayTeamId').val();

                    console.log("homeTeamId :: " + homeTeamId);
                    console.log("awayTeamId :: " + awayTeamId);
                    alert("homeTeamId :: " + homeTeamId);
                    alert("awayTeamId :: " + awayTeamId);
                    // Do some validation
                    if (homeTeamId === "") {
                        alert('Please choose a game category');
                        $('#homeTeamId').focus();
                        return false;
                    }


                    var r = confirm("Do you want to Submit?");
                    if (r == true) {
                        frm.submit();
                    } else {
                        return false;
                    }

                    //return true;

                    */

                }





            </script>


