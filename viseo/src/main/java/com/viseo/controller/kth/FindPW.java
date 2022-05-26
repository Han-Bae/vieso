package com.viseo.controller.kth;

/**
 * 
 * @author	김태현
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 김태현
 * 								내	용 : 비밀번호 재설정 유저 확인 처리
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.LoginDao;

public class FindPW implements BlpInter {

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
		String id = req.getParameter("fpwId");
		String mail = req.getParameter("fpwMail");
		// 데이터베이스 작업을 하고 결과받고
		LoginDao lDao = new LoginDao();
		int cnt = lDao.checkUserPw(id, mail);
		if(cnt == 1) {
			// 해당 유저가 존재하면
				// 이메일 처리 클래스 생성
//				CheckMail ckm = new CheckMail();
			req.getSession().setAttribute("status", "refindPw_next");
			req.getSession().setAttribute("id", id);
		} else {
			// 정보가 일치하지 않는다면
			req.setAttribute("msg", "아이디나 이메일이 일치하지 않습니다.");
			req.setAttribute("url", "/viseo/member/loginForm.blp");
				// 모달창 다시 오픈
			req.getSession().setAttribute("status", "refindPw");

			return "/member/loginRedirect";
		}
		
		return view;
	}

}
