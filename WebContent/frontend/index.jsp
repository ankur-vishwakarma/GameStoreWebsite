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
				<div style="display: inline-block; margin: 20px;">
					<div>
						<a href="view_game?id=${game.gameId}"> <img
							src="data:image/jpg;base64,${game.base64Image}" width="128"
							hieght="164" />
						</a>
					</div>
					<div>
						<a href="view_game?id=${game.gameId}"> <b>${game.title}</b>
						</a>
					</div>
					<div>
						<jsp:directive.include file="game_rating.jsp" />
					</div>
					<div>
						<i>By ${game.creator}</i>
					</div>
					<div>
						<b>Rs.${game.price}</b>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>