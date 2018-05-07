package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elokuvat.model.dao.MovieDAO;
import model.Movie;


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
		
		String title = request.getParameter("title");
		
		String desc = request.getParameter("description");
		
		String runtime = request.getParameter("runtime");
		
		String ratingStr = request.getParameter("userRating");
		ratingStr = ratingStr.replaceAll(",", ".");
		float userRating = new Float(ratingStr);

		String image = request.getParameter("image");
		
		MovieDAO modo = new MovieDAO();
		
		Movie movie = new Movie(title,desc,runtime,image,userRating);
		
		modo.addNew(movie);
		
		response.sendRedirect("listMovies");
	}

}
