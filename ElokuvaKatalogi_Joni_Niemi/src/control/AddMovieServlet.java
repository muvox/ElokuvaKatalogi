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
		request.setAttribute("error", "0");
		
		String jsp = "/view/movieform.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);	
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		try {
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
		System.out.println("Size of genres array: "+genres.length);
		
	
		String genreAction = request.getParameter("action");
		if(genreAction==null){
			dump = false;
			}
		else {
			dump = true;
			}
		genres[0] = dump;
		
		System.out.println("Genre action: "+genres[0]);		
		
		
		String genreComedy = request.getParameter("comedy");
		if(genreComedy==null){
			dump = false;
			}
		else {
			dump = true;
			}
		genres[1] = dump;
		System.out.println("Genre "+dump);
			
			
		String genreDrama = request.getParameter("drama");
		if(genreDrama==null){
			dump = false;
			}
		else {
			dump = true;
			}
		genres[2] = dump;
		System.out.println("Genre "+dump);
		
		String genreFantasy = request.getParameter("fantasy");
		if(genreFantasy==null){
			dump = false;
			}
		else {
			dump = true;
			}
		genres[3] = dump;
		System.out.println("Genre "+dump);
		
		String genreHorror = request.getParameter("horror");
		if(genreHorror==null){
			dump = false;
			}
		else {
			dump = true;
			}
		genres[4] = dump;
		System.out.println("Genre "+dump);
		
		String genreRomance = request.getParameter("romance");
		if(genreRomance==null){
			dump = false;
			}
		else {
			dump = true;
			}
		genres[5] = dump;
		System.out.println("Genre "+dump);
		
		
		String genreScifi = request.getParameter("scifi");
		if(genreScifi==null){
			dump = false;
			}
		else {
			dump = true;
			}
		genres[6] = dump;
		System.out.println("Genre "+dump);
		
		
		String genreWestern = request.getParameter("western");
		if(genreWestern==null){
			dump = false;
			}
		else {
			dump = true;
			}
		genres[7] = dump;
		System.out.println("Genre "+dump);
		
		
		String genreThriller = request.getParameter("thriller");
		if(genreThriller==null){
			dump = false;
			}
		else {
			dump = true;
			}
		genres[8] = dump;
		System.out.println("Genre "+dump);
		

		MovieDAO modo = new MovieDAO();
		
		Movie movie = new Movie(title,desc,runtime,image,userRating);
		
		movie.setGenreList(genres);
		
		modo.addNew(movie);
		
		response.sendRedirect("listMovies");
		
		}catch(Exception e) {
			request.setAttribute("error", "true");
			RequestDispatcher rd = request.getRequestDispatcher("addErrorCheck");
			rd.forward(request,response);			
		}
	}

}
