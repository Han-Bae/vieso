package com.viseo.controller.kth;

/**
 * 
 * @author	김태현
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 김태현
 * 								내	용 : 비밀번호 재설정
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.LoginDao;

public class RePW implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/loginForm";
		if(req.getSession().getAttribute("SID") != null) {
			// 이미 로그인 한 상태
			view = "/viseo/main.blp";
			return view;
		}
		// 아이디/이메일 이후 넘어온 status 초기화
		req.getSession().removeAttribute("status");
		
		// 파라미터 가져오기
		String id = (String)req.getSession().getAttribute("id");
		String rpw = req.getParameter("rpw");
		
		LoginDao lDao = new LoginDao();
		int cnt = lDao.editPW(id, rpw);
		
		if(cnt == 1) {
			// 성공했으면 id 세션 삭제
			req.getSession().removeAttribute("id");
		}else {
			// 실패한 경우
			req.setAttribute("msg", "오류가 발생했습니다. 다시 시도해주십시오.");
			req.setAttribute("url", "/viseo/member/loginForm.blp");
			// 모달창 다시 오픈
			req.getSession().setAttribute("status", "rePwInput");
			return "/member/loginRedirect";
		}
		return view;
	}

}
