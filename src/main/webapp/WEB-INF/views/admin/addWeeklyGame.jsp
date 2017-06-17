<%-- 
    Document   : addWeeklyGame
    Created on : Jun 16, 2017, 9:27:44 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Weekly Games</h3>
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
                        <h2>Add Weekly Game</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />
                        <div class="bs-callout bs-callout-warning hidden">
                            <h4>Oh snap!</h4>
                            <p>This form seems to be invalid :(</p>
                        </div>

                        <div class="bs-callout bs-callout-info hidden">
                            <h4>Yay!</h4>
                            <p>Everything seems to be ok :)</p>
                        </div>
                        <!--                        <form class="form-horizontal form-label-left" id="weeklyGame-form" data-parsley-validate>
                        
                                                 
                        
                        
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Week No<span class="required">*</span></label>
                                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                                            <input type="text" class="form-control" readonly="readonly" value="${weekNo}" required>                               
                                                        </div>
                                                    </div>
                        
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">prizeOfWinnerse<span class="required">*</span></label>
                                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                                            <input id="prizeOfWinners" type="number" class="form-control" name="prizeOfWinners" placeholder="Prize Of Winners" required >                                  
                                                        </div>
                                                    </div>
                        
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">prizeOfWinnerse<span class="required">*</span></label>
                                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                                            <input id="prizeOfWinners" type="number" class="form-control" name="prizeOfWinners" placeholder="Prize Of Winners" required >                                  
                                                        </div>
                                                    </div>
                        
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Name<span class="required">*</span></label>
                                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                                            <input id="gameName" type="text" class="form-control" name="gameName" placeholder="Input game Name" required >                                  
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Code<span class="required">*</span></label>
                                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                                            <input id="gameCode" type="text" class="form-control" name="gameCode" placeholder="Game Code" required >                                 
                                                        </div>
                                                    </div>
                        
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-3">Enabled</label>
                                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                                            <input id="enabled" name="enabled" type="checkbox"  data-toggle="toggle" >                                    
                                                        </div>
                                                    </div>
                        
                        
                                                    <input name="createdBy" value="${loggedinuser}" type="hidden"> </input>
                                                    <div class="ln_solid"></div>
                        
                                                    <div class="form-group">
                                                        <div class="col-md-9 col-md-offset-3">
                                                            <button type="submit" class="btn btn-primary">Cancel</button>
                                                            <button type="submit" class="btn btn-success">Submit</button>
                                                        </div>
                                                    </div>
                        
                                                </form>
                        -->

                        <form:form modelAttribute="weeklyGame" class="form-horizontal form-label-left" id="addWeeklyGame-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />

                            <div class="form-group">

                                <%--
                                 <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Category<span class="required">*</span></label>
                                <div class="col-sm-4">
                                    <form:select class="form-control" path="gameCategory" id="gameCategory"  name="gameCategory" required ="required" >
                                        <form:option value="" label="Choose Game Category "/>
                                        <form:options items="${gameList}" itemValue="id" itemLabel="gameName" />
                                     
                                        </form:select>     
                              
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Type<span class="required">*</span></label>
                                    <div class="col-sm-4">
                                        <form:select class="form-control" path="gamePlayType" id="gamePlayType"  name="gamePlayType" required ="required" >
                                        <form:option value="" label="Choose Game Type" />
                                            <form:options items="${gamePlayTypeList}" itemValue="id" itemLabel="typeName" />
                                          
                                            </form:select>                                                         
                                        </div>
                                    </div>
                            
                                --%>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Category<span class="required">*</span></label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="gameCategory"  name="gameCategory" required>
                                            <option value="">Choose Game Type</option>>
                                            <c:forEach  var="gameCate" items="${gameList}" varStatus="status" >

                                                <option value="${gameCate.id}">${gameCate.gameName}</option>
                                            </c:forEach>
                                        </select>                                                          
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Type<span class="required">*</span></label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="gameType"  name="gameType" required>
                                            <option value="">Choose Game Category</option>>
                                            <c:forEach  var="gameType" items="${gamePlayTypeList}" varStatus="status" >

                                                <option value="${gameType.id}">${gameType.typeName}</option>
                                            </c:forEach>
                                        </select>                                                          
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Week No<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="weekNo" id="weekNo" type="text" class="form-control" name="weekNo" readonly="readonly" value="${weekNo}" required ="required" />                                  
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
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Expiry Date<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="gameExpiryDate" id="gameExpiryDate" name="gameExpiryDate" type="text" class="form-control"  placeholder="Game Expiry date" required ="required" />                                 
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Rules<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:textarea path="gameRules" id="gameRules" name="gameRules" type="textarea" class="form-control" rows="3"  placeholder="Game Rules" required ="required" />                                 
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Text <span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="gameText" id="gameText" name="gameText" type="text" class="form-control"  placeholder="Game Text" required ="required" />                                 
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">GameImage<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="gameImage" id="gameImage" name="gameImage" type="file" class="form-control"  placeholder="Game Image" required ="required" accept=".png, .jpg, .jpeg" />                                 
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
                                                    <button type="submit" id="bth-submit" class="btn btn-success">Submit</button>
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
                    $('#gameExpiryDate').datetimepicker({
                        //autoclose: true,
                        //format: 'dd/mm/yyyy',
                        // startDate: d

                    });
                });




                jQuery(document).ready(function ($) {

                    $("#addWeeklyGame-form").submit(function (event) {
                        //var formData = $('addGame-form').serialize();
                        // Disble the search button
                        enableSearchButton(false);

                        // Prevent the form from submitting via the browser.
                        event.preventDefault();

                        searchViaAjax();

                    });

                });

                function searchViaAjax() {
                    /*
                     var search = {}
                     search["gameName"] = $("#gameName").val();
                     search["gameCode"] = $("#gameCode").val();
                     // search["createdBy"] = $("#createdBy").val();
                     search["createdBy"] = "test user";
                     //  search["enabled"] = $("#enabled").val();
                     */
                    var id = $('#id').val();
                    var gameCategory = $('#gameCategory').val();
                    var gameType = $('#gameType').val();
                    var weekNo = $('#weekNo').val();
                    var prizeOfWinners = $('#prizeOfWinners').val();
                    var noOfWinners = $('#noOfWinners').val();
                    //var gameExpiryDate = $('#gameExpiryDate').val();
                    var gameRules = $('#gameRules').val();
                    var gameText = $('#gameText').val();
                    var gameImage = $('#gameImage').val();
                    var createdBy = "test user";

                    // set a variable
                    var gameExpiryDate = new Date();
                    console.log("gameExpiryDate ::" + gameExpiryDate);

                    /*
                     if (enabled) {
                     enabled = 1;
                     } else {
                     enabled = 0;
                     }
                     console.log("enabled ", enabled);
                     */
                    var json = {
                        
                        "gameCategory": gameCategory,
                        "gameType": gameType,
                        "weekNo": weekNo,
                        "prizeOfWinners": prizeOfWinners,
                        "noOfWinners": noOfWinners,
                        "gameExpiryDate": gameExpiryDate,
                        "gameRules": gameRules,
                        "gameText": gameText,
                        "gameImage": gameImage,
                        "createdBy": createdBy

                    };

                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "${pageContext.request.contextPath}/api/weeklygames/create",
                        data: JSON.stringify(json),
                        dataType: 'json',
                        timeout: 100000,
                        success: function (data) {
                            console.log("SUCCESS: ", data);
                            display(data);
                        },
                        error: function (e) {
                            console.log("ERROR: ", e);
                            display(e);
                        },
                        done: function (e) {
                            console.log("DONE");
                            enableSearchButton(true);
                        }
                    });
                    
                    $("#addWeeklyGame-form")[0].reset();

                }

                function enableSearchButton(flag) {
                    $("#btn-submit").prop("disabled", flag);
                }

                function display(data) {
                    var json = "<h4>Ajax Response</h4><pre>"
                            + JSON.stringify(data, null, 4) + "</pre>";
                    $('#feedback').html(json);
                }
            </script>
