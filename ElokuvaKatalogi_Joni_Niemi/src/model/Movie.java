package model;

import java.util.List;

public class Movie {
	
	
	int id;				//yksil�iv� id
	
	String title;	//elokuvan nimi, alkuper�isell� kielella
	
	//TODO String movieNameTranslated **Elokuvan nimi kielell� jolla k�ytt�j�n maassa n�ytetty
	
	String description;	//lyhyt kuvaus elokuvasta
	
	List<Genre>  genres;		//lista genreist�

	String runtime;		// elokuvan kesto
	
	String image;			//url elokuvan kuvasta
	
	float userRating;			//floattinga k�ytt�jien arvosana 

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

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
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
	
	
}
