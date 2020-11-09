<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Game to Order</title>
</head>
<body>
   	<div align="center">
   		<h2>Add Game to Order Id: ${order.orderId}</h2>
   		<form action="add_game_to_order" method="post">
   			<table>
   				<tr>
   					<td>Select a game:</td>
   					<td>
   						<select name="gameId">
   							<c:forEach items="${listGame}" var="game" varStatus="status">
   								<option value="${game.gameId}">${game.title} - ${game.creator}</option>
   							</c:forEach>
   						</select>
   					</td>
   				</tr>
   				<tr><td>&nbsp;</td></tr>
   				<tr>
   					<td>Number of Copies:</td>
   					<td>
   						<select name="quantity">
   							<option value="1">1</option>
   							<option value="2">2</option>
   							<option value="3">3</option>
   							<option value="4">4</option>
   							<option value="5">5</option>
   						</select>
   					</td>
   				</tr>
   				<tr><td>&nbsp;</td></tr>
   				<tr>
   					<td colspan="2" align="center">
   						<input type="submit" value="Add" />
   						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   						<input type="button" value="Cancel" onclick="javascript: self.close();" />
   					</td>
   				</tr>
   			</table>
   		</form>
   	</div>
</body>
</html>