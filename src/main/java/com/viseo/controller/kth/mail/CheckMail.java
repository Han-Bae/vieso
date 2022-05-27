package com.viseo.controller.kth.mail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;

public class CheckMail implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("SID") != null) {
			// 이미 로그인 한 상태
			req.setAttribute("isRedirect", true);
			return "/viseo/main.blp";
		}
		req.setAttribute("check", "OK");
		req.setAttribute("icon", "success");
		req.setAttribute("title", "이메일 인증 성공!");
		req.setAttribute("url", "/viseo/member/joinForm.blp");
		req.setAttribute("msg", "남은 회원가입 절차를 진행해주세요.");
		return "/member/loginRedirect";
	}

}
