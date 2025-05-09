package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.Board;
import com.example.board.entity.FileAttach;
import java.util.List;


public interface FileAttachRepository 
    extends JpaRepository<FileAttach, Long> {
    
    List<FileAttach> findByBoard(Board board);
}
