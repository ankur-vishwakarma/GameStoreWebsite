<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${game.title} - Game store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<table width="80%" style="border:0;">
			<tr>
				<td colspan="3" align="left">
					<h2>${game.title}</h2> by ${game.creator}
				</td>
			</tr>
			<tr>
				<td rowspan="2" align="left">
					<img src="data:image/jpg;base64,${game.base64Image}" width="240" hieght="300" />
				</td>
				<td valign="top" align="left">
					<jsp:directive.include file="game_rating.jsp" /> &nbsp;&nbsp;
					<a href="#reviews">${fn:length(game.reviews)} Reviews</a>
				</td>
				<td valign="top" rowspan="2" width="20%">
					<h2>Rs.${game.price}</h2>
					<br/><br/>
					<button type="submit">Add to Cart</button>
				</td>
			</tr>
			<tr>
				<td valign="top" style="text-align:justify;">
					${game.description}
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td><h2><a id="reviews">Customer Reviews</a></h2></td>
				<td colspan="2" align="center">
					<button id="buttonWriteReview">Write a Customer Review</button>
				</td>
			</tr>
			
			<tr>
				<td colspan="3" align="left">
					<table class="normal" >
						<c:forEach items="${game.reviews}" var="review">
							<tr>
								<td>
									<c:forTokens items="${game.ratingStars}" delims="," var="star">
										<c:if test="${star eq 'on' }">
											<img alt="*" src="images/rating_on.png" />
										</c:if>
										<c:if test="${star eq 'off' }">
											<img alt="*" src="images/rating_off.png" />
										</c:if>
									</c:forTokens>
									- <b>${review.headline}</b>
								</td>
							</tr>
							<tr>
								<td>
									by ${review.customer.fullname} on ${review.reviewTime}
								</td>
							</tr>
							<tr><td><i>${review.comment}</i></td></tr>
							<tr><td>&nbsp;</td></tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			
		</table>

	</div>
	<jsp:directive.include file="footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function(){
			$("#buttonWriteReview").click(function() {
				window.location = 'write_review?game_id=' + ${game.gameId};
		    });
		});
	</script>
</body>
</html>