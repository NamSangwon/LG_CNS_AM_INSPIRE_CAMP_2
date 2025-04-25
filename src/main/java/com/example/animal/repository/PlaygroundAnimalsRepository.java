package com.example.animal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.animal.entity.PlaygroundAnimals;

public interface PlaygroundAnimalsRepository extends JpaRepository<PlaygroundAnimals, Long> {
  
}
