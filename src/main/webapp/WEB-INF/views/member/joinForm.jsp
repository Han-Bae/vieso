<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
<title>회원 가입</title>

	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/base.css">
	<link rel="stylesheet" type="text/css" href=https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cosmo/bootstrap.min.css>
	<script type="text/javascript" src="/viseo/resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/viseo/resource/js/bootstrap.js"></script>
	<script type="text/javascript" src="/viseo/resource/js/join.js"></script>

<style type="text/css">
	  	#pwmsg, #repwmsg, #idmsg, #nicknamemsg{
		display: none;
	}  
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<!-- 가운데 정렬 -->
    <div class="row input-center" style="width: 100%">
		 <div style="width: 30%; float:none; margin:0 auto" >
		<!-- 메인 로고 가운데 정렬 -->
		<div class="row input-center" style="width: 100%">
		 <div style="width: 30%; float:none; margin:0 auto" >	
		<a href="/viseo/main.viseo"><img src="/viseo/resource/icon/logo.png"/></a>
		</div></div><br></br>
		<!-- 타이틀 -->
		<h1 class="text-primary margin-top content"><strong>회원가입</strong></h1>
			<form method="POST" action="/viseo/member/joinProc.blp" name="frm" id="frm" class="frm">
				<!-- 회원 이름 -->
				<div class="form-group">
					 <label for="name" class="form-label mt-4 left">이름</label>
					 <input type="text" class="form-control col-50" id="name" name="neme" placeholder="Enter name">
				</div>
				
				<!-- 회원 아이디, 아이디 체크박스 --> 
				<div class="form-group">
					 <label for="id" class="form-label mt-4">아이디</label>
					 	<div class="input-group mb-4">
					 	<input type="text" class="form-control col-50" id="id" name="id" placeholder="Enter id">
						  <button class="btn btn-primary" type="button" id="idck">확인</button>
					  	</div>
						  <span id="idmsg" class="form-text text-muted"></span>
 				</div> 
				<!-- 비밀번호 -->
				<div class="form-group">
					 <label for="pw" class="form-label mt-4">비밀번호</label>
					 <input type="password" class="form-control col-50" id="pw" name="pw" placeholder="Passward">
					 <small id="pwHelp" class="form-text text-muted">숫자, 대소문자, 특문을 포함한 6~15자로 입력해주세요.</small>
					 <span id="pwmsg" class="form-text text-muted"></span>
				</div>
				<!-- 비밀번호 체크  -->
				<div class="form-group">
					 <label for="repw" class="form-label mt-4">비밀번호 확인</label>
					 <input type="password" class="form-control col-50" id="repw" placeholder="Passward check">
					 <span id="repwmsg" class="form-text text-muted"></span>
				</div>
				<!-- 주소 -->
				<div class="form-group">
					 <label for="address" class="form-label mt-4">주소</label>
					 <input type="text" class="form-control col-50" id="addr" name="addr" placeholder="Enter address">
				</div>
				<!-- 성별 -->
				<div class="form-group" id="gen">
			    <!-- <fieldset class="form-group"> -->
				      <label for="gen" class="form-label mt-4">성별</label>
				      <div class="form-check">
				          <input type="radio" class="form-check-input" name="gen" id="mgen" value="M">
				        <label class="form-check-label" for="mgen">
				          남자(Male)
				        </label>
		        	  </div>
				      <div class="form-check">
				          <input type="radio" class="form-check-input" name="gen" id="fgen" value="F">
				        <label class="form-check-label" for="fgen">
				          여자(Female)
				        </label>
				      </div>
		  		</div>
				<!-- 프로필 파일 -->
		        <div class="form-group">
		            <label for="formFile" class="form-label mt-4">프로필 이미지 선택</label>
		            <input class="form-control col-50" type="file" id="formFile" onchange="readURL(this);"> 
		           <img id="preview" />
		       </div>
<!-- 		       닉네임 체크박스 확인용
   				<div class="form-group">
				 <label for="id" class="form-label mt-4">아이디</label>
				 	<div class="input-group mb-4">
				 	<input type="text" class="form-control col-50" id="id" name="id" placeholder="Enter id">
					  <button class="btn btn-primary" type="button" id="idck">확인</button>
				  	</div>
					  <span id="idmsg" class="form-text text-muted"></span>
				</div>  -->
				<!-- 닉네임 -->
				<div class="form-group">
					 <label for="nickname" class="form-label mt-4">닉네임</label>
					 	<div class="input-group mb-4">
					 	<input type="text" class="form-control col-50" id="nickname" name="nickname" placeholder="Enter nickname">
						  <button class="btn btn-primary" type="button" id="nicknameck">확인</button>
					  	</div>
						  <span id="nicknamemsg" class="form-text text-muted"></span>						  
					  </div>
				<!-- 전화번호 -->
				<div class="form-group">
					 <label for="tel" class="form-label mt-4">전화번호</label>
					 <input type="text" class="form-control col-50" id="tel" name="tel" placeholder="Enter tel number">
				</div>
				<!-- 생일 -->
				<div class="form-group">
			         <label for="bir" class="form-label mt-4">생년월일</label>
			           <select class="form-select" name="yy" id="year">
			           </select><small class="form-text text-muted">태어난 년도를 선택해 주세요.</small>
			           <select class="form-select" name="mm" id="month">
			           </select><small class="form-text text-muted">태어난 월을 선택해 주세요.</small>
			           <select class="form-select" name="dd" id="day">
			           </select><small class="form-text text-muted">태어난 일을 선택해 주세요.</small>
	            </div>	         
				<!-- 이메일 -->
				<div class="form-group">
					<label for="email" class="form-label mt-4">이메일</label>
						<div class="input-group mb-4">
						<input type="text" class="form-control col-50" id="email" placeholder="Enter email">
						 <button class="btn btn-primary" type="button" id="emailck">인증</button>
						 <span id="remailmsg" class="form-text text-muted"></span>
				</div>
				<div class="content">
				<button type="submit" class="btn btn-primary btn-lg" id="jbtn">가입하기</button>
				</div>
					</div>
			</form>
			<br></br>
		<!-- 버튼 태그 -->
	 </div>
	<!-- 가운데정렬 -->
	</div>
</body>
</html>