<%-- 
    Document   : tzJsPage
    Created on : Nov 28, 2017, 3:33:33 PM
    Author     : Olakunle Awotunbo
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!--Include outside header-->
<%--<%@ include file="includes/outside/header.jsp" %>--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <form method="post" id="tzForm" action="tzValueHandler">
            <input id="tzInput" type="hidden" name="timeZoneOffset"><br>
            <input id="requestedUrl" type="hidden" name="requestedUrl" value="${requestedUrl}">
           <!--help-->
        </form>
        <%--<%@ include file="includes/outside/footer.jsp" %>--%>
         <script>
        var date = new Date();
        var offSet = date.getTimezoneOffset();
        document.getElementById("tzInput").value = offSet;
        document.getElementById("tzForm").submit();
    </script>

    <%--
        <script src="<c:url value='/resources/js/jquery.min.js' />"></script> 
          <script>
            var date = new Date();
            var offSet = date.getTimezoneOffset();
            console.log("here here");
            document.getElementById("tzInput").value = offSet;
            // document.getElementById("tzForm").submit();

            //var requestedUrl = $('#requestedUrl').val();
            var tzInput = document.getElementById("tzInput").value;
            var requestedUrl = document.getElementById("requestedUrl").value;

            var json = {

                "requestedUrl": requestedUrl,
                "timeZoneOffset": offSet

            };

            $.ajax({
                url: "${pageContext.request.contextPath}/tzValueHandler2",
                type: "get", //send it through get method
                data: {
                    requestedUrl : requestedUrl,
                    timeZoneOffset : tzInput,
                  
                },
                success: function (response) {
                    //Do Something
                    console.log("SUCCESS", response);
                },
                error: function (xhr) {
                    //Do Something to handle error
                    console.log("ERROR ", xhr);
                }
            });
            
        </script>
--%>
    </body>
</html>