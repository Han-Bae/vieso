package com.viseo.controller.kth;

/**
 * 
 * @author	김태현
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 김태현
 * 								내	용 : 로그인처리 클래스 제작
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.LoginDao;

public class LoginProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("SID") != null) {
			// 이미 로그인 한 상태
			req.setAttribute("isRedirect", true);
			return "/viseo/main.blp";
		}

		// 아직 로그인 안된 상태
		// 할일
		// 파라미터 받고
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		// 데이터베이스 작업을 하고 결과받고
		LoginDao lDao = new LoginDao();
		int cnt = lDao.getLogin(id, pw);
		if (cnt == 1) {
			// 아이디와 비밀번호가 일치하는
			// 회원이 있는 경우 -> 로그인 처리
			req.getSession().setAttribute("SID", id);
			req.setAttribute("icon", "success");
			req.setAttribute("title", "로그인 성공!");
			req.setAttribute("msg", id+"님 어서오세요.");
			req.setAttribute("url", "/viseo/main.blp");
			req.getSession().removeAttribute("status");
		} else {
			// 로그인 처리하면 안된다.
			// 정보가 정확하지 않거나 없는 회원이다.
			// 정보가 일치하지 않는다면
			req.setAttribute("icon", "error");
			req.setAttribute("title", "로그인 실패!");
			req.setAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
			req.setAttribute("url", "/viseo/member/loginForm.blp");
				// 모달창 다시 오픈
			req.getSession().setAttribute("status", "relogin");
		}
		// 결과에 따라서 처리하고
		return "/member/loginRedirect";
	}

}