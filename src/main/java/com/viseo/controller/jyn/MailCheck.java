package com.viseo.controller.jyn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.JoinDao;

/**
 * 
 * @author  정유나
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 정유나
 * 								내	용 : 메일 중복 체크
 * 										 이 함수는 비동기통신에서 요청한 json 문서를 만들어서 반환해준다.								 					
 *
 **/

public class MailCheck implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이 함수는 비동기 통신 처리용 함수이므로
		req.setAttribute("isRedirect", null);
		
		StringBuffer buff = new StringBuffer();

		// 파라미터 꺼내고
		String mail = req.getParameter("mail");
		// 데이터베이스에 문의하고
		JoinDao jDao = new JoinDao();
		// 결과받고
		int cnt = jDao.getMailCount(mail);
		
		// 결과에 따라 처리하고
		buff.append("{");
		buff.append("\"result\" : \"");
		if(cnt == 0) {
			// 사용가능한 이메일인 경우
			buff.append("OK");
			} else {
				// 사용불가능한 이메일인 경우
				buff.append("NO");
				}
				buff.append("\"");
				buff.append("}");
				
				// 응답문서내용 반환하고
				return buff.toString();
	}

}
