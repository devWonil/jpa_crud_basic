package com.tencoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.blog.dto.BoardSaveRequestDto;
import com.tencoding.blog.model.Board;
import com.tencoding.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/", "list"})
	public String list(@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable, Model model) {
		Page<Board> boards = boardService.글목록보기(pageable);
		System.out.println(boards.toString());
		model.addAttribute("boards", boards);
		return "list";
	}
	
	@GetMapping({"/listPage"})
	@ResponseBody
	public Page<Board> listPage(@PageableDefault(size = 1, sort = "id", direction = Direction.DESC) Pageable pageable, Model model){
		Page<Board> boards = boardService.글목록보기(pageable);
		System.out.println(boards.toString());
		model.addAttribute("boards", boards);
		return boards;
	}
	
	@GetMapping("/saveForm")
	public String saveForm() {
		return "saveForm";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public String save(@RequestBody BoardSaveRequestDto dto) {
		boardService.글쓰기(dto);
		return "ok";
	}
	
	@GetMapping("/updateForm/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		Board board = boardService.글상세보기(id);
		
		return "updateForm";
	}
}
