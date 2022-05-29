package com.viseo.controller.jyn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.JoinDao;
import com.viseo.dao.LoginDao;
import com.viseo.vo.JoinVO;

/**
 * 
 * @author  정유나
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 정유나
 * 								내	용 : 회원가입 Proc
 *
 */

public class JoinProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/viseo/member/loginForm.blp";
		if(req.getSession().getAttribute("SID") != null) {
			return view;
		}
		LoginDao lDao = new LoginDao();
		int cnt_mail = lDao.getOK(req.getParameter("mail"));
		
		// 허가받지 못한 이메일이라면
		if(cnt_mail != 1) {
			req.setAttribute("icon", "error");
			req.setAttribute("title", "메일 인증이 되지 않았습니다.");
			req.setAttribute("msg", "이메일 인증 메일을 확인 누른 뒤 진행해주세요");
			req.setAttribute("url", "/viseo/member/joinForm.blp");
			return "/member/loginRedirect";
		}
		// 이메일 수락 단계
		int cnt_join = lDao.joinOK(req.getParameter("mail"));
		if(cnt_join != 1) {
			req.setAttribute("icon", "error");
			req.setAttribute("title", "이메일 등록 오류 발생!");
			req.setAttribute("msg", "이메일 등록 중 오류가 발생했습니다. 다시 진행해주세요.");
			req.setAttribute("url", "/viseo/member/joinForm.blp");
			return "/member/loginRedirect";
		}
		// 파라미터 받고
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String mail = req.getParameter("mail");
		String gen = req.getParameter("gen");
		String nickname = req.getParameter("nickname");
		String tel = req.getParameter("tel");
		String birth = req.getParameter("birth");
		String ano = req.getParameter("addr");
		int addr = Integer.parseInt(ano);
		
		// vo에 담고
		JoinVO jVO = new JoinVO();
		jVO.setName(name);
		jVO.setId(id);
		jVO.setPw(pw);
		jVO.setMail(mail);
		jVO.setGen(gen);
		jVO.setNickname(nickname);
		jVO.setTel(tel);
		jVO.setBirth(birth);
		jVO.setAddr(addr);
		
		// 데이터베이스 작업
		JoinDao jDao = new JoinDao();
		int cnt = jDao.addMember(jVO);
		// 결과값에 따라 처리
		if(cnt != 1) {
			view = "/viseo/member/joinForm.blp";
			} else {
				// 로그인 처리
				req.getSession().setAttribute("SID", id);
				}
		
		return view;
	}

}
