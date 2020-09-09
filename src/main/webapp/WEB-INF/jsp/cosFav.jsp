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
<h1>Lista produselor tale favorite</h1>

<p style="color: red">${error}</p>

<table  border="1">
	<tr><th>Id</th><th>Denumire</th><th>Pret</th><th>Tip</th><th>Producator</th>
	<c:forEach items="${cosFavJsp}" var="produs">
		<tr>
		    <td>${produs.id}</td>
		    <td>${produs.denumire}</td>
		    <td>${produs.pret}</td>
		    <td>${produs.denumireTip}</td>
		    <td>${produs.denumireProducator}</td>
		</tr>
	</c:forEach>

</table>


</body>
</html>