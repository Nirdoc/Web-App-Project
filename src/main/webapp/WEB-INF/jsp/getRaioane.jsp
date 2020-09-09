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
<h1>Lista raioanelor existente in Supermarket</h1>

<table  border="1">
	<tr><th>Id</th><th>Denumire</th><tr>	

	<c:forEach items="${raioane}" var="raion">
		<tr>
		    <td>${raion.id}</td>
			<td>${raion.denumire}</td>
			
		</tr>
	</c:forEach>

</table>



</body>
</html>