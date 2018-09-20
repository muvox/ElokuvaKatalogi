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


@WebServlet("/updateMovie")
public class UpdateMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateMovieServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//otetaan vastaan elokuvan id, käännetään stringistä intiksi
		String stringID = (String)request.getParameter("movieid");
		int movieID = Integer.parseInt(stringID);
		
		//luodaan movieDAO ja haetaan elokuvan tiedot id:llä		
		MovieDAO modo = new MovieDAO();		
		Movie movie = modo.findMovieById(movieID);		
		
		//lähetetään elokuvan tiedot sivulle, asetetaan virheikkunan opacity arvo
		request.setAttribute("movie", movie);
		request.setAttribute("error", "0");
		
		//avataa sivu
		String jsp = "/view/movieUpdate.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);		
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);

		try {
			
		//otetaan sivulta uudet arvot elokuvaa varten
		String idStr = request.getParameter("id");
		System.out.println("doPost movieID: "+idStr);
		int id = Integer.parseInt(idStr);		
		String title = request.getParameter("title");
		String desc = request.getParameter("description");
		String runtime = request.getParameter("runtime");
		String ratingStr = request.getParameter("userRating");
		ratingStr = ratingStr.replaceAll(",", ".");
		float userRating = new Float(ratingStr);
		String image = request.getParameter("image");
		
		//luodaan movieDAO, etsitään (varmuuden vuoksi) elokuvan teidot uudestaan tietokannasta
		MovieDAO modo = new MovieDAO();		
		Movie movieBefore = modo.findMovieById(id);
		
		//luodaan uusi elokuva uusilla tiedoilla	
		Movie updateMovie = new Movie(title,desc,runtime,image,userRating);
		
		//lähetetään päivityspyyntö movieDAO:lle. parametreinä vanha elokuva ja uusi elokuva olioina
		modo.updateMovie(movieBefore, updateMovie);
		
		//ohjataan takaisin elokuvalistaan
		response.sendRedirect("listMovies");
		
		}catch(Exception e) {
			//virheen sattuessa asetetaan error arvoksi true ja ohjataan errorcheck servletille
			request.setAttribute("error", "true");
			RequestDispatcher rd = request.getRequestDispatcher("updateErrorCheck");
			rd.forward(request,response);			
		}
			
	}

}
