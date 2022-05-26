package com.viseo.controller.kth;

/**
 * 
 * @author	김태현
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.24	-	담당자 : 김태현
 * 								내	용 : 로그인폼 표시 클래스
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
			view = "/viseo/main.blp";
			req.setAttribute("SID", null);
		
		}
		if(req.getSession().getAttribute("status")!= null) {
			System.out.println("status : " + req.getSession().getAttribute("status"));
			req.setAttribute("status", req.getSession().getAttribute("status"));
			req.getSession().removeAttribute("status");
		}
		return view;
	}

}
