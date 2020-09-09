<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<% 
String role = request.getAttribute("role").toString();
String username = request.getAttribute("username").toString();
%>
<body>
<jsp:include page="menu.jsp" />
	<h1>Adauga un producator:</h1>
	<p style="color: green">${adaugat}</p>
	<p style="color: red">${neadaugat}</p>
	
	<form action="producatori?username=${username}" method="POST">  <!-- by default e GET -->
	<!--  folosim POST pentru ca facem modificare pe server: adaugam un nou produs -->
  
  Denumirea:<br>
  <input type="text" name="denumire" value=""><br/>
  Adresa:<br>
  <input type="text" name="adresa" value=""><br/>
  <input type="submit" value="Adauga producatorul in baza de date">
  <!-- Cand userul apasa pe butonul de submit, browserul trimite un HTTP request catre server
  cu toti parametrii pe care i-a completat userul in formular -->
	</form> 

</body>
</html>