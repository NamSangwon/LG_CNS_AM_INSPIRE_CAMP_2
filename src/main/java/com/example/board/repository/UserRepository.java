package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findByEmailOrName(String email, String name);

    List<User> findByEmailAndPwd(String email, String pwd);
}