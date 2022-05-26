package com.viseo.controller.hsr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.UpdateDao;
public class InfoUpdateProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/member/info/InfoUpdate.blp";
		// 로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/member/loginForm.blp";
		}
		
		// 파라미터 가져오고
		String smno = req.getParameter("mno");
		int mno = Integer.parseInt(smno);
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String tel = req.getParameter("tel");
		String addr = req.getParameter("addr");
		String gen = req.getParameter("gen");
		String nickname = req.getParameter("nickname");
		
		
		StringBuffer buff = new StringBuffer();
		
		if(pw != null) {
			buff.append(" , pw = '" + pw + "' ");
		}
		
		if(mail != null) {
			buff.append(" , mail = '" + mail + "' ");
		}
		
		if(tel != null) {
			buff.append(" , tel = '" + tel + "' ");
		}
		
		
		
		String psql  = buff.toString().substring(3);
		
		// 데이터베이스 작업하고
		UpdateDao uDao = new UpdateDao();
		int cnt = uDao.editMyInfo(mno, psql);
		
		// 결과에 따라 처리하고
		if(cnt != 1) {
			// 실패한 경우
			view = "/member/loginForm.blp";
		}
		
		return view;
	}
}



