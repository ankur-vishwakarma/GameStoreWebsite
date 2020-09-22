<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Game store</title>
	<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Administrative Dashboard</h2>
		<hr width="60%" />
	</div>
	
	<div align="center">
		<h2 class="pageheading">Quick Actions</h2>
		<b>
		<a href="create_game">New Game</a> &nbsp;
		<a href="create_game">New User</a> &nbsp;
		<a href="create_game">New Category</a> &nbsp;
		<a href="create_game">New Customer</a> &nbsp;
		</b>
		<hr width="60%" />
	</div>
	
	<div align="center">
		<h2 class="pageheading">Recent Sales</h2>
		<hr width="60%" />
	</div>
	
	<div align="center">
		<h2 class="pageheading">Recent Reviews</h2>
		<hr width="60%" />
	</div>
	
	<div align="center">
		<h2 class="pageheading">Statistics</h2>
		<hr width="60%" />
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>