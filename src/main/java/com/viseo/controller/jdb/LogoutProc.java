package com.viseo.controller.jdb;

/**
 * 
 * @author	전다빈
 * @since	2022.05.28
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.28	-	담당자 : 전다빈
 * 								내	용 : 로그아웃 처리 컨트롤러
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;

public class LogoutProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/viseo/member/loginForm.blp";
		req.setAttribute("isRedirect", true);
		
		req.getSession().removeAttribute("SID");
		return view;
	}

}
