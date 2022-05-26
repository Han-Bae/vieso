package com.viseo.controller.hsr;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.viseo.controller.BlpInter;
import com.viseo.dao.LoginDao;
import com.viseo.vo.FileVO;


public class InfoUpdate implements BlpInter {
	
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/info/InfoUpdate";
		// 로그인 체크
		/*
		  HttpSession session = req.getSession(); 
		  String sid = (String)session.getAttribute("SID"); 
		  if(sid == null) { 
		  view = "/member/loginForm.blp";
		  req.setAttribute("isRedirect", true); 
		  return view; 
		  }
		 
		
		  // 데이터베이스에서 내정보 꺼내오고 
		 MemberDao mDao = new MemberDao(); 
		  FileVO fVO = mDao.getIdInfo(sid); 
		 
		  // 데이터 심고 
		  req.setAttribute("DATA", fVO);
		*/
		return view;
	}

}
