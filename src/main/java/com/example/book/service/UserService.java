package com.example.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.book.dto.UserDTO;
import com.example.book.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserDTO createUser(UserDTO userDTO) {
        return userRepository.save(userDTO);
    }
}