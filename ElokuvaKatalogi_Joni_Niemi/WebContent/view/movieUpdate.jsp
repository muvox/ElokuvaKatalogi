<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update movie information</title>
<link rel="stylesheet" type="text/css" href="formStyle.css">

</head>
<body>
<div class="superContainer">
<div class="container">
	
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
			<div style="background: url(${movie.image})"></div>
			</div>
			<div class="container">
			<div class="toast jam" style="
								opacity: ${error};
  								background-color: #99004d;
  								color: #fff;
  								line-height: 1.5;
  								margin-bottom: 1em;
  								padding: 1.25em;
  								top: 1em;
  								width: 325px;">
  			<span class="close" ></span>
  			<H3>An error has occurred!</H3>
  			<p> Please make sure you filled every box correctly!</p>
  			<p> Title and can be almost anything, limited to 250 characters </p>
  			<p> Runtime is handled as a string for now, so it can basically be anything</p>
  			<p> User rating should be entered as a number ranging from 0.01 to 5</p>
  			<p> Please choose atleast one genre</p>
  			<p> Image should be a direct link to a image file (.jpg .png etc.)</p>
			</div>
			</div>
			</div>
</body>
</html>