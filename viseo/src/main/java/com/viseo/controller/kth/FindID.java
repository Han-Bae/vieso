package com.viseo.controller.kth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.LoginDao;

public class FindID implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/loginForm";
		if(req.getSession().getAttribute("SID")!= null) {
			// 이미 로그인 한 상태
			view = "/viseo/main.blp";
			return view;
		}
		// 할일
		// 파라미터 받고
		String name = req.getParameter("fidName");
		String mail = req.getParameter("fidMail");
		// 데이터베이스 작업을 하고 결과받고
		LoginDao lDao = new LoginDao();
		int cnt = lDao.checkUserId(name, mail);
		if(cnt == 1) {
			// 해당 유저가 존재하면
				// 이메일 처리 클래스 생성
//			CheckMail ckm = new CheckMail();
			System.out.println("일치");
		} else {
			// 정보가 일치하지 않는다면
			req.setAttribute("msg", "이름이나 이메일이 일치하지 않습니다.");
			req.setAttribute("url", "/viseo/member/loginForm.blp");
				// 모달창 다시 오픈
			req.getSession().setAttribute("status", "refindId");

			return "/member/loginRedirect";
		}
		
		return view;
	}

}
