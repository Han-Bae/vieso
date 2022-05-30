
package com.viseo.controller.kth.mail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.LoginDao;
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
		String mail = (String)req.getSession().getAttribute("mail");

		if(mail == null) {
			req.setAttribute("icon", "success");
			req.setAttribute("title", "이메일 인증 성공!");
			req.setAttribute("msg", "회원가입 페이지로 돌아가 남은 절차를 진행해주세요.");
			req.setAttribute("mailCheck", "pass");
			return "/member/loginRedirect";
		}
		
		// 이메일 저장
		LoginDao lDao = new LoginDao();
		int cnt_ck = lDao.getOK(mail);
		int cnt;
			// DB에 존재는 하지만 가입을 안한 메일이 존재하면 그냥 통과
		if (cnt_ck == 1) {
			cnt = 1;
		} else {			
			cnt = lDao.addMail(mail);
		}
			// 현재 이메일 비회원상태 isokay = no로 저장돼있다.
		if(cnt == 1) {
			// 회원가입 메일 인증 확인용 어트리뷰트
			// 알림창 파라미터
			req.setAttribute("status", "emailOK");
			req.setAttribute("icon", "success");
			req.setAttribute("title", "이메일 인증 성공!");
			req.setAttribute("msg", "회원가입 페이지로 돌아가 남은 절차를 진행해주세요.");
			req.setAttribute("mailCheck", "pass");
		} else {
			// 회원가입 메일 인증 확인용 어트리뷰트
			// 알림창 파라미터
			req.setAttribute("icon", "error");
			req.setAttribute("title", "이미 가입한 이메일입니다!");
			req.setAttribute("url", "/viseo/member/joinForm.blp");
			req.setAttribute("msg", "회원가입을 처음부터 진행해주세요");
		}
		// 사용한 파라미터 삭제
		req.getSession().removeAttribute("mail");
		return "/member/loginRedirect";
	}

}
