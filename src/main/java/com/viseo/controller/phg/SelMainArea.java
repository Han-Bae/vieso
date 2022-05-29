package com.viseo.controller.phg;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.viseo.controller.*;
import com.viseo.dao.WriteDao;
import com.viseo.vo.WriteVO;

/**
 * 지역조회
 * 
 * @author	박형근
 * @since	2022.05.29
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.29	-	담당자 : 박형근
 * 								내	용 : 지역 조회
 */

public class SelMainArea implements BlpInter {
	
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view ="/todo/write";
		
		// 로그인 확인
		String id = (String) req.getSession().getAttribute("SID");
		if(id == null) {
			req.setAttribute("isRedirect", true);
			return "/viseo/member/loginForm.blp";
		}
		
		String chcekDate = req.getParameter("chcekDate");	//지정날짜
		
		WriteVO wVO = new WriteVO();
		wVO.setChcekDate(chcekDate);
		
		WriteDao wDao = new WriteDao();
		ArrayList<String> list = wDao.getAreaName();
		
		// 데이터 심고
		req.setAttribute("LIST", list);
		req.setAttribute("DATE", wVO);

		return view;
	}
		
	}