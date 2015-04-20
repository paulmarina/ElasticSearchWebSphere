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

	private Movie newMovie;

	public DisplayMoviesDialog() {
		resetNewMovie();

	}

	private void resetNewMovie() {
		this.newMovie = new Movie("", "", null, null, "coffee.jpg");
	}

	@PostConstruct
	public void init3000() {
		Properties properties = loadPaths();
		displayCtrl.init(properties);
		displayCtrl.setMoviesList(displayCtrl.displayMovies());
	}

	public List<Movie> displayMoviesList() {
		return displayCtrl.getMoviesList();
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
		displayCtrl.deleteMovie(movie);
		return "index.xhtml?faces-redirect=true";
	}

	public String addMovie() {
		displayCtrl.addMovie(newMovie);
		resetNewMovie();
		return "editMovie.xhtml?faces-redirect=true";
	}

	public Movie getNewMovie() {
		return newMovie;
	}

	public void setNewMovie(Movie newMovie) {
		this.newMovie = newMovie;
	}
}
