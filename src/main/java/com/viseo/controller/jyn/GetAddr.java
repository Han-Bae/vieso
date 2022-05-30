package com.viseo.controller.jyn;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.JoinDao;
import com.viseo.vo.*;
/**
 * 
 * @author  정유나
 * @since	2022.05.28
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.28	-	담당자 : 정유나
 * 								내	용 : 주소 반환
 * 										 이 함수는 비동기통신에서 요청한 json 문서를 만들어서 반환해준다.							 					
 *
 **/
public class GetAddr implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", null);
		
		// areaname을 파라미터 값으로 넣는다.
		String areaname = req.getParameter("areaname");
		
		JoinDao jDao = new JoinDao();
		// 2차원 배열에 담기
		ArrayList<JoinVO> list = jDao.getCityList(areaname);
		
		StringBuffer buff = new StringBuffer();
		buff.append("[");
		// 반복문 사용
		for(int i = 0; i < list.size(); i++ ) {
			if(i != 0) {
				buff.append(",");
			}
			buff.append("{");
			buff.append(" \"addr\": \"" + list.get(i).getAddr() + "\",");
			buff.append("\"city\": \"" + list.get(i).getCity() + "\"");
			buff.append("}");
		}
		
		buff.append("]");
		
		return buff.toString();
	}

}
