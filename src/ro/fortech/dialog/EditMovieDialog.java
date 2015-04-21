package ro.fortech.dialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import ro.fortech.business.DisplayMoviesController;
import ro.fortech.model.Movie;
import ro.fortech.utils.Constants;

@ManagedBean(name = "editMovieDialog")
@SessionScoped
public class EditMovieDialog implements Serializable {

	private static final long serialVersionUID = 4128776498153336119L;

	private Movie movie;

	@Inject
	private DisplayMoviesController displayCtrl;

	private boolean isEditMode;

	private String imagePath;

	public EditMovieDialog() {

		this.setMovie(new Movie());
	}

	public String editMovie(Movie movie) {

		imagePath = "/rest/images/" + movie.getId();

		setMovie(displayCtrl.editMovie(movie.getId()));
		return "editMovie?faces-redirect=true";
	}

	public String updateMovie(Movie movie) {

		displayCtrl.updateMovie(movie);
		return "index?faces-redirect=true";
	}

	public String checkEditMode(ActionEvent event) {

		String buttonId = event.getComponent().getId();

		if (buttonId.equals("edit")) {
			isEditMode = true;
		}

		else {
			isEditMode = false;
		}

		return String.valueOf(isEditMode);
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public boolean getIsEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Properties loadPaths() {

		Properties result = null;
		try {

			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String path = servletContext.getRealPath(Constants.XML_PATH);
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