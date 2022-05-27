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
 * @since 2022.05.26
 * @version v.1.0
 * 		작업 이력)
 * 				2022.05.12	- 담당자 : 한서라
 * 							  내용  : 회원정보 탈퇴
 * 						
 */
public class DelInfo implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 할일
		// 뷰 부르는 방식 설정하고
		req.setAttribute("isRedirect", true);
		// 뷰 설정하고
		String view = "/main";
		// 로그인 체크
		HttpSession session = req.getSession();
		// 세션값 꺼내고
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			// 로그인 안된 경우이므로 로그인 페이지로 보낸다
			view = "/viseo/member/loginForm.blp";
			return view;
		}
		
		
		// 파라미터 꺼내고
		String password = req.getParameter("password");
		
		// 데이터베이스 작업하고 결과받고
		UpdateDao uDao = new UpdateDao();
		int cnt = uDao.delMember(sid);
		// 결과에 따라서 처리하고
		if(cnt != 1) {
			// 실패한 경우
			view = "/viseo/member/info/withdrawal.blp"; 
			// 알람창으로 실패했습니다! 뜨게하고 시퍼
		} else {
			// 로그아웃 처리하고
			session.removeAttribute("SID");
		}
		// 뷰 반환하고
		return view;
	}

}

