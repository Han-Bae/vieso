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
<title>Viseo Register</title>

	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/signin.css">
	<script type="text/javascript" src="/viseo/resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/viseo/resource/js/bootstrap.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script type="text/javascript" src="/viseo/resource/js/join.js"></script>

<style type="text/css">
	.bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }
@media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
body	{ overflow: auto;}
body::before {
	    position: fixed;
	    top: 0;
	    left: 0;
	    right: 0;
	    bottom: 0;
	    background-image: url(../resource/icon/calendar.jpg);
	    background-size: cover;
	    -webkit-filter: blur(3px); 
	    -moz-filter: blur(3px); 
	    -o-filter: blur(3px); 
	    -ms-filter: blur(3px); 
	    filter: blur(5px);
	    transform: scale(1.02);
	    z-index: -1; 
	    content: "";
		}
h4{
	font-size: 10pt;
}
.form-signin {
      background-color: #ffffff;
      opacity: initial;
    }
	 
.ckdiv{
		text-align:right;
		margin-top: 3px;
	}
.ckbtn{
	 	width:calc(1.5rem + 80px);
	 	font-size: 0.8rem;
	 	height: calc(2rem + 2px);
	 	line-height: 1.25;
	 }
</style>
<script type="text/javascript">
</script>
</head>
<body class="text-center"
	style=" 
	    background-image: url(../resource/icon/calendar.jpg);
	    background-size: cover;">
	<!-- 가운데 정렬 -->
    <main class="form-signin" style="max-width:650px;">
		<form method="POST" action="/viseo/member/joinProc.blp"
			  name="frm" id="frm" class="frm">
		<!-- 메인 로고 가운데 정렬 -->
			<a href="/viseo/main.blp"><img src="../resource/icon/logo.png" alt="" style="height:80px; width:auto;"></a>
		<!-- 타이틀 -->
			<h2 style="margin-bottom: 10px;" class="text-primary margin-top content"><strong>회원가입</strong></h2>
				<!-- 회원 이름 -->
			<div class="form-floating" style="margin-bottom: 5px;">
					 <input type="text" class="form-control" id="name" name="name"
					 	title="이름을 입력해주세요." placeholder="Enter name" required autofocus>
					 <label for="name">Name</label>
			</div> 
				<!-- 회원 아이디, 아이디 체크박스 --> 
			<div class="form-floating" style="margin-bottom: 5px;">
			      <input type="text" name="id" class="form-control" id="id" title="숫자, 대소문자 4~10글자로 입력해주세요."
			      	placeholder="아이디를 입력하세요" required>
			      <label for="id">ID</label>
			      <div class="ckdiv" style="text-align: right; margin-top: 1.5px;">
				      <h4 id="idmsg"></h4>
					  <button class="btn btn-primary ckbtn" type="button" id="idck">확인</button>
			      </div>
<!-- 			      <span id="idmsg" class="form-text text-muted"></span> -->
			</div>
 				
				<!-- 비밀번호 -->
			<div class="form-floating" style="margin-bottom: 5px;">
				<input type="password" name="pw" class="form-control" id="pw" title="숫자, 대소문자, 특문을 포함한 6~15자로 입력해주세요."
		      		pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,15}$" placeholder="비밀번호를 입력하세요" required>
		      	<label for="pw">Password</label>
		      	<div class="ckdiv" style="text-align: right; margin-top: 1.5px;">
					<h4 id="pwmsg"></h4>
				</div>
			</div>
				
			<!-- 비밀번호 체크  -->
			<div class="form-floating">
		      	<input type="password" name="repw" class="form-control" id="repw" placeholder="비밀번호를 다시 입력해주세요." required>
		      	<label for="repw">비밀번호 확인</label>
		      	<div class="ckdiv" style="text-align: right; margin-top: 1.5px;">
				    <h4 id="repwmsg"></h4>
				</div>
			</div>
			
			<!-- 주소 -->
			<div class="form-group">
				<label for="aname" style="float: left" class="form-label mt-4">주소</label>
				<select class="form-select" id="aname">
					<option>지역 선택</option>
<c:forEach var="loc" items="${LIST}">
						<option value="${loc}">${loc}</option>
</c:forEach>
				</select>
					<small class="form-text text-muted" style="margin-bottom: 1.8rem;">area name</small>
				
				<select class="form-select" id="city" name="addr" style="display: none;">
					<option>도시 선택</option>						
				</select>
					<small id="citymsg" class="form-text text-muted" style="margin-bottom: 1.8rem; display: none;">city name</small>
			</div>
			
			<!-- 성별 -->
			<div class="form-group" style="text-align: left;" id="gen">
		    <!-- <fieldset class="form-group"> -->
			    <label for="gen" style="display: inline-block;" class="form-label mt-4">성별</label>
			      	<div class="form-check" style="text-align: center;">
			      		<div style="display: inline-block">
			          	<input type="radio"
			          		class="form-check-input" name="gen" id="mgen" value="M">
			      		</div>
			        	<label class="form-check-label" for="mgen">
			          	남자(Male)
			        	</label>
		        	</div>
				    <div class="form-check" style="text-align: center;">
			      		<div style="display: inline-block">
				    	<input type="radio"
				    		class="form-check-input" name="gen" id="fgen" value="F">
			      		</div>
				    	<label class="form-check-label" for="fgen">
				        여자(Female)
				     	</label>
				   </div>
	  		</div>

		  		
				<!-- 프로필 파일 : 기능 추후에 추가 -->
<!-- 				
		        <div class="form-group">
		            <label for="formFile" class="form-label mt-4">프로필 이미지 선택</label>
		            <input class="form-control col-50" type="file" id="formFile" onchange="readURL(this);"> 
		           <img id="preview" />
		       </div>
		        -->
		       
				<!-- 닉네임 -->
			<div class="form-floating" style="margin-bottom: 5px;">
			    <input type="text" name="nickname" class="form-control" id="nickname"
			      	placeholder="닉네임을 입력하세요" required>
			    <label for="nickname">NickName</label>
			    <div class="ckdiv"  style="text-align: right; margin-top: 1.5px;">
			    	<h4 id="nicknamemsg"></h4>
					<button class="ckbtn btn btn-primary" type="button" id="nicknameck">확인</button>
				</div>
			</div>
					  
				<!-- 전화번호 -->
			<div class="form-floating" style="margin-bottom: 5px;">
			      <input type="text" name="tel" class="form-control" id="tel"
			      	placeholder="전화번호를 입력하세요." required>
			      <label for="tel">전화번호</label>
			</div>
				
				<!-- 생일 -->
				<div class="form-group">
			         <label for="bir" style="float: left;" class="form-label mt-4">생년월일</label>
			           <select class="form-select" name="yy" id="year">
			           </select><small style="margin-bottom: 1rem;" class="form-text text-muted">birth year</small>
			           <select class="form-select" name="mm" id="month">
			           </select><small style="margin-bottom: 1rem;" class="form-text text-muted">the month of birth</small>
			           <select class="form-select" name="dd" id="day">
			           </select><small style="margin-bottom: 1rem;" class="form-text text-muted">the date of birth</small>
			           <input type="hidden" name="birth" id="birth">
	            </div>	    
	                 
				<!-- 이메일 -->
			<div class="form-floating" style="margin-bottom: 5px;">
			    <input type="text" name="mail" class="form-control" id="mail"
			      	placeholder="이메일을 입력하세요." required>
			    <label for="mail">Mail</label>
			    <div class="ckdiv"  style="text-align: right; margin-top: 1.5px;">
			      	<button class="ckbtn btn btn-primary" type="button" id="mailck">확인</button>
					<button type="button" class="ckbtn btn btn-primary" id="mbtn">인증</button>
			    </div>
				<span id="mailckmsg" class="form-text text-muted"></span>
			</div>
						 
					<!-- 인증 버튼 이벤트 -->
				

				<!-- 버튼 이벤트 -->
				<div class="content">
					<button style="margin-top:8px;" type="button" class="btn btn-primary btn-lg" id="jbtn">가입하기</button>
				</div>
			</form>			
	<!-- 가운데정렬 태그 끝 -->
	</main>
</body>
</html>