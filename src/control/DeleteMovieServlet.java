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


@WebServlet("/deleteMovie")
public class DeleteMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteMovieServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//luodaan movieDAO
		MovieDAO modo = new MovieDAO();
		
		//otetaan sivulta elokuva id stringiksi ja käännetään se int arvoksi
		String stringID = (String)request.getParameter("movieid");		
		int movieID = Integer.parseInt(stringID);
		
		//luodaan elokuva olio ja etsitään elokuvan olio id:llä
		Movie movie = modo.findMovieById(movieID);
		
		//lähetetään poistamispyyntö movieDAO:lle
		try {System.out.println("Deleting movie: "+movie.getTitle()+" With ID: "+movie.getId());
		modo.deleteMovie(movie);
		
		}catch(Exception e){
			System.out.println("Error occurred!!!");
		}
		
		//ohjataan takaisin elokuva listaan
		RequestDispatcher rd = request.getRequestDispatcher("listMovies");
		rd.forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
