package com.example.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.board.dto.BoardListDto;
import com.example.board.dto.BoardViewDto;
import com.example.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // JPA 사용 대신 [Entity 간의 매핑]과 [Query Method] 만으로 해결 가능
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}