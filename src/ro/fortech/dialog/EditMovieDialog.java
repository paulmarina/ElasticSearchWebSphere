package ro.fortech.dialog;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import ro.fortech.business.DisplayMoviesController;
import ro.fortech.model.Movie;


@ManagedBean(name="editMovieDialog")
@SessionScoped
public class EditMovieDialog implements Serializable{

	
	
	private static final long serialVersionUID = 4128776498153336119L;
	
	private Movie movie;
	
	@Inject
	private DisplayMoviesController displayCtrl;
	
	private boolean isEditMode;

	public EditMovieDialog() {
		
		this.setMovie(new Movie());
	}
	
	
	public String editMovie(Movie movie) {

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
	
	
	

}
