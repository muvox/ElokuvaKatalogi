package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Movie;
import movie.model.dao.MovieDAO;


@WebServlet("/listGenre")
public class ListMoviesByGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ListMoviesByGenreServlet() {
        super();
        }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringId = (String)request.getParameter("genreid");
		int genreId = Integer.parseInt(stringId);
		

		
		MovieDAO moDAO = new MovieDAO();
		
		
		
		String headerText = "Showing movies under "+moDAO.findGenreName(genreId);
		
		ArrayList<Movie> movies = moDAO.findAllByGenre(genreId);	
		
		request.setAttribute("headerText", headerText);
		request.setAttribute("movies", movies);
		
		String jsp = "/view/movieList.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("search");
		
		request.setAttribute("search", searchString);

		RequestDispatcher rd = request.getRequestDispatcher("searchMovie");
		rd.forward(request,response);
	}

}