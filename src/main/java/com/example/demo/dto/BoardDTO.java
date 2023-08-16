package com.example.demo.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
//	@Schema : Swagger에서 데이터 모델을 정의할 때 사용
	@Schema(description = "글번호")
	private int no;
	@Schema(description = "제목")
	private String title;
	@Schema(description = "내용")
	private String content;
	@Schema(description = "작성자")
	private String writer;
	@Schema(description = "생성일자")
	private LocalDateTime regDate;
	@Schema(description = "수정일자")
	private LocalDateTime modDate;
	private int viewCount;

}
