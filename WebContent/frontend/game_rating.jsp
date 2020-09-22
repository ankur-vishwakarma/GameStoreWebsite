<c:forTokens items="${game.ratingStars}" delims="," var="star">
	<c:if test="${star eq 'on' }">
		<img alt="*" src="images/rating_on.png" />
	</c:if>
	<c:if test="${star eq 'off' }">
		<img alt="*" src="images/rating_off.png" />
	</c:if>
	<c:if test="${star eq 'half' }">
		<img alt="*" src="images/rating_half.png" />
	</c:if>
</c:forTokens>