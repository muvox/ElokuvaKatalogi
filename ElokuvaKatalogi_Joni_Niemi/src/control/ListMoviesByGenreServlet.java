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
		
		//otetaan sivulta genren id arvo stringinä ja käänetään int arvoksi
		String stringId = (String)request.getParameter("genreid");
		int genreId = Integer.parseInt(stringId);
		
		//luodaan movieDAO
		MovieDAO moDAO = new MovieDAO();
		
		//etsitään genren nimi movieDAO:n kautta tietokannasta. Tästä luodaan stringi joka ohjataan
		//sivulle jotta saadaan otsikko kertomaan sivulla mitä genreä näytetään
		String headerText = "Showing movies under "+moDAO.findGenreName(genreId);
		request.setAttribute("headerText", headerText);
		
		//haetaan alokuvat genre id:llä ja luodaan array elokuvista
		ArrayList<Movie> movies = moDAO.findAllByGenre(genreId);	
		
		//lähetetään elokuva array sivulle
		request.setAttribute("movies", movies);
		
		//ladataan sivu
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