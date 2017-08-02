<%-- 
    Document   : listGameCategory
    Created on : 25-Jul-2017, 08:20:52
    Author     : BELLO
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>View Game Category</h3>
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

           

        </div>


        <div class="row">
            <!-- form input mask -->
            <!--              <div class="col-md-6 col-sm-12 col-xs-12">--> 
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>List of Game Categories</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>S/N</th>
                                    <th>GAME NAME</th>
                                    <th>GAME CODE</th>
                   
                                    <th></th>
                                    

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${gameList}" var="item">  
                                    <tr>  
                                        <td><c:out value="${item.id}"/></td>  
                                        <td><c:out value="${item.gameName}"/></td>  
                                        <td><c:out value="${item.gameCode}"/></td> 
                                        <%--   <td><c:out value="${item.enabled}"/></td>  --%>
                                       
                                        <td>
                                       <c:set var = "categorylisttypetracker" scope = "session" value = "CategoryforAnswer"/>
                                  <c:choose>
                                            <c:when test = "${categorylisttype == categorylisttypetracker}">
                                                <a href="<c:url value='/admin/view-all-gameCategory-answer-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> View Category Answers
                                            </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="<c:url value='/admin/view-gameCategory-answer-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> View Active Week Answers
                                            </a>
                                             </c:otherwise>
                                        </c:choose>
                                            
                                        </td>
                                        
                                    </tr>  
                                </c:forEach>  
                            </tbody>
                        </table>


                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>         



        <%@ include file="../includes/footer.jsp" %>

        <!-- JSColor.js -->
        <script src="<c:url value='/resources/js/jscolor.min.js' />"></script> 
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

                                                    //$("#success-alert").hide();
                                                    // success alert
                                                    $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
                                                    $("#success-alert").slideUp(500);
                                                    });
                                                    
                                                    // error alert
                                                    $("#success-error").fadeTo(2000, 500).slideUp(500, function(){
                                                    $("#success-error").slideUp(500);
                                                    });
                                                    
                                                    $("#gameCategory-form").submit(function (event) {
                                                    //var formData = $('addGame-form').serialize();
                                                    // Disble the search button
                                                    enableSearchButton(false);
                                                    // Prevent the form from submitting via the browser.
                                                    event.preventDefault();
                                                    searchViaAjax();
                                                    });
                                                    });
                                                    function searchViaAjax() {
                                                    var id = $('#id').val();
                                                    var gameName = $('#gameName').val();
                                                    var gameCode = $('#gameCode').val();
                                                    var gameRules = $('#gameRules').val();
                                                    var createdBy = $('#createdBy').val();
                                                    var enabled = $("#enabled").is(":checked");
                                                    // set a variable
                                                    var gameExpiryDate = new Date();
                                                    var color = '#' + $('#color').val();
                                                    if (color === "#FFFFFF") {
                                                    alert('Please choose another color');
                                                    $('#color').focus();
                                                    return false;
                                                    }
                                                    console.log("gameExpiryDate ::" + gameExpiryDate);
                                                    /*
                                                     if (enabled) {
                                                     enabled = 1;
                                                     } else {
                                                     enabled = 0;
                                                     }
                                                     */
                                                    console.log("enabled ", enabled);
                                                    var json = {
                                                    "id": id,
                                                            "gameName": gameName,
                                                            "gameCode": gameCode,
                                                            "gameRules": gameRules,
                                                            "createdBy": createdBy,
                                                            "enabled": enabled,
                                                            "color": color
                                                    };
                                                    $.ajax({
                                                    type: "POST",
                                                            contentType: "application/json",
                                                            url: "${pageContext.request.contextPath}/api/game/create",
                                                            data: JSON.stringify(json),
                                                            dataType: 'json',
                                                            timeout: 100000,
                                                            success: function (data) {
                                                            console.log("SUCCESS: ", data);
                                                            //  display(data);
                                                            notification("Notification", "Game category added successfully.", "success");
                                                            },
                                                            error: function (e) {
                                                            console.log("ERROR: ", e);
                                                            //  display(e);
                                                            //notification("Notification", "Failed to add game category", "error");
                                                            },
                                                            done: function (e) {
                                                            console.log("DONE");
                                                            enableSearchButton(true);
                                                            }
                                                    });
                                                    $("#gameCategory-form")[0].reset();
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
            //var gameCategory = $('#gameCategory').val();
            //var gameType = $('#gameType').val();
            //var prizeOfWinners = $('#prizeOfWinners').val();
            // var noOfWinners = $('#noOfWinners').val();
            var file = $('#gameImage1').val();
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

            var r = confirm("Do you want to Submit?");
            if (r == true) {
            frm.submit();
            } else {
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