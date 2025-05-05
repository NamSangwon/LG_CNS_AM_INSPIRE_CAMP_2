package com.example.board.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.board.dto.BoardCommentDto;
import com.example.board.dto.BoardListDto;
import com.example.board.dto.BoardViewDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;

	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BoardLike> likes = new ArrayList<>();

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BoardComment> comments = new ArrayList<>();

	public BoardListDto toBoardListDto() {
		return new BoardListDto(
			id,
			title,
			user.getName(),
			(long) likes.size()
		);
	}

	public BoardViewDto toBoardViewDto() {
		return new BoardViewDto(
			id,
			title,
			content,
			user.getName(),
			(long) likes.size(),
			comments.stream()
					.filter(comment -> comment.parentComment == null)
					.map(BoardComment::toBoardCommentDto)
					.toList()
		);
	}
}