<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>실시간 대기오염 정보</h1>
	
	지역:
	<select id="location">
		<option>서울</option>
		<option>부산</option>
		<option>대전</option>
	</select>
	
	<button id="btn1">해당 지역 대기 오염 정보</button>
	
	<table border="1" id="result1" align="center">
		<thead>
			<tr>
				<th>측정소명</th>
				<th>측정일시</th>
				<th>통합대기환경수치</th>
				<th>메시먼지농도</th>
				<th>아황산가스농도</th>
				<th>일산화탄소농도</th>
				<th>이산화질소농도</th>
				<th>오존농도</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	
	<script>
		$(function(){
			$("#btn1").click(function(){
		/* 응답데이터 json 형식
				$.ajax({
					url: "air.do",
					data: {location:$("#location").val()},
					success: function(result){
						console.log(result)
						var itemArr = result.response.body.items;
						var value = "";
						for(var i in itemArr){
							value += "<tr>"
								   + "<td>" + itemArr[i].stationName + "</td>"
								   + "<td>" + itemArr[i].dataTime + "</td>"
								   + "<td>" + itemArr[i].khaiValue + "</td>"
								   + "<td>" + itemArr[i].pm10Value + "</td>"
								   + "<td>" + itemArr[i].so2Value + "</td>"
								   + "<td>" + itemArr[i].coValue + "</td>"
								   + "<td>" + itemArr[i].no2Value + "</td>"
								   + "<td>" + itemArr[i].o3Value + "</td>"
								   + "</tr>";
						}
						$("#result1 > tbody").html(value);
					},
					error: function(){
						console.log("통신실패")
					}
				})
		*/
		
// 		응답데이터를 xml로 받았을 경우
				$.ajax({
					url: "air.do",
					data: {location:$("#location").val()},
					success: function(result){
					//jQuery에서 사용했던 find() 활용
					//같은  markup language이기 때문에 사용가능
					//넘겨받은 xml 응답 데이터를 $() 제이쿼리화 시킨 후
					//find 메서드 활용
// 						console.log($(result).find("item"));
					//응답 데이터가 xml 형태라면
					//1. 응답 데이터를 $() 제이쿼리화 시킨 후
					//2. 찾고자 하는 요소를 find로 찾아준다
					//3. 찾은 요소가 여러개라면 해당 요소들을 반복적으로 접근하여 원하는 값을 추출하기
					//4. 추출한 요소로 동적 DOM만들어 주기
					var itemArr = $(result).find("item");
					var value = "";
					itemArr.each(function(index, item){
						console.log(index);
						console.log($(item).find("coGrade"));
						value += "<tr>"
							   + "<td>" + $(item).find("stationName").text() + "</td>"
							   + "<td>" + $(item).find("dataTime").text() + "</td>"
							   + "<td>" + $(item).find("khaiValue").text() + "</td>"
							   + "<td>" + $(item).find("pm10Value").text() + "</td>"
							   + "<td>" + $(item).find("so2Value").text() + "</td>"
							   + "<td>" + $(item).find("coValue").text() + "</td>"
							   + "<td>" + $(item).find("no2Value").text() + "</td>"
							   + "<td>" + $(item).find("o3Value").text() + "</td>"
							   + "</tr>";
					})
					$("#result1 > tbody").html(value);
					},
					error: function(){
						console.log("통신실패");
					}
				})
		
			})
		})
	</script>
	
</body>
</html>

















