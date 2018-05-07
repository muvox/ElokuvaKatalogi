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
<div class="cointainer" >
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
					<div class="heading"><:out value="${movie.title}" /></div>
					<div class="data"><c:out value="${movie.desc}" /></div>
				</div>
				</div>
		</div>
		</c:forEach>
</div>

    <div class="container">
        <div class="card">
            <div class="wrapper" style="background: linear-gradient(to bottom, rgba(0, 0, 0, 0.5) 1%, rgba(0, 0, 0, 0) 55%, black), url(https://image.tmdb.org/t/p/w600_and_h900_bestv2/uxzzxijgPIY7slzFvMotPv8wjKA.jpg) center/cover no-repeat;">
                <div class="top">
                    <div class="date">

                    </div>

                </div>
                <div class="data-section">
                    <div class="content">
                        <div class="heading">Daredevil: Returns to Hell's Kitchen. </div>
                        <div class="data"> Heading into Season 2, the “Devil of Hell’s Kitchen” will face challenges new and familiar to save his city!</div>
                        <a href="movieInfo" class="button">Movie Info</a>
                    </div>
                </div>
            </div>
        </div>


</body>
</html>