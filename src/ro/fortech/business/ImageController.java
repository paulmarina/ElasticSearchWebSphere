package ro.fortech.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ro.fortech.access.ImageAccess;
import ro.fortech.model.Image;

@Stateless
public class ImageController {
	
	@Inject
	private ImageAccess imgAcc;
	
	private List<Image> moviesList;
}
