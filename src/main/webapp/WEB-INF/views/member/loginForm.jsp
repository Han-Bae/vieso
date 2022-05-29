<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<%--
 	로그인 화면, 아이디 찾기/비밀번호 찾기 모달창을 포함한 jsp파일
 			@author 김태현
  			@since 2022.05.24
  			@version v.1.0
  				제작자 김태현   --%>
<html>
 <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
	<title>Viseo Login</title>


	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/viseo/resource/css/signin.css">
	<script type="text/javascript" src="/viseo/resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/viseo/resource/js/bootstrap.js"></script>
	<script type="text/javascript" src="/viseo/resource/js/login.js"></script>
    <!-- Bootstrap core CSS -->


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
    .form-signin {
      background-color: #ffffff;
      opacity: initial;
    }
    
    .ipj {
		display:flex;
    	justify-content: space-around;
    	margin-bottom: 10px;
    }
    .subbtn{
    	border: 0px;
    	background-color: #ffffff;    
    }
    .sub{
    	font-size: 0.8rem;		
      }
    .logoBtn{
    	  float: left; 
    	  width: 80px;
    	  height:40px;
    	  background-image: url(../resource/icon/logo.png);
    	  background-size: cover;
    	  border: 0px;
    	  margin-left: 5px;
    	  margin-top: 5px;
    }
    #rpwmsg{
    	margin-top: 10px;
    	text-align: right;
    	font-size: 10pt;
    }
    </style>

    
    <!-- Custom styles for this template -->
  </head>
    <body class="text-center">
    	<%-- 로그인화면  --%>
		<main class="form-signin">
			  <form method="post" id="loginFrm" action="/viseo/member/loginProc.blp" class="frm">
			    <img class="mb-4" src="../resource/icon/logo.png" alt="" style="height:80px; width:auto;">
			    <div class="form-floating">
			      <input type="text" name="id" class="form-control" id="id" title="숫자, 대소문자 4~10글자로 입력해주세요."
			      	pattern="^([A-Za-z0-9]){4,10}$" placeholder="아이디를 입력하세요" required autofocus>
			      <label for="id">ID</label>
			    </div>
			    <div class="form-floating">
			      <input type="password" name="pw" class="form-control" id="pw" title="숫자, 대소문자, 특문을 포함한 6~15자로 입력해주세요."
			      	pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,15}$" placeholder="비밀번호를 입력하세요" required>
			      <label for="pw">Password</label>
			    </div>			
			    <div class="checkbox mb-3">
			      <label>
			        <input type="checkbox" name="remember" value="remember-me"> Remember me
			      </label>
			    </div>
			    <div class="ipj">
			    	<button type="button" class="subbtn sub" data-target="#fid" data-toggle="modal">아이디찾기</button>
			    	<div class="sub">│</div>
			    	<button type="button" class="subbtn sub" data-target="#fpw" data-toggle="modal">비밀번호찾기</button>
			    	<div class="sub">│</div>
			    	<button type="button" class="subbtn sub" id="join">회원가입</button>
			    </div>
			    <button class="w-100 btn btn-lg btn-primary" id="signIn" type="submit">Sign in</button>
			    <!-- <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p> -->
			  </form>
			  
		</main>
			  <footer>
			  	<div>
					<a href="https://pixabay.com/ko/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=428293">&copy; Pixabay</a>로부터 입수된 <a href=
					"https://pixabay.com/ko/users/jarmoluk-143740/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=428293">Michal Jarmoluk</a>님의 이미지 입니다.
			  	</div>
			  </footer>
	<c:if test="${not empty status}">
		<input type="hidden" id=status value="${status}">
	</c:if>	  
	<%-- 아이디찾기 모달  --%>
	<div class="modal" id="fid">		  
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <button type="button" data-dismiss="modal" class="logoBtn"></button>
	      <div class="modal-header">
	        <div class="modal-title">아이디 찾기</div>
	        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true"></span>
	        </button>
	      </div>
     	<form method="post" id="fidFrm" action="/viseo/member/findID.blp" class="frm">
	      <div class="modal-body">
			    <div class="form-floating" style="margin-bottom: 10px">
			      <input type="text" name="fidName" class="form-control" id="fidName" placeholder="이름을 입력하세요." required autofocus>
			      <label for="fidName">이름</label>
			    </div>
			    <div class="form-floating">
			      <input type="email" name="fidMail" class="form-control" id="fidMail" title="ex)asdf@asd.qwe"
			      	pattern="^([a-zA-Z0-9]){4,10}@([a-zA-Z]){2,10}.([a-zA-Z]){2,3}$" placeholder="이메일을 입력하세요." required>
			      <label for="fidMail">이메일</label>
			    </div>
	      </div>
	      <div class="modal-footer">
			<button class="w-100 btn btn-lg btn-primary" id="fidbtn" type="submit">메일에서 확인하기</button>
	      </div>
      	</form>
	    </div>
	  </div>
	</div>
	
	<%-- 비밀번호 찾기 모달  --%>
	<div class="modal" id="fpw">		  
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <button type="button" data-dismiss="modal" class="logoBtn"></button>
	      <div class="modal-header">
	        <div class="modal-title">비밀번호 찾기</div>
	        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true"></span>
	        </button>
	      </div>
      	<form method="post" id="fpwFrm" action="/viseo/member/findPW.blp"  class="frm">
	      <div class="modal-body">
			    <div class="form-floating" style="margin-bottom: 10px">
			      <input type="text" name="fpwId" class="form-control" id="fpwId" title="숫자, 대소문자 4~10글자로 입력해주세요."
			      	pattern="^([A-Za-z0-9]){4,10}$" placeholder="아이디를 입력하세요." required autofocus>
			      <label for="fpwId">아이디</label>
			    </div>
			    <div class="form-floating">
			      <input type="email" name="fpwMail" class="form-control" id="fpwMail" title="ex)asdf@asd.qwe(4글자/2글자/2글자 이상)"
			      	pattern="^([a-zA-Z0-9]){4,10}@([a-zA-Z]){2,10}.([a-zA-Z]){2,3}$" placeholder="이메일을 입력하세요." required>
			      <label for="fpwMail">이메일</label>
			    </div>			
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary" id="fpwbtn" data-toggle="modal">다음</button>
	      </div>
      	</form>
	    </div>
	  </div>
	</div>
	
	<!-- 비밀번호 찾기 다음 모달 -->
	<div class="modal" id="rpwmd">		  
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <button type="button" data-dismiss="modal" class="logoBtn"></button>
	      <div class="modal-header">
	        <div class="modal-title">비밀번호 재설정</div>
	        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true"></span>
	        </button>
	      </div>
     	<form id="rpwFrm" class="frm" action="/viseo/member/rePW.blp">
	      <div class="modal-body">
			    <div class="form-floating" style="margin-bottom: 10px">
			      <input type="password" name="rpw" class="form-control" id="rpw" title="숫자, 대소문자, 특문을 포함한 6~15자로 입력해주세요."
			      	pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,15}$" placeholder="변경할 비밀번호를 입력해주세요." required autofocus>
			      <label for="rpw">변경할 비밀번호</label>
			    </div>
			    <div class="form-floating">
			      <input type="password" name="rpwck" class="form-control" id="rpwck" placeholder="비밀번호를 다시 입력해주세요." required>
			      <label for="rpwck">비밀번호 확인</label>
				      <h4 id="rpwmsg"></h4>
				</div>			
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary" id="rpwbtn">비밀번호 재설정</button>
	      </div>
      	</form>
	    </div>
	  </div>
	</div>
  </body>
</html>