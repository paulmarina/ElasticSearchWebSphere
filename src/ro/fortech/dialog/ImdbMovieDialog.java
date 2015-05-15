package ro.fortech.dialog;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ro.fortech.business.IMDBMoviesController;

@ManagedBean(name = "imdb")
@SessionScoped
public class ImdbMovieDialog implements Serializable {

	private static final long serialVersionUID = -5433075411679637433L;

	@Inject
	private IMDBMoviesController imdbMovieController;

	public String searchImdbByTitle() {

		imdbMovieController.searchImdbByTitle();
		return "";
	}

}
