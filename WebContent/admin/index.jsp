<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
		<a href="new_game">New Game</a> &nbsp;
		<a href="user_form.jsp">New User</a> &nbsp;
		<a href="category_form.jsp">New Category</a> &nbsp;
		<a href="customer_form.jsp">New Customer</a> &nbsp;
		</b>
		<hr width="60%" />
	</div>
	
	<div align="center">
		<h2 class="pageheading">Recent Sales</h2>
		<hr width="60%" />
		<table border="1">
			<tr>
				<th>Order ID</th>
				<th>Ordered By</th>
				<th>Game Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
			</tr>
			<c:forEach items="${listMostRecentSales}" var="order" varStatus="status" >
				<tr>
					<td><a href="view_order?id=${order.orderId}">${order.orderId}</a></td>
					<td>${order.customer.fullname}</td>
					<td>${order.gameCopies}</td>
					<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div align="center">
		<h2 class="pageheading">Recent Reviews</h2>
		<hr width="60%" />
		<table border="1">
			<tr>
				<th>Game</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review on</th>
			</tr>
			<c:forEach items="${listMostRecentReviews}" var="review" varStatus="status" >
				<tr>
					<td>${review.game.title}</td>
					<td>${review.rating}</td>
					<td><a href="edit_review?id=${review.reviewId}">${review.headline}</a></td>
					<td>${review.customer.fullname}</td>
					<td>${review.reviewTime}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div align="center">
		<h2 class="pageheading">Statistics</h2>
		<hr width="60%" />
		Total Users: ${totalUsers} &nbsp;&nbsp;&nbsp;&nbsp;
		Total Games: ${totalGames} &nbsp;&nbsp;&nbsp;&nbsp;
		Total Customers: ${totalCustomers} &nbsp;&nbsp;&nbsp;&nbsp;
		Total Reviews: ${totalReviews} &nbsp;&nbsp;&nbsp;&nbsp;
		Total Orders: ${totalOrders}
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>