package com.viseo.controller.hsr;
/**
 * 
 * @author	한서라
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 한서라
 * 								내	용 : 프로필 수정 처리 클래스 제작
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.UpdateDao;
public class ProfileUpdateProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/viseo/member/info/ProfileUpdate.blp";
		// 로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/member/loginForm.blp";
		}
		
		// 파라미터 가져오고
		String nickname = req.getParameter("nickname");
		
		StringBuffer buff = new StringBuffer();
		
		if(nickname != null) {
			buff.append(" , nickname = '" + nickname + "' ");
		}

		System.out.println("#### : "+ buff.toString());
		String psql  = buff.toString().substring(3);
		
		// 데이터베이스 작업하고
		UpdateDao uDao = new UpdateDao();
		int cnt = uDao.editMyInfo(sid, psql); 
		// 결과에 따라 처리하고
		if(cnt != 1) {
			// 실패한 경우
			
			req.setAttribute("isRedirect", false);
			req.setAttribute("icon", "error");
			req.setAttribute("msg", "정보 수정에 실패했습니다");
			req.setAttribute("url", "/viseo/member/info/ProfileUpdate.blp");			
			req.setAttribute("title", "실패");
			return "/member/loginRedirect";
		}
			req.setAttribute("isRedirect", false);
			req.setAttribute("icon", "success");
			req.setAttribute("msg", "정보 수정에 성공했습니다");
			req.setAttribute("url", "/viseo/member/info/ProfileUpdate.blp");			
			req.setAttribute("title", "성공");
		return "/member/loginRedirect";
		
	}
}



