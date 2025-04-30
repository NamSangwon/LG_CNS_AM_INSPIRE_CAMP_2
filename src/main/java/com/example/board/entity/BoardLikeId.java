package com.example.board.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BoardLikeId implements Serializable {

    private Long boardId;
    private Long userId;

    public BoardLikeId() {}

    public BoardLikeId(Long boardId, Long userId) {
        this.boardId = boardId;
        this.userId = userId;
    }

    // equals & hashCode 구현 필수
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardLikeId)) return false;
        BoardLikeId that = (BoardLikeId) o;
        return Objects.equals(boardId, that.boardId) &&
               Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, userId);
    }

    // Getter/Setter 생략 가능
}
