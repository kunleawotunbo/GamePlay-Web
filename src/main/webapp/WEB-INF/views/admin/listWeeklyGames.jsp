<%-- 
    Document   : listWeeklyGames
    Created on : Jun 17, 2017, 1:40:55 PM
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
             <div class="col-md-10 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Weekly Game List</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />

                        <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>S/N</th>
                                    <th>Game Week</th>
                                    <th>Game Category</th>
                                    <th>Game PlayType</th>
                                    <th>Expiry Date</th>
                                    <th>Action</th>
                                    <th></th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${weeklyGamesList}" var="item">  
                                    <tr>  
                                        <td><c:out value="${item.id}"/></td>  
                                        <td><c:out value="${item.weekNo}"/></td>  
                                        <td><c:out value="${item.gameCategory}"/></td>  
                                        <td><c:out value="${item.gamePlayType}"/></td> 
                                        <td><c:out value="${item.gameExpiryDate}"/></td> 
                                        <td><a href="<c:url value='/edit-weeklyGames-${item.id}' />" class="btn btn-success custom-width">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit
                                            </a>
                                        </td>
                                        <td><a href="<c:url value='/delete-weeklyGames-${item.id}' />" class="btn btn-danger custom-width"> 
                                                <i class="f fa fa-trash-o" aria-hidden="true"></i> Delete
                                            </a>
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
