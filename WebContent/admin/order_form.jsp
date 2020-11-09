<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Order - Game store</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Edit Order ID: ${order.orderId}</h2>
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>
	
	<form action="update_order" method="post" id="orderForm">
	<div align="center">
		<table>
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Order date: </b></td>
				<td>${order.orderDate}</td>
			</tr>
			<tr>
				<td><b>Recipient Name: </b></td>
				<td><input type="text" name="recipientName" value="${order.recipientName}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Recipient Phone: </b></td>
				<td><input type="text" name="recipientPhone" value="${order.recipientPhone}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Ship to: </b></td>
				<td><input type="text" name="shippingAddress" value="${order.shippingAddress}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>
					<select name="paymentMethod">
						<option value="Cash On Delivery">Cash On Delivery</option>
					</select>
        		</td>
			</tr>
			<tr>
				<td><b>Order status: </b></td>
				<td>
					<select name="orderStatus">
						<option value="Processing" <c:if test="${order.status eq 'Processing'}">selected='selected'</c:if> >Processing</option>
						<option value="Shipping" <c:if test="${order.status eq 'Shipping'}">selected='selected'</c:if> >Shipping</option>
						<option value="Delivered" <c:if test="${order.status eq 'Delivered'}">selected='selected'</c:if> >Delivered</option>
						<option value="Completed" <c:if test="${order.status eq 'Completed'}">selected='selected'</c:if> >Completed</option>
						<option value="Cancelled" <c:if test="${order.status eq 'Cancelled'}">selected='selected'</c:if> >Cancelled</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<h2>Ordered Games</h2>
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Game Title</th>
				<th>Creator</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
				<th></th>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${orderDetail.game.title}</td>
					<td>${orderDetail.game.creator}</td>
					<td>
						<input type="hidden" name="price" value="${orderDetail.game.price}" />
						<fmt:formatNumber value="${orderDetail.game.price}" type="currency" />
					</td>
					<td>
						<input type="hidden" name="gameId" value="${orderDetail.game.gameId}" />
						<input type="text" name="quantity${status.index+1}" value="${orderDetail.quantity}" size="5" />
					</td>
					<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /></td>
					<td><a href="remove_game_from_order?id=${orderDetail.game.gameId}">Remove</a></td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4" align="right"><b><i>TOTAL:</i></b></td>
				<td><b>${order.gameCopies}</b></td>
				<td><b><fmt:formatNumber value="${order.total}" type="currency" /></b></td>
				<td></td>
			</tr>
		</table>
	</div>
	<br/>
	<div align="center">
		<a href="javascript:showAddGamePopup()"><b>Add Games</b></a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="Save" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Cancel" onclick="javascript:window.location.href='list_order';" />
		
	</div>
	</form>
	
	<jsp:directive.include file="footer.jsp" />
	
	<script>
		function showAddGamePopup(){
			var width=600;
			var height=250;
			var left=(screen.width-width)/2;
			var top=(screen.height-height)/2;
			
			window.open('add_game_form', '_blank',
					'width='+width+ ', height='+height+ ', top='+top+', left='+left);
		}
		
		$(document).ready(function(){
			$("#orderForm").validate({
				rules: {
					recipientName: "required",
					recipientPhone: "required",
					shippingAddress: "required",
					
					<c:forEach items="${order.orderDetails}" var="game" varStatus="status">
						quantity${status.index+1} : {
							required:true,
							number:true,
							min: 1
						},
					</c:forEach>
				},
				
				messages: {
					recipientName: "Recipient name is required!",
					recipientPhone: "Recipient Phone is required!",
					shippingAddress: "Shipping Address is required!",
					
					<c:forEach items="${order.orderDetails}" var="game" varStatus="status">
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
</body>
</html>