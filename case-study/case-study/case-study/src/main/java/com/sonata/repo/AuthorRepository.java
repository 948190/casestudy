package com.sonata.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonata.model.Author;


public interface AuthorRepository extends JpaRepository<Author, Long>{

}
