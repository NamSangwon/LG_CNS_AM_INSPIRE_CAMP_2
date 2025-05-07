package com.example.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import com.example.board.entity.BoardComment;
import com.example.board.entity.BoardLike;
import com.example.board.entity.BoardLikeId;
import com.example.board.entity.User;
import com.example.board.repository.BoardCommentRepository;
import com.example.board.repository.BoardLikeRepository;
import com.example.board.repository.BoardRepository;

import ch.qos.logback.core.encoder.EchoEncoder;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class BoardController {
	@Autowired
	BoardRepository boardRepository;

	@Autowired
	BoardLikeRepository boardLikeRepository;

	@Autowired
	BoardCommentRepository boardCommentRepository;
	
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
				User user = (User) obj;
				Board board = opt.get();

				if (user.getId() == board.getUser().getId()) {
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
				User user = (User) obj;
				Board board = opt.get();

				if (user.getId() == board.getUser().getId()) {
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

			if (board.getTitle().isEmpty())
				throw new Exception();

			if (board.getContent().isEmpty())
				throw new Exception();

			// Board 데이터를 찾은 후 바꿀 값만 바꾸는 것이 더 안전한 방안
			Optional<Board> opt = boardRepository.findById(boardId);
			if (opt.isPresent()) {
				User user = (User) obj;
				Board optBoard = opt.get();

				if (user.getId() == optBoard.getUser().getId()) {
					optBoard.setTitle(board.getTitle());
					optBoard.setContent(board.getContent());

					boardRepository.save(optBoard);
				}
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

				// JPA 사용 대신 [Entity 간의 매핑]과 [Query Method] 만으로 해결 가능
				BoardViewDto boardViewDto = board.toBoardViewDto();
				model.addAttribute("board", boardViewDto);
				
				boolean bLikeClicked = false;
				if (obj != null) {

					// 추천 클릭 유무 확인
					User user = (User) obj;

					if (user.getId() == board.getUser().getId()) {
						model.addAttribute("bIsOwner", true);
					}
					
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

	@GetMapping("/board/comment/{boardId}")
	public String comment(
		@PathVariable Long boardId,
		@RequestParam(required = false) Long parentCommentId,
		@RequestParam String comment,
		HttpSession session
	) {
		try {
			Object obj = session.getAttribute("user_info");
	
			if (obj == null)
				throw new Exception();

			if (boardId == null)
				throw new Exception();

			Optional<Board> opt = boardRepository.findById(boardId);
			if (opt.isPresent())
			{
				Board board = opt.get();
				
				if (obj instanceof User) {
					User user = (User) obj;
	
					BoardComment newComment = new BoardComment();
					newComment.setBoard(board);
					newComment.setUser(user);
					newComment.setContent(comment);

					if (parentCommentId != null) {
						Optional<BoardComment> optParentComment = boardCommentRepository.findById(parentCommentId);
						if (optParentComment.isPresent()) {
							newComment.setParentComment(optParentComment.get());
						}
					}
			
					boardCommentRepository.save(newComment);
				}
				else {
					throw new Exception();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());

			return "redirect:/board/list";
		}

		return "redirect:/board/view/" + boardId;
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

		Sort sort = Sort.by(Direction.DESC, "id");
		Pageable pageable = PageRequest.of(page - 1, pageCount, sort);

		// JPA 사용 대신 [Entity 간의 매핑]과 [Query Method] 만으로 해결 가능
		Page<Board> pages = boardRepository.findByTitleContainingOrContentContaining(search, search, pageable);

		List<BoardListDto> list = pages.stream().map(Board::toBoardListDto).toList();

		int totalPages = pages.getTotalPages();
		int startPage = (page - 1) / pageCount * pageCount + 1;
		int endPage = (startPage + 9 > totalPages) ? (totalPages) : (startPage + 9);

		model.addAttribute("search", search);

		model.addAttribute("totalPages", totalPages);
		model.addAttribute("page", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("list", list);

		return "board/list";
	}

	@GetMapping("/board/write")
	public String boardWrite() {
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