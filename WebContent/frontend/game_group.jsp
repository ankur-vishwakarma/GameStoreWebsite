<div style="display: inline-block; margin: 20px;">
	<div>
		<a href="view_game?id=${game.gameId}"> <img
			src="data:image/jpg;base64,${game.base64Image}" width="128"
			hieght="164" />
		</a>
	</div>
	<div>
		<a href="view_game?id=${game.gameId}"> <b>${game.title}</b>
		</a>
	</div>
	<div>
		<jsp:directive.include file="game_rating.jsp" />
	</div>
	<div>
		<i>By ${game.creator}</i>
	</div>
	<div>
		<b>Rs.${game.price}</b>
	</div>
</div>