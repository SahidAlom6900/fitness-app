package com.technoelevate.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.fitness.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByProductId(int productId);
}
