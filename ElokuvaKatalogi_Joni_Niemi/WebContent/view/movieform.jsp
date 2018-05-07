<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
					<td><div class ="button"><a href="listMovies">Cancel</a></div></td>
					<td>
						<input type="submit" name="submit-button" class="submit-button" value="Add" />
					</td>
				</tr>	
			</table>
			</form>
		
</body>
</html>