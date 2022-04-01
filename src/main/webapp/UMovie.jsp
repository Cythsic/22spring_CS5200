<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>UMovie Home page</title>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
<div class="container-fluid">
	<a class="navbar-brand" href="#">UMovie</a>
	<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
		data-bs-target="#navbarNavDarkDropdown"
		aria-controls="navbarNavDarkDropdown" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link active"
				aria-current="page" href="umovie">Home</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDarkDropdownMenuLink" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> Users </a>
				<ul class="dropdown-menu dropdown-menu-dark"
					aria-labelledby="navbarDarkDropdownMenuLink">
					<li><a class="dropdown-item" href="Likes/findlikes">Likes</a></li>
					<li><a class="dropdown-item"
						href="Subscriptions/findsubscriptions">Subscriptions</a></li>
					<li><a class="dropdown-item" href="findpreferences">Preferences</a></li>
					<li><a class="dropdown-item" href="findrating">Ratings</a></li>
				</ul>
			<li class="nav-item"><a class="nav-link" href="findMoives">Movies</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="findPersons">Crews</a>
			</li>
		</ul>
	</div>
	<form class="d-flex">
		<a href="Users/usercreate" class="btn btn-secondary btn" tabindex="-1"
			role="button" aria-disabled="true">Sign Up</a> <a
			href="Users/findusers" class="btn btn-primary btn" tabindex="-1"
			role="button" aria-disabled="true">Sign In</a>
	</form>
</div>
</nav>
</head>
<body>
	<div class="container">
		<h2 style="text-align: center;">Welcome to UMovie!</h2>
		<img src="Movies_image.webp" class="img-fluid" alt="Movies Image">
		<div class="card">
			<h3 class="card-header" style="text-align: center;">About
				UMovie:</h3>
			<div class="card-body">
				<p class="card-text">UMovie is a movie recommendation system
					that has a massive quantity of movie related datasets and aims to
					provide personalized, integrated suggestions for audiences who
					would like to have more personalized movies recommendations based
					on their own tastes, instead of being distracted by others' reviews
					and rankings when choosing movies.</p>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>