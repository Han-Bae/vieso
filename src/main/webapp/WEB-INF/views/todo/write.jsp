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
<link rel="stylesheet" type="text/css" href=https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cosmo/bootstrap.min.css>
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
<%------------------------------------------------------------------------------------------%>
<%-- 모달  --%>
   <div class="modal" id="toDofid">        
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
<input type="hidden" name="area" id="area" value="">
	 <div class="modal-body">
	<div>
		
		<div>
			<div>
				<div class="form-group">
			      		<label for="exampleSelect1" class="form-label mt-4">선택한 날짜</label>
			     		<input class="form-control form-control-lg"  type="text" id="chcekDate" name="chcekDate" value="${DATE.chcekDate}" >
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
					 <label for="aname" class="form-label mt-4">약속장소</label>
					<select class="form-select" id="aname">
						<option>지역 선택</option>
				<c:forEach var="loc" items="${LIST}">
						<option value="${loc}">${loc}</option>
				</c:forEach>
					</select>
					
					<select class="form-select" id="city" name="addr" style="display: none;">
						<option>도시 선택</option>						
					</select>
				</div>
			</div>
<!--------------------------------------------------------------------------------------->
		<div class="form-group">
			<div>
				<label for="exampleSelect1" class="form-label mt-4">약속시간</label>
			</div>
			<div class="form-check">
		        	<input class="form-check-input" type="checkbox" id="c123" name=""  checked>
		       		<label class="form-check-label" for="flexCheckDefault">종일</label>
		    </div>
			<input class="form-control form-control-lg" type="time" id="chcekTime" name="chcekTime" value="" disabled >
			<div class="form-label mt-4">
		     		<select class="form-select" id="alarmRepeat" name="alarmRepeat" hidden>
						<option>알림없음</option>
						<option>30분전알림</option>
	     			  	<option>한시간전알림</option>
	     			  	<option>두시간전알림</option>
	     			  	<option>세시간전알림</option>
		      		</select>
		    </div>
		</div>
<!--------------------------------------------------------------------------------------->
				<div class="form-group">
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
</div>
</body>
</html>