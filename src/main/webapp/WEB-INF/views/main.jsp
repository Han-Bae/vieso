<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	@author		전다빈, 박형근(할일 모달창 작업_쓰기,조회,수정)
	@since		2022.05.23
	@version	v.1.0
	
				작업이력	]
						2022.05.23	-	담당자 : 전다빈
										내	용 : 헤더, 캘린더, 메뉴, 카테고리 체크 박스 틀 잡기
										
						2022.05.26	-	담당자 : 전다빈
										내	용 : 기온, 날씨 데이터 적용
										
						2022.05.28	-	담당자 : 전다빈
										내	용 : 카테고리 스타일 추가, jstl로 자바 ArrayList 가져옴

						2022.05.23-29	-	담당자 : 박형근
											내	용 : 할일 모달창 쓰기페이지 / 조회페이지/ 수정페이지
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>Viseo</title>
<link rel="stylesheet" type="text/css" href="/viseo/resource/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/viseo/resource/css/base.css">
<link rel="stylesheet" type="text/css" href="/viseo/resource/css/main.css">
<link rel="stylesheet" type="text/css" href="/viseo/resource/css/menu.css">
<script type="text/javascript" src="/viseo/resource/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/viseo/resource/js/bootstrap.js"></script>
<script type="text/javascript" src="/viseo/resource/js/write.js"></script>
<script src="https://kit.fontawesome.com/e0f46f82c6.js"></script>
<style>
</style>
<script type="text/javascript">
	let todayDate = "${maDATA.todayDate}";
	todayDate = todayDate.substring(0, 8);
	let lastDate = "${maDATA.lastDate}";
	const firstDay = "${maDATA.firstDay}";
</script>
</head>
<body class="main-tagbody">	
	<!-- 메뉴 -->
	<div class="menu">
		<i class="fa-solid fa-bars menu-btn"></i>
		<div class="menu-bar bg-primary">
			<ul>
				<li class="profileBtn"><i class="fa-solid fa-user-astronaut"></i>Profile</li>
				<li class="settingBtn"><i class="fa-solid fa-gear"></i>Settings</li>
				<li class="logoutBtn"><i class="fa-solid fa-arrow-right-from-bracket"></i>Log out</li>
			</ul>
		</div>
	</div>
	
	<!-- 메인 페이지 -->
	<div class="main-page">
	
		<!-- 헤더 -->
		<div class="main-header">
			<img src="/viseo/resource/icon/logo.png" width="150px">
<c:if test="${not empty maDATA}">
			<div class="mainCalHeader">
				<div class="main-year"><span class="mainYear">${maDATA.year}</span>/</div>
				<h1 style="margin: 0px; font-weight: bold; font-size: 50px;" class="mainMonth">${maDATA.month}</h1>
			</div>
</c:if>
			
			<div class="main-weather">
<c:if test="${not empty maDATA}">
				<div class="main-weather-loc">
					<i class="fa-solid fa-location-dot"></i>
					<div>
						<span class="main-areaname">${maDATA.areaname}</span>
						<span class="main-city">${maDATA.city}</span>
					</div>
				</div>
</c:if>
				
<c:if test="${not empty wDATA}">
				<div class="main-weather-tmp">
					<i class="fa-solid fa-temperature-half"></i>
					<span>${wDATA.TMP}°C</span>
				</div>
	<%-- 하늘상태(SKY)코드 :맑음(1), 구름많음(3), 흐림(4) --%>
	<%-- 강수형태(PTY)코드 :없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4) --%>
	<c:if test="${wDATA.PTY eq 0}">
		<c:if test="${wDATA.SKY eq 1}">
				<i class="fa-solid fa-sun"></i>
		</c:if>
		<c:if test="${wDATA.SKY eq 3}">
				<i class="fa-solid fa-clouds"></i>
		</c:if>
		<c:if test="${wDATA.SKY eq 4}">
				<i class="fa-solid fa-smog"></i>
		</c:if>
	</c:if>
	<c:if test="${wDATA.PTY eq 1}">
				<i class="fa-solid fa-cloud-rain"></i>
	</c:if>
	<c:if test="${wDATA.PTY eq 2}">
				<i class="fa-solid fa-cloud-sleet"></i>
	</c:if>
	<c:if test="${wDATA.PTY eq 3}">
				<i class="fa-solid fa-cloud-snow"></i>
	</c:if>
	<c:if test="${wDATA.PTY eq 4}">
				<i class="fa-solid fa-cloud-showers"></i>
	</c:if>
</c:if>
			</div>
		</div>
		
		<div class="main-body">
			<!-- 캘린더 -->
			<table id="calender" class="table">
				<thead class="main-days">
					<tr>
						<th style="color:#ff0039;">Sun</th>
						<th>Mon</th>
						<th>Tue</th>
						<th>Wed</th>
						<th>Thu</th>
						<th>Fri</th>
						<th>Sat</th>
					</tr>
				</thead>
				<tbody class="mainDates">
					<!-- 1주 -->
					<tr class="date-row">
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
					</tr>
					<!-- 2주 -->
					<tr class="date-row">
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
					</tr>
					<!-- 3주 -->
					<tr class="date-row">
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
					</tr>
					<!-- 4주 -->
					<tr class="date-row">
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
					</tr>
					<!-- 5주 -->
					<tr class="date-row">
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
						<td class="border-primary mainDate"></td>
					</tr>
				</tbody>
			</table>
			
			<div class="category">
				<label><input id="checkCompany" type="checkbox">회사</label>
				<label><input id="checkFamily" type="checkbox">가족</label>
				<label><input id="checkFriend" type="checkbox">친구</label>
				<label><input id="checkOther" type="checkbox">지인</label>
			</div>
		</div>
		
	</div>
	
	<div class="card border-primary self-modal" style="max-width: 20rem;">
	  <div class="card-header self-modal-header">
		<button type="button" class="btn-close self-modal-close-btn"></button>
	  </div>
	  <div class="card-body self-modal-body">
	    <p class="card-text">달력 페이지 변경</p>
		<input type="month" id="dateInput">
		<button type="button" class="btn btn-primary btn-sm changePageBtn">변경</button>
	  </div>
	</div>
	
	<form action="" name="mainForm" class="hidden">
		<input type="hidden">
		<input type="hidden">
	</form>
	
<%------------------------------------------------------------------------------------------%>
<%-- 모달  --%>
   <div class="modal" id="fid">        
     <div class="modal-dialog" role="document">
       <div class="modal-content">
         <button type="button" data-dismiss="modal" class="logoBtn"></button>
         <div class="modal-header">
           <div class="modal-title"></div>
           <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" id="closeBtn">
             <span aria-hidden="true"></span>
           </button>
         </div>
<form method="POST" class="" id="frm" name="frm">
	         <div class="modal-body">
	<div>
		
		<div>
			<div>
				<div class="form-group">
			      		<label for="exampleSelect1" class="form-label mt-4">선택한 날짜</label>
			     		<input class="form-control form-control-lg"  type="text" id="chcekDate" name="chcekDate" value="" >
			    </div>
				<div class="form-group">
			      		<label for="exampleSelect1" class="form-label mt-4">카테고리 선택</label>
			     		<select class="form-select" id="category" name="category">
				       		<option>회사</option>
				        	<option>가족</option>
				      		<option>친구</option>
				   		    <option>지인</option>
			      		</select>
			    </div>
				<div class="form-group">
		  			<input class="form-control form-control-lg" type="text" placeholder="제목을 입력하세요" id="title" name="title">
				</div>
				<div class="form-group">
				<label for="exampleSelect1" class="">지역</label>
			      		<input class="form-control form-control-lg" type="text" id="area" name="area" value="서울시 종로구" >
			      		<%-- <input type="date" id="area" name="area" value="${maDATA.areaname} ${maDATA.city}" > --%>
			    </div>
			</div>
			
			<div>
			<label for="exampleSelect1" class="">시간을 입력하세요</label>
			</div>
			<input type="time" id="chcekTime" name="chcekTime" value="" disabled >
			<div class="form-check">
		        	<input class="form-check-input" type="checkbox" id="c123" name=""  checked>
		       		<label class="form-check-label" for="flexCheckDefault">종일</label>
		    </div>
			<div class="">
		      		<!-- <label for="exampleSelect1" class="form-label mt-4"></label> -->
		     		<select class="form-select" id="alarmRepeat" name="alarmRepeat" hidden>
						<option>알림없음</option>
						<option>30분전알림</option>
	     			  	<option>한시간전알림</option>
	     			  	<option>두시간전알림</option>
	     			  	<option>세시간전알림</option>
		      		</select>
		    	</div>
			
		
			<div class="">
	      		<!-- <label for="exampleTextarea" class="form-label mt-4"></label> -->
	     		<textarea class="form-control" placeholder="메모하세요" id="memo" name="memo" rows="3" style="height: 150px;"></textarea>
	    	</div>
		</div>
		<div>
		</div>
		<div>
			<div>
				<button type="button" class="btn btn-primary" id="savebtn" name="savebtn">저장</button>
			</div>
		</div>
		</div>
	</div>
</form>
</div>
</div>
</div>
	
<script type="text/javascript" src="/viseo/resource/js/main.js"></script>
<script type="text/javascript" src="/viseo/resource/js/menu.js"></script>
</body>
</html>