package com.viseo.controller.jdb;

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
