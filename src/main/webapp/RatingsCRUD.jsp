<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create, delete, update, get ratings</title>
</head>
<body>
	<form action="findrating" method="post">
		<h1>Search for ratings by UserName</h1>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/><br/>
	<h1>Create rating</h1>
	<form action="ratingcreate" method="post">
		<p>
			<label for="ratingid">RatingId</label>
			<input id="ratingid" name="ratingid" value="">
		</p>
		<p>
			<label for="ratingtime">RatingTime</label>
			<input id="ratingtime" name="ratingtime" value="">
		</p>
		<p>
			<label for="rating">Rating</label>
			<input id="rating" name="rating" value="">
		</p>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="">
		</p>
		<p>
			<label for="moviename">MovieName</label>
			<input id="moviename" name="moviename" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	
		<h1>Update Rating</h1>
	<form action="ratingupdate" method="post">
		<p>
			<label for="ratingid">RatingId</label>
			<input id="ratingid" name="ratingid" value="${fn:escapeXml(param.ratingid)}">
		</p>
		<p>
			<label for="rating">New Rating</label>
			<input id="rating" name="rating" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	
		<h1>Delete a Rating by RatingId</h1>
	<form action="ratingdelete" method="post">
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="ratingid">Rating ID</label>
				<input id="ratingid" name="ratingid" value="${fn:escapeXml(param.ratingid)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
</body>
</html>