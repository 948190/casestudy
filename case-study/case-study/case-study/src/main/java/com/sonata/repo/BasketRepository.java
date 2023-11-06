package com.sonata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonata.model.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long>{

	List<Basket> findByUserId(Long userId);

}
