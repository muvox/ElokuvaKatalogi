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
		
		//luodaan movieDAO
		MovieDAO moDAO = new MovieDAO();
		
		//pyydetään movieDAO:lta kaikki alokuvat
		ArrayList<Movie> movies = moDAO.findAll();	
		
		//asetetaan elokuvat arvoiksi sivulle
		request.setAttribute("movies", movies);
		
		//avataan movieList sivu		
		String jsp = "/view/movieList.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//otetaan talteen hakusana
		String searchString = request.getParameter("search");
		
		//lähetetään hakusana arvona eteenpäin SearchMovieServletille
		request.setAttribute("search", searchString);
		
		//avataan SearchMovieServlet
		RequestDispatcher rd = request.getRequestDispatcher("searchMovie");
		rd.forward(request,response);
	}

}
