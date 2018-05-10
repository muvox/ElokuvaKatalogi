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
		
		String stringID = (String)request.getParameter("movieid");
		
		System.out.println("doGet movieID: "+stringID);
		
		int movieID = Integer.parseInt(stringID);
		
		MovieDAO modo = new MovieDAO();
		
		Movie movie = modo.findMovieById(movieID);		
		
		//TODO: WIP
		request.setAttribute("movie", movie);

		request.setAttribute("error", "1");
		String jsp = "/view/movieUpdate.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
