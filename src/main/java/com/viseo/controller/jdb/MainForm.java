package com.viseo.controller.jdb;

/**
 * 
 * @author	전다빈
 * @since	2022.05.23
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.23	-	담당자 : 전다빈
 * 								내	용 : 클래스 제작, 뷰 연결
 * 
 * 				2022.05.26	-	담당자 : 전다빈
 * 								내	용 : WeatherUtil 연결
 * 
 * 				2022.05.27	-	담당자 : 전다빈
 * 								내	용 : 캘린더 세팅
 */

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.*;
import com.viseo.dao.*;
import com.viseo.util.*;
import com.viseo.vo.*;

public class MainForm implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/main";
		
		// 로그인 확인
		String sessionId = (String) req.getSession().getAttribute("SID");
		if(sessionId == null) {
			req.setAttribute("isRedirect", true);
			return "/viseo/member/loginForm.blp";
		}
		
		// 날짜랑 시간 데이터 가져오기
		MainDao maDao = new MainDao();
		
		LocalDateTime today = LocalDateTime.now();
		Timestamp tsToday = Timestamp.valueOf(today);
		MainVO maVO = maDao.getMainDate(tsToday);
		String dateNTime = maVO.getTodayDate();

		// 여기서는 다 현재 날짜, 시간
		maVO.setYear(dateNTime.substring(0, 4));
		maVO.setMonth(dateNTime.substring(4, 6));
		maVO.setDate(dateNTime.substring(6, 8));
		maVO.setTime(dateNTime.substring(8, 10));
		
		// 캘린더 세팅 데이터 가져오기
		maDao.getUserInfo(maVO, sessionId);
		
		// 회원 정보 넘기기 - 이번에 안함
		
		// 날짜 정보 넘기기
		WeatherUtil wUtil = new WeatherUtil();
		WeatherVO wVO = wUtil.getXMLTag(maVO);
		
		req.setAttribute("maDATA", maVO);
		req.setAttribute("wDATA", wVO);
		
		return view;
	}

}
