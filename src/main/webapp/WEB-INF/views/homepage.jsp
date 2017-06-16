<%-- 
    Document   : homepage
    Created on : Jun 13, 2017, 10:12:57 PM
    Author     : olakunle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://ww
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <span>Dear <strong>${loggedinuser}</strong>
        <h1>Hello World! this is homepage</h1>
       
             <c:if test="${!empty gameList}">  
          <div class="media-body">
             <h4>LIST ALL BANKS</h4>
          </div>
	     <table id="bank-list" class="table table-striped table-bordered responsive">  
		 <thead>
	      <tr>  
	       <th>S/N</th>  
	       <th>Game Category</th>
	   
	       <th> </th>
	       <th></th>  
	      </tr>  
            </thead>
            <tbody>
	      <c:forEach items="${gameList}" var="game" varStatus = "status">  
	       <tr>  
		<td><c:out value="${status.index + 1}"/></td>  
		<td><c:out value="${game.gameName}"/></td>  
		                
		<td align="center"><a href="bank/edit/${game.id}">Edit</a></td>  
                <td><a href="bank/delete/${game.id}">Delete</a></td>
	       </tr>  
	      </c:forEach>  
		  </tbody>
	     </table>  
   </c:if>  
    </body>
</html>
