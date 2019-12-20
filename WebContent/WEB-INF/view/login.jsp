<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Custom Login Page</title>
</head>
<body>
	<h3>My Custom Login Page</h3>
	<form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
	
		<c:if test="${param.logout != null}">
			<p>Logout Success</p>
		</c:if>
	
		<c:if test="${param.error != null}">
			<i>Sorry! You entered invalid username/password</i>
		</c:if>
		<p>
			User Name: <input type="text" name="username"/>
		</p>
		<p>
			Password: <input type="password" name="password"/>
		</p>
		<input type="submit" value="Login" />
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> 
	</form>
</body>
</html>