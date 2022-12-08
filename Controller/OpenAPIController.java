package com.kh.opendata.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OpenAPIController {
	
	private static final String SERVICE_KEY = "7Z7DK4ezXteIy3d6EgwNObZl88p5KvuvVC12MYwWDUenp59wX%2FCFVf0JSTiOadkS50Kxp%2BXdU3tNVvPDLMWTUg%3D%3D";
	
	
//	@ResponseBody
//	@RequestMapping(value="air.do", produces="application/json; charset=UTF-8")
//	public String viewAirStatement(String location) throws IOException {
//		
//		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
//		url += "?servicekey=" + SERVICE_KEY; //서비스키 추가
//		url += "&sidoName=" + URLEncoder.encode(location,"UTF-8"); //지역명 추가(한글이 들어가면 인코딩처리필수)
//		url += "&returnType=json"; //리턴 타입
//		url += "&numOfRows=50"; //결과 개수
//		
//		//작성된 url정보를 넣어 URL 객체 생성
//		URL requestUrl = new URL(url); 
//
//		//생성된 URL 객체로 urlConnection 생성
//		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection(); 
//		urlConnection.setRequestMethod("GET");
//		
//		//전달받은 응답데이터를 읽어줄 스트림 연결(기반스트림을 얻어와 보조스트림을 생성한다)
//		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//		
//		String responseText = "";
//		String line;
//		
//		//br로 읽어온 데이터를 line 변수에 넣고 더이상 읽을 데이터가 없을때까지 반복
//		while((line = br.readLine()) != null) {
//			responseText += line;
//		}
//				
//		//자원반납 및 연결해제
//		br.close();
//		urlConnection.disconnect();
//		
//		return responseText;
//	}
	
	//xml형식으로 openAPI 활용하기
	@ResponseBody
	@RequestMapping(value="air.do", produces="text/xml; charset=UTF-8")
	public String viewAirMethod(String location) throws IOException {
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?servicekey=" + SERVICE_KEY; //서비스키 추가
		url += "&sidoName=" + URLEncoder.encode(location,"UTF-8"); //지역명 추가(한글이 들어가면 인코딩처리필수)
		url += "&returnType=xml"; //리턴 타입
		url += "&numOfRows=50"; //결과 개수
		
//		작성된 url정보를 넣어 URL 객체 생성
		URL requestUrl = new URL(url); 

		//생성된 URL 객체로 urlConnection 생성
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection(); 
		urlConnection.setRequestMethod("GET");
		
		//전달받은 응답데이터를 읽어줄 스트림 연결(기반스트림을 얻어와 보조스트림을 생성한다)
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		
		//br로 읽어온 데이터를 line 변수에 넣고 더이상 읽을 데이터가 없을때까지 반복
		while((line = br.readLine()) != null) {
			responseText += line;
		}
				
		//자원반납 및 연결해제
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	}
	
	
}



















