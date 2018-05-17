package movie.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Genre;
import model.Movie;

public class MovieDAO extends DataAccessObject{
	
	//tarkistetaan oliko kaikki booleanit false. Käytetään vain genrejen boolean listan tarkistamiseen
	public static boolean allFalse(boolean[] array) {
		
		for(boolean b : array) if(b) return false;
		return true;
	}
	
	//haetan elokuvan nimeä hakusanalla, palauttaa listan osumista.
	public ArrayList<Movie> searchMovieByTitle(String searchString){
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
		String sqlSelect = "SELECT id, title, description, runtime, image, userRating FROM movie "
				+ "WHERE title LIKE ? ORDER BY movie.title;";
		//Valmistellaan komento:
		stmt = conn.prepareStatement(sqlSelect);
		searchString = "%"+searchString+"%";		
		stmt.setString(1, searchString);
		
		// lähetetään komento
		rs = stmt.executeQuery();
		// käydään läpi tulokset readMovie metodilla, ja jokaista elokuvaa kohden käydään läpi genret readGenre metodilla
		while (rs.next()) {
			movie = readMovie(rs);
			
			//Valmistellaan genren haku komento
			String sqlGenreSelect= "SELECT genre.id, genre.name "
					+ "FROM genre, movie_genres, movie "
					+ "WHERE movie.id = ? "
					+ "AND movie.id = movie_genres.movie_id "
					+ "AND genre.id = movie_genres.genre_id "
					+ "ORDER BY genre.id";
			
			stmtGenre = conn.prepareStatement(sqlGenreSelect);
			stmtGenre.setInt(1, movie.getId());
			
			//lähetetään komento
			rsGenre = stmtGenre.executeQuery();
			
			//luodaan lista genrejä varten
			ArrayList<Genre> genres = new ArrayList<>();
			while(rsGenre.next()) {
				//luetaan genre
				genre = readGenre(rsGenre);
				//lisätään genre listaan
				genres.add(genre);
				
				}
			//asetetaan elokuvalle genret
			movie.setGenres(genres);

			//lisätään elokuva elokuvalistaan
			movies.add(movie);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
			} finally {
				close(rs, stmt, conn); // Suljetaan
				}
		return movies;
		}

	//tällä metodilla etsitään kaikki elokuvat tietokannasta
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
			String sqlSelect = "SELECT id, title, description, runtime, image, userRating FROM movie ORDER BY movie.title;";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// Lähetään komento:
			rs = stmt.executeQuery();
			// käydään läpi tulokset readMovie metodilla, ja jokaista elokuvaa kohden käydään läpi genret readGenre metodilla
			while (rs.next()) {
				movie = readMovie(rs);
				
				//Valmistellaan genren haku komento
				String sqlGenreSelect= "SELECT genre.id, genre.name "
						+ "FROM genre, movie_genres, movie "
						+ "WHERE movie.id = ? "
						+ "AND movie.id = movie_genres.movie_id "
						+ "AND genre.id = movie_genres.genre_id "
						+ "ORDER BY genre.id";

				stmtGenre = conn.prepareStatement(sqlGenreSelect);
				stmtGenre.setInt(1, movie.getId());
				
				//lähetetään komento
				rsGenre = stmtGenre.executeQuery();
				
				//luodaan lista genrejä varten
				ArrayList<Genre> genres = new ArrayList<>();
				
				while(rsGenre.next()) {
					//luetaan genre
					genre = readGenre(rsGenre);
					//lisätään genre listaan
					genres.add(genre);
					}
				//asetetaan genret elokuvalle
				movie.setGenres(genres);
				
				//lisätään elokuva elokuvalistaan
				movies.add(movie);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
				} finally {
					close(rs, stmt, conn); // Suljetaan
					}
		return movies;
		}
	
	//metodi jota käytetään elokuvien lukemiseen tietokannan vastauksista
	private Movie readMovie(ResultSet rs) {	
		try {
			// Haetaan elokuvan tiedon kenttä kerrallaan
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String desc = rs.getString("description");
			String runtime = rs.getString("runtime");
			String image = rs.getString("image");
			float userRating = rs.getFloat("userRating");
			
			//luodaan uusi elokuva hettujen tietojen perusteella, ja palautetaan elokuva olio
			return new Movie(id, title, desc, runtime, image, userRating);
			} catch (SQLException e) {
				throw new RuntimeException(e);
				}
		}
	
	//metodi jota käytetään genrejen lukemiseen tietokanna vastauksista
	private Genre readGenre(ResultSet rs) {
		try {
			//haetaan genren tiedot kenttä kerrallaan
			int id = rs.getInt("id");
			String name = rs.getString("name");
			
			//palautetaan uusi genre haetuilla arvoilla
			return new Genre(id, name);
			}catch (SQLException e) {
				throw new RuntimeException(e);
				}
		}
	
	//metodia käytetään genren nimen etsimiseen
	public String findGenreName(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String genreName = null;

		try {
			//valmistellaan komento
			String sqlSelect = "SELECT genre.name FROM genre "
							+ "WHERE genre.id = ? ;";
			
			conn = getConnection();
			stmt = conn.prepareStatement(sqlSelect);
			stmt.setInt(1, id);
			
			//lähetetään komento
			rs = stmt.executeQuery();
			
			//käydään vastaukset läpi readGenre metodilla
			while(rs.next()) {
				genreName = rs.getString("name");
				}
			}catch(SQLException e) {
				throw new RuntimeException(e);
				}finally {
					close(rs, stmt, conn);
					}
		return genreName;
		}
	
	//metodi joka hakee listan elokuvia genren perusteella
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
			// Luodaan komento: haetaan kaikki rivit movie taulusta
			String sqlSelect = "SELECT movie.id, movie.title, movie.description, movie.runtime, movie.image, movie.userRating " 
								+"FROM movie, movie_genres, genre "
								+"WHERE genre_id = ? "
								+"AND genre.id = movie_genres.genre_id "
								+"AND movie.id = movie_genres.movie_id";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			stmt.setInt(1, id);
			
			// Lähetetään komento
			rs = stmt.executeQuery();
			
			// käydään läpi tulokset readMovie metodilla, ja jokaista elokuvaa kohden käydään läpi genret readGenre metodilla
			while (rs.next()) {
				movie = readMovie(rs);
				
				//luodaan komento
				String sqlGenreSelect= "SELECT genre.id, genre.name "
						+ "FROM genre, movie_genres, movie "
						+ "WHERE movie.id = ? "
						+ "AND movie.id = movie_genres.movie_id "
						+ "AND genre.id = movie_genres.genre_id "
						+ "ORDER BY genre.id";
				
				//valmistellaan komento
				stmtGenre = conn.prepareStatement(sqlGenreSelect);
				stmtGenre.setInt(1, movie.getId());
				
				//lähetetään komento
				rsGenre = stmtGenre.executeQuery();
				
				//luodaan lista genrejä varten
				ArrayList<Genre> genres = new ArrayList<>();
				
				//käydään genret läpi apumetodilla
				while(rsGenre.next()) {
					genre = readGenre(rsGenre);
					//lisätään genre listaan
					genres.add(genre);
					}
				//asetetaan genret elokuvalle
				movie.setGenres(genres);
				
				//lisätään elokuva elokuvalistaan
				movies.add(movie);
				}
			}catch(SQLException e) {
				throw new RuntimeException(e);
				}finally {
					close(rs, stmt, conn);
					}
		return movies;
		}
	
	//metodi etsimään elokuva id:n perusteella. Käytetään kansoittamaan UpdateServletin:n sivu:n formi
	public Movie findMovieById(int id) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		Movie movie = null; 
		try {
			// Luodaan yhteys
			conn = getConnection();
			
			// Luodaan komento joka hakee kaikki tiedot id:llä
			String sqlSelect = "SELECT id, title, description, runtime, image, userRating FROM movie WHERE id=?;";
			
			//valmistellaan komento
			stmt = conn.prepareStatement(sqlSelect);
			stmt.setInt(1, id);
			
			//lähetään komento
			rs = stmt.executeQuery();
			while(rs.next()) {
				//luetaan elokuva tuloksista
				movie = readMovie(rs);
				}
			}catch(SQLException e) {
				throw new RuntimeException(e);
				}finally {
					close(rs, stmt, conn); // Suljetaan
					}
		return movie;
		}
	
	//etsitään viimeksi lisätyn elokuvan id
	public int findLatestMovieId() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int id = 0;
		try {
			// Luodaan yhteys
			conn = getConnection();
			
			//luodaan komento
			String sqlSelect = "SELECT MAX(id) FROM movie";
			
			//valmistellaan komento
			stmt = conn.prepareStatement(sqlSelect);
			
			//lähetetään komento
			rs = stmt.executeQuery();
			
			//käydään läpi tulokset
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
	
	
	//metodi jolla elokuvat lisätään tietokantaan !!! HUOM !!! elokuva pitää lisätä movie tauluun ja elokuvan genret merkata movie_nenres tauluun
	public void addNew(Movie movie) {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		
		try {
		//luodaan yhteys
		connection = getConnection();
		
		//luodaan komento
		String sqlInsert = "INSERT INTO movie(title, description, runtime, image, userRating) VALUES (?,?,?,?,?)";
		
		//valmistellaan komento
		stmtInsert = connection.prepareStatement(sqlInsert);
		stmtInsert.setString(1, movie.getTitle());
		stmtInsert.setString(2, movie.getDescription());
		stmtInsert.setString(3, movie.getRuntime());
		stmtInsert.setString(4, movie.getImage());
		stmtInsert.setFloat(5, movie.getUserRating());
		
		//ajetaan komento
		stmtInsert.executeUpdate();
		
		//putsataan stmtInsert uutta käyttöä varten
		stmtInsert = null;
		
		//etsitään viimeksi lisätyn elokuvan id
		int id = findLatestMovieId();
		
		//etsitään elokuvan genret genreList bool arrayn avulla
		boolean[] boolList = movie.getGenreList();
		
		//tarkistetaan että kaikki eivät olleet falseja(ei genreä)
		boolean allFalse = allFalse(boolList);
		
		if(allFalse) {
			//jos mitään genreä ei valittu, asetetaan tällä lauseella elokuvan genreksi "noGenre"
			String genresInsert = "INSERT INTO movie_genres(movie_id,genre_id) VALUES ("+id+",10)";
			stmtInsert = connection.prepareStatement(genresInsert);
			stmtInsert.executeUpdate();			
		}
		else {
			//luodaan komento
			String genresInsert = "INSERT INTO movie_genres(movie_id,genre_id) VALUES ";
			
			//käydään läpi genreLista, katsotaan kuinka monta lisä arvoa komento tarvii
			for(int i=0; i<boolList.length;i++) {				
				if(boolList[i]) {
					int add = i+1;
					genresInsert = genresInsert + "("+id+","+add+"),";
				}
				
			}
		
			//pätkäistään ylimääräinen pilkku komennon perästä
			genresInsert = genresInsert.substring(0, genresInsert.length()-1);

			
			//valmistellaan komento
			stmtInsert = connection.prepareStatement(genresInsert);
			
			//ajetaan komento
			stmtInsert.executeUpdate();
		}	
		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			close(stmtInsert, connection);
		}
	}
	
	//elokuvien poisto metodi
	public void deleteMovie(Movie movie) {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		String movieID = Integer.toString(movie.getId());
		
		try {
			//luodaan yhteys
			connection = getConnection();
			
			//luodaan komento
			String sqlDelete = "DELETE m, mg FROM movie m JOIN movie_genres mg ON m.id = mg.movie_id WHERE m.id = ?";
			
			//valmistellaan komento
			stmtInsert = connection.prepareStatement(sqlDelete);//ajetaan komento
			stmtInsert.setString(1, movieID);
			
			//ajetaan komento
			stmtInsert.executeUpdate();
			}catch(SQLException e) {
				throw new RuntimeException(e);
				}finally {
					close(stmtInsert, connection);
					}
		}
	 
	//elokuvan päivitysmetodi
	public void updateMovie(Movie movieBefore, Movie updatedMovie) {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
			
		try {
			//luodaan yhteys
			connection = getConnection();
			
			//luodaan komento
			String sqlUpdate = "UPDATE movie "
							+"SET title=?, description=?, runtime=?, image=?, userRating=? "
							+"WHERE id=?";
			
			//valmistellaan komento
			stmtInsert = connection.prepareStatement(sqlUpdate);
			stmtInsert.setString(1, updatedMovie.getTitle());
			stmtInsert.setString(2, updatedMovie.getDescription());
			stmtInsert.setString(3, updatedMovie.getRuntime());
			stmtInsert.setString(4, updatedMovie.getImage());
			stmtInsert.setFloat(5, updatedMovie.getUserRating());
			stmtInsert.setInt(6, movieBefore.getId());
			
			//ajetaan komento
			stmtInsert.executeUpdate();
			}catch(SQLException e){
				throw new RuntimeException(e);
				}
		finally {
			close(stmtInsert, connection);
			}
		}
	}