package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Major;
import java.util.List;
import java.util.Optional;


public interface MajorRepository 
    extends JpaRepository<Major, Integer> {
    
    // Query Method
    Optional<Major> findById(Integer id);
    
    List<Major> findByName(String name);
}
