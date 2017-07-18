<%-- 
    Document   : setupThisGameCategory
    Created on : 17-Jul-2017, 11:41:50
    Author     : BELLO
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3> Setup ${game.gameName}</h3>
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

            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <!--<h2>Input Mask</h2>-->

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

                        <div id="feedback"></div>

                        <form:form modelAttribute="game" class="form-horizontal form-label-left" id="gameCategory-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Upload Image 1<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input id="gameName" type="text" class="form-control" name="gameName" placeholder="Upload Image" required ="required" />                                  
                                </div>
                            </div>
                            <!--<div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Code<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="gameCode" id="gameCode" type="text" class="form-control" name="gameCode" placeholder="Game Code" required ="required" />                                 
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Enabled</label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:checkbox path="enabled" id="enabled" name="enabled"  data-toggle="toggle" />                                    
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Background Color<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="color" id="color" name="color"  type="text"  class="jscolor" required ="required" />                                 
                                </div>
                            </div>



                            <form:input path="createdBy" name="createdBy" value="${loggedinuser}" type="hidden" /> 
                            <div class="ln_solid"></div>-->


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
                                                <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
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
            
             <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <!--<h2>Input Mask</h2>-->

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

                        <div id="feedback"></div>

                        <form:form modelAttribute="game" class="form-horizontal form-label-left" id="gameCategory-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="gameName" id="gameName" type="text" class="form-control" name="gameName" placeholder="Input game Name" required ="required" />                                  
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Code<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="gameCode" id="gameCode" type="text" class="form-control" name="gameCode" placeholder="Game Code" required ="required" />                                 
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Enabled</label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:checkbox path="enabled" id="enabled" name="enabled"  data-toggle="toggle" />                                    
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Background Color<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="color" id="color" name="color"  type="text"  class="jscolor" required ="required" />                                 
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
                                                <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
            
            

        </div>
        
        <div class="clearfix"></div>

        <div class="row">
            <!-- form input mask -->
            <!--              <div class="col-md-6 col-sm-12 col-xs-12">--> 

            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <!--<h2>Input Mask</h2>-->

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

                        <div id="feedback"></div>

                        <form:form modelAttribute="game" class="form-horizontal form-label-left" id="gameCategory-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="gameName" id="gameName" type="text" class="form-control" name="gameName" placeholder="Input game Name" required ="required" />                                  
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Code<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="gameCode" id="gameCode" type="text" class="form-control" name="gameCode" placeholder="Game Code" required ="required" />                                 
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Enabled</label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:checkbox path="enabled" id="enabled" name="enabled"  data-toggle="toggle" />                                    
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Background Color<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="color" id="color" name="color"  type="text"  class="jscolor" required ="required" />                                 
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
                                                <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
            
             <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <!--<h2>Input Mask</h2>-->

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

                        <div id="feedback"></div>

                        <form:form modelAttribute="game" class="form-horizontal form-label-left" id="gameCategory-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <input id="gameName" type="textarea" class="form-control" name="gameName" placeholder="Input game Name" required ="required" />                                  
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Code<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="gameCode" id="gameCode" type="text" class="form-control" name="gameCode" placeholder="Game Code" required ="required" />                                 
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Enabled</label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:checkbox path="enabled" id="enabled" name="enabled"  data-toggle="toggle" />                                    
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Background Color<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="color" id="color" name="color"  type="text"  class="jscolor" required ="required" />                                 
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
                                                <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
        </div>
        
         <!-- <form:form modelAttribute="game" class="form-horizontal form-label-left" id="gameCategory-form" data-parsley-validate="">
                            <form:hidden path="id" id="id" name="id" />
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Name<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="gameName" id="gameName" type="text" class="form-control" name="gameName" placeholder="Input game Name" required ="required" />                                  
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Game Code<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="gameCode" id="gameCode" type="text" class="form-control" name="gameCode" placeholder="Game Code" required ="required" />                                 
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Enabled</label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:checkbox path="enabled" id="enabled" name="enabled"  data-toggle="toggle" />                                    
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Background Color<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-9">
                                    <form:input path="color" id="color" name="color"  type="text"  class="jscolor" required ="required" />                                 
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
                                                <button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </form:form>-->
        
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
                var createdBy = "test user";
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


