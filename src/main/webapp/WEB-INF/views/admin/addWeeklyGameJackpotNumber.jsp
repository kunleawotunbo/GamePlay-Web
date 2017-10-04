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
                        <form:form modelAttribute="weeklyGame" method="POST" enctype="multipart/form-data" class="form-horizontal form-label-left" id="addWeeklyGame-form" data-parsley-validate="">  
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

                                <div class="form-group" id="gameTypeForm">
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
                                        <form:input path="files" id="gameImage1" name="gameImage" type="file" class="form-control"  placeholder="Game Image"  accept=".png, .jpg, .jpeg" />                                 
                                    </div>
                                </div>
                                <c:if test="${edit}">          

                                    <div class="form-group " id="gameImage1edit" >
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3"><span class="required"></span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <img src="data:image/jpeg;base64,${encodedPictureString}" alt="..."floatRight width="200" height="200">

                                        </div>
                                    </div>  



                                </c:if>  

                                <!--secocnd image-->
                                <div class="form-group" id="gameImage2" >
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">GameImage 2<span class="required">*</span></label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <form:input path="files" id="gameImage2" name="gameImage2" type="file" class="form-control"  placeholder="Game Image 2"  accept=".png, .jpg, .jpeg" />                                 
                                    </div>
                                </div>    

                                <c:if test="${edit}">          

                                    <div class="form-group " id="gameImage2edit" >
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3"><span class="required"></span></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <img src="data:image/jpeg;base64,${encodedGameImage2}" alt="..."floatRight width="200" height="200">

                                        </div>
                                    </div>  

                                </c:if>  



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
                                        <form:input path="gameStartDate" id="gameStartDate" name="gameStartDate" type="text" class="form-control"  placeholder="Game Start date" required ="required" />                                 
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
                $('#gameExpiryDate').datetimepicker({
                //autoclose: true,
                format: 'DD-MM-YYYY HH:mm:ss'
                        //format: 'dd MMM yyyy HH:mm:ss zzz'
                        // startDate: d

                });
                $('#gameStartDate').datetimepicker({
                //autoclose: true,
                format: 'DD-MM-YYYY HH:mm:ss'
                        //format: 'dd MMM yyyy HH:mm:ss zzz'
                        // startDate: d

                });
                });
                jQuery(document).ready(function ($) {

                // success alert
                $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
                $("#success-alert").slideUp(500);
                });
                // error alert
                $("#success-error").fadeTo(2000, 500).slideUp(500, function(){
                $("#success-error").slideUp(500);
                });
                /*
                 // Hide gameText div
                 $('#gameText').hide();
                 $('#gameText2').hide();
                 // Remove requred for gameText1
                 $("#gameText1").prop("required", false);
                 
                 // Hide gameImage div
                 $('#gameImage').hide();
                 $('#gameImage1edit').hide();
                 // Remove requred for gameImage1
                 $("#gameImage1").prop("required", false);
                 
                 */

                var gameType1 = $('#gameType').val();
                //console.log(" gameType1 ::" + gameType);
                if (gameType1 == "1") {
                // Since gameType is Picutre, then Hide gameText div and remove required attribute
                $('#gameText').hide();
                $('#gameText2').hide();
                $("#gameText1").prop("required", false);
                // Show gameImage since gameType is picture and sett attribute required = true
                $('#gameImage').show();
                $('#gameImage1edit').show();
                $("#gameImage1").prop("required", true);
                //show the second image upload field
                $('#gameImage2').show();
                $('#gameImage2edit').show();
                $("#gameImage2").prop("required", true);
                } else {
                // console.log(" gameType is Question ::" + gameText);

                // Since gameType is text question, then Hide gameImage div and remove required attribute
                $('#gameImage').hide();
                $('#gameImage1edit').hide();
                $("#gameImage1").prop("required", false);
                // Hide the second image upload field
                $('#gameImage2').hide();
                $('#gameImage2edit').hide();
                $("#gameImage2").prop("required", false);
                // Show gameText since gameType is picture and sett attribute gameText1 required = true
                $('#gameText').show();
                //$('#gameText2').show();
                //$('#gameText2').hide();
                $("#gameText1").prop("required", true);
                }

                
                


                $("#gameType").change(function (event) {


                //console.log("else gameText ::" + gameText);
                var gameType = $('#gameType').val();
                //console.log(" gameType ::" + gameType);
                if (gameType == "1") {
                // console.log(" gameType is Picture ::" + gameText);
                // 
                // Since gameType is Picutre, then Hide gameText div and remove required attribute
                $('#gameText').hide();
                $('#gameText2').hide();
                $("#gameText1").prop("required", false);
                // Show gameImage since gameType is picture and sett attribute required = true
                $('#gameImage').show();
                $('#gameImage1edit').show();
                $("#gameImage1").prop("required", true);
                // Show the second image upload field on drop down changed to text based
                $('#gameImage2').show();
                $('#gameImage2edit').show();
                $("#gameImage2").prop("required", true);
                } else {
                // console.log(" gameType is Question ::" + gameText);

                // Since gameType is text question, then Hide gameImage div and remove required attribute
                $('#gameImage').hide();
                $('#gameImage1edit').hide();
                $("#gameImage1").prop("required", false);
                // Hide the second image field
                $('#gameImage2').hide();
                $('#gameImage2edit').hide();
                $("#gameImage2").prop("required", false);
                // Show gameText since gameType is picture and sett attribute gameText1 required = true
                $('#gameText').show();
                //$('#gameText2').show();
                $("#gameText1").prop("required", true);
                }


                });
                });
                function searchViaAjax() {

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
                        //"gameImage": gameImage,
                        "createdBy": createdBy,
                        "file": gameImage
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


            <script type="text/javascript">
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

                if (file.length == 0) {
                alert('Please upload a passport');
                $('#file').focus();
                return false;
                } else {

                var checkimg = image.toLowerCase();
                if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)) {
                console.log("I am insie checking");
                alert("Please upload Image File Extensions .jpg,.png,.jpeg");
                $('#file').focus();
                return false;
                }
                console.log("Outside checking");
                }

                if (file2.length == 0) {
                alert('Please upload an item');
                $('#file2').focus();
                return false;
                } else {

                var checkimg = image.toLowerCase();
                if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)) {
                console.log("I am insie checking");
                alert("Please upload Image File Extensions .jpg,.png,.jpeg");
                $('#file2').focus();
                return false;
                }
                console.log("Outside checking");
                }

                var r = confirm("Do you want to Submit?");
                if (r == true) {
                frm.submit();
                } else {
                return false;
                }

                return true;
                }
                ;

            </script>