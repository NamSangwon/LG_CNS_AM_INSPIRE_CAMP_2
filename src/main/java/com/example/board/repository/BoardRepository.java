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

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    /*
        + @OneToMany, @ManyToOne 등과 같이 Entity에서 매핑 명시한 경우
            -> 아래의 [LEFT JOIN b.user u] 만 작성하여도 [명시된 매핑에 의해, ON 구문 생략 가능!]
            -> board_id와 같이 작성된 칼럼은 [board.id]로 작성

        + 검색어 조회 시 LOWER(CONCAT(...))와 같이 LOWER()을 사용하여, 대소문자 구분 X

        + new com.example.board.dto.BoardListDto()를 통해 [DTO 클래스 구성]하여 반환
            -> 소속된 패키지 명시 [필수]
    */

    /*
        SELECT b.id, b.title, u.name, COUNT(*) AS "LIKE"
        FROM Board b
        LEFT JOIN User u ON b.user_id = u.id
        LEFT JOIN board_like bl ON b.id = bl.board_id
        WHERE b.title LIKE "%1%" OR b.content LIKE "%1%"
        GROUP BY b.id, b.title, u.name;
    */
    @Query("""
        SELECT new com.example.board.dto.BoardListDto(b.id, b.title, u.name, COUNT(bl))
        FROM Board b
        LEFT JOIN board_like bl ON b.id = bl.board.id
        LEFT JOIN b.user u
        WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%'))
        GROUP BY b.id, b.title, u.name
    """)
    Page<BoardListDto> findBoardListWithLikeCount(@Param("keyword") String keyword, Pageable pageable);

    /*        
        SELECT b.id, b.title, b.content, u.name, COUNT(*) AS "LIKE"
        FROM Board b
        LEFT JOIN User u ON b.user_id = u.id
        LEFT JOIN board_like bl ON b.id = bl.board_id
        GROUP BY b.id, b.title, b.content, u.name;
    */
    @Query("""
        SELECT new com.example.board.dto.BoardViewDto(b.id, b.title, b.content, u.name, COUNT(bl))
        FROM Board b
        LEFT JOIN board_like bl ON b.id = bl.board.id
        LEFT JOIN b.user u
        WHERE b.id = :boardId
        GROUP BY b.id, b.title, b.content, u.name
        """)
    BoardViewDto findBoardViewDto(Long boardId);
}