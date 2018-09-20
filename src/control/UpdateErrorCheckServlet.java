package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Movie;
import movie.model.dao.MovieDAO;


@WebServlet("/updateErrorCheck")
public class UpdateErrorCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateErrorCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Otetan vastaan elokuva id stringinä, muutetaan intiksi		
		String stringID = (String)request.getParameter("movieid");
		int movieID = Integer.parseInt(stringID);
		
		//luodaan movieDao, ja pyydetään eloku id:n perusteella
		MovieDAO modo = new MovieDAO();		
		Movie movie = modo.findMovieById(movieID);		
		
		//asetetaan eloku arvoksi sivulle
		request.setAttribute("movie", movie);

		//asetetaan virheikkunan opacity arvo ja ohjataan sivulle
		request.setAttribute("error", "1");
		String jsp = "/view/movieUpdate.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
