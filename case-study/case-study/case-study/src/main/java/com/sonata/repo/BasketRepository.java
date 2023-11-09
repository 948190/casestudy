package com.sonata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonata.model.Basket;
import com.sonata.model.User;

public interface BasketRepository extends JpaRepository<Basket, Long>{

	//List<Basket> findByUserId(Long userId);

	List<Basket> findByUser(User user);

}
