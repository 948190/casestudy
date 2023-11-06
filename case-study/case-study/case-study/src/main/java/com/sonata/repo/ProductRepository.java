package com.sonata.repo;

import java.util.Date;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sonata.model.Category;
import com.sonata.model.Product;



import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	//Optional<Product> findTopByOrderByPriceDesc();

	List<Product> findByColor(String color);
	 List<Product> findByCategory(Category category);
	 
	 List<Product> findProductsByName(String name);

	
	List<Product> findByName(String name);
	

	

	List<Product> findByNameAndDiscontinuedAfter(String name, Date currentDate);
    

    
	
}

