package org.fotoalbum.controller.api;

import java.util.List;
import org.fotoalbum.pojo.Photo;
import org.fotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/photos")
@CrossOrigin("*")
public class PhotoControllerApi {

	@Autowired
	private PhotoService photoService;

	@GetMapping("/all")
	public List<Photo> getAllPhotos() {

		List<Photo> allPhotos = photoService.findAll();
		return allPhotos;
	}
	
	@GetMapping("/search/{query}")
	public List<Photo> searchPhotoByTitleOrTag(@PathVariable("query") String query) {
		
		List<Photo> photos = query == null ? photoService.findAll() : photoService.findByTitleOrTag(query);
		
		return photos;
 	}

	
}
