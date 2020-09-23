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
			<h2>No Items in Cart. Continue Shopping!</h2>
		</c:if>
		
		<c:if test="${cart.totalItems>0}">
			<div>
				<form>
					<table border="1">
						<tr>
							<th>No</th>
							<th colspan="2">Game</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
							<th>
								<a href=""><b>Clear Cart</b></a>
							</th>
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
								<td>${item.value}</td>
								<td><fmt:formatNumber value="${item.key.price}" type="currency" /></td>
								<td><fmt:formatNumber value="${item.value * item.key.price}" type="currency" /></td>
								<td><a href="">Remove</a></td>
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
				</form>
			</div>
		</c:if>

		
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">

	$(document).ready(function(){
		$("#loginForm").validate({
			rules: {
				email: {
					 required: true,
					 email: true
				},
				
				password: "required",
			},
			
			messages: {
				email: {
					required: "Email is required!",
					email: "Please enter a vald email address!"
				},
				
				password: "Password is required!"
			}
		});
		
	});
	
</script>
</html>