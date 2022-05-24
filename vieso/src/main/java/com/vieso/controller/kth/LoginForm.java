package com.vieso.controller.kth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vieso.controller.BlpInter;

public class LoginForm implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/loginForm";
		return view;
	}

}
