package com.example.book.repository;

import org.springframework.stereotype.Repository;

import com.example.book.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

  private final List<UserDTO> users = new ArrayList<>(List.of(
      new UserDTO(1L, "김철수", 28),
      new UserDTO(2L, "이영희", 34),
      new UserDTO(3L, "박지훈", 23),
      new UserDTO(4L, "최민정", 29),
      new UserDTO(5L, "홍길동", 45),
      new UserDTO(6L, "장보고", 31),
      new UserDTO(7L, "신사임당", 38),
      new UserDTO(8L, "유관순", 19),
      new UserDTO(9L, "안중근", 42),
      new UserDTO(10L, "윤봉길", 33)));

  public List<UserDTO> findAll() {
    return users;
  }

  public UserDTO findById(Long id) {
    return users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
  }

  public UserDTO save(UserDTO userDTO) {
    users.add(userDTO);
    return userDTO;
  }
}