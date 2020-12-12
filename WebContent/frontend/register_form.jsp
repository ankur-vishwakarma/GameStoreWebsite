<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Registration</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			Customer Registration
		</h2>
		<hr width="60%" />
	</div>
	
	<div align="center">
		<form action="register_customer" method="post" id="customerForm">
		
		<jsp:directive.include file="../common/customer_form.jsp" />
		
		</form>
	</div>
	
	
	
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript" src="js/customer-form.js"></script>
</html>