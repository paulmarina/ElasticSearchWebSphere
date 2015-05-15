package ro.fortech.access;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import ro.fortech.model.Movie;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class ImdbMovieAccess {

	public Movie searchImdbByTitle(String title) {

		Movie movie = new Movie();
		try {

			InputStream input = new URL("http://www.omdbapi.com/?t="
					+ URLEncoder.encode(title, "UTF-8")).openStream();
			Map<String, String> map = new Gson().fromJson(
					new InputStreamReader(input, "UTF-8"),
					new TypeToken<Map<String, String>>() {

						private static final long serialVersionUID = 1L;
					}.getType());

			movie.setDirector(map.get("Director"));
			movie.setTitle(map.get("Title"));
			movie.setYear(Integer.parseInt(map.get("Year")));
			
			System.out.println(movie.getDirector());

		} catch (Exception e) {
			System.out.println("exception: " + e.getMessage());
		}
		return movie;
	}

}
