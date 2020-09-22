<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Posted - Online Gamestore</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">

			<table class="normal" width="60%">
				<tr>
					<td><h2>Your Reviews</h2></td>
					<td>&nbsp;</td>
					<td><h2>${loggedCustomer.fullname}</h2></td>
				</tr>
				<tr>
					<td colspan="3"><hr/></td>
				</tr>
				<tr>
					<td>
						<span id="game-title">${game.title}</span><br/>
						<img src="data:image/jpg;base64,${game.base64Image}" width="240" hieght="300" />
					</td>
					<td colspan="2">
						<h3>Your Review has been posted. Thank You!</h3>
					</td>
				</tr>
			</table>
		
		
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>

</html>