<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
<h1>Lista produselor existente in Supermarket</h1>


<p style="color: green">${adaugat}</p>
<p style="color: red">${neadaugat}</p>
<p style="color: green">${adaugat2}</p>
<p style="color: red">${neadaugat2}</p>
<p style="color: red">${neadaugat3}</p>
<table  border="1">
	<tr><th>Id</th><th>Denumire</th><th>Pret</th><th>Stoc</th><th>Tip</th><th>Producator</th><th>Cumpara</th><th>I like it</th><tr>	

	<c:forEach items="${produse}" var="produs">
		<tr>
		    <td>${produs.id}</td>
			<td>${produs.denumire}</td>
			<td>${produs.pret}</td>
			<td>${produs.stoc}</td>
			<td>${produs.denumireTip}</td>
			<td>${produs.denumireProducator}</td>
			<td>
				<form action="cosCumparaturi?username=${username}" method="POST">
					<input type="hidden" name="idProdusParam" value="${produs.id}" />
					<input type="hidden" name="denumireProdusParam" value="${produs.denumire}" />
					<input type="hidden" name="pretProdusParam" value="${produs.pret}" />
					<input type="hidden" name="denumireTipProdusParam" value="${produs.denumireTip}" />
					<input type="hidden" name="denumireProducatorProdusParam" value="${produs.denumireProducator}" />
					<input type = "submit" value="ADAUGA IN COS"/>	
				</form>
			</td> 
			<td>
				<form action="produsFavorit?username=${username}" method="POST">
					<input type="hidden" name="idProdusParam" value="${produs.id}" />
					<input type="hidden" name="denumireProdusParam" value="${produs.denumire}" />
					<input type="hidden" name="pretProdusParam" value="${produs.pret}" />
					<input type="hidden" name="denumireTipProdusParam" value="${produs.denumireTip}" />
					<input type="hidden" name="denumireProducatorProdusParam" value="${produs.denumireProducator}" />
					<input type = "submit" value="&#128525"/>	
				</form>
			</td>
			
			
			 
		</tr>
	</c:forEach>

</table>

<a href="cosCumparaturi?username=${username}">Vizualizeaza continutul cosului de cumparaturi</a><br>
<a href="produsFavorit?username=${username}">Vizualizeaza produse favorite</a>


</body>
</html>