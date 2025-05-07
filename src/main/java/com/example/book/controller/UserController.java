package com.example.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.book.common.ApiResponse;
import com.example.book.dto.UserDTO;
import com.example.book.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserDTO>> getAllUsers() {
        return ApiResponse.success(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getUserById(@PathVariable Long id) {
        return ApiResponse.success(userService.getUserById(id));
    }

    @PostMapping
    public ApiResponse<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ApiResponse.success(userService.createUser(userDTO));
    }
}