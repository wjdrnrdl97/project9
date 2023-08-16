package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Board;

import jakarta.transaction.Transactional;
// Jparepository를 상속받음(객체, 객체기본키의 객체자료형) 

@Transactional
public interface BoardRepository extends JpaRepository<Board, Integer>{
	/*
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.modifying-queries
	 *
	 * @Modifying : 쿼리를 수정할 때 사용
	 * @query : sql문
	 * */
	@Modifying  
	@Query("update Board b set b.viewCount = b.viewCount + 1 where b.no = :no")
	int updateView(int no);
}
