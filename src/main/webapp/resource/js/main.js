/**
 * 
 *	@author 	전다빈
 *	@since		2022.05.24
 *	@version 	v.1.0
 
 				작업이력	]
 					2022.05.24	-	담당자 : 전다빈
 									내	용 : 이벤트 추가
 									
 					2022.05.27	-	담당자 : 전다빈
 									내	용 : 날짜 세팅
 									
 					2022.05.28	-	담당자 : 전다빈
 									내	용 : 변경 날짜 세팅, 체크박스 활성화
*/
$(document).ready(function(){
	
	// 페이지 로딩 후 실행되는 함수!
	// 1:일, 2:월, 3:화, 4:수, 5:목, 6:금, 7:토
	const setCalender = function(){
		
		// 달력 일 세팅
		const dateNodeList = document.querySelectorAll(".mainDate");
		let date = Number(lastDate.substring(0, 6) + "01");
		
		for(let i = 0 ; i < dateNodeList.length ; i++ ) {
			const list = dateNodeList[i]
			
			// Number(firstDay) - 1 보다 인덱스가 작으면
			if(i < Number(firstDay) - 1){
				list.innerHTML = "<span class='mainDateNumber'></span>";
				continue;
				
			// 이번 달의 마지막 날이 35번째 칸이 아닐 때
			} else if(date > Number(lastDate)){
				list.innerHTML = "<span class='mainDateNumber'></span>";
				continue;
			}	
			
			list.id = date;
			list.innerHTML = "<span class='mainDateNumber'>" + String(date).substring(6, 8) + "</span>";
			date++;
			
		}
		
		$("td:first-child span:first-child").css("color", "#ff0039");
		$("#" + todayDate).css("background-color", "#d4e6f9");
		
		
		// 달력 스케줄 세팅
		$.ajax({
			url: "/viseo/setTodo.blp",
			type: "post",
			dataType: "json",
			data: {
				today: todayDate,
				lastday: lastDate
			},
			success: function(data){
				for(let i = 0 ; i < data.length ; i++){
					let cate = "";
					switch(data[i].category){
					case "회사":
						cate = "company";
						break;
					case "가족":
						cate = "family";
						break;
					case "친구":
						cate = "friend";
						break;
					case "지인":
						cate = "other";
						break;
					}
					$("#" + data[i].dateId).append("<div class='" + cate + "'></div>");
				}
			},
			error: function(){
				alert("### 스케줄 불러오기 통신에러 ###");
			}
		});
		
		// 체크박스 체크 세팅
		const checkBoxs = $(".category input[type='checkbox']");
		for(const checkBox of checkBoxs){
			checkBox.checked = true;
			$(checkBox).prop("checked", "true");
		}
		
	};
	
	// 함수 호출!!
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
		let year = value.substring(0, 4);
		let month = value.substring(5, 7);
		$(".mainYear").text(year);
		$(".mainMonth").text(month);
		$(".self-modal").css("z-index", "-1");
		
		
		// 비동기로 달력 날짜 변경
		$.ajax({
			url: "/viseo/changeCalender.blp",
			type: "post",
			dataType: "json",
			data: {
				changeValue: $("#dateInput").val()
			},
			async: false,
			success: function(data){
				
				// 달력 채우기
				const dateNodeList = document.querySelectorAll(".mainDate");
				Array.from(dateNodeList).forEach(function(item){
					item.removeAttribute("id");
				});
				
				let date = Number(data.lastdate.substring(0, 6) + "01");
				
				for(let i = 0 ; i < dateNodeList.length ; i++ ) {
					const list = dateNodeList[i]
					
					// Number(firstDay) - 1 보다 인덱스가 작으면
					if(i < Number(data.firstday) - 1){
						list.innerHTML = "<span class='mainDateNumber'></span>";
						continue;
						
					// 이번 달의 마지막 날이 35번째 칸이 아닐 때
					} else if(date > Number(data.lastdate)){
						list.innerHTML = "<span class='mainDateNumber'></span>";
						continue;
					}	
					
					list.id = date;
					list.innerHTML = "<span class='mainDateNumber'>" + String(date).substring(6, 8) + "</span>";
					date++;
					
				}
				
				$("td:first-child span:first-child").css("color", "#ff0039");
			},
			error: function(){
				alert("### 캘린더 갱신 통신 에러 ###");
			}
		});
		
		// 스케줄 가져와서 채우기
		// 첫날 구하기
		let spanId = "";
		for(let i = 0 ; i <= 7 ; i++){
			const spanText = $("tr:first-child td:nth-child(" + i + ") span").text();
			if(!spanText){
				continue;
			}
			spanId = $("tr:first-child td:nth-child(" + i + ")").attr("id");
			break;
		}
		year = Number(spanId.substring(0, 4));
		month = Number(spanId.substring(4, 6));
		
		let lastChangeDate = new Date(year, month, 0).getDate();
		lastChangeDate = String(year) + 
			(month < 10 ? "0" + String(month) : String(month))
			 + String(lastChangeDate);
		
		// 달력 스케줄 세팅
		$.ajax({
			url: "/viseo/setTodo.blp",
			type: "post",
			dataType: "json",
			data: {
				today: spanId,
				lastday: lastChangeDate
			},
			async: false,
			success: function(data){
				for(let i = 0 ; i < data.length ; i++){
					let cate = "";
					switch(data[i].category){
					case "회사":
						cate = "company";
						break;
					case "가족":
						cate = "family";
						break;
					case "친구":
						cate = "friend";
						break;
					case "지인":
						cate = "other";
						break;
					}
					$("#" + data[i].dateId).append("<div class='" + cate + "'></div>");
				}
			},
			error: function(){
				alert("### 달력 변경 스케줄 갱신 통신에러 ###");
			}
		});
		
		const checkBoxs = $(".category input[type='checkbox']");
		for(const checkBox of checkBoxs){
			// 스케줄 불러왔을 때 체크박스가 체크되어 있지 않으면
			const labelId = $(checkBox).parent("label").attr("id");
			if(!$(checkBox).prop("checked")){
				switch(labelId){
				case "checkCompany":
					$(".company").css("display", "none");
					break;
				case "checkFamily":
					$(".family").css("display", "none");
					break;
				case "checkFriend":
					$(".friend").css("display", "none");
					break;
				case "checkOther":
					$(".other").css("display", "none");
					break;
				}
				
			}
		}
		
	});
	
	
	// check box change 이벤트 추가
	$(".category input[type='checkbox']").click(function(){
		const labelId = $(this).parent("label").attr("id");
		
		// 체크 되어있으면
		if($(this).prop("checked")){
			console.log("체크?");
			
			switch(labelId){
			case "checkCompany":
				$(".company").css("display", "block");
				break;
			case "checkFamily":
				$(".family").css("display", "block");
				break;
			case "checkFriend":
				$(".friend").css("display", "block");
				break;
			case "checkOther":
				$(".other").css("display", "block");
				break;
			}
			
		} else {
			console.log("안체크?");
						
			switch(labelId){
			case "checkCompany":
				$(".company").css("display", "none");
				break;
			case "checkFamily":
				$(".family").css("display", "none");
				break;
			case "checkFriend":
				$(".friend").css("display", "none");
				break;
			case "checkOther":
				$(".other").css("display", "none");
				break;
			}
			
		}
		
	});
	
});