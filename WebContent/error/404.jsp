<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page Not Found Error</title>
</head>
<body>
<div align="center">
	<div >
		<img alt="logo" src="${pageContext.request.contextPath}/images/GamestoreLogo.png" />
	</div>
		
	<div>
		<h2>Sorry, the requested page could not be found!</h2>
	</div>
	
	<div>
		<a href="javascript:history.go(-1);">Go Back</a>
	</div>
</div>
</body>
</html>