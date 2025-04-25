package com.example.animal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.animal.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
  
}
