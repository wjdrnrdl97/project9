	package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;

public interface BoardService {
	
	// 게시물을 등록
	int register(BoardDTO dto);
	
	// dto를 엔티티로 변환하는 메소드
	/*
	 * 자바8부터 default를 통해 인터페이스에서 일반메소드를 가질 수 있다.
	 * db는 데이터를 엔티티 클래스로 저장하기 때문에 컨트롤러에서 클라이언트가 등록한 데이터를 dto에서 엔티티클래스로 변환하여 저장한다.
	 * */
	default Board dtoToEntity(BoardDTO dto) {
		Board entity = Board.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.viewCount(dto.getViewCount())
				.build();
		return entity;
	}
	
	// 방명록 목록조회
	/*
	 * 방명록이 여러개을 조회하기에 자료구조인 리스트를 통해 가져온다
	 * */
	List<BoardDTO> getList();
	
	// 엔티티를 dto로 변환하는 메소드
	/*
	 * 컨트롤러는 데이터를 dto로 전송받기 때문에 db에 저장된 엔티티클래스를 dto클래스로 변환 후 가져온다. 
	 * */
	default BoardDTO entityToDto(Board entity) {
		BoardDTO dto = BoardDTO.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.viewCount(entity.getViewCount())
				.build();
		
		return dto;
	}
	
	// 게시물 상세조회
	/*
	 * 컨트롤에서 글번호를 전달받아 게시물 한건을 반환한다.
	 * */
	BoardDTO read(Integer no);
	
//	void getView(BoardDTO boardDTO);
	
	// 게시물 수정메소드
	void modify(BoardDTO boardDTO);
	
	// 게시물 삭제
	int remove(int no);	
}
