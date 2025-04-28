package com.example.animal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.animal.entity.Playground;
import java.util.List;


public interface PlaygroundRepository extends JpaRepository<Playground, Long> {
  List<Playground> findByAddress(String address);
}
