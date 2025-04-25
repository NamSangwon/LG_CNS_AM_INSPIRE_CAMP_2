package com.example.animal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.animal.entity.Warranty;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
  
}
