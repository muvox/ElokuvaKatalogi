package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add-movie")
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AddMovieServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("Vittu ku ei toimi");
		String jsp = "/view/movieList.jsp";
		RequestDispatcher dispatcher  = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);	
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
