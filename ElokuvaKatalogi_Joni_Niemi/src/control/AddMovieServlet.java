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
		//asetetaan error arvoksi 0 (virheikkunan opacity arvo)
		request.setAttribute("error", "0");
		
		//avataan sivu
		String jsp = "/view/movieform.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);	
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		try {
		//luodaan booleani jota käytetään lisäämään genrejen boolean arvoja boolean arrayssa 
		boolean dump;
		boolean[] genres;
		
		//Otetaan sivulta elokuvan tiedot talteen
		String title = request.getParameter("title");
		
		String desc = request.getParameter("description");
		
		String runtime = request.getParameter("runtime");
		
		String ratingStr = request.getParameter("userRating");
		ratingStr = ratingStr.replaceAll(",", ".");
		float userRating = new Float(ratingStr);

		String image = request.getParameter("image");
		
		
		//alustetaan genres boolean array
		genres = new boolean[9];
		//action, comedy. drama, fantasy, horror, romance, scifi, western, thriller (järhestys jossa genret esiintyvät)
		
	
		//otetaan sivulta action arvon checkbox arvo. Palauttaa 1 mikäli checkbox oli ruksattu. Palauttaa null mikäli ei
		String genreAction = request.getParameter("action");
		if(genreAction==null){
			dump = false;
			}
		else {
			dump = true;
			}
		//asetetaan sivulta otettu arvo ensin dump booleaniksi ja sitten lisätään boolean arrayhin		
		genres[0] = dump;
		
		//sama toistuu jokaiselle checkboxille sivulla
		
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
		
		//luodaan MovieDAO
		MovieDAO modo = new MovieDAO();
		
		//luodaan elokuva olio sivulta saaduilla arvoilla
		Movie movie = new Movie(title,desc,runtime,image,userRating);
		
		//lisätään genre array genrelistaksi elokuva oliolle
		movie.setGenreList(genres);
		
		//pyydetään movieDAO:lta elokuvan lisäystä tietokantaan
		modo.addNew(movie);
		
		//ohjataan takaisin elokuva listaan
		response.sendRedirect("listMovies");
		
		}catch(Exception e) {
			//asetetaan error arvoksi true ja lähetetään arvo addErrorCheck servletille
			System.out.println(e);
			request.setAttribute("error", "true");
			RequestDispatcher rd = request.getRequestDispatcher("addErrorCheck");
			rd.forward(request,response);			
		}
	}

}
