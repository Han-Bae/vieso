/**
 * 
 *	@author 	전다빈
 *	@since		2022.05.24
 *	@version 	v.1.0
 
 				작업이력	]
 					2022.05.24	-	담당자 : 전다빈
 									내	용 : 이벤트 추가
*/
$(document).ready(function(){
	// 1:일, 2:월, 3:화, 4:수, 5:목, 6:금, 7:토
	const setCalender = function(){
		const dateNodeList = document.querySelectorAll(".mainDate");
		20220501
		let date = Number(lastDate.substring(0, 6) + "01");
		
		for(let i = 0 ; i < dateNodeList.length ; i++ ) {
			const list = dateNodeList[i]
			
			// Number(firstDay) - 1 보다 인덱스가 작으면
			if(i < Number(firstDay) - 1){
				list.innerHTML = "<span class='mainDateNumber'></span>";
				date++;
				continue;
				
				
			// 이번 달의 마지막 날이 35번째 칸이 아닐 때
			} else if(date > Number(lastDate)){
				list.innerHTML = "<span class='mainDateNumber'></span>";
				date++;
				continue;
			}	
			
			
			list.id = date;
			list.innerHTML = "<span class='mainDateNumber'>" + String(date).substring(6, 8) + "</span>";
			date++;
			
		}
		
		$("td:first-child span:first-child").css("color", "#ff0039");
	};
	
	setCalender();
	
	// 년도/월을 누르면 모달 띄우기
	$(".mainCalHeader").click(function(){
			$(".self-modal").css("z-index", "1");
	});
	
	// 모달 X 버튼 click 이벤트
	$(".self-modal-close-btn").click(function(){
			$(".self-modal").css("z-index", "-1");
	});
	
	// 년월 모달 창 닫으면 main 페이지 비동기?
	$(".changePageBtn").click(function(){
		const value = $("#dateInput").val();
		if(!value){
			$(".self-modal").css("z-index", "-1");
			return;
		}
		const year = value.substring(0, 4);
		const month = value.substring(5, 7);
		$(".mainYear").text(year);
		$(".mainMonth").text(month);
		$(".self-modal").css("z-index", "-1");
		
		// 비동기로 달력 날짜 변경
	});
	
	// check box change 이벤트 추가
	// 비동기로 데이터 받아올 것
	
	
});