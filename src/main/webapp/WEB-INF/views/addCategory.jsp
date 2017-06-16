<%-- 
    Document   : addCategory
    Created on : Jun 16, 2017, 12:49:07 AM
    Author     : Olakunle Awotunbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700%7CRoboto%7CJosefin+Sans:100,300,400,500" rel="stylesheet" type="text/css">

    <!--
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://unpkg.com/scrollreveal/dist/scrollreveal.min.js"></script>
    -->
     <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>"rel="stylesheet"></link>      
    <link href="<c:url value='/resources/css/style.css' />"  rel="stylesheet"></link>                
    <script src="<c:url value='/resources/js/scrollreveal.min.js' />"></script> 
    <script src="http://code.jquery.com/jquery-2.1.1.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Category</title>
    </head>
    <body>
        <h1>Hello World!</h1>

<!--        <form   id="addGame-form">


             form-group 
            <div class="form-group" >
                <label class="col-sm-4 control-label tooltips" data-toggle="tooltip" title="Enter the total number of records in the file to upload">Game Name</label>
                <div class="col-sm-3">
                    <input name="gameName" class="form-control"  id="gameName" required="required" />
                </div>
                <div>

                </div>
            </div> form-group  

            <div class="form-group" >
                <label class="col-sm-4 control-label tooltips" data-toggle="tooltip" title="Enter the gameCode">Game Code</label>
                <div class="col-sm-3">
                    <input name="gameCode" class="form-control"  id="gameCode" required="required" />
                </div>
                <div>

                </div>
            </div>

            <div class="form-group" >
                <label class="col-sm-4 control-label tooltips" data-toggle="tooltip" title="Enter the createdBy">Created By</label>
                <div class="col-sm-3">
                    <input name="createdBy" class="form-control"  id="createdBy" required="required" />
                </div>
                <div>

                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label"></label>
                <div class="col-sm-4">

                    <button type="submit" onclick="register()" class="btn btn-danger mr5" id="uploadBtn">Submit</button>   
                     <button type="submit" onclick="register()" class="btn btn-danger mr5" id="uploadBtn">Submit</button>   

                </div>
            </div>
        </form>
          -->
        <nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Spring 4 MVC Ajax Hello World</a>
		</div>
	</div>
</nav>

<div class="container" style="min-height: 500px">

	<div class="starter-template">
		<h1>Search Form</h1>
		<br>

		<div id="feedback"></div>

		<form class="form-horizontal" id="search-form">
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">gameName</label>
				<div class="col-sm-10">
					<input type=text class="form-control" id="gameName">
				</div>
			</div>
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">gameCode</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="gameCode">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="bth-search"
						class="btn btn-primary btn-lg">Search</button>
				</div>
			</div>
		</form>

	</div>

</div>

<div class="container">
	<footer>
		<p>
			© <a href="http://www.mkyong.com">Mkyong.com</a> 2015
		</p>
	</footer>
</div>

      
       <!--<script src="jquery.min.js"></script>-->
        <!--<script src="<c:url value='/resources/js/jquery-3.2.1.min.js' />"></script>-->    
<!--<script type="text/javascript">
    
    var serverContext = [[@{/}]];
 
function register(){
    $(".alert").html("").hide();
    var formData= $('form').serialize();
    //$.post(serverContext + "/api/game/create",formData ,function(data){
    $.post(serverContext + "/api/game/create",formData ,function(data){
         headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    }
        if(data.message == "success"){
            window.location.href = serverContext +"/successRegister.html";
        }
    })
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("MailError") > -1)
        {
            window.location.href = serverContext + "/emailError.html";
        }
        else if(data.responseJSON.error.indexOf("InternalError") > -1){
            window.location.href = serverContext + 
              "/login.html?message=" + data.responseJSON.message;
        }
        else if(data.responseJSON.error == "UserAlreadyExist"){
            $("#emailError").show().html(data.responseJSON.message);
        }
        else{
            var errors = $.parseJSON(data.responseJSON.message);
            $.each( errors, function( index,item ){
                $("#"+item.field+"Error").show().html(item.defaultMessage);
            });
            errors = $.parseJSON(data.responseJSON.error);
            $.each( errors, function( index,item ){
                $("#globalError").show().append(item.defaultMessage+"<br>");
            });
 }
}
    
    
    
   </script>-->


<script>
	jQuery(document).ready(function($) {

		$("#search-form").submit(function(event) {
                    var formData= $('addGame-form').serialize();
			// Disble the search button
			enableSearchButton(false);

			// Prevent the form from submitting via the browser.
			event.preventDefault();

			searchViaAjax();

		});

	});

	function searchViaAjax() {

		var search = {}
		search["gameName"] = $("#gameName").val();
		search["gameCode"] = $("#gameCode").val();
               //  var json = {"gameName": gameName, "gameCode": gameCode};

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/api/game/create",
			data : JSON.stringify(search),                       
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
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

                        

    </body>
</html>
