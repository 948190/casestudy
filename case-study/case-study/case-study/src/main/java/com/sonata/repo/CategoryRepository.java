package com.sonata.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonata.model.Brand;
import com.sonata.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

	

	Category findByName(String name);

}
