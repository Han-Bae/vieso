<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<%--
 	할일 입력창 모달창 jsp파일
 			@author 박형근
  			@since 2022.05.26
  			@version v.1.0
  				제작자 박형근   --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/viseo/resource/css/base.css">
<link rel="stylesheet" type="text/css" href="/viseo/resource/css/bootstrap.css">
<script type="text/javascript" src="/viseo/resource/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/viseo/resource/js/write.js"></script>
<script type="text/javascript" src="/viseo/resource/js/bootstrap.js"></script>
<style type="text/css">
	 .logoBtn{
         float: left; width:60px; height:25px;
         background-image: url(../resource/icon/logo.png); 
         background-size: cover;
         border: 0px;
         margin-left: 5px;
         margin-top: 5px;
    }
	
	label {
		font-size: 16pt;
	}
	
	#msg {
		font-size: 20pt;
		font-weight: bold;
		color: indigo;
	}
</style>
</head>
<body>
<div>
<%-- 아이디찾기 버튼 --%>
<button type="button" class="subbtn sub" data-target="#fid" data-toggle="modal">메인달력에서 할일입력하기</button>

<%-- 아이디찾기 모달  --%>
   <div class="modal" id="fid">        
     <div class="modal-dialog" role="document">
       <div class="modal-content">
         <button type="button" data-dismiss="modal" class="logoBtn"></button>
         <div class="modal-header">
           <div class="modal-title"></div>
           <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
             <span aria-hidden="true"></span>
           </button>
         </div>
<form method="POST" class="" id="frm" name="frm">
	         <div class="modal-body">
<input type="hidden" id="id" name="id" value="sin">
	<div>
		<!-- <div>
			<div id="hbtn"><img src="../resource/icon/logo.png" alt=""></div>
			
		</div> -->
		<div>
			<div>
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
			      		<label for="exampleSelect1" class="form-label mt-4">지역선택</label>
			     		<select class="form-select" id="area" name="area">
				       		<option>서울</option>
				        	<option>대구</option>
				      		<option>부산</option>
				   		    <option>인천</option>
			      		</select>
			    </div>
			    
			    <!-- 강사가 설명한 forEach문 사용으로 지역을 db를 끄집어내서 만들어낼수있다 -->
			    <%-- <c:forEach var="data" items="${LIST}">
					<div class="w3-col w3-white w3-hover-blue w3-center w3-border-bottom w3-border-left w3-border-right brdList" id="${data.bno}">
						<div class="w3-col m3">
							<div class="w3-col m5 w3-border-right">${data.bno}</div>
							<div class="w3-col m7 w3-border-right">${data.id }</div>
						</div>
						<div class="w3-col m4 w3-border-right">${data.title}</div>
						<div class="w3-col m3 w3-border-right">${data.sdate}</div>
						<div class="w3-col m1 w3-border-right">${data.click}</div>
						<div class="w3-col m1">${data.cnt}</div>
					</div>
				</c:forEach> --%>
			    
			    
			</div>
			
			<div>
			<label for="exampleSelect1" class="">날짜와시간을 입력하세요</label>
			</div>
			<input type="date" id="chcekDate" name="chcekDate" value="" >
			<input type="time" id="chcekTime" name="chcekTime" value="" disabled >
			<div class="form-check">
		        	<input class="form-check-input" type="checkbox" id="c123" name=""  checked>
		       		<label class="form-check-label" for="flexCheckDefault">종일</label>
		    </div>
			<div class="">
		      		<!-- <label for="exampleSelect1" class="form-label mt-4"></label> -->
		     		<select class="form-select" id="alarmRepeat" name="alarmRepeat" hidden>
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
			<!-- <input class="btn btn-primary" id="chcekDateAlarm"  name="chcekDateAlarm" value="2022.05.24"> -->
			<!-- <input type="time" id="chcekDateAlarm" name="chcekDateAlarm" value=""> -->
		</div>
		<div>
			<div>
				<button type="button" class="btn btn-primary" id="savebtn" name="savebtn">저장</button>
			</div>
		</div>
		<form name=exf1>
			<B>알람설정 :</B>
			<input type=text name=h size=2>시  <input type=text name=m size=2> 분 <input type=text name=s size=2>초 
			<input type=button name=b onclick=setAlarm() value="Set Alarm"><input type=button name=r onclick=clearAlarm() value="Turn Alarm Off"><BR>
			<B>현재시간 :</B> 
			<input type=text name=ch size=2>시<input type=text name=cm size=2>분 <input type=text name=cs size=2>초
			</form>
			
			<script>
			
			var alarmTimer = null;
			var alarmSet;
			function setAlarm()   { alarmSet = true;  }
			function clearAlarm() { alarmSet = false; }
			function initAlarm() {
			  if (alarmTimer!=null)clearInterval(alarmTimer);
			  var nowTime = new Date();
			  clearAlarm();
			  document.exf1.h.value = nowTime.getHours();
			  document.exf1.m.value = nowTime.getMinutes();
			  document.exf1.s.value = nowTime.getSeconds();
			  alarmTimer=setInterval("countTime()",1000);
			}
			function matchH() { return (document.exf1.ch.value == document.exf1.h.value); }
			function matchM() { return (document.exf1.cm.value == document.exf1.m.value); }
			function matchS() { return (document.exf1.cs.value == document.exf1.s.value); }
			function countTime() {
			  var nowTime = new Date();
			  document.exf1.ch.value = nowTime.getHours();
			  document.exf1.cm.value = nowTime.getMinutes();
			  document.exf1.cs.value = nowTime.getSeconds();
			  if (matchH() && matchM() && matchS()) {
			    alert("뚜뚜뚜뚜...일어나세요... ");
			  }
			}
			onload=initAlarm;
			</script>

	</div>
	</div>
</form>
       </div>
     </div>
   </div>
</div>
</body>
</html>