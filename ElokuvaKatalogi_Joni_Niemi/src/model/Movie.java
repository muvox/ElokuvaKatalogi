package model;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	
	
	int id;					//yksilöivä id
	
	String title;			//elokuvan nimi, alkuper�isell� kielella
	
	String description;		//lyhyt kuvaus elokuvasta
	
	boolean[] genreList;	//lista genreistä jota käytetään elokuvaa luodessa

	String runtime;			// elokuvan kesto
	
	String image;			//url elokuvan kuvasta
	
	float userRating;		//floattinga  käyttäjän arvosana 
	
	ArrayList<Genre> genres;//lista genreistä
	
	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}

	public int getId() {
		return id;
	}

	public void setId(int movieId) {
		this.id = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String movieOriginalTitle) {
		this.title = movieOriginalTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String movieDescription) {
		this.description = movieDescription;
	}


	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String movieRuntime) {
		this.runtime = movieRuntime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String movieImage) {
		this.image = movieImage;
	}

	public float getUserRating() {
		return userRating;
	}

	public void setUserRating(float userRating) {
		this.userRating = userRating;
	}

	
	public boolean[] getGenreList() {
		return genreList;
	}

	public void setGenreList(boolean[] genreList) {
		this.genreList = genreList;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", description=" + description + "]";
	}

	public Movie(int id, String title, String description, String runtime,
			String image, float userRating) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.runtime = runtime;
		this.image = image;
		this.userRating = userRating;

	}
	
	public Movie(String title, String description, String runtime,
			String image, float userRating) {
		super();
		this.title = title;
		this.description = description;
		this.runtime = runtime;
		this.image = image;
		this.userRating = userRating;
	}
	
	public Movie() {
		
	}
	
	public Movie(int id, String title, String description, String runtime,
			String image, float userRating, ArrayList<Genre> genres) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.runtime = runtime;
		this.image = image;
		this.userRating = userRating;
		this.genres = genres;
	}
	
	public Movie(Movie movie) {
		this.id = movie.id;
		this.title = movie.title;
		this.description = movie.description;
		this.runtime = movie.runtime;
		this.image = movie.image;
		this.userRating = movie.userRating;
		this.genres = movie.genres;
	}
	
	
}
