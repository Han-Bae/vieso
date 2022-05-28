<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
	@author		한서라
	@since		2022.05.23
	@version	v.1.0
	
				작업이력	]
						2022.05.23	-	담당자 : 한서라
										내	용 : 프로필업데이트 화면 구성
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 설정</title>
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/base.css">
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/main.css">
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/menu.css">
	<script type="text/javascript" src="/viseo/resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/viseo/resource/js/main.js"></script>
	<script type="text/javascript" src="/viseo/resource/js/menu.js"></script>
	<script type="text/javascript" src="/viseo/resource/js/edit.js"></script>
	<script src="https://kit.fontawesome.com/e0f46f82c6.js"></script>
<style>
	#preview{
	width: 250px; 
	}
</style>

<script type="text/javascript">
	function readURL(input) {
		  if (input.files && input.files[0]) {
		    var reader = new FileReader();
		    reader.onload = function(e) {
		      document.getElementById('preview').src = e.target.result;
		    };
		    reader.readAsDataURL(input.files[0]);
		  } else {
		    document.getElementById('preview').src = "";
		  }
		}
</script> 
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
    <a class="navbar-brand" href="com.viseo.controller.main">VISEO</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="profile">

<%--     <c:if test="${not empty sessionScope.principal}">
				<li class="nav-item">
					<a href="/blog/user?cmd=profileUpload">
					<img style = "border-radius:50%;" onerror="this.src ='/blog/image/userProfile.png'" 
					src="${sessionScope.principal.userProfile}"  width="40px" height="40px" />
					</a>
				</li>
				</c:if>
 --%>
    
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
	<h5 class="text-primary"><b>Profile Edit</b></h5>
	<hr>
	
	<form action="/viseo/member/info/ProfileUpdateProc.blp" method="post" name="frm" id="frm">
		<input type="hidden" id="tname" value="${DATA.nickname}">
		
	 	<div class="form-group">
		      <label for="nickname" class="form-label mt-4 text-primary"><b>닉네임</b></label>
		      <input type="text" class="form-control col-8" id="nickname" name="nickname" value="${DATA.nickname}">
		 </div>
		  <div class="form-group">
		      <label for="formFile" class="form-label mt-4 text-primary"><b>profile image select</b></label>
			  <img id="preview" />
		      <input class="form-control col-8" type="file" id="formFile" onchange="readURL(this);"> 
	    </div>
	
	    
	    <button type="submit" class="btn btn-primary">EDIT</button>
	    <button type="button" class="btn btn-primary exit-btn">EXIT</button>
	</form>
	</div>
</div>
</body>
</html>