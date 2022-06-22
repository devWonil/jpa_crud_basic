package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.dto.BoardSaveRequestDto;
import com.tencoding.blog.model.Board;
import com.tencoding.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(BoardSaveRequestDto dto) {
		Board boardEntity = BoardSaveRequestDto.toEntity(dto); 
		boardRepository.save(boardEntity);
	}
	
	@Transactional
	public Page<Board> 글목록보기(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional
	public Board 글상세보기(int id) {
		
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new RuntimeException("해당글은 삭제되었습니다");
		});
		
		// 더티체킹 = 조회수 증가
		board.setReadCount(board.getReadCount() + 1);
		
		return board;
	}
}
