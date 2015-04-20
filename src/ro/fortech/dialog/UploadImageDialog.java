package ro.fortech.dialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import ro.fortech.access.ImageAccess;
import ro.fortech.model.Image;
import ro.fortech.model.Movie;
import ro.fortech.utils.Constants;

@ManagedBean(name = "uploadImageDialog")
@SessionScoped
public class UploadImageDialog implements Serializable{
	
	private static final long serialVersionUID = 6977230876142770216L;
	
	@Inject
	private ImageAccess imgAcc;
	
	public String uploadImage(Movie movie, String imageName) {

		Image image = new Image(imageName, movie.getId());
		Properties properties = loadPaths();
		imgAcc.init(properties);

		imgAcc.addImage(image);

		//imagePath = "/rest/images/" + movie.getId();
		return "movieImage?faces-redirect=true";
	}

	public Properties loadPaths() {

		Properties result = null;
		try {

			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String path = servletContext.getRealPath(Constants.XML_PATH);
			System.out.println("HEREEE" + path);
			File file = new File(path);

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
