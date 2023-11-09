package com.sonata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sonata.model.Inventory;
import com.sonata.model.Product;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Inventory> findAllByProduct(Product product);

	@Query("SELECT MAX(i.price) FROM Inventory i WHERE i.product.productId = :productId")
    Double findMaxPriceByProductId(@Param("productId") Long productId);

	@Query("SELECT i.batchId FROM Inventory i WHERE i.product.productId = :productId AND i.price = :maxPrice")
    Long findBatchIdForMaxPrice(@Param("productId") Long productId, @Param("maxPrice") Double maxPrice);

}


