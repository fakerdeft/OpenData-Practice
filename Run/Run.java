package com.kh.opendata.Run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.Model.vo.AirVo;


public class Run {	
	//발급받은 인증키 변수처리 (변할일 없기 때문에 상수처리)
	public static final String SERVICE_KEY = "7Z7DK4ezXteIy3d6EgwNObZl88p5KvuvVC12MYwWDUenp59wX%2FCFVf0JSTiOadkS50Kxp%2BXdU3tNVvPDLMWTUg%3D%3D";
	
	public static void main(String[] args) throws IOException {
		
		//OPEN API에 요청할 URL을 작성
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?servicekey=" + SERVICE_KEY;
		url += "&sidoName=" + URLEncoder.encode("서울","UTF-8"); //요청시 전달값에 한글이 있다면 encoding처리 해야한다.
		url += "&returnType=json";
		
		//HttpURLConnection 객체를 이용하여 api 요청작업을 한다.
		//1. 요청하고자 하는 url을 전달하면서 java.net.URL 객체를 생성한다.
		URL requestUrl = new URL(url);
		
		//2. 생성된 URL 객체로 HttpURLConnection 객체를 생성한다.
		HttpURLConnection urlConn = (HttpURLConnection)requestUrl.openConnection();
		
		//3. 요청에 필요한 Header 설정
		urlConn.setRequestMethod("GET");
		
		//4. 해당 OpenAPI 서버로 요청 후 스트림을 통해서 응답데이터 읽어오기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		//보조스트림은 단독 존재가 불가능
		//보조스트림인 BufferedReader는 문자 스트림(2Byte)
		//기반스트림인 urlConn.getInputStream()은 (1Byte)
		//보조스트림과 기반스트림의 스트림 통로를 일치시켜야 하기 때문에 보조스트림 (2Byte)에 맞추기 위해
		//urlConn.getInputStream()의 1byte를 InputStreamReader()로 2byte짜리 통로로 변경시켜준것.
		
		//5. 반복적으로 응답데이터 읽어오기
		String responseText = "";
		String line;
		while((line = br.readLine()) != null) { //한줄씩 읽어올 데이터가 있는 동안 반복시켜라
			responseText += line;
		}
		//System.out.println(responseText);
		//json 데이터를 원하는 데이터만 추출하여 Vo에 담기
		//응답 데이터 Text를 JSONObject화 시키는 작업 (파싱)
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();		
		System.out.println("total: " + totalObj);
		//전체 JSON 형식으로 부터 response 속성명으로 접근한 것
		JsonObject responseObj = totalObj.getAsJsonObject("response");
		System.out.println("response: " + responseObj);
		//전체 response 형식으로 부터 body 속성명으로 접근한 것
		JsonObject bodyObj = responseObj.getAsJsonObject("body");
		System.out.println("body: " + bodyObj);
		
		//body에서 totalCount 접근
		int totalCount = bodyObj.get("totalCount").getAsInt();
		System.out.println("totalCount: " + totalCount);
		//body에서 items(JSONArray형태) 접근
		JsonArray itemArr = bodyObj.getAsJsonArray("items");
		System.out.println("itemArr: " + itemArr);
		
		//items에 있는 각 item을 VO에 담고 담은 VO들을 ArrayList에 담기
		ArrayList<AirVo> list = new ArrayList<>();
		System.out.println("=================================");
		for(JsonElement itemElement : itemArr) {
			//itemArr에 담겨있는 item객체 하나씩 추출
			JsonObject item = itemElement.getAsJsonObject();
//			System.out.println(item);
			
			AirVo air = new AirVo();
			
			air.setStationName(item.get("stationName").getAsString());
			air.setDataTime(item.get("dataTime").getAsString());
			air.setKhaiValue(item.get("khaiValue").getAsString());
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setSo2Value(item.get("so2Grade").getAsString());
			air.setCoValue(item.get("coValue").getAsString());
			air.setNo2Value(item.get("no2Value").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			
			list.add(air);
		}
		System.out.println("다담은 list: " + list);
		
		//list에 담긴 VO객체 확인
		for(AirVo a : list) {
			System.out.println(a);
		}
		//작업이 끝났으면 자원반납 및 연결 해제
		br.close();
		urlConn.disconnect();
		
	}
}


















