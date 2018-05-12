<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new movie</title>
</head>
<body>
	<h1>Add a new movie</h1>
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
					<td>
					<input type="checkbox" name="action" value="action"> Action
					<input type="checkbox" name="comedy" value="comedy"> Comedy
					<input type="checkbox" name="drama" value="drama"> Drama
					<input type="checkbox" name="fantasy" value="fantasy"> Fantasy
					<input type="checkbox" name="horror" value="horror"> Horror
					<input type="checkbox" name="romance" value="romance"> Romance
					<input type="checkbox" name="scifi" value="scifi"> Sci-Fi
					<input type="checkbox" name="western" value="western"> Western
					<input type="checkbox" name="thriller" value="thriller"> Thriller
					</td>
				</tr>
				<tr>
					<td><div class ="button"><a href="listMovies">Cancel</a></div></td>
					<td>
						<input type="submit" name="submit-button" class="submit-button" value="Add" />
					</td>
				</tr>	
			</table>
			</form>
		
</body>
</html>