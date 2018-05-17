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


@WebServlet("/addErrorCheck")
public class AddErrorCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddErrorCheckServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Pyydetään elokuvan id edelliseltä servletiltä
		String stringID = (String)request.getParameter("movieid");
		
		
		if(stringID != null) {
		
		int movieID = Integer.parseInt(stringID);
		
		// luodaan MovieDAO 
		MovieDAO modo = new MovieDAO();
		
		//Pyydetään movieDAO:lta elokuva id:n perusteella
		Movie movie = modo.findMovieById(movieID);		
		
		//asetetaan elokuva olio attribuutiksi sivulle
		request.setAttribute("movie", movie);
		}
		
		//asetetaan error arvo ykköseksi (tämä asettaa virheikkunan opacity arvon 1:ksi)
		request.setAttribute("error", "1");
		
		String jsp = "/view/movieform.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);	
		
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
