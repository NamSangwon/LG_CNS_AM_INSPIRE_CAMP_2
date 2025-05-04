package com.example.board.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.dto.BoardListDto;
import com.example.board.dto.BoardViewDto;
import com.example.board.entity.Board;
import com.example.board.entity.BoardLike;
import com.example.board.entity.BoardLikeId;
import com.example.board.entity.User;
import com.example.board.repository.BoardLikeRepository;
import com.example.board.repository.BoardRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	@Autowired
	BoardRepository boardRepository;

	@Autowired
	BoardLikeRepository boardLikeRepository;
	
	@GetMapping("/board/delete/{boardId}")
	public String boardDelete(
		@PathVariable Long boardId,
		HttpSession session
	) {
		try {
			Object obj = session.getAttribute("user_info");
			if (obj == null)
				throw new Exception();

			if (boardId == null)
				throw new Exception();

			Optional<Board> opt = boardRepository.findById(boardId);
			if (opt.isPresent()) {
				Board board = opt.get();

				if (obj.equals(board.getUser())) {
					boardRepository.delete(board);
				}
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return "redirect:/board";
	}

	@GetMapping("/board/update/{boardId}")
	public String boardUpdate(
		@PathVariable Long boardId,
		HttpSession session,
		Model model
	) {
		try {
			Object obj = session.getAttribute("user_info");
			
			if (boardId == null)
				throw new Exception();

			if (obj == null) 
				throw new Exception();

			Optional<Board> opt = boardRepository.findById(boardId);
			if (opt.isPresent())
			{
				Board board = opt.get();

				if (obj.equals(board.getUser())) {
					model.addAttribute("board", board);
				}
				else {
					throw new Exception();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());

			return "redirect:/board";
		}

		return "board/update";
	}
		
	@PostMapping("/board/update/{boardId}")
	public String boardUpdatePost(
		@PathVariable Long boardId,
		@ModelAttribute Board board,
		HttpSession session
	) {
		try {
			Object obj = session.getAttribute("user_info");

			if (obj == null)
				throw new Exception();

			if (obj instanceof User) {
				User user = (User) obj;

				board.setId(boardId);
				board.setUser(user);

				boardRepository.save(board);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return "redirect:/board";
	}

	@GetMapping("/board/view/{boardId}")
	public String boardView(
		@PathVariable Long boardId,
		HttpSession session,
		Model model
	) {
		try {
			Object obj = session.getAttribute("user_info");

			if (boardId == null)
				throw new Exception();

			Optional<Board> opt = boardRepository.findById(boardId);

			if (opt.isPresent()) {
				Board board = opt.get();

				BoardViewDto boardViewDto = boardRepository.findBoardViewDto(board.getId());
				model.addAttribute("board", boardViewDto);
				
				boolean bLikeClicked = false;
				if (obj != null) {
					if (obj.equals(board.getUser())) {
						model.addAttribute("owner", true);
					}

					// 추천 클릭 유무 확인
					User user = (User) obj;
					
					Optional<BoardLike> optBoardLike = boardLikeRepository.findById(new BoardLikeId(boardId, user.getId()));
					if (optBoardLike.isPresent()) {
						bLikeClicked = true;
					}
				}

				// 추천 클릭 유무 확인 후 페이지에 반영
				model.addAttribute("bLikeClicked", bLikeClicked);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());

			return "redirect:/board";
		}

		return "board/view";
	}

	// 추천 버튼 클릭 시, 페이지 업데이트 (BoardLike 테이블 업데이트)
	@GetMapping("/board/like/{boardId}")
	public String updateLike(
		@PathVariable Long boardId, 
		HttpSession session,
		Model model
	) {
		try {
			Object obj = session.getAttribute("user_info");

			if (obj == null) 
				throw new Exception();

			if (boardId == null)
				throw new Exception();

			Optional<Board> optBoard = boardRepository.findById(boardId);
			if (optBoard.isPresent()) {
				Board board = optBoard.get();
				User user = (User) obj;

				BoardLikeId boardLikeId = new BoardLikeId(boardId, user.getId());

				Optional<BoardLike> opt = boardLikeRepository.findById(boardLikeId);
				// 게시판 추천 해제
				if (opt.isPresent()) {
					BoardLike boardLike = opt.get();
					boardLikeRepository.delete(boardLike);
					model.addAttribute("bLikeClicked", false);
				}
				// 게시판 추천
				else {
					BoardLike boardLike = new BoardLike(boardLikeId, board, user);
					boardLikeRepository.save(boardLike);
					model.addAttribute("bLikeClicked", true);
				}				
			}
			else {
				throw new Exception();
			}


		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());

			return "redirect:/board";
		}

		return "redirect:/board/view/" + boardId;
	}

	@GetMapping("/board")
	public String board() {

		return "redirect:/board/list";
	}

	@GetMapping("/board/list")
	public String boardList(
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "") String search,
		Model model
	) {
		int pageCount = 10;

		Pageable pageable = PageRequest.of(page - 1, pageCount);
		Page<BoardListDto> pages = boardRepository.findBoardListWithLikeCount(search, pageable);

		// List<BoardListDto> list = pages.stream().map(Board::toBoardListDto).toList();

		int totalPages = pages.getTotalPages();
		int startPage = (page - 1) / pageCount * pageCount + 1;
		int endPage = (startPage + 9 > totalPages) ? (totalPages) : (startPage + 9);

		model.addAttribute("search", search);

		model.addAttribute("totalPages", totalPages);
		model.addAttribute("page", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("list", pages.getContent());

		return "board/list";
	}

	@GetMapping("/board/write")
	public String boardWrite(HttpSession session) {
		try {
			Object obj = session.getAttribute("user_info");

			if (obj == null) 
				throw new Exception();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());

			return "redirect:/board";
		}

		return "board/write";
	}
	
	@PostMapping("/board/write")
	public String boardWritePost(
		@ModelAttribute Board board,
		HttpSession session
	) {
		try {
			Object obj = session.getAttribute("user_info");
			
			if (obj == null)
				throw new Exception();

			if (obj instanceof User) {
				User user = (User) obj;

				board.setUser(user);

				// user의 PK인 id가 존재하지 않으면, 오류 발생
				boardRepository.save(board);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return "redirect:/board";
	}
}