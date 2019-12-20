<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">		
	<title>Welcome to spring</title>
</head>
<body>
	<h1>Welcome to Spring Security</h1>
	
	<!-- display user name and role -->
	User: <security:authentication property="principal.username"/>
	<br><br>
	Role(s): <security:authentication property="principal.authorities" />
	
	<p>
		<a href="${pageContext.request.contextPath}/leaders">LeaderShip Meeting</a>
		(Only for Managers peeps)
	</p>
	
	<p>
		<a href="${pageContext.request.contextPath}/systems">TI Systems</a>
		(Only for Admins)
	</p>
	
	<form:form method="POST" action="${pageContext.request.contextPath}/logout">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>