package com.viseo.controller.hsr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.viseo.controller.BlpInter;
import com.viseo.dao.UpdateDao;
import com.viseo.vo.UpdateVO;

/**
 * 이 클래스는 회원 관련 데이터베이스 작업을 전담해서 처리하는 클래스
 * @author 한서라
 * @since 2022.05.27
 * @version v.1.0
 * 		작업 이력)
 * 				2022.05.27	- 담당자 : 한서라
 * 							  내용  :
 * 						
 */

public class ProfileUpdate implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/info/ProfileUpdate";
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
		  
		  //  UpdateVO fVO = uDAO.getProfile(sid); 
		 
		  // 데이터 심고 
		  req.setAttribute("DATA", fVO);
		
		return view;
	}

}