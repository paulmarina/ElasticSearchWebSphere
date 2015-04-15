package ro.fortech.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/image")
public class ImageService {

	@GET
    public String getImage() {
		
        return "Hello World!";
    }

	

}
