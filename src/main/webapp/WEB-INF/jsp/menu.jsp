<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">
<%
//out.print(request.getAttribute("username"));
//out.print(request.getAttribute("role"));
String role = request.getAttribute("role").toString();
String username = request.getAttribute("username").toString();
%>
    
    <a href="${pageContext.request.contextPath}/?username=${username}">Home</a> | &nbsp; 
    <c:choose>
    <c:when test="${role=='admin' }">
    <a href="${pageContext.request.contextPath}/addProduse?username=${username}">Add Products</a> | &nbsp; 
    <a href="${pageContext.request.contextPath}/produse?username=${username}">Show Products</a>| &nbsp;
    <a href="${pageContext.request.contextPath}/addProducatori?username=${username}">Add Providers</a>| &nbsp;
    <a href="${pageContext.request.contextPath}/producatori?username=${username}">Show Providers</a>| &nbsp;
    <a href="${pageContext.request.contextPath}/addRaioane?username=${username}">Add Departments</a>| &nbsp;
    <a href="${pageContext.request.contextPath}/raioane?username=${username}">Show Departments</a>| &nbsp;
    <a href="${pageContext.request.contextPath}/addSupermarkete?username=${username}">Add Supermarkets</a>| &nbsp;
    <a href="${pageContext.request.contextPath}/supermarkete?username=${username}">Show Supermarkets</a>| &nbsp;
    <a href="${pageContext.request.contextPath}/addTipuri?username=${username}">Add Types</a> | &nbsp; 
    <a href="${pageContext.request.contextPath}/tipuri?username=${username}">Show Types</a>| &nbsp;
    <a href="${pageContext.request.contextPath}/addPersoane?username=${username}">Add People</a>| &nbsp; 
    <a href="${pageContext.request.contextPath}/persoane?username=${username}">Show People</a>| &nbsp;  
    </c:when>   
    
     <c:when test="${role=='user' }">
     <a href="${pageContext.request.contextPath}/produse?username=${username}">Show Products</a>| &nbsp;
     <a href="${pageContext.request.contextPath}/producatori?username=${username}">Show Providers</a>| &nbsp;
     <a href="${pageContext.request.contextPath}/raioane?username=${username}">Show Departments</a>| &nbsp;
     <a href="${pageContext.request.contextPath}/supermarkete?username=${username}">Show Supermarkets</a>| &nbsp;
     <a href="${pageContext.request.contextPath}/tipuri?username=${username}">Show Types</a>| &nbsp;
     </c:when>  
     
     </c:choose>
     
     
        <a onclick="document.forms['logoutForm'].submit()">Logout</a>
         

    <form id="logoutForm" method="POST" action="/logout?username=${username}">
    </form>

</div>