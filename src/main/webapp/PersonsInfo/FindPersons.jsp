<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Find a person</title>
</head>

<body>
	<div class="container theme-showcase" role="main">

		<form action="findPersons" method="post">
			<div class="jumbotron">
				<h1>Search for persons</h1>
			</div>
			
			<h2>
				<label for="primaryNames">Search person by name</label>
			</h2>
			<p>
			<input id="primaryName" name="primaryName"
				value="${fn:escapeXml(param.parimaryName)}">
			</p>
			<p>
				<input type="submit" class="btn btn-lg btn-success"> <br />
				<br />
			</p>
		</form>
		
		<br />
		<h1>Matching Persons</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>NConst</th>
					<th>PrimaryName</th>
					<th>BirthYear</th>
					<th>DeathYear</th>
					<th>Famous Movie(s)</th>
				</tr>
			</thead>
			<c:forEach items="${persons}" var="person">
				<tbody>
					<tr>
						<td><c:out value="${person.getnConst()}" /></td>
						<td><c:out value="${person.getPrimaryName()}" /></td>
						<td><c:out value="${person.getBirthYear()}" /></td>
						<td><c:out value="${person.getDeathYear()}" /></td>
						<td><c:out value="${person.getKnownForWorksTitle()}" /></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>

	</div>

	<!-- Bootstrap -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
