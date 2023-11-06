package com.sonata.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sonata.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
