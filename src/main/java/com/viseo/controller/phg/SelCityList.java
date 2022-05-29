package com.viseo.controller.phg;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.WriteDao;
import com.viseo.vo.*;

/**
 * 상세지역조회
 * 
 * @author	박형근
 * @since	2022.05.29
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.29	-	담당자 : 박형근
 * 								내	용 : 상세지역조회
 */

public class SelCityList implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("isRedirect", null);
		String areaname = req.getParameter("areaname");
		
		WriteDao wDao = new WriteDao();
		
		ArrayList<WriteVO> list = wDao.getCityList(areaname);
		
		StringBuffer buff = new StringBuffer();
		buff.append("[");
		
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
