package com.viseo.controller.phg;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.viseo.controller.BlpInter;

public class Write implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view ="/todo/write";
		return view;
	}

}