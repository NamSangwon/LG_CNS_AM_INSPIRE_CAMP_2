package com.example.board.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Data
@Entity(name = "board_like")
public class BoardLike {
    @EmbeddedId
    private BoardLikeId id;

    @ManyToOne
    @MapsId("boardId") // id.boardId에 매핑
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @MapsId("userId") // id.userId에 매핑
    @JoinColumn(name = "user_id")
    private User user;

}
