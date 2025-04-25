package com.example.animal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.animal.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  
}
