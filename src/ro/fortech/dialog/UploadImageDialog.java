package ro.fortech.dialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import ro.fortech.access.ImageAccess;
import ro.fortech.business.DisplayMoviesController;
import ro.fortech.model.Image;
import ro.fortech.model.Movie;
import ro.fortech.utils.Constants;
import ro.fortech.utils.MediaData;

@ManagedBean(name = "uploadImageDialog")
@SessionScoped
public class UploadImageDialog implements Serializable {

	private static final long serialVersionUID = 6977230876142770216L;

	@Inject
	private ImageAccess imgAcc;

	@Inject
	private DisplayMoviesController moviesCtrl;

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

	public void paint(OutputStream stream, Object object) throws IOException {
		/*
		 * String id = (String) object; BinaryContent content = (BinaryContent)
		 * getContentById(id); os.write(content.getContent());
		 */
		if (object instanceof MediaData) {
			stream.write(getImages().get((Integer) object).getData());
			stream.close();
		}
	}

	public String listener(FileUploadEvent event) throws Exception {
		UploadedFile item = event.getUploadedFile();
		Image image = new Image();
		image.setLength(item.getData().length);
		image.setName(item.getName());
		image.setData(item.getData());
		image.setMovieId(editedMovie.getId());
		images.add(image);
		return "editMovie.xhtml?faces-redirect=true";
	}

	public String clearUploadData() {
		images.clear();
		setUploadsAvailable(5);
		return null;
	}

	public String uploadImage(Movie movie, String imageName) {

		Image image = new Image(imageName, movie.getId());
		Properties properties = loadPaths();
		imgAcc.init(properties);

		imgAcc.addImage(image);

		// imagePath = "/rest/images/" + movie.getId();
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
