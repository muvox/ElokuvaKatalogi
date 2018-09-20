<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="formStyle.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new movie</title>
</head>
<body>
	<h1>Add a new movie</h1>
	<div class="superContainer">
	<div class="container">
	
	
		<form method="post">
			<table>
				<tr>
					<td>Title:</td>
					<td><input type="text" value=""	name="title" size="50"  />
					</td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><input type="text" value=""	name="description" size="50"  />
					</td>
				</tr>
				<tr>
					<td>Runtime:</td>
					<td><input type="text" 	value="" name="runtime" size="50"  />
					</td>
				</tr>	
				<tr>
					<td>User rating:</td>
					<td><input type="text" 	value="" name="userRating" size="50"  />
					</td>
				</tr>	
				<tr>
					<td>Image (Direct http link)</td>
					<td><input type="text" 	value="" name="image" size="50"  />
					</td>
				</tr>
				<tr>
					<td>Genres</td>
					<td class="genreBox">
					<input class="genreCheck" type="checkbox" name="action" value="action"> Action
					<input class="genreCheck" type="checkbox" name="comedy" value="comedy"> Comedy
					<input class="genreCheck" type="checkbox" name="drama" value="drama"> Drama
					<input class="genreCheck" type="checkbox" name="fantasy" value="fantasy"> Fantasy
					<input class="genreCheck" type="checkbox" name="horror" value="horror"> Horror
					</td>
				</tr>
				<tr>
				<td>
				</td>
				<td class="genreBox">	
					<input class="genreCheck" type="checkbox" name="romance" value="romance"> Romance
					<input class="genreCheck" type="checkbox" name="scifi" value="scifi"> Sci-Fi
					<input class="genreCheck" type="checkbox" name="western" value="western"> Western
					<input class="genreCheck" type="checkbox" name="thriller" value="thriller"> Thriller
	
					<td>
					
				</tr>
				<tr>
					<td><div class="button"><a href="listMovies">Cancel</a></div></td>
					<td>
						<input type="submit" name="submit-button" class="submit-button" value="Add" />
					</td>
				</tr>	
			</table>
			</form>
						
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
			</div></div>
			</div>
		
</body>
</html>