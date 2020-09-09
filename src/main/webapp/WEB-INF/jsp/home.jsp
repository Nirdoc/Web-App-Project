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
//out.print(request.getAttribute("username"));
//out.print(request.getAttribute("role"));
//String username = request.getAttribute("username").toString();
%>
<jsp:include page="menu.jsp" />



<h1>
<%
String username = request.getAttribute("username").toString();
if(username!=null){
out.print("Hello "+ username);
}
%> 
</h1>
<h2>Click <a href="vanzari?username=${username}">aici</a> pentru lista facturilor tale.</h2>
<h3>Supermaket</h3>

Text si poze pe aici....

</body>
</html>