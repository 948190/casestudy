package com.sonata.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sonata.model.Brand;




@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

	

	Brand findByName(String name);
}