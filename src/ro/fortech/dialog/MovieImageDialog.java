package ro.fortech.dialog;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import ro.fortech.model.Movie;

@ManagedBean(name = "movieImageDialog")
@SessionScoped
public class MovieImageDialog implements Serializable {

	private static final long serialVersionUID = 8621173128034519586L;
	private String imagePath;

	public String displayImage(Movie movie) {
		
		
		imagePath = "/rest/images/" + movie.getId();
		System.out.println(imagePath);

		return "movieImage?faces-redirect=true";
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
