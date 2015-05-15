package ro.fortech.dialog;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ro.fortech.business.IMDBMoviesController;
import ro.fortech.model.Movie;

@ManagedBean(name = "imdb")
@SessionScoped
public class ImdbMovieDialog implements Serializable {

	private static final long serialVersionUID = -5433075411679637433L;

	@Inject
	private IMDBMoviesController imdbMovieController;
	
	private Movie movie;
	
	public ImdbMovieDialog(){
		
		this.movie = new Movie();
		
	}

	public String searchImdbByTitle(String title) {

		imdbMovieController.searchImdbByTitle(title);
		return "index?faces-redirect=true";
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
