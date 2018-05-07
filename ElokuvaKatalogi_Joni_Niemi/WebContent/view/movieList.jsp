<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles.css">
<title>Insert title here</title>
</head>
<body>
<a href="addMovie">Add new Movie</a>
<div class="cointainer" style="display:flex;flex-direction:row;">
	<c:forEach items="${movies}" var="movie">
	
		<div class="moviecard">
  			<img src="${movie.image }" alt="Poster" style="width:100%">
  				<div class="cont">
    				<h4><c:out value="${movie.title}" /></h4> 
    				<p><c:out value="${movie.desc}"/></p> 
    				<br>
    				<p>Käyttäjäarvosana: <c:out value="${movie.userRating}"/><p>
 				 </div>
		</div>
		
		
	</c:forEach>
	</div>


</body>
</html>