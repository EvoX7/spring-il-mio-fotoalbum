package org.fotoalbum.service;

import java.util.List;
import java.util.Optional;
import org.fotoalbum.pojo.Photo;
import org.fotoalbum.repo.PhotoRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepo photoRepo;

	public Photo save(Photo photo) {
		photoRepo.save(photo);

		return photoRepo.save(photo);
	}

	public List<Photo> findAll() {
		return photoRepo.findAll();
	}

	public Optional<Photo> findPhotobyId(int id) {
		return photoRepo.findById(id);
	}

	public List<Photo> findByTitleOrTag(String query) {
		return photoRepo.findByTitleContainingOrTagContaining(query, query);
	}

	public void deleteById(int id) {

		photoRepo.deleteById(id);
	}

	@Transactional
	public List<Photo> findAllPlusCategory() {
		List<Photo> photos = photoRepo.findAll();
		for (Photo p : photos)
			Hibernate.initialize(p.getCategories());
		return photos;
	}

}
