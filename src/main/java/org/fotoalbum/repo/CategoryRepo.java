package org.fotoalbum.repo;

import java.util.List;

import org.fotoalbum.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	public List<Category> findByNameContaining(String name);

}
