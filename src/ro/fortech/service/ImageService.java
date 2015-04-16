package ro.fortech.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import ro.fortech.access.MovieAccess;
import ro.fortech.model.Movie;
import ro.fortech.utils.Constants;

@Path("images")
public class ImageService {

	@Inject
	private MovieAccess movieAcc;

	
	@GET
	@Path("{id}")
	public BufferedImage getImage(@PathParam("id") String id, @Context ServletContext ctx) {

				
		Properties properties;
		try {
			properties = loadPaths(ctx.getResource(Constants.XML_PATH_JAX).getPath());
			movieAcc.init(properties);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		List<Movie> moviesList = movieAcc.searchDocument("id", id);
		String imageName = moviesList.get(0).getImagine();
		System.out.println("Nume imagine:" + imageName);
		/*String finalPath = "/images/" + imageName;

		System.out.println("Service");*/
		
		BufferedImage img = null;

		/*try {
			img = ImageIO.read(new File(finalPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		return img;

	}

	public Properties loadPaths(String preferencesPath) {

		Properties result = null;
		try {

			/*URL resourceUrl = URL.class.getResource("/WEB-INF/classes/file1.xml");
			try {
				File resourceFile = new File(resourceUrl.toURI());
				System.out.println("ResourcePath:" +resourceFile.getAbsolutePath());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		
			File file = new File(preferencesPath);
			System.out.println(file.getAbsolutePath());

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


