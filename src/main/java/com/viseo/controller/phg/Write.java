package com.viseo.controller.phg;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.viseo.controller.BlpInter;

/**
 * 스케줄 입력 폼
 * 
 * @author	박형근
 * @since	2022.05.23
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.23	-	담당자 : 박형근
 * 								내	용 : 스케줄 입력 폼
 */

public class Write implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view ="/todo/write";

		return view;
	}

}