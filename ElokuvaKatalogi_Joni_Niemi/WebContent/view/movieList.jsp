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
<br>

		<c:forEach items="${movies}" var="movie">
	
			<div class="card">
				<div class="wrapper" style="background: linear-gradient(to bottom, rgba(0, 0, 0, 0.5) 1%, rgba(0, 0, 0, 0) 55%, black), url(${movie.image}) center/cover no-repeat;">
					<div class="top">
						<div class="date">
						 	<a class="edit" href="updateMovie?movieid=${movie.id}">Update</a>
                        	<br>
                        	<a class="edit" href="deleteMovie?movieid=${movie.id}">Delete</a>
						</div>				
					</div>
				
		
					<div class="data-section">
						<div class="content">
							<div class="heading">${movie.title}</div>
							<div class="data">${movie.description}</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>



</body>
</html>