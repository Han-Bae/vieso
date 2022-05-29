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
 * 등록한 스케줄 건수 조회
 * 
 * @author	박형근
 * @since	2022.05.28
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.28	-	담당자 : 박형근
 * 								내	용 : 등록한 스케줄 건수 조회
 */

public class ReadCntTodo implements BlpInter {

		public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			req.setAttribute("isRedirect", null);
			StringBuffer buff = new StringBuffer();
			
			// 로그인 확인
			String id = (String) req.getSession().getAttribute("SID");
			if(id == null) {
				req.setAttribute("isRedirect", true);
				return "/viseo/member/loginForm.blp";
			}
			
			// 파라미터 받고
			String chcekDate = req.getParameter("checkDate");	//지정날짜
			
			// vo에 담고
			WriteVO wVO = new WriteVO();
			wVO.setId(id);
			wVO.setChcekDate(chcekDate);

			WriteDao wDao = new WriteDao();
			int result = wDao.writeCnt(wVO);
			
			buff.append("{"); // {result:OK}
			buff.append("\"result\" : \"");
			if(result >= 1) {	
				buff.append("OK"); //정상 저장처리된 경우 AJAX OK로 리턴
			} else {
				buff.append("NO"); //비정상 처리된 경우 AJAX NO로 리턴
			}
			buff.append("\"");
			buff.append("}");
			
			return buff.toString();
			
		}	
	}