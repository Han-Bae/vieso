package com.viseo.controller.jyn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;

public class JoinForm implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/joinForm";
		return view;
	}

}
