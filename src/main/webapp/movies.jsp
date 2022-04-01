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
        <title>Find Movies</title>
    </head>

    <body>
    <div class="container theme-showcase" role="main">

        <form action="findMovies" method="post">
            <div class="jumbotron">
                <h1>Search for movies</h1>
            </div>

            <h2>
                <label for="originalTitle">Search Movies by Title</label>
            </h2>
            <p>
                <input id="originalTitle" name="originalTitle"
                       value="${fn:escapeXml(param.originalTitle)}">
            </p>
            <p>
                <input type="submit" class="btn btn-lg btn-success"> <br />
                <br />
            </p>
        </form>

        <br />
        <h1>Matching Movies</h1>
        <p>${messsages.success}</p>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>TConst</th>
                <th>OriginalTitle</th>
                <th>IsAdult</th>
                <th>StartYear</th>
                <th>RuntimeMinutes</th>
            </tr>
            </thead>
            <c:forEach items="${movies}" var="movie">
                <tbody>
                <tr>
                    <td><c:out value="${movie.gettConst()}" /></td>
                    <td><c:out value="${movie.getOriginalTitle()}" /></td>
                    <td><c:out value="${movie.getIsAdult()}" /></td>
                    <td><c:out value="${movie.getStartYear()}" /></td>
                    <td><c:out value="${movie.getRuntimeMinutes()}" /></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>

    </div>
    </body>

    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</html>