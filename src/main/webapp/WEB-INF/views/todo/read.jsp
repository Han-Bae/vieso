<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<%--
 	할일 조회창 모달창 jsp파일
 			@author 박형근
  			@since 2022.05.27
  			@version v.1.0
  				제작자 박형근   --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/viseo/resource/css/base.css">
<link rel="stylesheet" type="text/css" href="/viseo/resource/css/bootstrap.css">
<script type="text/javascript" src="/viseo/resource/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/viseo/resource/js/bootstrap.js"></script>
<script type="text/javascript" src="/viseo/resource/js/read.js"></script>
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
<!-- <button type="button" class="subbtn sub" data-target="#fid" data-toggle="modal">조회하기</button> -->

<%-- 아이디찾기 모달  --%>
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
			<input type="hidden" id="tmpCategory" name="tmpCategory" value="${DATA.category}">
			<input type="hidden" id="tmpAlarmRepeat" name="tmpAlarmRepeat" value="${DATA.alarmRepeat}">
			<input type="hidden" id="tmpCheckTime" name="tmpCheckTime" value="${DATA.chcekTime}">
			<div class="modal-body">
		
			<div>
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
				  			<input class="form-control form-control-lg" type="text" id="title" name="title" value="${DATA.title}">
						</div>
						<div class="form-group">
					      		<label for="exampleSelect1" class="form-label mt-4">지역</label>
					      		<p>${DATA.area}</p>
					    </div>
					</div>
					<div>
					<label for="exampleSelect1" class="">날짜와시간</label>
					</div>
					<input type="text" id="chcekDate" name="chcekDate" value="${DATA.chcekDate}" readOnly>
					
				   <div>
				        <input class="form-check-input" type="checkbox" id="c123" name="c123"  >
				       	<label class="form-check-label" for="flexCheckDefault">종일</label>
						<input type="time" id="chcekTime" name="chcekTime" value="${DATA.chcekTime}" >
						<select class="form-select" id="alarmRepeat" name="alarmRepeat" >
							<option>알람안함</option>
							<option>30분전알림</option>
		     			  	<option>한시간전알림</option>
		     			  	<option>두시간전알림</option>
		     			  	<option>세시간전알림</option>
		      			</select>
	      			</div>
					
					<div class="">
					<textarea class="form-control" id="memo" name="memo" rows="3" style="height: 150px;">${DATA.memo}</textarea>
			    	</div>
				</div>
				<div>
					<!-- <input class="btn btn-primary" id="chcekDateAlarm"  name="chcekDateAlarm" value="2022.05.24"> -->
					<!-- <input type="time" id="chcekDateAlarm" name="chcekDateAlarm" value=""> -->
				</div>
				<div>
					<div>
						<button type="button" class="btn btn-primary" id="confirmbtn" name="confirmbtn">확인</button>
						<button type="button" class="btn btn-primary" id="updatbtn" name="updatebtn">수정</button>
					</div>
				</div>
			</div>
			</div>
		</form>
       </div>
     </div>
   </div>
</div>
</body>
</html>