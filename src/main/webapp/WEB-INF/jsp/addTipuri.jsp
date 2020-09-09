<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
%>
<jsp:include page="menu.jsp" />
<h1>Adauga un tip:</h1>
<p style="color: green">${adaugat}</p>
<p style="color: red">${neadaugat}</p>

<form action="tipuri?username=${username}" method="POST">  <!-- by default e GET -->
<!--  folosim POST pentru ca facem modificare pe server: adaugam un nou produs -->
  
  Denumirea:<br>
  <input type="text" name="denumire" value="">
  <select name="raionId">
	  <c:forEach items="${raioane}" var="raion">
	  	<option value="${raion.id}">${raion.denumire}</option>
	  </c:forEach>
  </select><br/>
  <!--
  <select name="cars">
  <option value="volvo">Volvo</option>
  <option value="saab">Saab</option>
  <option value="opel">Opel</option>
  <option value="audi">Audi</option>
  </select> 
  --> 
  <input type="submit" value="Adauga tipul in baza de date">
  <!-- Cand userul apasa pe butonul de submit, browserul trimite un HTTP request catre server
  cu toti parametrii pe care i-a completat userul in formular -->
</form> 

</body>
</html>