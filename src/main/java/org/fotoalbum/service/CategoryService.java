package org.fotoalbum.service;

import java.util.List;
import java.util.Optional;
import org.fotoalbum.pojo.Category;
import org.fotoalbum.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	public void save(Category category) {
		categoryRepo.save(category);
	}

	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	public Optional<Category> findCategorybyId(int id) {
		return categoryRepo.findById(id);
	}
	
	public List<Category> findByName(String query) {
		return categoryRepo.findByNameContaining(query);
	}

	public void deleteCategoryById(int id) {
		categoryRepo.deleteById(id);
	}

}
