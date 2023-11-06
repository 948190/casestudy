package com.sonata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sonata.model.Inventory;
import com.sonata.model.Product;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Inventory> findAllByProduct(Product product);

}
