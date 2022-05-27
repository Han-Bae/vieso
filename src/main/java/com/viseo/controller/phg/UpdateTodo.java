package com.viseo.controller.phg;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import com.viseo.controller.*;
import com.viseo.dao.WriteDao;
import com.viseo.vo.WriteVO;

public class UpdateTodo implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/todo/update";

		// 로그인 체크
		/*
		 * HttpSession session = req.getSession(); String sid = (String)
		 * session.getAttribute("SID"); if(sid == null) { view =
		 * "/whistle/member/login.blp"; req.setAttribute("isRedirect", true); return
		 * view; }
		 */
		
		// 데이터베이스에서 내정보 꺼내오고
		String id = req.getParameter("id");	//아이디
		String chcekDate = req.getParameter("chcekDate");	//지정날짜
		
		id = "sin";  // 임시... 나중에 지울것
		chcekDate = "2022-05-17"; // 임시... 나중에 지울것
		
		WriteDao wDao = new WriteDao();
		WriteVO wVO = wDao.writeSelect(id, chcekDate);
		
		// 데이터 심고
		req.setAttribute("DATA", wVO);
		
		return view;
		
	}

}