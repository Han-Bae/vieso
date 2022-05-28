package com.viseo.controller.phg;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import com.viseo.controller.*;
import com.viseo.dao.WriteDao;
import com.viseo.vo.WriteVO;

/**
 * 등록한 스케쥴 조회
 * 
 * @author	박형근
 * @since	2022.05.28
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.28	-	담당자 : 박형근
 * 								내	용 : 등록한 스케쥴 조회
 */

public class ReadTodo implements BlpInter {

		public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String view ="/todo/read";
			
			// 로그인 확인
			String id = (String) req.getSession().getAttribute("SID");
			if(id == null) {
				req.setAttribute("isRedirect", true);
				return "/viseo/member/loginForm.blp";
			}
			
			// 파라미터 받고
			String chcekDate = req.getParameter("chcekDate");	//지정날짜
				
			WriteDao wDao = new WriteDao();
			WriteVO wVO = wDao.writeSelect(id, chcekDate);
			
			// 데이터 심고
			req.setAttribute("DATA", wVO);
			
			return view;
		}	
	}