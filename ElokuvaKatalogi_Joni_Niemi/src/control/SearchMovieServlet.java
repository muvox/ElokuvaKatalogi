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


@WebServlet("/searchMovie")
public class SearchMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchMovieServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("search");
		
		MovieDAO moDAO = new MovieDAO();
		
		ArrayList<Movie> movies = moDAO.searchMovieByTitle(searchString);	
		
		request.setAttribute("movies", movies);
		
		String headerText = "Showing movies with search word: "+searchString;
		
		request.setAttribute("headerText", headerText);

		String jsp = "/view/movieList.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
