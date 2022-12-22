package org.fotoalbum.controller.api;

import java.util.List;
import org.fotoalbum.pojo.Category;
import org.fotoalbum.pojo.Photo;
import org.fotoalbum.service.CategoryService;
import org.fotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1")
@CrossOrigin("*")
public class CategoryControllerApi {

	@Autowired
	private PhotoService photoService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/all")
	public List<Category> getAll() {
		return categoryService.findAll();
	}
	
	@GetMapping("/category/{id}")
	public List<Category> getCategoryByPhotoId(@PathVariable("id") int id) {
		
		Photo photo = photoService.findPhotobyId(id).get();
		return  photo.getCategories();
	}
}
