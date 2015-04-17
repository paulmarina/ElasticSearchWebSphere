package ro.fortech.dialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import ro.fortech.business.DisplayMoviesController;
import ro.fortech.model.Movie;
import ro.fortech.utils.Constants;

@ManagedBean(name = "displayMoviesDialog")
@SessionScoped
public class DisplayMoviesDialog implements Serializable {

	private static final long serialVersionUID = -1718678896995420865L;

	@Inject
	private DisplayMoviesController displayCtrl;
	private List<Movie> moviesList;
	private Movie newMovie;

	public Movie getNewMovie() {
		return newMovie;
	}

	public void setNewMovie(Movie newMovie) {
		this.newMovie = newMovie;
	}

	public DisplayMoviesDialog() {
		resetNewMovie();
	}

	@PostConstruct
	public void init3000() {
		Properties properties = loadPaths();
		displayCtrl.init(properties);
		setMoviesList(displayCtrl.displayMovies());
	}

	public List<Movie> getMoviesList() {
		return moviesList;
	}

	public void setMoviesList(List<Movie> moviesList) {
		this.moviesList = moviesList;
	}
	
	private void resetNewMovie(){
		this.newMovie = new Movie("", "", null, null, "coffee.jpg");
	}

	public Properties loadPaths() {

		Properties result = null;
		try {

			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String path = servletContext.getRealPath(Constants.XML_PATH);
			System.out.println("HEREEE" + path);
			File file = new File(path);

			FileInputStream fileInput = new FileInputStream(file);

			Properties properties = new Properties();
			properties.loadFromXML(fileInput);
			fileInput.close();
			result = properties;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String deleteMovie(Movie movie) {
		Boolean deleteSucceeded = displayCtrl.deleteMovie(String.valueOf(movie.getId()));
		if (deleteSucceeded){
			this.moviesList.remove(movie);
		}
		return "index.xhtml?faces-redirect=true";
	}
	
	public String addMovie() {
		String newMovieId = displayCtrl.addMovie(newMovie);
		if (!newMovieId.equals("")){
			newMovie.setId(newMovieId);
			this.moviesList.add(newMovie);
		}
		resetNewMovie();
		return "index.xhtml?faces-redirect=true";
	}
}
