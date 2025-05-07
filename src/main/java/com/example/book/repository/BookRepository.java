package com.example.book.repository;

import org.springframework.stereotype.Repository;

import com.example.book.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

  private final List<BookDTO> books = new ArrayList<>(List.of(
      new BookDTO(1L, "자바의 정석", "남궁성"),
      new BookDTO(2L, "Effective Java", "Joshua Bloch"),
      new BookDTO(3L, "스프링 인 액션", "Craig Walls"),
      new BookDTO(4L, "토비의 스프링", "이일민"),
      new BookDTO(5L, "모두의 파이썬", "이지스퍼블리싱"),
      new BookDTO(6L, "클린 코드", "Robert C. Martin"),
      new BookDTO(7L, "Go 언어 프로그래밍", "마크 서머필드"),
      new BookDTO(8L, "혼자 공부하는 SQL", "강성윤"),
      new BookDTO(9L, "Do it! HTML5 + CSS3", "고경희"),
      new BookDTO(10L, "면접을 위한 CS 전공지식", "정종현")));

  public List<BookDTO> findAll() {
    return books;
  }

  public BookDTO findById(Long id) {
    return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
  }

  public BookDTO save(BookDTO bookDTO) {
    books.add(bookDTO);
    return bookDTO;
  }

  public void deleteById(Long id) {
    books.removeIf(b -> b.getId().equals(id));
  }
}