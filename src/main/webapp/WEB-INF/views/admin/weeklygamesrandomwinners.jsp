<%-- 
    Document   : weeklygamesrandomwinners
    Created on : 24-Aug-2017, 21:26:23
    Author     : BELLO
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
                        <h2>Random Winners List</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                       <table id="datatable-responsive" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>S/N</th>
                                    <th>GAME CATEGORY</th>
                                    <th>USER PHONE NO</th>                                    
                                    <th>USER ANSWER</th>
                                    <th>WEEK NO</th>
                                    <!--<th></th>
                                    <th></th>
                                    <th></th>-->

                                </tr>
                            </thead>
                            <tbody>
                                 <c:forEach items="${weeklyGameAnswer}" var="item" varStatus = "status">
                                     <tr>  
                                        <td><c:out value="${status.index + 1}"/></td> 
                                        <td><c:out value="${item.gameId}"/></td>  
                                        <td><c:out value="${item.userPhoneNo}"/></td>                                          
                                        <td><c:out value="${item.userAnswer}"/></td> 
                                        <td><c:out value="${item.weekNo}"/></td> 
                                         <!-- <td><a href="<c:url value='/admin/generate-RandomWinners-${item.id}' />" class="btn btn-success ">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i> Generate Random Winners
                                            </a>
                                        </td>
                                        <td><a href="<c:url value='/admin/edit-weeklyGames-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit
                                            </a>
                                        </td>
                                        <td><a href="<c:url value='/admin/delete-weeklyGames-${item.id}' />" class="btn btn-danger custom-width"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Delete
                                            </a>
                                        </td>
                                    </tr>-->  
                                
                               </c:forEach>             
                            </tbody>
                        </table>


                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>         



        <%@ include file="../includes/footer.jsp" %>

        <script>




            jQuery(document).ready(function ($) {

                $("#gameCategory-form").submit(function (event) {
                    var formData = $('addGame-form').serialize();
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
                var gameName = $('#gameName').val();
                var gameCode = $('#gameCode').val();
                var createdBy = "test user";
                var enabled = $("#enabled").is(":checked");


                if (enabled) {
                    enabled = 1;
                } else {
                    enabled = 0;
                }
                console.log("enabled ", enabled);

                var json = {
                    "id": id,
                    "gameName": gameName,
                    "gameCode": gameCode,
                    "createdBy": createdBy,
                    "enabled": enabled

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
                        display(data);
                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                        display(e);
                        jQuery("#submitResponse").css("display", "none");
                    },
                    done: function (e) {
                        console.log("DONE");
                        enableSearchButton(true);
                    }
                    
                    $("#gameCategory-form")[0].reset();
                });

            }

            function enableSearchButton(flag) {
                $("#btn-search").prop("disabled", flag);
            }

            function display(data) {
                var json = "<h4>Ajax Response</h4><pre>"
                        + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);
            }
        </script>


