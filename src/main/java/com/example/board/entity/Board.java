package com.example.board.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.example.board.dto.BoardListDto;
import com.example.board.dto.BoardViewDto;

import jakarta.persistence.Column;
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



	public BoardListDto toBoardListDto() {
		return new BoardListDto(id, title, user.getName());
	}

	public BoardViewDto toBoardViewDto() {
		return new BoardViewDto(id, title, content, user.getName());
	}
}