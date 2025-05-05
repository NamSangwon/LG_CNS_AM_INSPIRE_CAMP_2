package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.BoardComment;

public interface BoardCommentRepository 
    extends JpaRepository<BoardComment, Long> {
    
}
