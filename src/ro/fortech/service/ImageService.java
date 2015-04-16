package ro.fortech.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;

import javax.activation.MimetypesFileTypeMap;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import ro.fortech.access.MovieAccess;
import ro.fortech.model.Movie;
import ro.fortech.utils.Constants;

@Path("images")
public class ImageService {

	@Inject
	private MovieAccess movieAcc;

	@GET
	@Path("{id}")
	@Produces("image/jpg")
	public Response getImage(@PathParam("id") String id,
			@Context ServletContext ctx) {

		Properties properties;
		try {
			properties = loadPaths(ctx.getResource(Constants.XML_PATH_JAX)
					.getPath());
			movieAcc.init(properties);

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		List<Movie> moviesList = movieAcc.searchDocument("id", id);
		String imageName = moviesList.get(0).getImagine();

		File f = null;
		String mt = "";

		try {
			String myPath = ctx.getResource(Constants.IMAGE_PATH_JAX).getPath()
					+ imageName;
			System.out.println("MyPath: " + myPath);
			f = new File(myPath);
			mt = new MimetypesFileTypeMap().getContentType(f);

		} catch (MalformedURLException e) {

			e.printStackTrace();
		}

		return Response.ok(f, mt).build();

	}

	public Properties loadPaths(String preferencesPath) {

		Properties result = null;
		try {

			File file = new File(preferencesPath);
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
}
