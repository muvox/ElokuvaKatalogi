package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elokuvat.model.dao.MovieDAO;
import model.Movie;


@WebServlet("/listMovies")
public class ListMoviesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListMoviesServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//id, title, desc, runtime, image, floatRating
		
		//ArrayList<Movie> movies = new ArrayList<Movie>();
		
		MovieDAO moDAO = new MovieDAO();
		
		ArrayList<Movie> movies = moDAO.findAll();	
		
		/* Testidataa
		Movie movie1 = new Movie(1,"Testi Elokuva","T�m� on ensimm�inen testielokuva.","1,29","https://image.tmdb.org/t/p/w600_and_h900_bestv2/30oXQKwibh0uANGMs0Sytw3uN22.jpg", 4.95f);
		Movie movie2 = new Movie(2,"Testi Elokuva 2; Electrid Boogaloo","T�m� on toinen testielokuva, jatko osa huippumenestykselle; Testi Elokuva 1.","1,19","https://image.tmdb.org/t/p/w600_and_h900_bestv2/30oXQKwibh0uANGMs0Sytw3uN22.jpg", 3.15f);
		Movie movie3 = new Movie(3,"Testi Elokuva 3; Testin paluu","Kolmas testielokuva. P��tt�� huikean trilogian testielokuvien chronologiasta.","1,46","https://image.tmdb.org/t/p/w600_and_h900_bestv2/30oXQKwibh0uANGMs0Sytw3uN22.jpg", 4.65f);
		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);
		*/
		request.setAttribute("movies", movies);
		
		String jsp = "/view/movielist.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
