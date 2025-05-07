package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findByEmailOrName(String email, String name);

    Optional<User> findByEmailAndPwd(String email, String pwd);
}