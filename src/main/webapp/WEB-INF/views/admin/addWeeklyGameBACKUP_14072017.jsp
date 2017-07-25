<%-- 
    Document   : addWeeklyGameBACKUP_14072017
    Created on : Jul 14, 2017, 8:38:44 AM
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

                        <form:form modelAttribute="weeklyGame" class="form-horizontal form-label-left" id="addWeeklyGame-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />

                            <div class="form-group">

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Category<span class="required">*</span></label>
                                    <div class="col-sm-4">                                         
                                        <form:select id="gameCategory" path="gameCategory" title="Choose Game Type" class="form-control">
                                            <option value="">Choose Game Type</option>
                                            <form:options items="${gameList}" itemValue="id" itemLabel="gameName"/>
                                        </form:select>   
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Type<span class="required">*</span></label>
                                    <div class="col-sm-4">

                                        <form:select id="gameType" path="gamePlayType" title=' Game Type' class="form-control">
                                            <option value="">Choose Game Type</option>
                                            <form:options items="${gamePlayTypeList}" itemValue="id" itemLabel="typeName"/>
                                        </form:select>   

                                        <%--<form:radiobuttons id="gameType" path="gamePlayType" items="${gamePlayTypeList}"  itemValue="id" itemLabel="typeName"/>--%>


                                        <%--
                                        <form:radiobutton id="gameType" path="gamePlayType" value="1" />Image Game 
                                           <form:radiobutton id="gameType2" path="gamePlayType" value="2" />Text 
                                        --%>
                                        </select>                                                          
                                    </div>
                                </div>        


                                <div class="form-group" id="gameText">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Text <span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <%--<form:input path="gameText" id="gameText1" name="gameText" type="text" class="form-control"  placeholder="Game Text" />--%>        
                                        <form:textarea path="gameText" id="gameText1" name="gameText" type="textarea" class="form-control" rows="3"  placeholder="Game Text" />        
                                    </div>
                                </div>

                                <div class="form-group" id="gameImage" >
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">GameImage<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="gameImage" id="gameImage1" name="gameImage" type="file" class="form-control"  placeholder="Game Image"  accept=".png, .jpg, .jpeg" />                                 
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
                        format: 'DD-MM-YYYY HH:mm:ss'
                                //format: 'dd MMM yyyy HH:mm:ss zzz'
                                // startDate: d

                    });
                });


                jQuery(document).ready(function ($) {
                    // Hide gameText div
                    $('#gameText').hide();
                    // Remove requred for gameText1
                    $("#gameText1").prop("required", false);

                    // Hide gameImage div
                    $('#gameImage').hide();
                    // Remove requred for gameImage1
                    $("#gameImage1").prop("required", false);

                    /*
                     $("#gameType2").change(function (event) {
                     var gameType2 = $('#gameType2').val();
                     console.log(" gameType2 ::" + gameType2);
                     if (gameType2 == "2")  {
                     // console.log(" gameType is Question ::" + gameText);
                     console.log(" gameType ::" + gameType);
                     // Since gameType is text question, then Hide gameImage div and remove required attribute
                     $('#gameImage').hide();
                     $("#gameImage1").prop("required", false);
                     
                     // Show gameText since gameType is picture and sett attribute gameText1 required = true
                     $('#gameText').show();
                     $("#gameText1").prop("required", true);
                     
                     }
                     });
                     
                     */
                    $("#gameType").change(function (event) {


                        //console.log("else gameText ::" + gameText);
                        var gameType = $('#gameType').val();
                        console.log(" gameType ::" + gameType);

                        /*
                         if (gameType == "1") {
                         // console.log(" gameType is Picture ::" + gameText);
                         // 
                         // Since gameType is Picutre, then Hide gameText div and remove required attribute
                         $('#gameText').hide();
                         $("#gameText1").prop("required", false);
                         
                         // Show gameImage since gameType is picture and sett attribute required = true
                         $('#gameImage').show();
                         $("#gameImage1").prop("required", true);
                         
                         } 
                         
                         */



                        if (gameType == "1") {
                            // console.log(" gameType is Picture ::" + gameText);
                            // 
                            // Since gameType is Picutre, then Hide gameText div and remove required attribute
                            $('#gameText').hide();
                            $("#gameText1").prop("required", false);

                            // Show gameImage since gameType is picture and sett attribute required = true
                            $('#gameImage').show();
                            $("#gameImage1").prop("required", true);

                        } else {
                            // console.log(" gameType is Question ::" + gameText);

                            // Since gameType is text question, then Hide gameImage div and remove required attribute
                            $('#gameImage').hide();
                            $("#gameImage1").prop("required", false);

                            // Show gameText since gameType is picture and sett attribute gameText1 required = true
                            $('#gameText').show();
                            $("#gameText1").prop("required", true);

                        }


                    });


                    $("#addWeeklyGame-form").submit(function (event) {
                        //var formData = $('addGame-form').serialize();                       

                        var gameCategory = $('#gameCategory').val();
                        var gameType = $('#gameType').val();
                        var prizeOfWinners = $('#prizeOfWinners').val();
                        var noOfWinners = $('#noOfWinners').val();
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
                    var gameExpiryDate = $('#gameExpiryDate').val();
                    var gameRules = $('#gameRules').val();
                    var gameText = $('#gameText1').val();
                    var gameImage = $('#gameImage1').val();
                    var createdBy = $('#createdBy').val();

                    // console.log("gamePlayType ::" + gameType);
                    console.log("gameImage ::" + gameImage);
                    /*
                     if (enabled) {
                     enabled = 1;
                     } else {
                     enabled = 0;
                     }
                     console.log("enabled ", enabled);
                     */
                    // Date.parse(gameExpiryDate)  is used to parse the date in string to Date for to consume.
                    var json = {
                        "id": id,
                        "gameCategory": gameCategory,
                        "gamePlayType": gameType,
                        "weekNo": weekNo,
                        "prizeOfWinners": prizeOfWinners,
                        "noOfWinners": noOfWinners,
                        "gameExpiryDate": Date.parse(gameExpiryDate),
                        "gameRules": gameRules,
                        "gameText": gameText,
                        "gameImage": gameImage,
                        "createdBy": createdBy,
                       // "file": gameImage
                       // "files": gameImage

                    };

                    //var data = new FormData(json);

                    console.log(JSON.stringify(json));
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                       url: "${pageContext.request.contextPath}/api/weeklygames/create",
                       // url: "${pageContext.request.contextPath}/api/game/createtest",
                        data: JSON.stringify(json),
                       //  data: data,
                        enctype: 'multipart/form-data',
                        processData: false, // Important!
                        contentType: false,
                        cache: false,
                        dataType: 'json',
                        timeout: 600000,
                        
                        /*
                        beforeSend: function (xhr) {
                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");
                            xhr.setRequestHeader(header, token);
                        },
                                */
                        success: function (data) {
                            console.log("SUCCESS: ", data);
                            //display(data);                      
                            notification("Notification", "Weekly game added successfully.", "success");
                        },
                        error: function (e) {
                            console.log("ERROR: ", e);
                            //display(e);
                            notification("Notification", "Failed to add Weekly game.", "error");
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

                function notification(title, text, type) {

                    new PNotify({
                        title: title,
                        text: text,
                        type: type,
                        styling: 'bootstrap3'
                    });
                }
            </script>
