<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="menu.jsp" />
<%
//out.print(request.getAttribute("username"));
//out.print(request.getAttribute("role"));
String role = request.getAttribute("role").toString();
String username = request.getAttribute("username").toString();
%>
<h1>Lista facturilor existente</h1>

<table  border="1">
	<tr><th>Cod</th><th>Id produs</th><th>Cantitate</th><tr>	

	<c:forEach items="${vanzari}" var="vanzare">
		<tr>
		    <td>${vanzare.codFactura}</td>
			<td>${vanzare.idProdus}</td>
			<td>${vanzare.cantitate}</td>
		</tr>
	</c:forEach>

</table>


</body>
</html>