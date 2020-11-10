<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Game store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<div align="center" style="width: 80%; margin: 0 auto;">
			<h2>New Games</h2>
			<c:forEach items="${listNewGames}" var="game">
				<jsp:directive.include file="game_group.jsp" />
			</c:forEach>
		</div>
		
		<div class="next-row">
			<h2>Best-Selling Games</h2>
			
			<c:forEach items="${listBestSellingGames}" var="game">
				<jsp:directive.include file="game_group.jsp" />
			</c:forEach>
		</div>
		
		<div class="next-row">
			<h2>Best-Selling Games</h2>
			
			<c:forEach items="${listMostFavoredGames}" var="game">
				<jsp:directive.include file="game_group.jsp" />
			</c:forEach>
		</div>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>