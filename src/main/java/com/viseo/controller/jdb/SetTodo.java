package com.viseo.controller.jdb;

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
		ArrayList<String> list = maDao.getTododate();
		int listSize = list.size();
		System.out.println(listSize);
		int count = 1;
		
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
			
			if(count == listSize) {
				buff.append("}");
				break;
			}
			
			System.out.println(count);
			buff.append("},");
			
			noDate++;
			count++;
		}
		
		return buff.toString();
	}

}
