package com.viseo.controller.jdb;

/**
 * 
 * @author	전다빈
 * @since	2022.05.23
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.23	-	담당자 : 전다빈
 * 								내	용 : 클래스 제작, 뷰 연결
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;

public class MainForm implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/main";
		/*
		// 로그인 확인
		String sessionId = (String) req.getSession().getAttribute("SID");
		if(sessionId == null) {
			req.setAttribute("isRedirect", true);
			view = "/vieso/member/login.blp";
			return view;
		}
		*/
		
		// 캘린더 정보 가져오기
		
		// 회원 정보 넘기기
		
		// 회원번호로 스케줄 가져오기
		
		
		return view;
	}

}
