package model;

import java.util.List;

public class Movie {
	
	
	int id;				//yksil�iv� id
	
	String title;	//elokuvan nimi, alkuper�isell� kielella
	
	//TODO String movieNameTranslated **Elokuvan nimi kielell� jolla k�ytt�j�n maassa n�ytetty
	
	String desc;	//lyhyt kuvaus elokuvasta
	
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String movieDescription) {
		this.desc = movieDescription;
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
		return "Movie [movieOriginalTitle=" + title + ", movieDescription=" + desc + "]";
	}

	public Movie(int id, String title, String desc, String runtime,
			String image, float userRating) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.runtime = runtime;
		this.image = image;
		this.userRating = userRating;
	}
	
	public Movie(String title, String desc, String runtime,
			String image, float userRating) {
		super();
		this.title = title;
		this.desc = desc;
		this.runtime = runtime;
		this.image = image;
		this.userRating = userRating;
	}
	
	
}
