package com.viseo.controller.jdb;

import java.io.IOException;

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
		
		StringBuffer buff = new StringBuffer();
		
		// 파라미터
		String id = req.getParameter("id");
		String date = req.getParameter("today");
		date = date.substring(0, 6) + "01";
		String lastdate = req.getParameter("lastday");
		
		MainDao maDao = new MainDao();
		MainVO maVO = new MainVO();
		
		// 반복문으로 json 만들기
		
		int noDate = Integer.parseInt(date);
		int noLastDate = Integer.parseInt(lastdate);
		
		while(noDate < noLastDate) {
			date = String.valueOf(noLastDate);
			maVO = maDao.getTodoCnt(id, date);
			
			buff.append("{");
			buff.append("\"date\" : \"" + date + "\",");
			buff.append("\"cnt\" : \"" + maVO.getCnt() + "\",");
			buff.append("\"category\" : \"" + maVO.getCategory() + "\"");
			
			if(noDate <= noLastDate) {
				buff.append("},");
			}
			
			noDate++;
		}
		buff.append("}");
		
		return buff.toString();
	}

}
