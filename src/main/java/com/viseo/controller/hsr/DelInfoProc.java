
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
 * 				2022.05.26	- 담당자 : 한서라
 * 							  내용  : 회원정보 탈퇴
 * 						
 */
public class DelInfoProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("isRedirect", true);
		String view = "/viseo/member/loginForm.blp";
		HttpSession session = req.getSession();
		
		// 로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		String password = req.getParameter("password");

		if(sid == null) {
			return "/member/loginForm.blp";
		}		
		
		// 데이터베이스 작업하고 결과받고
		UpdateDao uDao = new UpdateDao();
		boolean result = uDao.deleteId(sid, password);
		
		if(result) {
			session.invalidate();

			req.setAttribute("isRedirect", false);
			req.setAttribute("icon", "success");
			req.setAttribute("msg", "회원 탈퇴에 성공했습니다");
			req.setAttribute("url", "/viseo/member/loginForm.blp");			
			req.setAttribute("title", "Good Bye! See u again");
			return "/member/loginRedirect";
		} else {
			req.setAttribute("isRedirect", false);
			req.setAttribute("icon", "error");
			req.setAttribute("msg", "회원 탈퇴에 실패했습니다^ㅠ^");
			req.setAttribute("url", "/viseo/member/info/withdrawal.blp");			
			req.setAttribute("title", "Good Bye! See u again");
			return "/member/loginRedirect";
			
		}
		// 뷰 반환하고
	}

}
