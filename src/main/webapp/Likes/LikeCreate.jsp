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
<title>Create a Like</title>
</head>
<body>
	
	<div class="container">
	<h1 class="row align-items-center justify-content-center">Create Like</h1>
	<form action="likecreate" method="post">
	<div class="row mb-3 align-items-center justify-content-center">
		<label for="username" class="col-sm-2 col-form-label">UserName</label>
		<div class="col-sm-4">
				<input type="text" id="username" name="username" class="form-control">
		</div>
	</div>
	<div class="row mb-3 align-items-center justify-content-center">
		<label for="tconst" class="col-sm-2 col-form-label">tConst</label>
		<div class="col-sm-4">
			<input type="text" id="tconst" name="tconst" class="form-control">
		</div>
	</div>

	<div class="row mb-3 align-items-center justify-content-center">
		<button type="submit" class="btn btn-lg btn-primary">Submit</button>
	</div>
	</form>
	
	<h1 class="row align-items-center justify-content-center">New Like</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>LikeId</th>
					<th>LikeTime</th>
					<th>Tconst</th>
					<th>UserName</th>
				</tr>
			</thead>
				<tbody>
					<tr>
						<td>${like.getLikeId()}</td>
						<td>${like.getLikeTime()}</td>
						<td>${like.getTconst()}</td>
						<td>${like.getUserName()}</td>
					</tr>
				</tbody>
		</table>
	</div>
</body>
</html>