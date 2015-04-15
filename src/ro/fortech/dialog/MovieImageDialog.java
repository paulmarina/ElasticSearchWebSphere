package ro.fortech.dialog;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="movieImageDialog")
@SessionScoped
public class MovieImageDialog implements Serializable{

	
	private static final long serialVersionUID = 8621173128034519586L;
    private String imagine;
    
    
	public String getImagine() {
		return imagine;
	}
	public void setImagine(String imagine) {
		this.imagine = imagine;
	}
    
	 
}
