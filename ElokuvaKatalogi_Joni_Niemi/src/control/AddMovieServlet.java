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


@WebServlet("/addMovie")
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AddMovieServlet() {
        super();
        
    }
    
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = "/view/movieform.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);	
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		boolean dump;
		
		String title = request.getParameter("title");
		
		String desc = request.getParameter("description");
		
		String runtime = request.getParameter("runtime");
		
		String ratingStr = request.getParameter("userRating");
		ratingStr = ratingStr.replaceAll(",", ".");
		float userRating = new Float(ratingStr);

		String image = request.getParameter("image");
		
		boolean[] genres;
		
		genres = new boolean[9];
		//action, comedy. drama, fantasy, horror, romance, scifi, western, thriller
		System.out.println("Size of genres arrat: "+genres.length);
		
		String genreAction = request.getParameter("action");
		System.out.println(genreAction);
		dump = Boolean.valueOf(genreAction);
		genres[0] = dump;
		String genreComedy = request.getParameter("comedy");
		dump = Boolean.valueOf(genreAction);
		genres[1] = dump;
		String genreDrama = request.getParameter("drama");
		dump = Boolean.valueOf(genreAction);
		genres[2] = dump;
		String genreFantasy = request.getParameter("fantasy");
		dump = Boolean.valueOf(genreAction);
		genres[3] = dump;
		String genreHorror = request.getParameter("horror");
		dump = Boolean.valueOf(genreAction);
		genres[4] = dump;
		String genreRomance = request.getParameter("romance");
		dump = Boolean.valueOf(genreAction);
		genres[5] = dump;
		String genreScifi = request.getParameter("scifi");
		dump = Boolean.valueOf(genreAction);
		genres[6] = dump;
		String genreWestern = request.getParameter("western");
		dump = Boolean.valueOf(genreAction);
		genres[7] = dump;
		String genreThriller = request.getParameter("thriller");
		dump = Boolean.valueOf(genreAction);
		genres[8] = dump;

		MovieDAO modo = new MovieDAO();
		
		Movie movie = new Movie(title,desc,runtime,image,userRating);
		
		movie.setGenres(genres);
		
		modo.addNew(movie);
		
		response.sendRedirect("listMovies");
	}

}
