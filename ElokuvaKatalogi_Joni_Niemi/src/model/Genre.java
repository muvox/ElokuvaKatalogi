package model;

public class Genre {
	
	String name;	//genren nimi
	
	int id;			//yksilöivä id
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Genre( int id, String name) {
		super();
		this.name = name;
		this.id = id;
	}


	

}
