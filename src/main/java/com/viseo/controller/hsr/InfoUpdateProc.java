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
		String view = "/viseo/member/info/InfoUpdate.blp";
		// 로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/member/loginForm.blp";
		}
		
		// 파라미터 가져오고
		String id = req.getParameter("id");
		String pw = req.getParameter("Password1");
		String mail = req.getParameter("mail");
		
		String tel = req.getParameter("tel");
		String addr = req.getParameter("addr");
		
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
		
		if(addr != null) {
			buff.append(" , addr = '" + addr + "' ");
		}
		

		System.out.println("#### : "+ buff.toString());
		String psql  = buff.toString().substring(3);
		
		// 데이터베이스 작업하고
		UpdateDao uDao = new UpdateDao();
		int cnt = uDao.editMyInfo(sid, psql); 
		// 결과에 따라 처리하고
		if(cnt != 1) {
			// 실패한 경우
			//view = "/main.blp";
			
			req.setAttribute("isRedirect", false);
			req.setAttribute("icon", "error");
			req.setAttribute("msg", "정보 수정에 실패했습니다");
			req.setAttribute("url", "/viseo/member/info/InfoUpdate.blp");			
			req.setAttribute("title", "실패");
			return "/member/loginRedirect";
		}
			req.setAttribute("isRedirect", false);
			req.setAttribute("icon", "success");
			req.setAttribute("msg", "정보 수정에 성공했습니다");
			req.setAttribute("url", "/viseo/member/info/InfoUpdate.blp");			
			req.setAttribute("title", "성공");
		return "/member/loginRedirect";
		
		
		//return view; // 성공했을 경우에도 모달창 같은 거로 하나 띄우고 싶다

	}
}



