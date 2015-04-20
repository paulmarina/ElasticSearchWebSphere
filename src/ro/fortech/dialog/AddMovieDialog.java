package ro.fortech.dialog;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ro.fortech.business.DisplayMoviesController;
import ro.fortech.model.Movie;


@ManagedBean(name="addMovieDialog")
@SessionScoped
public class AddMovieDialog implements Serializable {
	
	private static final long serialVersionUID = -8105770864383970169L;
	
	@Inject
	private DisplayMoviesController displayCtrl;

	private Movie newMovie;

	public AddMovieDialog() {
		resetNewMovie();
	}
	
	private void resetNewMovie() {
		this.newMovie = new Movie("", "", null, null, "coffee.jpg");
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
