package ro.fortech.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ro.fortech.access.MovieAccess;
import ro.fortech.model.Movie;
import ro.fortech.utils.Constants;

@Stateless
public class DisplayMoviesController {
	
	@Inject
	private MovieAccess movieAccess;
	
	public DisplayMoviesController(){		
	}
	
	public List<Movie> displayMovies() {
		/*Movie mov = new Movie("Pearl Harbor", "Michael Bay", 2001, 16, "coffee.jpg");
		
		client.prepareIndex(Constants.INDEX, Constants.TYPE,
				   String.valueOf(mov.getId()))
				   .setSource(movieAccess.createJsonDocument(mov)).execute()
				   .actionGet();*/
		
		//Movie mov1 = new Movie("Fast and furious", "Francis Ford Coppola", 1972, 34,"coffee.jpg");
	/*	Movie mov2 = new Movie("12 angry men", "Sidney Lumet", 1957, 18);
		Movie mov3 = new Movie("Pulp Fiction", "Quentin Tarantino", 1994, 19);
		Movie mov4 = new Movie("Inception", "Christopher Nolan", 2010, 20);
		Movie mov5 = new Movie("The Silence of the Lambs", "Jonathan Demme", 1991, 21);
		movieAccess.upsertDocument(mov);
		movieAccess.upsertDocument(mov1);
		movieAccess.upsertDocument(mov2);
		movieAccess.upsertDocument(mov3);
		movieAccess.upsertDocument(mov4);*/
		//movieAccess.upsertDocument(mov1);
		return movieAccess.searchDocument();
	}
	
	public List<Movie> deleteMovie(String id) {
		movieAccess.deleteDocument(id);
		return displayMovies();
	}

}
