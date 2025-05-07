package com.example.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.book.common.ApiResponse;
import com.example.book.dto.BookDTO;
import com.example.book.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ApiResponse<List<BookDTO>> getAllBooks() {
        return ApiResponse.success(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ApiResponse<BookDTO> getBookById(@PathVariable Long id) {
        return ApiResponse.success(bookService.getBookById(id));
    }

    @PostMapping
    public ApiResponse<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return ApiResponse.success(bookService.createBook(bookDTO));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ApiResponse.success(null);
    }
}