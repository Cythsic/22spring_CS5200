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
<title>Update a User</title>
</head>
<body>
<div class="container">
	<h1 class="row align-items-center justify-content-center">Update Information</h1>
	<form action="userupdate" method="post">
	<div class="row mb-3 align-items-center justify-content-center">
		<label for="username" class="col-sm-2 col-form-label">UserName</label>
		<div class="col-sm-4">
				<input type="text" id="username" name="username" class="form-control">
		</div>
	</div>
	<div class="row mb-3 align-items-center justify-content-center">
		<label for="firstname" class="col-sm-2 col-form-label">FirstName</label>
		<div class="col-sm-4">
			<input type="text" id="firstname" name="firstname" class="form-control">
		</div>
	</div>
	<div class="row mb-3 align-items-center justify-content-center">
		<label for="lastname" class="col-sm-2 col-form-label">LastName</label>
		<div class="col-sm-4">
				<input type="text" id="lastname" name="lastname" class="form-control">
		</div>
	</div>
	<div class="row mb-3 align-items-center justify-content-center">
		<label for="email" class="col-sm-2 col-form-label">Email</label>
		<div class="col-sm-4">
				<input type="email" id="email" name="email" class="form-control">
		</div>
	</div>
	<div class="row mb-3 align-items-center justify-content-center">
		<label for="phone" class="col-sm-2 col-form-label">Phone</label>
		<div class="col-sm-4">
				<input type="text" id="phone" name="phone" class="form-control">
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
					<th>UserName</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Email</th>
					<th>Phone</th>
				</tr>
			</thead>
				<tbody>
					<tr>
						<td>${user.getUserName()}</td>
						<td>${user.getFirstName()}</td>
						<td>${user.getLastName()}</td>
						<td>${user.getEmail()}</td>
						<td>${user.getPhone()}</td>
					</tr>
				</tbody>
		</table>
	</div>
</body>
</html>