package com.viseo.controller.hsr;
/**
 * 
 * @author	한서라
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 한서라
 * 								내	용 : 회원정보 수정 클래스 제작
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.viseo.controller.BlpInter;
import com.viseo.dao.JoinDao;
import com.viseo.dao.LoginDao;
import com.viseo.dao.UpdateDao;
import com.viseo.vo.JoinVO;
import com.viseo.vo.UpdateVO;

public class InfoUpdate implements BlpInter {
	
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/info/InfoUpdate";
		// 로그인 체크
		
		  HttpSession session = req.getSession(); 
		  String sid = (String)session.getAttribute("SID"); 
		  if(sid == null) { 
		  view = "/member/loginForm.blp";
		  req.setAttribute("isRedirect", true); 
		  return view; 
		  }
		 
		  
		
		  // 데이터베이스에서 내정보 꺼내오고 
		  UpdateDao uDAO = new UpdateDao(); 
		  UpdateVO fVO = uDAO.getIdInfo(sid); 
		  
		  JoinDao jDao = new JoinDao();
		  ArrayList<String> list = jDao.getAreaName();
		  //ArrayList<JoinVO> list = jDao.getCityList(areaname);
		 
		  // 데이터 심고 
		  req.setAttribute("DATA", fVO);
		  req.setAttribute("JDATA", list);
		
		return view;
	}

}
