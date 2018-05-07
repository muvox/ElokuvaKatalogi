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
//<div class="cointainer" style="display:flex;flex-direction:row;">
	<c:forEach items="${movies}" var="movie">
	
		<div class="card">
			<div class="wrapper" style="backgroiund: linear-gradient(to bottom, rgba(0, 0, 0, 0.5) 1%, rgba(0, 0, 0, 0) 55%, black), url(${movie.image}) center/cover no-repeat;">
				<div class="top">
					<div class="date">
					</div>				
					
				
				</div>
		</div>
		
		<div class="data-section">
			<div class="content">
				<div class="heading"><:out value="${movie.title}"/></div>
				<div class="data"><c:out value="${movie.desc}"/></div>
			</div>
		
		
		<!-- </div>
		
  			<img src="${movie.image }" alt="Poster" style="width:100%">
  				<div class="cont">
    				<h4><c:out value="${movie.title}" /></h4> 
    				<p><c:out value="${movie.desc}"/></p> 
    				<br>
    				<p>Käyttäjäarvosana: <c:out value="${movie.userRating}"/><p>
 				 </div>
		</div>
		-->
		
	</c:forEach>
	</div>


</body>
</html>