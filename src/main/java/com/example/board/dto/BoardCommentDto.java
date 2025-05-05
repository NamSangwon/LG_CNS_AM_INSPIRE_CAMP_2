package com.example.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentDto {
    Long id;
    String userName;
    String content;
    List<BoardCommentDto> childComments;
}
