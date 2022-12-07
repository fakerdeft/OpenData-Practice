package com.kh.opendata.Model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirVo {
	private String stationName; //측정소명
	private String dataTime; //측정일시
	private String khaiValue; //통합대기환경수치
	private String pm10Value; //미세먼지농도
	private String so2Value; //아황산가스
	private String coValue; //일산화탄소
	private String no2Value; //이산화질소
	private String o3Value; //오존농도
	
}
