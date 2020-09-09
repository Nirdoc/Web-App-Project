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
<h1>Lista tipurile existente in Supermarket</h1>

<table  border="1">
	<tr><th>Id</th><th>Denumire</th><th>Raion</th><tr>	

	<c:forEach items="${tipuri}" var="tip">
		<tr>
		    <td>${tip.id}</td>
			<td>${tip.denumire}</td>
			<td>${tip.denumireRaion}</td>
		</tr>
	</c:forEach>

</table>



</body>
</html>