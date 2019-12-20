<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Welcome to Landing Page</title>
</head>
<body>
	<p>
		Welcome to the landing page! This page is open to the public ... no login required :-)
	</p>
	
	<p>
		<a href="${pageContext.request.contextPath}/employees">Access Secure Site (requires login)</a>
	</p>
</body>
</html>