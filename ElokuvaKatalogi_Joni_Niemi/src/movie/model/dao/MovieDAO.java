package movie.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Genre;
import model.Movie;

public class MovieDAO extends DataAccessObject{
	
	public static boolean allFalse(boolean[] array) {
		
		for(boolean b : array) if(b) return false;
		return true;
	}

	public ArrayList<Movie> findAll() {	
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtGenre = null;
		ResultSet rs = null;
		ResultSet rsGenre = null;

		ArrayList<Movie> movies = new ArrayList<Movie>();
		Movie movie = null; 
		Genre genre = null;
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
				
				String sqlGenreSelect= "SELECT genre.id, genre.name "
						+ "FROM genre, movie_genres, movie "
						+ "WHERE movie.id = ? "
						+ "AND movie.id = movie_genres.movie_id "
						+ "AND genre.id = movie_genres.genre_id "
						+ "ORDER BY genre.id";
				
				stmtGenre = conn.prepareStatement(sqlGenreSelect);
				stmtGenre.setInt(1, movie.getId());
				
				rsGenre = stmtGenre.executeQuery();
				ArrayList<Genre> genres = new ArrayList<>();
				while(rsGenre.next()) {
					genre = readGenre(rsGenre);

					//System.out.println("Genre: "+genre.getName());
					genres.add(genre);
					
					}
				movie.setGenres(genres);
				
				
				System.out.println(movie.getGenres().get(0).getName());
				
				// lisätään genret elokuvaan ja elokuvat l
				
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
	
	private Genre readGenre(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			
			//System.out.println("Genre id: "+id+" ja genre nimi: "+name);
			return new Genre(id, name);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Movie> findAllByGenre(int id){
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtGenre = null;
		ResultSet rsGenre = null;
		ResultSet rs = null;
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Movie movie = null; 
		Genre genre = null;
		
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit henkilo-taulusta
			String sqlSelect = "SELECT movie.id, movie.title, movie.description, movie.runtime, movie.image, movie.userRating " 
								+"FROM movie, movie_genres, genre"
								+"WHERE genre_id = ? "
								+"AND genre.id = movie_genres.genre_id "
								+"AND movie.id = movie_genres.movie_id;";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			stmt.setFloat(1, id);
			// L�hetet��n komento:

			rs = stmt.executeQuery();
			// K�yd��n tulostaulun rivit l�pi ja luetaan readHenkilo()-metodilla:
			while (rs.next()) {
				movie = readMovie(rs);
				
				String sqlGenreSelect= "SELECT genre.id, genre.name "
						+ "FROM genre, movie_genres, movie "
						+ "WHERE movie.id = ? "
						+ "AND movie.id = movie_genres.movie_id "
						+ "AND genre.id = movie_genres.genre_id "
						+ "ORDER BY genre.id";
				
				stmtGenre = conn.prepareStatement(sqlGenreSelect);
				stmtGenre.setInt(1, movie.getId());				
				rsGenre = stmtGenre.executeQuery();
				
				ArrayList<Genre> genres = new ArrayList<>();
				
				while(rsGenre.next()) {
					genre = readGenre(rsGenre);
					
					//System.out.println("Genre: "+genre.getName());
					
					genres.add(genre);
					}
				movie.setGenres(genres);
				
				System.out.println(movie.getGenres().get(0).getName());
				
				// lisätään genret elokuvaan ja elokuvat l
				
				movies.add(movie);
				}
			}catch(SQLException e) {
				throw new RuntimeException(e);
				}finally {
					close(rs, stmt, conn);
					}
		return movies;
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
	
	public int findLatestMovieId() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int id = 0;
		try {
			// Luodaan yhteys
			conn = getConnection();
			String sqlSelect = "SELECT MAX(id) FROM movie";
			
			stmt = conn.prepareStatement(sqlSelect);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {				
				id = rs.getInt(1);
			}
			
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}finally {
				close(stmt, conn); // Suljetaan
			}
		
			
			return id;
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
		
		stmtInsert = null;
		
		int id = findLatestMovieId();
		
		boolean[] boolList = movie.getGenreList();
		
		boolean allFalse = allFalse(boolList);
		
		System.out.println("allFalse :"+allFalse);
		
		if(allFalse) {
			
			String genresInsert = "INSERT INTO movie_genres(movie_id,genre_id) VALUES ("+id+",10)";
			stmtInsert = connection.prepareStatement(genresInsert);
			stmtInsert.executeUpdate();			
		}
		else {
			String genresInsert = "INSERT INTO movie_genres(movie_id,genre_id) VALUES ";
			
		
			for(int i=0; i<boolList.length;i++) {				
				if(boolList[i]) {
					int add = i+1;
					genresInsert = genresInsert + "("+id+","+add+"),";
					System.out.println(genresInsert);
					System.out.println(boolList[i]);
				}
				
			}
		

			genresInsert = genresInsert.substring(0, genresInsert.length()-1);

			System.out.println("Final genreInsert"+genresInsert);
			stmtInsert = connection.prepareStatement(genresInsert);
			stmtInsert.executeUpdate();
		}	
		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			close(stmtInsert, connection);
		}
	}
	
	public void deleteMovie(Movie movie) {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		String movieID = Integer.toString(movie.getId());
		
		try {
		connection = getConnection();
		String sqlDelete = "DELETE m, mg FROM movie m JOIN movie_genres mg ON m.id = mg.movie_id WHERE m.id = ?";
		stmtInsert = connection.prepareStatement(sqlDelete);
		stmtInsert.setString(1, movieID);
		System.out.println("movie id : "+movieID);
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
