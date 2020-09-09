<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
String role = request.getAttribute("role").toString();
String username = request.getAttribute("username").toString();
//out.println(username);
%>
<jsp:include page="menu.jsp" />

	<h1>Adauga o persoana:</h1>
	
	<p style="color: green">${adaugat}</p>
	<p style="color: red">${neadaugat}</p>
	
	

	<form action="persoane?username=${username}" method="POST">  <!-- by default e GET -->
	<!--  folosim POST pentru ca facem modificare pe server: adaugam un nou produs -->
  
  Username:<br>
  <input type="text" name="username2" value=""><br/>
  Firstname:<br>
  <input type="text" name="firstname" value=""><br/>
  Lastname:<br>
  <input type="text" name="lastname" value=""><br/>
  Age:<br>
  <input type="text" name="age" value=""><br/>
  Password:<br>
  <input type="text" name="password" value=""><br/>
  Role:<br>
  <input type="text" name="role" value=""><br/>
  
  
  <input type="submit" value="Adauga persoana in baza de date">
  <!-- Cand userul apasa pe butonul de submit, browserul trimite un HTTP request catre server
  cu toti parametrii pe care i-a completat userul in formular -->
	</form> 


</body>
</html>