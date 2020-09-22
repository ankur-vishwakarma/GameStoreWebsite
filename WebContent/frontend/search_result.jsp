<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results for ${keyword} - Game store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
	<c:if test="${fn:length(result) == 0}">
		<h2>No Results for "${keyword}"</h2>
	</c:if>
	
	<c:if test="${fn:length(result) > 0}">
		<div align="left" style="width: 80%; margin: 0 auto;">
			<center><h2>Results for "${keyword}"</h2></center>
			<c:forEach items="${result}" var="game">
				<div>
				<div style="display: inline-block; margin: 20px; width: 10%;">
					<div align="left">
						<a href="view_game?id=${game.gameId}"> <img
							src="data:image/jpg;base64,${game.base64Image}" width="128"
							hieght="164" />
						</a>
					</div>
				</div>
				<div style="display: inline-block; margin: 20px; vertical-align: top; width: 50%;" align="left">
					<div>
						<h2><a href="view_game?id=${game.gameId}"> <b>${game.title}</b></h2>
						</a>
					</div>
					<div>Rating ****</div>
					<div>
						<i>By ${game.creator}</i>
					</div>
					<div>
						<p>${fn:substring(game.description,0 , 100)}...</p>
					</div>
				</div>
				<div style="display: inline-block; margin: 20px; vertical-align: top;">
					<h3>Rs.${game.price}</h3>
					<h3><a href="">Add to Cart</a></h3>
				</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>