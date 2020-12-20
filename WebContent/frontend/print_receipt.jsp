<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Receipt - gamestore</title>
<link rel="stylesheet" href="../css/style.css">

</head>
<body onload="window.print();">
	<div>
		<jsp:directive.include file="receipt.jsp" />
	</div>
</body>
</html>