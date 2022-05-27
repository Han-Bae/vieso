package com.viseo.controller.kth;

/**
 * 
 * @author	김태현
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 김태현
 * 								내	용 : 로그인폼 표시 클래스
 */

/*
 * 한거 : 로그인 처리, 아이디 / 비밀번호 사용자 인증 처리, 비밀번호 재설정 -> 리다이렉트 사용해서 알림창 띄우고 후처리   
 * 남은거 : 회원가입 연결 + 이메일 연동
 * 도와주세요선샌ㄴ님ㄴ들 : 이메일 연동
*/

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;

public class LoginForm implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/loginForm";
		if(req.getSession().getAttribute("SID") != null) {
			// 이미 로그인한 경우
			req.setAttribute("isRedirect", true);
			req.setAttribute("SID", null);
			
			// view = "/viseo/main.blp";
			// 일단 로그아웃 시켜두게
			req.getSession().removeAttribute("SID");
		}
		
		
//		if(req.getSession().getAttribute("status")!= null) {
//			System.out.println("status : " + (String)req.getSession().getAttribute("status"));
//			req.setAttribute("status", req.getSession().getAttribute("status"));
//			req.getSession().removeAttribute("status");
//		}
		
		
		return view;
	}

}
