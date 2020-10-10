<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Shopping Cart</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2>Your Shopping Cart</h2>

		<c:if test="${message != null}">
			<div align="center">
				<h4 class="message">${message}</h4>
			</div>
		</c:if>
		
		<c:set 	var="cart" value="${sessionScope['cart']}" />
		
		<c:if test="${cart.totalItems==0}">
			<h2>No Items in Cart. <a href="${pageContext.request.contextPath}/">Continue Shopping</a>!</h2>
		</c:if>
		
		<c:if test="${cart.totalItems>0}">
			<form action="update_cart" method="post" id="cartForm">
				<div>
					<table border="1">
						<tr>
							<th>No</th>
							<th colspan="2">Game</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
							<th></th>
						</tr>
						
						<c:forEach items="${cart.items}" var="item" varStatus="status">
							<tr>
								<td>${status.index+1}</td>
								<td>
									<img src="data:image/jpg;base64,${item.key.base64Image}" width="128" hieght="164" />
								</td>
								<td>
									<span id="game-title">${item.key.title}</span>
								</td>
								<td>    
									<input type="hidden" name="gameId" value="${item.key.gameId}" />
									<input type="text" name="quantity${status.index+1}" value="${item.value}" size="5" />
								</td>
								<td><fmt:formatNumber value="${item.key.price}" type="currency" /></td>
								<td><fmt:formatNumber value="${item.value * item.key.price}" type="currency" /></td>
								<td><a href="remove_from_cart?game_id=${item.key.gameId}">Remove</a></td>
							</tr>
						</c:forEach>
						
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><b>${cart.totalQuantity} book(s)</b></td>
							<td><b>Total:</b></td>
							<td colspan="2"><b><fmt:formatNumber value="${cart.totalAmount}" type="currency" /></b></td>
						</tr>
					</table>
				</div>
				<div>
					<table class="normal">
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td></td>
							<td><button type="submit">Update</button></td>
							<td><input type="button" id="clearCart" value="Clear Cart" /></td>
							<td><a href="${pageContext.request.contextPath}/">Continue Shopping</a></td>
							<td><a href="checkout">CheckOut</a></td>
						</tr>
					</table>
				</div>
			</form>
			
		</c:if>

		
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript" >

	$(document).ready(function(){
		
		$("#clearCart").click(function(){
			window.location= "clear_cart";
		});
		$("#cartForm").validate({
			rules: {
				<c:forEach items="${cart.items}" var="item" varStatus="status">
					quantity${status.index+1} : {
							required:true,
							number:true,
							min: 1
						},
				</c:forEach>
			},
			
			messages: {
				<c:forEach items="${cart.items}" var="item" varStatus="status">
					quantity${status.index+1} : {
							required: "Please Enter Quantity",
							number: "Quantity must be a number",
							min: "Quantity must be greater than 0"
						},
				</c:forEach>
			}
		});
		
	});
	
</script>
</html>