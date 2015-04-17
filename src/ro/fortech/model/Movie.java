package ro.fortech.model;

public class Movie {

	private String title;
	private String director;
	private Integer year;
	private String id;
	private String imagine;
	
	public Movie(String title, String director, Integer year, String id, String imagine) {
		super();
		this.title = title;
		this.director = director;
		this.year = year;
		this.id = id;
		this.imagine = imagine;
	}

	public String getTitle() {
		
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagine() {
		return imagine;
	}

	public void setImagine(String imagine) {
		this.imagine = imagine;
	}

}
