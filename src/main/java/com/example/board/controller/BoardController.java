package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.board.repository.BoardRepository;

@Controller
public class BoardController {
	@Autowired
	BoardRepository boardRepository;
	
	@GetMapping("/board/delete")
	public String boardDelete() {
		return "redirect:/board";
	}

	@GetMapping("/board/update")
	public String boardUpdate() {

		return "board/update";
	}
		
	@PostMapping("/board/update")
	public String boardUpdatePost() {
		return "redirect:/board";
	}

	@GetMapping("/board/view")
	public String boardView() {
		return "board/view";
	}

	@GetMapping("/board")
	public String board() {

		return "redirect:/board/list";
	}

	@GetMapping("/board/list")
	public String boardList() {

		return "board/list";
	}

	@GetMapping("/board/write")
	public String boardWrite() {

		return "board/write";
	}
	
	@PostMapping("/board/write")
	public String boardWritePost() {

		return "redirect:/board";
	}
}