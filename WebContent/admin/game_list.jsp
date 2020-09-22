<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Games - Game store</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Game Management</h2>
		<hr width="60%" />
		<h3><a href="new_game">Create New Game</a></h3>
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="5" >
			<tr>
				<th>Index</th>
				<th>Id</th>
				<th>Image</th>
				<th>Title</th>
				<th>Creator</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach var="game" items="${listGames}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${game.gameId}</td>
					
					<td>
						<img src="data:image/jpg;base64,${game.base64Image}" width="84" hieght="110" />
					</td>
					
					<td>${game.title}</td>
					<td>${game.creator}</td>
					<td>${game.category.name}</td>
					<td>Rs. ${game.price}</td>
					<td><fmt:formatDate pattern='MM/dd/yyyy' value='${game.published}' /></td>
					<td>
						<a href="edit_game?id=${game.gameId}">Edit</a> &nbsp;
						<a href="javascript:void(0);" class="deleteLink" id="${game.gameId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	
	<jsp:directive.include file="footer.jsp" />
	
	<script>
		$(document).ready(function(){
			$(".deleteLink").each(function(){
				$(this).on("click",function(){
					gameId = $(this).attr("id");
					if(confirm('Are you sure you want to delete the game with ID '+ gameId + ' ?')){
						window.location='delete_game?id=' + gameId;
					}
				});
			});
		});	
		
			
	</script>
</body>
</html>