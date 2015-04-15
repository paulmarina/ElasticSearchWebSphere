package ro.fortech.dialog;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ro.fortech.business.DisplayMoviesController;
import ro.fortech.model.Movie;

@ManagedBean(name = "displayMoviesDialog")
@SessionScoped
public class DisplayMoviesDialog implements Serializable {

	private static final long serialVersionUID = -1718678896995420865L;

	@Inject
	private DisplayMoviesController displayCtrl;
	private List<Movie> moviesList;

	public DisplayMoviesDialog() {
	}

	@PostConstruct
	public void init3000() {
		setMoviesList(displayCtrl.displayMovies());
	}

	public List<Movie> getMoviesList() {
		return moviesList;
	}

	public void setMoviesList(List<Movie> moviesList) {
		this.moviesList = moviesList;
	}

	public String deleteMovie(Movie movie) {
		Boolean deleteSucceeded = displayCtrl.deleteMovie(String.valueOf(movie.getId()));
		if (deleteSucceeded){
			this.moviesList.remove(movie);
		}
		return "index.xhtml?faces-redirect=true";
	}
}
