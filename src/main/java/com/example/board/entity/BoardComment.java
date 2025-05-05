package com.example.board.entity;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

import com.example.board.dto.BoardCommentDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(optional = false)
    User user;

    @ManyToOne(optional = false)
    Board board;

    @ManyToOne
    @JoinColumn(nullable = true)
    BoardComment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    List<BoardComment> childComments = new ArrayList<>();

    @Lob
    @Column(nullable = false)
    String content;

    public BoardCommentDto toBoardCommentDto() {
        return new BoardCommentDto(
            id,
            user.getName(),
            content,
            childComments.stream().map(BoardComment::toBoardCommentDto).toList()
        );
    }
}
