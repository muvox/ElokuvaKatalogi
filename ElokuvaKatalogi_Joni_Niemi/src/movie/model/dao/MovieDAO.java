package movie.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Movie;

public class MovieDAO extends DataAccessObject{

	public ArrayList<Movie> findAll() {	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Movie movie = null; 
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit henkilo-taulusta
			String sqlSelect = "SELECT id, title, description, runtime, image, userRating FROM movie;";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// L�hetet��n komento:
			rs = stmt.executeQuery();
			// K�yd��n tulostaulun rivit l�pi ja luetaan readHenkilo()-metodilla:
			while (rs.next()) {
				movie = readMovie(rs);
				// lis�t��n henkil� listaan
				movies.add(movie);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn); // Suljetaan
		}
	
		return movies;
	}
	
	private Movie readMovie(ResultSet rs) {	
		try {
			// Haetaan yhden henkil�n tiedot kyselyn tulostaulun (ResultSet-tyyppinen rs-olion) aktiiviselta tietorivilt�
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String desc = rs.getString("description");
			String runtime = rs.getString("runtime");
			String image = rs.getString("image");
			float userRating = rs.getFloat("userRating");
			
			//  Luodaan ja palautetaan uusi henkilo
			return new Movie(id, title, desc, runtime, image, userRating);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Movie findMovieById(int id) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		Movie movie = null; 
		try {
			// Luodaan yhteys
			conn = getConnection();
			
			// Luodaan lause joka hakee kaikki id:llä (ID rivi taulussa kaikilla uniikki, voi antaa vain yhden tuloksen
			String sqlSelect = "SELECT id, title, description, runtime, image, userRating FROM movie WHERE id=?;";
			
			stmt = conn.prepareStatement(sqlSelect);
			
			System.out.println("Queryyn menevän elokuvan ID:"+id); //testi
			
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				movie = readMovie(rs);
			}
	

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			close(rs, stmt, conn); // Suljetaan
		}
		
		return movie;
	}
	
	public void addNew(Movie movie) {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		
		try {
		connection = getConnection();
		
		String sqlInsert = "INSERT INTO movie(title, description, runtime, image, userRating) VALUES (?,?,?,?,?)";
		stmtInsert = connection.prepareStatement(sqlInsert);
		stmtInsert.setString(1, movie.getTitle());
		stmtInsert.setString(2, movie.getDescription());
		stmtInsert.setString(3, movie.getRuntime());
		stmtInsert.setString(4, movie.getImage());
		stmtInsert.setFloat(5, movie.getUserRating());
		stmtInsert.executeUpdate();
		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			close(stmtInsert, connection);
		}
	}
	
	public void updateMovie(Movie movieBefore, Movie updatedMovie) {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		
	
		try {
			connection = getConnection();
			
			String sqlUpdate = "UPDATE movie "
							+"SET title=?, description=?, runtime=?, image=?, userRating=? "
							+"WHERE id=?";
			stmtInsert = connection.prepareStatement(sqlUpdate);
			stmtInsert.setString(1, updatedMovie.getTitle());
			stmtInsert.setString(2, updatedMovie.getDescription());
			stmtInsert.setString(3, updatedMovie.getRuntime());
			stmtInsert.setString(4, updatedMovie.getImage());
			stmtInsert.setFloat(5, updatedMovie.getUserRating());
			
			stmtInsert.setInt(6, movieBefore.getId());
			
			System.out.println("Elokuva jota päivitetään:"+movieBefore.getId()+" Vanhat runtime: "+movieBefore.getRuntime()+" Uusi runtime: "+updatedMovie.getRuntime()); //testi

			
			stmtInsert.executeUpdate();
			

					
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
			finally {
				close(stmtInsert, connection);
			
		}
	}
}
