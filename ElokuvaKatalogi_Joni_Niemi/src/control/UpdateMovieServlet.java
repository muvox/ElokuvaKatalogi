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
		String stringID = request.getParameter("movieid");
		int movieID = Integer.parseInt(stringID);
		
		MovieDAO modo = new MovieDAO();
		
		Movie movie = modo.findMovieById(movieID);		
		
		//TODO: WIP
		request.setAttribute("movie", movie);
				
		String jsp = "/view/movieUpdate.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);		
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);		
		
		String title = request.getParameter("title");
		
		String desc = request.getParameter("description");
		
		String runtime = request.getParameter("runtime");
		
		String ratingStr = request.getParameter("userRating");
		ratingStr = ratingStr.replaceAll(",", ".");
		float userRating = new Float(ratingStr);

		String image = request.getParameter("image");
		
		MovieDAO modo = new MovieDAO();
		
		Movie movieBefore = modo.findMovieById(id);
		
		Movie updateMovie = new Movie(title,desc,runtime,image,userRating);
		
		modo.updateMovie(movieBefore, updateMovie);
		
		response.sendRedirect("listMovies");
	}

}
