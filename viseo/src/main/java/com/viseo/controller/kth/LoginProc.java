package com.viseo.controller.kth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.LoginDao;

public class LoginProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/main";
		if(req.getSession().getAttribute("SID")!= null) {
			// 이미 로그인 한 상태
			return view;
		}
		
		// 아직 로그인 안된 상태
		// 할일
		// 파라미터 받고
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		// 데이터베이스 작업을 하고 결과받고
		LoginDao lDao = new LoginDao();
		int cnt = lDao.getLogin(id, pw);
		if(cnt == 1) {
			// 아이디와 비밀번호가 일치하는
			// 회원이 있는 경우 -> 로그인 처리
			req.getSession().setAttribute("SID", id);
				// 세션에 데이터 기록했으면 메인페이지로 돌려보낸다.
			// 메인페이지로 돌려보내는 뷰는 위에서 만들어 뒀으니 그냥 사용
		} else {
			// 로그인 처리하면 안된다.
			// 정보가 정확하지 않거나 없는 회원이다.
			view = "/member/loginForm";
		}
		// 결과에 따라서 처리하고
		return view;
	}

}
