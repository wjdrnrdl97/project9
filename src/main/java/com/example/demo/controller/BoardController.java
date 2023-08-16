package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

//@Tag : Swagger의 제목과 설명 설정할 때
@Tag(name = "게시물관리API", description = "Swagger 테스트용 API")
@Slf4j // 로그의 기록 저장
@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
//	@Operation : 기능별 설명을 설정할 때
	@Operation(summary="게시물 등록", description = "파라미터로 받은 게시물 정보를 등록합니다.")
	@PostMapping
	public ResponseEntity<Integer> register(@RequestBody BoardDTO boardDTO) {
		log.info("게시물을 등록합니다.");
		int no = boardService.register(boardDTO);
		return new ResponseEntity<>(no, HttpStatus.CREATED); // 201코드 및 새로운 글번호 반환

	}
	@Operation(summary="게시물 목록조회", description = "모든 게시물의 정보를 입력합니다.")
	@GetMapping
	public ResponseEntity<List<BoardDTO>> getList() {
		log.info("게시물 목록을 조회합니다.");
		List<BoardDTO> list = boardService.getList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@Operation(summary="게시물 상세조회", description = "파라미터로 받은 글번호로 게시물을 조회합니다.")
	@GetMapping("/{no}")
	public ResponseEntity<BoardDTO> read(@PathVariable("no") int no) {
		log.info("게시물을 상세 조회합니다.");
		BoardDTO boardDTO = boardService.read(no);
		return new ResponseEntity<>(boardDTO, HttpStatus.OK);
	}

	@Operation(summary="게시물 수정", description = "파라미터로 받은 게시물 정보로 게시물을 수정합니다.")
	@PutMapping
	public ResponseEntity modify(@RequestBody BoardDTO boardDTO) {
		log.info("게시물을 수정합니다.");
		boardService.modify(boardDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204코드
	}
	@Operation(summary="게시물 삭제", description = "파라미터로 받은 게시물 번호의 게시물을 삭제합니다.")
	@DeleteMapping("/{no}")
	public ResponseEntity remove(@PathVariable("no") int no) {
		log.info("게시물을 삭제합니다.");
		boardService.remove(no);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
