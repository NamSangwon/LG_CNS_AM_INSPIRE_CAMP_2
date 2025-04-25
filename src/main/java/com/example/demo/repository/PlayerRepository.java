package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Player;

public interface PlayerRepository 
    extends JpaRepository<Player, Integer> {
    
}
