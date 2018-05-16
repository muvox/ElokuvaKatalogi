package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Genre;
import model.Movie;
import movie.model.dao.MovieDAO;


@WebServlet("/listMovies")
public class ListMoviesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListMoviesServlet() {
        super();
    } 


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MovieDAO moDAO = new MovieDAO();
		
		ArrayList<Movie> movies = moDAO.findAll();	
		
		request.setAttribute("movies", movies);
		
		Movie movie = new Movie(movies.get(0));
		
		System.out.println(movie.getTitle());
		
		System.out.println("Genre: "+movie.getGenres().size());
		
		
		
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
