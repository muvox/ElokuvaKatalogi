<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update movie information</title>
</head>
<body>
	<div class="toast jam" style="opacity: ${error};">
  	<span class="close" aria-role="button" tabindex="0"></span>
  	Lightly toasted with jam.
	</div>
	<h1>Update movie information</h1>
		<form method="post">
			<table>
				<tr>
					<td>Movie ID: <c:out value="${movie.id}" /></td>
					<td><input style="opacity: 0;" type="text" value="${movie.id}"	name="id" size="50"  />
					</td>
				</tr>
				<tr>
					<td>Title:</td>
					<td><input type="text" value="${movie.title}"	name="title" size="50"  />
					</td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><input type="text" value="${movie.description}"	name="description" size="50"  />
					</td>
				</tr>
				<tr>
					<td>Runtime:</td>
					<td><input type="text" 	value="${movie.runtime}" name="runtime" size="50"  />
					</td>
				</tr>	
				<tr>
					<td>User rating:</td>
					<td><input type="text" 	value="${movie.userRating}" name="userRating" size="50"  />
					</td>
				</tr>	
				<tr>
					<td>Image (Direct http link)</td>
					<td><input type="text" 	value="${movie.image}" name="image" size="50"  />
					</td>
				</tr>	
				<tr>
					<td><div class ="button"><a href="listMovies">Cancel</a></div></td>
					<td>
						<input type="submit" name="submit-button" class="submit-button" value="Update" />
					</td>
				</tr>	
			</table>
			</form>
			<br>
			<h2>Current Image</h2>
			<br>
			<div style="background: url(${movie.image}) "></div>
</body>
</html>