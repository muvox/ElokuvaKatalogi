<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<title>Insert title here</title>
</head>


<body>
<div class="sidebar" style="width:15em">

<ul class="cd-accordion-menu">
	<li><a href="listMovies">Home</a>
	
	<li>
	 <form class="search" method="post">
	 	<input type="text" name="search" placeholder="Search...  (Title)">
	 	<button type="submit" name="submit-button" class="submit-button"><i class="fas fa-search"></i></button>
	 	</li>
   

   <li class="has-children">
  
      <input type="checkbox" name ="group-1" id="group-1">

      <label for="group-1">Genres</label>
   

      <ul>
         <li><a a href="listGenre?genreid=1">Action</a></li>
         <li><a a href="listGenre?genreid=2">Comedy</a></li>
         <li><a a href="listGenre?genreid=3">Drama</a></li>
         <li><a a href="listGenre?genreid=4">Fantasy</a></li>
         <li><a a href="listGenre?genreid=5">Horror</a></li>
         <li><a a href="listGenre?genreid=6">Romance</a></li>
         <li><a a href="listGenre?genreid=7">SciFi</a></li>
         <li><a a href="listGenre?genreid=8">Western</a></li>
         <li><a a href="listGenre?genreid=9">Thriller</a></li>         
      </ul>
   </li>
   
   <li><a href="addMovie">Add new Movie</a></li>

</ul>
</div>

<div class="container" style="margin-left:15em">
		<H1><c:out value="${headerText}"></c:out></H1>
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
							<div class="heading"> ${movie.title} </div>
							<ul class="tags">
						<c:forEach items="${movie.genres}" var="genre">
							<li><a href="listGenre?genreid=${genre.id}"> ${genre.name} </a></li>
						</c:forEach>
						</ul>
						<br><br>
							<div class="data"> ${movie.description} </div>
						</div>
						
					</div>
				</div>
			</div>
		</c:forEach>
</div>


</body>
</html>