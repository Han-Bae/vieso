package com.viseo.controller.phg;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import com.viseo.controller.*;
import com.viseo.dao.WriteDao;
import com.viseo.vo.WriteVO;

public class ReadTodo implements BlpInter {

		public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String view ="/todo/read";
			
			// 파라미터 받고
			String id = req.getParameter("id");	//아이디
			String chcekDate = req.getParameter("chcekDate");	//지정날짜
			
			// 로그인 세션값을 가져와서 로그인 정보가 없으면 로그인페이지로 보내줌
			/*
			 * String sid = (String) req.getSession().getAttribute("SID"); if(sid == null) {
			 * return "/whistle/member/login.blp"; }
			 */
			
			id = "sin";  // 임시... 나중에 지울것
			chcekDate = "2022-05-17"; // 임시... 나중에 지울것
			
			WriteDao wDao = new WriteDao();
			WriteVO wVO = wDao.writeSelect(id, chcekDate);
			
			System.out.println("ReadTodo.java[타이틀]==========="
			+ wVO.getTitle() + wVO.getCategory() + wVO.getArea());
			
			// 데이터 심고
			req.setAttribute("DATA", wVO);
			
			return view;
		}	
	}