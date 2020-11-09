<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Game To order</title>
</head>
<body>
	<div align="center">
		<h2>
			The game ${game.title} has been added to the order ID ${order.orderId}
		</h2>
		<input type="button" value="Close" onclick="javascript: self.close();" />
	</div>
	<script>
		window.onunload = function(){
			window.opener.location.reload();
		}
	</script>
</body>
</html>