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
<h1>Lista persoanelor</h1>

<table  border="1">
	<tr><th>Id</th><th>Username</th><th>Firstname</th><th>Lastname</th><th>Age</th><th>Password</th><th>Role</th><tr>	

	<c:forEach items="${persoane}" var="persoana">
		<tr>
		    <td>${persoana.id}</td>
			<td>${persoana.username}</td>
			<td>${persoana.firstname}</td>
			<td>${persoana.lastname}</td>
			<td>${persoana.age}</td>
			<td>${persoana.password}</td>
			<td>${persoana.role}</td>
		</tr>
	</c:forEach>

</table>


</body>
</html>