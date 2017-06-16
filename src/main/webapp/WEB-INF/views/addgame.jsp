<%-- 
    Document   : addgame
    Created on : Jun 16, 2017, 12:24:42 AM
    Author     : Olakunle Awotunbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
          <form action="${pageContext.request.
                                    contextPath}/api/game/listgames"  id="savingsForm" method="post">
                        <div class="form-group">
                            <label class="col-sm-4 control-label tooltips" data-toggle="tooltip" title="Select the product to upload transactions for">Product <span class="asterisk">*</span></label>
                            <div class="col-sm-4">
                                <select  cssClass="width300" id="gameplaytype"  name="productId"  required="required"  >
                                    <option value="0">Choose Product</option>>
                                    <c:forEach  var="gamePlayItem" items="${gameplaytype}" varStatus="status" >

                                        <option value="${gamePlayItem.id}">${gamePlayItem.typeName}</option>
                                    </c:forEach>
                                </select>

                            </div>
                        </div><!-- form-group -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label tooltips" data-toggle="tooltip" title="Select the product to upload transactions for">Select Game <span class="asterisk">*</span></label>
                            <div class="col-sm-4">
                                <select  cssClass="width300" id="gameList"  name="gameList"  required="required"  >
                                    <option value="0">Choose Product</option>>
                                    <c:forEach  var="gameItem" items="${gameList}" varStatus="status" >

                                        <option value="${gameItem.id}">${gameItem.gameName}</option>
                                    </c:forEach>
                                </select>

                            </div>
                        </div>
                        
                        <!-- form-group -->
                        <div class="form-group" >
                            <label class="col-sm-4 control-label tooltips" data-toggle="tooltip" title="Enter the total number of records in the file to upload">rize Of Winners</label>
                            <div class="col-sm-3">
                                <input name="prizeOfWinners" class="form-control"  id="prizeOfWinners" required="required" />
                            </div>
                            <div>

                            </div>
                        </div><!-- form-group --> 
          </form>
    </body>
</html>
