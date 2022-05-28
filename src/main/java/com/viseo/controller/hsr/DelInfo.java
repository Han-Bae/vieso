package com.viseo.controller.hsr;
/**
 * 
 * @author	한서라
 * @since	2022.05.27
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 한서라
 * 								내	용 : 회원정보 삭제 클래스 제작
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.viseo.controller.BlpInter;

/**
 * 이 클래스는 회원 관련 데이터베이스 작업을 전담해서 처리하는 클래스
 * @author 한서라
 * @since 2022.05.26
 * @version v.1.0
 * 		작업 이력)
 * 				2022.05.26	- 담당자 : 한서라
 * 							  내용  : 회원정보 탈퇴
 * 						
 */
public class DelInfo implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 할일
		// 뷰 부르는 방식 설정하고
		String view = "/member/info/withdrawal";
		// 로그인 체크
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			// 로그인 안된 경우이므로 로그인 페이지로 보낸다
			view = "/viseo/member/loginForm.blp";
			req.setAttribute("isRedirect", true); 
		}
		return view;
		
	}
}
