package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.FileAttach;

public interface FileAttachRepository 
    extends JpaRepository<FileAttach, Long> {
    
}
