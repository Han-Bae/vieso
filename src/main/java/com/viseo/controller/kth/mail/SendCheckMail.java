package com.viseo.controller.kth.mail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;

public class SendCheckMail implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이 함수는 비동기 통신 처리용 함수이므로
		req.setAttribute("isRedirect", null);
		
		StringBuffer buff = new StringBuffer();

		// 파라미터 꺼내고
		String mail = req.getParameter("mail");
		// 메일 보내고
			// 성공할때 OK 실패하면 NO
		buff.append("{");
		buff.append("\"result\" : \"");
		try {			
		new MailSend(mail);
		buff.append("OK");
		req.getSession().setAttribute("mail", mail);
		} catch(Exception e) {
			buff.append("NO");			
		}
		buff.append("\"");
		buff.append("}");
		

		// 응답문서내용 반환하고
		return buff.toString();
	}

}
