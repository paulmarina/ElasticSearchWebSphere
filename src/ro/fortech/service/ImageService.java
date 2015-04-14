package ro.fortech.service;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ro.fortech.access.MovieAccess;
import ro.fortech.model.Movie;
import ro.fortech.utils.Constants;


public class ImageService {
	
	/*private Properties properties = new Properties();
	@Inject
	private MovieAccess movieAcc;
	
	@GET
	@Path("/{id}")
	public Response getImage(@PathParam("id") String id){
		
		//properties = movieAcc.loadPaths();
		
		//List<Movie> moviesList = movieAcc.searchDocument(id, id);
		
		//File imagine = new File(properties.getProperty(Constants.IMAGE_DIR) + moviesList.get(0).getImagine());
		File imagine = new File(properties.getProperty(Constants.IMAGE_DIR));
		return Response.ok().entity(imagine).build();
	
		
		
	}*/
	
	@GET
    public String sayHelloWorld() {
        return "Hello World!";
    }

	

}
