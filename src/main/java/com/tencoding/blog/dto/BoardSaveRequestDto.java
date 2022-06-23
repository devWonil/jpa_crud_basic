package com.tencoding.blog.dto;

import com.tencoding.blog.model.Board;

import lombok.Data;

@Data
public class BoardSaveRequestDto {

	private String title;
	private String content;
	
	// 한단계 추가
	public static Board toEntity(BoardSaveRequestDto dto) {
		
		Board boardEntity = Board.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
		return boardEntity;
	}
}
