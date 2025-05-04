package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.BoardLike;
import com.example.board.entity.BoardLikeId;

public interface BoardLikeRepository 
    extends JpaRepository<BoardLike, BoardLikeId> {
    
}
