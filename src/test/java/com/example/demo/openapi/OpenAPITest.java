package com.example.demo.openapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenAPITest {

	String serviceKey = "Srqf3rIaIVqjVDlxfdaBqh%2BEeN0MdhrZupxh5B3A4YVikhjT%2Bx6M%2BbDPeWGmxHN6RPzRY2JD1wqNB50XHpK%2FDg%3D%3D";
	String dataType = "JSON";
	String code = "11B20201";

	@Test
	public String getWeather() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="+ URLEncoder.encode(dataType, "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
		urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "=" + URLEncoder.encode(code,"UTF-8")); /* 11A00101(백령도), 11B10101 (서울), 11B20201(인천) 등... 별첨 엑셀자료 참조(‘육상’ 구분 값 참고) */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		return sb.toString();
	}
	@Test
	public void jsonToDto() throws IOException{
		ObjectMapper mapper = new ObjectMapper();  // 매퍼 클래스 생성(Java 객체와 JSON 데이터 간의 변환을 담당하는 중요한 클래스)
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		/*
		 * FAIL_ON_UNKNOWN_PROPERTIES (기본값: true):
		   알려지지 않은 JSON 속성들이 객체의 필드에 매핑될 때 예외를 발생시키거나 무시할지를 제어합니다. 설정을 false로 변경하면 알려지지 않은 속성들이 무시되어 예외가 발생하지 않습니다.
		 * */
		
		String weather = getWeather();  // 날씨 데이터 가져오기
		ResponseResult response = null;
		response = mapper.readValue(weather, ResponseResult.class);
		//T object = objectMapper.readValue(jsonString, valueType); => json String을 class 객체로 변환하는 메소드
		System.out.println(weather);
		System.out.println(response.toString());
		System.out.println(response.response.header.resultCode);
		System.out.println(response.response.header.resultMsg);
	}
}
