<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Game store- create Game</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="..//css/richtext.min.css">


<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.css"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${game!=null}">Edit Game</c:if>
			<c:if test="${game==null}">Create New Game</c:if>
		</h2>
		<hr width="60%" />
	</div>
	
	<div align="center">
		<c:if test="${game!=null}">
			<form action="update_game" method="post" id="gameForm" enctype="multipart/form-data">
			<input type="hidden" name="gameId" value="${game.gameId}" />
		</c:if>
		<c:if test="${game==null}">
			<form action="create_game" method="post" id="gameForm" enctype="multipart/form-data">
		</c:if>
		<table class="form">
			<tr>
				<td>Category:</td>
				<td>
					<select name="category">
						<c:forEach items="${listCategory}" var="category">
							<c:if test="${category.categoryId eq game.category.categoryId}">
								<option value="${category.categoryId}" selected>
							</c:if>
							<c:if test="${category.categoryId ne game.category.categoryId}">
								<option value="${category.categoryId}">
							</c:if>
								${category.name}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input type="text" id="title" name="title" size="30" value="${game.title}"/></td>
			</tr>
			<tr>
				<td align="right">Creator:</td>
				<td align="left"><input type="text" id="creator" name="creator" size="30" value="${game.creator}"/></td>
			</tr>
			<tr>
				<td align="right">ISBN:</td>
				<td align="left"><input type="text" id="isbn" name="isbn" size="30" value="${game.isbn}"/></td>
			</tr>
			<tr>
				<td align="right">Launch Date:</td>
				<td align="left"><input type="text" id="published" name="published" size="30" 
				value="<fmt:formatDate pattern='MM/dd/yyyy' value='${game.published}' />" /></td>
			</tr>
			<tr>
				<td align="right">Game Image:</td>
				<td align="left">
					<input type="file" id="gameImage" name="gameImage" size="30" /><br/>
					<img id="thumbnail" alt="Preview" style="width:20%; margin-top: 10px" 
						src="data:image/jpg;base64,${game.base64Image}"
					/>
				</td>
			</tr>
			<tr>
				<td align="right">Price:</td>
				<td align="left"><input type="text" id="price" name="price" size="30" value="${game.price}"/></td>
			</tr>
			<tr>
				<td align="right">Description:</td>
				<td align="left">
					<textarea rows="5" cols="50" id="description" name="description">${game.description}</textarea>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	
	
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">

	$(document).ready(function(){
		$('#published').datepicker();
		$('#description').richText();
		
		$('#gameImage').change(function(){
			showImageThumbnail(this);
		});
		
		$("#gameForm").validate({
			rules: {
				category: "required",
				title: "required",
				creator: "required",
				isbn: "required",
				published: "required",
				
				<c:if test="${game==null}">
				   gameImage: "required",
				</c:if>
				
				price: "required",
				description: "required",
			},
			
			messages: {
				category: "Please select category!",
				title: "Please enter title!",
				creator: "Please enter creator!",
				isbn: "Please enter isbn!",
				published: "Please enter launch date!",
				gameImage: "Please choose image!",
				price: "Please enter price!",
				description: "Please enter description!"
			}
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
	    });
		
	});
	
	function showImageThumbnail(fileInput){
		var file=fileInput.files[0];
		
		var reader = new FileReader();
		
		reader.onload= function(e){
			$('#thumbnail').attr('src',e.target.result);
		};
		
		reader.readAsDataURL(file);
	}
</script>
</html>