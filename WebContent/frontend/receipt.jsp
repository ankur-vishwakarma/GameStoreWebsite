<h3>Your Payment Receipt</h3>
		<h2>Seller Information</h2>
		<table>
			<tr>
				<td><b>Email:</b></td>
				<td>sales@gamestore.com</td>
			</tr>
			<tr>
				<td><b>Phone:</b></td>
				<td>+91 5177681811</td>
			</tr>
		</table>
		
		<h2>Buyer Information</h2>
		<table>
			<tr>
				<td><b>First Name:</b></td>
				<td>${payer.firstName}</td>
			</tr>
			<tr>
				<td><b>Last Name:</b></td>
				<td>${payer.lastName}</td>
			</tr>
			<tr>
				<td><b>E-mail:</b></td>
				<td>${payer.email}</td>
			</tr>
		</table>
		
		<h2>Order Details</h2>
		<table>
			<tr>
				<td><b>Order ID:</b></td>
				<td>${orderId}</td>
			</tr>
			<tr>
				<td><b>Ordered By:</b></td>
				<td>${loggedCustomer.fullname}</td>
			</tr>
			<tr>
				<td><b>Transaction Description:</b></td>
				<td>${transaction.description}</td>
			</tr>
			
			<tr>
				<td colspan="2"><b>Items:</b></td>
			</tr>
			<tr>
				<td colspan="2">
				<table border="1">
					<tr>
						<th>No.</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Subtotal</th>
					</tr>
					<c:forEach items="${transaction.itemList.items}" var="item" varStatus="var">
						<tr>
							<td>${var.index + 1}</td>
							<td>${item.name}</td>
							<td>${item.quantity}</td>
							<td><fmt:formatNumber value="${item.price * item.quantity}" /></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5" align="right">
							<p>Subtotal: <fmt:formatNumber value="${transaction.amount.details.subtotal}" type="currency" /></p>
							<p>Tax: <fmt:formatNumber value="${transaction.amount.details.tax}" type="currency" /></p>
							<p>Shipping Fee: <fmt:formatNumber value="${transaction.amount.details.shippingFee}" type="currency" /></p>
							<p>Total: <fmt:formatNumber value="${transaction.amount.details.total}" type="currency" /></p>
						</td>
					</tr>
				</table>
				<div>
					<br/>
					<form action="execute_payment" method="post">
						<input type="hidden" name="paymentId" value="${param.paymentId}" />
						<input type="hidden" name="payerId" value="${param.payerId}" />
						<input type="submit" value="Pay Now" />
					</form>
				</div>
				</td>
			</tr>
		</table>