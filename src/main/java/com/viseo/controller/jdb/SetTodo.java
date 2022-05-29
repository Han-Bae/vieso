package com.viseo.controller.jdb;

/**
 * 
 * @author	전다빈
 * @since	2022.05.28
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.28	-	담당자 : 전다빈
 * 								내	용 : 스케줄 세팅 컨트롤러
 */

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.MainDao;
import com.viseo.vo.MainVO;

public class SetTodo implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", null);
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		StringBuffer buff = new StringBuffer();
		
		// 파라미터
		String id = (String) req.getSession().getAttribute("SID");
		String date = req.getParameter("today");
		date = date.substring(0, 6) + "01";
		String lastdate = req.getParameter("lastday");
		
		MainDao maDao = new MainDao();
		MainVO maVO = new MainVO();
		
		// 반복문으로 json 만들기
		
		int noDate = Integer.parseInt(date);
		int noLastDate = Integer.parseInt(lastdate);
		
		// 없으면 null 처리 해야함
		ArrayList<String> list = maDao.getTododate(date);
		int listSize = list.size();
		int count = 1;
		
		// 여러개라서 배열 안에 안넣으면 에러난다
		buff.append("[");
		while(noDate <= noLastDate) {
			date = String.valueOf(noDate);
			if(!list.contains(date)) {
				noDate++;
				continue;
			}
			maVO = maDao.getTodoCnt(id, date);
			
			buff.append("{");
			buff.append("\"dateId\" : \"" + date + "\",");
			buff.append("\"cnt\" : \"" + maVO.getCnt() + "\",");
			buff.append("\"category\" : \"" + maVO.getCategory() + "\"");
			
			if(count >= listSize) {
				buff.append("}");
				break;
			}
			
			buff.append("},");
			
			noDate++;
			count++;
		}
		buff.append("]");
		
		return buff.toString();
	}

}
