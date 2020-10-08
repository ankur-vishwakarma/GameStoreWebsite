<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
		<div>
			<a href="${pageContext.request.contextPath}/admin/">
				<img alt="logo" src="../images/GamestoreAdminLogo.png" />
			</a>
		</div>
		
		<div>
		   Welcome, <c:out value="${sessionScope.useremail}"></c:out> |  <a href="logout">Logout</a>
		   <br/><br/>
		</div>
		
		<div id="headermenu">
			<div >
				<a href="list_users">
					<img alt="user" src="../images/user.png" height="50px" width="50px" /><br/>Users
				</a>
			</div>
			
			<div >
			<a href="list_category">
				<img alt="user" src="../images/category.png" height="50px" width="50px" /><br/>
				Categories</a>
			</div>
			
			<div >
			<a href="list_games">
				<img alt="user" src="../images/game.png" height="50px" width="50px" /><br/>
				Games</a>
			</div>
			
			<div >
			<a href="list_customer">
				<img alt="user" src="../images/customer.png" height="50px" width="50px" /><br/>
				Customers</a>
			</div>
			
			<div>
			<a href="list_review">
				<img alt="user" src="../images/review.png" height="50px" width="50px" /><br/>
				Reviews</a>
			</div>
			
			<div >
			<a href="list_order">
				 <img alt="user" src="../images/order.png" height="50px" width="50px" /><br/>
				 Orders</a>
			</div>
		</div>
</div>