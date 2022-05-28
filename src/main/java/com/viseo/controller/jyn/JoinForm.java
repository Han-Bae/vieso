package com.viseo.controller.jyn;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.viseo.controller.BlpInter;
import com.viseo.controller.*;
import com.viseo.dao.*;
import com.viseo.vo.*;
/**
 * 
 * @author  정유나
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.25	-	담당자 : 정유나
 * 								내	용 : 회원가입 Form
 *
 */
public class JoinForm implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/joinForm";
		// 세션 검사
		HttpSession session = req.getSession();
		
		// 로그인 한 경우
		if(session.getAttribute("SID") != null) {
			// 리다이렉트 셋팅
			req.setAttribute("isRedirect", true);
			// 요청주소 반환
			return "/viseo/main.blp";
		}
		
		JoinDao jDao = new JoinDao();
		ArrayList<String> list = jDao.getAreaName();
		
		// 데이터 심고
		req.setAttribute("LIST", list);
		
		return view;
	}
 
}
