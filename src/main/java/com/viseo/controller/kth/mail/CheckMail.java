
package com.viseo.controller.kth.mail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
/**
 * 		이메일에 전송한 확인 버튼과 연결된 요청
 * 		이메일 인증이 성공되면
 * 		성공 확인용 정보를 전달하고 알림창 생성
 *  
 * @author 김태현
 * @since 2022.05.27
 * @version v.1.0
 *			작업이력)
 *					2022.05.27			- 클래스제작
 *										담당자 김태현
 */
public class CheckMail implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("SID") != null) {
			// 이미 로그인 한 상태
			req.setAttribute("isRedirect", true);
			return "/viseo/main.blp";
		}
		// 회원가입 메일 인증 확인용 어트리뷰트
		req.getSession().setAttribute("check", "OK");
		// 알림창 파라미터
		req.setAttribute("icon", "success");
		req.setAttribute("title", "이메일 인증 성공!");
		req.setAttribute("url", "/viseo/member/joinForm.blp");
		req.setAttribute("msg", "남은 회원가입 절차를 진행해주세요.");
		return "/member/loginRedirect";
	}

}
