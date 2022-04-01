<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<title>Find Likes</title>
</head>
<body>
<div class="container">
	<h1 class="row align-items-center justify-content-center">Find Likes</h1>
	<form action="findlikes" method="post">
	<div class="row mb-3 align-items-center justify-content-center">
		<label for="username" class="col-sm-2 col-form-label">UserName</label>
		<div class="col-sm-4">
				<input type="text" id="username" name="username" class="form-control">
		</div>
	</div>
	<div class="row mb-3 align-items-center justify-content-center">
		<button type="submit" class="btn btn-lg btn-primary">Submit</button>
	</div>
	</form>
	
	<h1 class="row align-items-center justify-content-center">${messages.success}</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>LikeId</th>
					<th>LikeTime</th>
					<th>Tconst</th>
					<th>UserName</th>
				</tr>
			</thead>
			<c:forEach items="${likes}" var="like">
				<tbody>
				<tr>
					<td><c:out value="${like.getLikeId()}" /></td>
					<td><c:out value="${like.getLikeTime()}" /></td>
					<td><c:out value="${like.getTConst()}" /></td>
					<td><c:out value="${like.getUserName()}" /></td>
				</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</body>
</html>
