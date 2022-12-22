package org.fotoalbum.controller;

import java.util.List;
import java.util.Optional;
import org.fotoalbum.pojo.Category;
import org.fotoalbum.pojo.Photo;
import org.fotoalbum.service.CategoryService;
import org.fotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	public PhotoService photoService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String getPhotos(Model model) {

		List<Photo> photos = photoService.findAll();
		model.addAttribute("photos", photos);

		return "index-photo";
	}

	@GetMapping("/admin/create")
	public String createNewPhoto(Model model) {

		Photo photo = new Photo();
		model.addAttribute("photo", photo);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		return "photo-create";
	}

	@PostMapping("/admin/create")
	public String storePhoto(@Valid Photo photo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

			return "redirect:/photos/create";
		}

		photoService.save(photo);
		return "redirect:/photos";
	}

	@GetMapping("/admin/edit/{id}")
	public String editPhoto(@PathVariable("id") int id, Model model) {

		Optional<Photo> optPhoto = photoService.findPhotobyId(id);
		Photo photo = optPhoto.get();

		List<Category> categories = categoryService.findAll();

		model.addAttribute("categories", categories);
		model.addAttribute("photo", photo);

		return "photo-update";
	}

	@PostMapping("/admin/edit")
	public String updatePhoto(@Valid Photo photo) {

		photoService.save(photo);

		return "redirect:/photos";
	}

	@GetMapping("/admin/delete/{id}")
	public String deletePhoto(@PathVariable("id") int id) {

		photoService.deleteById(id);

		return "redirect:/photos";
	}

	@GetMapping("/admin/show/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		Optional<Photo> optPhoto = photoService.findPhotobyId(id);

		Photo photo = optPhoto.get();
		List<Category> categories = photo.getCategories();

		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);

		return "photo-show";
	}

//	SEARCH BAR 

	@GetMapping("/search")

	public String SearchPhotoByTitle(Model model, @RequestParam(name = "query", required = false) String query) {

		List<Photo> photos = null;
		if (query == null) {

			photos = photoService.findAll();

		} else {

			photos = photoService.findByTitleOrTag(query);
		}

		model.addAttribute("photos", photos);
		model.addAttribute("query", query);

		return "photo-search";
	}

}
