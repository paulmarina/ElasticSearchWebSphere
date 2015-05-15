package ro.fortech.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ro.fortech.access.ImdbMovieAccess;

@Stateless
public class IMDBMoviesController {

	@Inject
	private ImdbMovieAccess imdbMovieAccess;

	public void searchImdbByTitle(String title) {

		imdbMovieAccess.searchImdbByTitle(title);

	}
}