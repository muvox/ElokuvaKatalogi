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
		String stringID = (String)request.getParameter("movieid");
		
		System.out.println("doGet movieID: "+stringID);
		int movieID = Integer.parseInt(stringID);
		
		MovieDAO modo = new MovieDAO();
		
		Movie movie = modo.findMovieById(movieID);		
		
		String errorString = (String)request.getParameter("error");
		System.out.println("Error stringin tulos: "+errorString);
		//TODO: WIP
		request.setAttribute("movie", movie);
		request.setAttribute("error", "0");
		
		String jsp = "/view/movieUpdate.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);		
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);

		try {
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
		
		MovieDAO modo = new MovieDAO();
		
		Movie movieBefore = modo.findMovieById(id);
		
		Movie updateMovie = new Movie(title,desc,runtime,image,userRating);
		
		modo.updateMovie(movieBefore, updateMovie);
		
		response.sendRedirect("listMovies");
		
		}catch(Exception e) {
			request.setAttribute("error", "true");
			RequestDispatcher rd = request.getRequestDispatcher("updateErrorCheck");
			rd.forward(request,response);			
		}
		
		

		
	}

}
