<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
<h1>Adauga un produs:</h1>
<p style="color: green">${adaugat}</p>
<p style="color: red">${neadaugat}</p>

<form action="produse?username=${username}" method="POST">  <!-- by default e GET -->
<!--  folosim POST pentru ca facem modificare pe server: adaugam un nou produs -->
  
  Denumirea:<br>
  <input type="text" name="denumire" value=""><br>
  Pret:<br>
  <input type="text" name="pret" value=""><br>
  Stoc:<br>
  <input type="text" name="stoc" value=""><br><br>
  Alege tipul:
  <select name="tipId">
	  <c:forEach items="${tipuri}" var="tip">
	  	<option value="${tip.id}">${tip.denumire}</option>
	  </c:forEach>
  </select><br/>
  Alege producatorul:
  <select name="producatorId">
	  <c:forEach items="${producatori}" var="producator">
	  	<option value="${producator.id}">${producator.denumire}</option>
	  </c:forEach>
  </select><br/><br>
  <!--
  <select name="cars">
  <option value="volvo">Volvo</option>
  <option value="saab">Saab</option>
  <option value="opel">Opel</option>
  <option value="audi">Audi</option>
  </select> 
  --> 
	
   
  <!-- Cand userul apasa pe butonul de submit, browserul trimite un HTTP request catre server
  cu toti parametrii pe care i-a completat userul in formular -->
  <input type="submit" value="Adauga produsul in baza de date">
</form>


<% 
//Aici mai sus nu stiu cum sa trimit username-ul atunci cand se apasa butonul "Adauga produsul in baza de date"
%>

</body>
</html>