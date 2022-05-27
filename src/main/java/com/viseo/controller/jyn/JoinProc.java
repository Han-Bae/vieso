package com.viseo.controller.jyn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.JoinDao;
import com.viseo.vo.JoinVO;

public class JoinProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/viseo/main.blp";
		if(req.getSession().getAttribute("SID") != null) {
			return view;
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
		// 데이터베이스 작업하고
		JoinDao jDao = new JoinDao();
		int cnt = jDao.addMember(jVO);
		// 결과값에 따라 처리하고
		if(cnt != 1) {
			view = "/viseo/member/joinForm.blp";
		} else {
			// 로그인 처리
			req.getSession().setAttribute("SID", id);
		}
		
		return view;
	}

}
