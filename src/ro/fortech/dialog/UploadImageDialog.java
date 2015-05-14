package ro.fortech.dialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.UploadedFile;

import ro.fortech.business.DisplayMoviesController;
import ro.fortech.business.ImageController;
import ro.fortech.model.Image;
import ro.fortech.model.Movie;
import ro.fortech.utils.Constants;

@ManagedBean(name = "uploadImageDialog")
@SessionScoped
public class UploadImageDialog implements Serializable {

	private static final long serialVersionUID = 6977230876142770216L;

	@Inject
	private DisplayMoviesController moviesCtrl;
	
	@Inject
	private ImageController imgCtrl;

	public UploadImageDialog() {

	}

	private ArrayList<Image> images = new ArrayList<Image>();
	private int uploadsAvailable = 5;
	private Movie editedMovie;

	public Movie getEditedMovie() {
		return editedMovie;
	}

	public void setEditedMovie(Movie editedMovie) {
		this.editedMovie = editedMovie;
	}

	@PostConstruct
	public void init7() {
		this.editedMovie = moviesCtrl.getAddedMovie();
	}

	public ArrayList<Image> getImages() {
		return images;
	}

	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getSize() {
		if (getImages().size() > 0) {
			return getImages().size();
		} else {
			return 0;
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		
		Image uploadedImage = new Image(); 
		uploadedImage.setName(file.getFileName());
		uploadedImage.setMovieId(editedMovie.getId());
		
		Properties properties = loadPaths();
		imgCtrl.init(properties);
		imgCtrl.addImage(uploadedImage);
		
        FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
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
