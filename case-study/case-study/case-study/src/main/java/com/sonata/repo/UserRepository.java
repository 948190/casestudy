package com.sonata.repo;

import java.util.List;
import java.util.OptionalDouble;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sonata.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User u")
    List<User> findAllUsers();

	

}
