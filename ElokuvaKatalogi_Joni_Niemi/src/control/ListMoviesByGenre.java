package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Movie;
import movie.model.dao.MovieDAO;


@WebServlet("/listGenre")
public class ListMoviesByGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ListMoviesByGenre() {
        super();
        }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringId = (String)request.getParameter("genreid");
		int genreId = Integer.parseInt(stringId);
		
		
		MovieDAO moDAO = new MovieDAO();
		
		ArrayList<Movie> movies = moDAO.findAllByGenre(genreId);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
