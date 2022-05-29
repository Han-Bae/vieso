<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	@author		한서라
	@since		2022.05.23
	@version	v.1.0
	
				작업이력	]
						2022.05.23	-	담당자 : 한서라
										내	용 : 메뉴, 회원탈퇴 페이지 제작
--%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/base.css">
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/main.css">
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/menu.css">
	<script type="text/javascript" src="/viseo/resource/js/jquery-3.6.0.min.js"></script>
<!--
 	<script type="text/javascript" src="/viseo/resource/js/main.js"></script>
	<script type="text/javascript" src="/viseo/resource/js/menu.js"></script> 
	<script type="text/javascript" src="/viseo/resource/js/edit.js"></script>
	-->
	<script src="https://kit.fontawesome.com/e0f46f82c6.js"></script>
</head>
<body>

   <div class="menu">
      <i class="fa-solid fa-bars menu-btn menu-btn--white"></i>
      <div class="menu-bar bg-primary">
         <ul>
			<li class="profileBtn"><i class="fa-solid fa-user-astronaut"></i>Profile</li>
			<li class="settingBtn"><i class="fa-solid fa-gear"></i>Settings</li>
			<li class="logoutBtn"><i class="fa-solid fa-arrow-right-from-bracket"></i>Log out</li>
         </ul>
      </div>
   </div>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="/main.blp">VISEO</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse profile" id="profile">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="/viseo/member/info/ProfileUpdate.blp">프로필 수정</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/viseo/member/info/InfoUpdate.blp">회원정보 수정</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/viseo/member/info/withdrawal.blp">회원탈퇴</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<br>




<div class="row input-center" style="width: 100%">
	<div style="width: 50%; float:none; margin:0 auto" >
		<form action="/viseo/member/info/DelInfoProc.blp" method="post" name="frm" id="frm">
				<h5 class="text-primary"><b>회원탈퇴</b></h5>
				<hr>
				<small><em>회원탈퇴를 원하시면 비밀번호를 입력해주세요.</em></small>
				
					<div class="form-group">
					    <label for="Password" class="form-label mt-4">비밀번호</label>
					    <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호 입력">
					 </div>
				<button type="submit" class="btn btn-primary">탈퇴하기</button>
				<button  type="button"class="btn btn-primary exit-btn">취소</button>
			</form>
		</div>
	</div>
</body>
</html>