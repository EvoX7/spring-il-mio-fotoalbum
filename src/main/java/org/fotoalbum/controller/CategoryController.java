package org.fotoalbum.controller;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;
import org.fotoalbum.pojo.Category;
import org.fotoalbum.pojo.Photo;
import org.fotoalbum.service.CategoryService;
import org.fotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PhotoService photoService;

	@GetMapping
	public String categoryIndex(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "index-category";
	}

	@GetMapping("/admin/create")
	public String createNewCategory(Model model) {

		Category category = new Category();
		
		List<Photo> photos = photoService.findAll();
		model.addAttribute("category", category);
		model.addAttribute("photos", photos);
		

		return "category-create";
	}

	@PostMapping("/admin/create")
	public String storeCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/category/create";
		}

		categoryService.save(category);
		return "redirect:/category";
	}

	@GetMapping("/admin/edit/{id}")
	public String editCategory(@PathVariable("id") int id, Model model) {

		Optional<Category> optCategory = categoryService.findCategorybyId(id);
		Category category = optCategory.get();
		
		List<Photo> photos = photoService.findAll();

		model.addAttribute("category", category);
		model.addAttribute("photos", photos);

		return "category-update";
	}

	@PostMapping("/admin/edit")
	public String updateCategory(@Valid @ModelAttribute("category") Category category) {

		categoryService.save(category);

		return "redirect:/category";
	}


	@GetMapping("/admin/delete/{id}")
	public String deleteCategory(@PathVariable("id") int id) {

		Optional<Category> optCategory = categoryService.findCategorybyId(id);
		Category category = optCategory.get();

		for (Photo photo : category.getPhotos()) {
			photo.removeCategory(category);
		}

		categoryService.deleteCategoryById(id);
		return "redirect:/category";
	}

//	SEARCH BAR 

	@GetMapping("/search")

	public String SearchCategoryByTitle(Model model, @RequestParam(name = "query", required = false) String query) {

		List<Category> categories = null;
		if (query == null) {

			categories = categoryService.findAll();

		} else {

			categories = categoryService.findByName(query);
		}

		model.addAttribute("categories", categories);
		model.addAttribute("query", query);

		return "category-search";
	}
}
