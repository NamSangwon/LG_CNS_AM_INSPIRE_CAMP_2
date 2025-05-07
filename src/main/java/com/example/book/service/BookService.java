package com.example.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDTO;
import com.example.book.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public BookDTO createBook(BookDTO bookDTO) {
        return bookRepository.save(bookDTO);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}