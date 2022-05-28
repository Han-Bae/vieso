package com.viseo.controller.jyn;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viseo.controller.BlpInter;
import com.viseo.dao.JoinDao;
import com.viseo.vo.*;

public class GetAddr implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", null);
		String areaname = req.getParameter("areaname");
		
		JoinDao jDao = new JoinDao();
		
		ArrayList<JoinVO> list = jDao.getCityList(areaname);
		
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
