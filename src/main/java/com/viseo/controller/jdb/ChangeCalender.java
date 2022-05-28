package com.viseo.controller.jdb;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.MainDao;
import com.viseo.vo.MainVO;

public class ChangeCalender implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 비동기
		req.setAttribute("isRedirect", null);
		
		StringBuffer buff = new StringBuffer();
		
		// 파라미터
		String changeValue = req.getParameter("changeValue");
		String year = changeValue.substring(0, 4);
		String month = changeValue.substring(5, 7);
		
		MainDao maDao = new MainDao();

		LocalDate locDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
		LocalTime locTime = LocalTime.of(0, 0);
		LocalDateTime locTimeDate = LocalDateTime.of(locDate, locTime);
		
		Timestamp timestamp = Timestamp.valueOf(locTimeDate);
		
		MainVO maVO = new MainVO();
		maVO = maDao.getMainDate(timestamp);
		
		buff.append("{");
		buff.append("\"lastdate\" : \"" + maVO.getLastDate() + "\",");
		buff.append("\"firstday\" : \"" + maVO.getFirstDay() + "\"");
		buff.append("}");
		
		return buff.toString();
	}

}
