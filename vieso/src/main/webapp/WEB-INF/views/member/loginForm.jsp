<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
	<title>Vieso Login</title>


	<link rel="stylesheet" type="text/css" href="/vieso/resource/css/signin.css">
	<link rel="stylesheet" type="text/css" href="/vieso/resource/css/bootstrap.css">
	<script type="text/javascript" src="/vieso/resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/vieso/resource/js/bootstrap.js"></script>
	<script type="text/javascript" src="/vieso/resource/js/login.js"></script>
    <!-- Bootstrap core CSS -->


    <style>
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
    .form-signin {
      background-color: #ffffff;
      opacity: initial;
    }
    
    .ipj {
		display:flex;
    	justify-content: space-around;
    	margin-bottom: 10px;
    }
    .sub{
    	font-size: 0.8rem;		
      }
    </style>

    
    <!-- Custom styles for this template -->
  </head>
    <body class="text-center">
		<main class="form-signin">
			  <form>
			    <img class="mb-4" src="../resource/icon/logo.png" alt="" width="72" height="57">
			    <div class="form-floating">
			      <input type="text" name="id" class="form-control" id="floatingInput" placeholder="아이디를 입력하세요">
			      <label for="floatingInput">ID</label>
			    </div>
			    <div class="form-floating">
			      <input type="password" name="pw" class="form-control" id="floatingPassword" placeholder="비밀번호를 입력하세요">
			      <label for="floatingPassword">Password</label>
			    </div>			
			    <div class="checkbox mb-3">
			      <label>
			        <input type="checkbox" value="remember-me"> Remember me
			      </label>
			    </div>
			    <div class="ipj">
			    	<button type="button" class="subbtn sub" data-target="#fid" data-toggle="modal">아이디찾기</button>
			    	<div class="sub">│</div>
			    	<div class="subbtn sub" data-target="#fpw" data-toggle="modal">비밀번호찾기</div>
			    	<div class="sub">│</div>
			    	<div class="subbtn sub">회원가입</div> 
			    </div>
			    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
			    <!-- <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p> -->
			  </form>
			  
		</main>
			  <footer>
			  	<div>
					<a href="https://pixabay.com/ko/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=428293">&copy; Pixabay</a>로부터 입수된 <a href=
					"https://pixabay.com/ko/users/jarmoluk-143740/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=428293">Michal Jarmoluk</a>님의 이미지 입니다.
			  	</div>
			  </footer>
	<div class="modal" id="fid">		  
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <img src="../resource/icon/logo.png" style="float: left; width:20%; hieght:10%;">
	      <div class="modal-header">
	        <div class="modal-title" style="font-size: 1rem;">아이디 찾기</div>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true"></span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>Modal body text goes here.</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary">Save changes</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
  </body>
</html>