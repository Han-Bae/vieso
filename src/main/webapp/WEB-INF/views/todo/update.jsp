<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/viseo/resource/css/base.css">
<link rel="stylesheet" type="text/css" href="/viseo/resource/css/bootstrap.css">
<script type="text/javascript" src="/viseo/resource/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/viseo/resource/js/bootstrap.js"></script>
<script type="text/javascript" src="/viseo/resource/js/update.js"></script>
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
<%-- 아이디찾기 버튼 --%>
<input type="hidden" class="subbtn sub" data-target="#fid" data-toggle="modal" id="update"></input>

<%-- 아이디찾기 모달  --%>
   <div class="modal" id="fid">        
     <div class="modal-dialog" role="document">
       <div class="modal-content">
         <button type="button" data-dismiss="modal" class="logoBtn"></button>
         <div class="modal-header">
           <div class="modal-title"></div>
           <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" id="close">
             <span aria-hidden="true"></span>
           </button>
         </div>
<body>
<div>
	<form method="POST" class="" id="frm" name="frm">
		<div class="modal-body">
			<div>
				<div>
					<div>
						<div class="form-group">
					      		<label for="exampleSelect1" class="form-label mt-4">카테고리 선택</label>
					     		<p>${DATA.category}</p>
					    </div>
						<div class="form-group">
				  			<p>${DATA.title}</p>
						</div>
						<div class="form-group">
					      		<label for="exampleSelect1" class="form-label mt-4">지역선택</label>
					      		<p>${DATA.area}</p>
					    </div>
					</div>
					<div>
					<label for="exampleSelect1" class="">날짜와시간</label>
					</div>
					<p>${DATA.chcekDate}</p>
					<p>${DATA.chcekTime}</p>
					<!-- <div class="form-check">
				        	<input class="form-check-input" type="checkbox" id="c123" name=""  checked>
				       		<label class="form-check-label" for="flexCheckDefault">종일</label>
				    </div> -->
				   
				   <c:if test="${not empty DATA.chcekTime}">
					<div id="alarmRepeat" name="alarmRepeat" >
				      		<p>${DATA.alarmRepeat}</p>
				    	</div>
					</c:if>
					
					<div class="">
					<p>${DATA.memo}</p>
			    	</div>
				</div>
				<div>
					<!-- <input class="btn btn-primary" id="chcekDateAlarm"  name="chcekDateAlarm" value="2022.05.24"> -->
					<!-- <input type="time" id="chcekDateAlarm" name="chcekDateAlarm" value=""> -->
				</div>
				<div>
					<div>
						<button type="button" class="btn btn-primary" id="up_savebtn" name="up_savebtn">저장</button>
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