package com.viseo.controller.kth;

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
			req.setAttribute("SID", null);		}
		return view;
	}

}
