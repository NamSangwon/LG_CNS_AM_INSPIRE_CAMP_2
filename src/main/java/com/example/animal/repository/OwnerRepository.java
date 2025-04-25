package com.example.animal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.animal.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
  
}
